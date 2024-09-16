package com.github.zandy.bamboolib.scoreboard;

import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.stream.Stream;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class Scoreboard {
   private final Player player;
   private final String title;
   private final List<String> lines;
   private final Objective objective;
   private final org.bukkit.scoreboard.Scoreboard scoreboard;
   private final HashMap<String, Callable<String>> placeholders;
   private final HashMap<Team, String> refresh = new HashMap<>();

   public Scoreboard(Player player, String title, List<String> lines, HashMap<String, Callable<String>> placeholders) {
      this.player = player;
      this.title = title;
      ArrayList<String> formattedLines = new ArrayList<>();
      formattedLines.add(" ");
      formattedLines.addAll(lines);
      this.lines = formattedLines;
      this.placeholders = placeholders;
      this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
      this.objective = this.scoreboard.registerNewObjective("Scoreboard", "dummy");
      this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      this.setLines();
      player.setScoreboard(this.scoreboard);
   }

   private void setLines() {
      this.scoreboard.getTeams().forEach(Team::unregister);
      this.objective.setDisplayName(this.title);
      byte score = 0;

      for (int i = this.lines.size(); i > 1; --i) {
         Team team = this.scoreboard.registerNewTeam("line-" + i);
         team.addEntry(ChatColor.values()[i - 1].toString());
         this.objective.getScore(ChatColor.values()[i - 1].toString()).setScore(score);
         ++score;
         String line = this.lines.get(i - 1);
         if (!this.refresh.containsKey(team)) {
            if (this.containsPAPI(line)) {
               this.refresh.put(team, line);
            }

            Stream<String> placeholderKeys = this.placeholders.keySet().stream();
            Objects.requireNonNull(line);
            placeholderKeys.filter(line::contains).forEach((key) -> {
               this.refresh.put(team, line);
            });
         }

         this.setLine(team, line);
      }
   }

   private void setLine(Team team, String line) {
      if (this.refresh.containsKey(team)) {
         if (this.containsPAPI(line)) {
            line = PlaceholderAPI.setPlaceholders(this.player, line);
         }

         Entry<String, Callable<String>> entry;
         try {
            for (Iterator<Entry<String, Callable<String>>> iterator = this.placeholders.entrySet().iterator(); iterator.hasNext(); line = line.replace(entry.getKey(), entry.getValue().call())) {
               entry = iterator.next();
            }
         } catch (Exception ignored) {
         }
      }

      if (line.length() >= 16) {
         String prefix = line.substring(0, 16);
         String suffix;
         if (!prefix.endsWith("&") && !prefix.endsWith("§")) {
            if (!prefix.substring(0, 15).endsWith("&") && !prefix.substring(0, 15).endsWith("§")) {
               suffix = ChatColor.getLastColors(prefix) + line.substring(prefix.length());
               if (suffix.length() > 16) {
                  suffix = suffix.substring(0, 16);
               }
            } else {
               prefix = prefix.substring(0, prefix.length() - 2);
               suffix = line.substring(prefix.length());
            }
         } else {
            prefix = prefix.substring(0, prefix.length() - 1);
            suffix = line.substring(prefix.length());
         }

         team.setPrefix(prefix);
         team.setSuffix(suffix);
      } else {
         team.setPrefix(line);
         team.setSuffix("");
      }
   }

   public void refresh() {
      this.refresh.forEach(this::setLine);
   }

   public void destroy() {
      this.player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
   }

   private boolean containsPAPI(String text) {
      return BambooUtils.isPluginEnabled("PlaceholderAPI") && PlaceholderAPI.containsPlaceholders(text);
   }
}
