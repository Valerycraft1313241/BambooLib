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

   public Scoreboard(Player var1, String var2, List<String> var3, HashMap<String, Callable<String>> var4) {
      this.player = var1;
      this.title = var2;
      ArrayList<String> var5 = new ArrayList<>();
      var5.add(" ");
      var5.addAll(var3);
      this.lines = var5;
      this.placeholders = var4;
      this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
      this.objective = this.scoreboard.registerNewObjective("Scoreboard", "dummy");
      this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      this.setLines();
      var1.setScoreboard(this.scoreboard);
   }

   private void setLines() {
      this.scoreboard.getTeams().forEach(Team::unregister);
      this.objective.setDisplayName(this.title);
      byte var1 = 0;

      for(int var2 = this.lines.size(); var2 > 1; --var2) {
         Team var3 = this.scoreboard.registerNewTeam("line-" + var2);
         var3.addEntry(ChatColor.values()[var2 - 1].toString());
         this.objective.getScore(ChatColor.values()[var2 - 1].toString()).setScore(var1);
         ++var1;
         String var4 = this.lines.get(var2 - 1);
         if (!this.refresh.containsKey(var3)) {
            if (this.containsPAPI(var4)) {
               this.refresh.put(var3, var4);
            }

            Stream<String> var10000 = this.placeholders.keySet().stream();
            Objects.requireNonNull(var4);
            var10000.filter(var4::contains).forEach((var3x) -> {
               this.refresh.put(var3, var4);
            });
         }

         this.setLine(var3, var4);
      }

   }

   private void setLine(Team var1, String var2) {
      if (this.refresh.containsKey(var1)) {
         if (this.containsPAPI(var2)) {
            var2 = PlaceholderAPI.setPlaceholders(this.player, var2);
         }

         Entry var4;
         try {
            for(Iterator var3 = this.placeholders.entrySet().iterator(); var3.hasNext(); var2 = var2.replace((CharSequence)var4.getKey(), (CharSequence)((Callable)var4.getValue()).call())) {
               var4 = (Entry)var3.next();
            }
         } catch (Exception var5) {
         }
      }

      if (var2.length() >= 16) {
         String var7 = var2.substring(0, 16);
         String var6;
         if (!var7.endsWith("&") && !var7.endsWith("§")) {
            if (!var7.substring(0, 15).endsWith("&") && !var7.substring(0, 15).endsWith("§")) {
               var6 = ChatColor.getLastColors(var7) + var2.substring(var7.length());
               if (var6.length() > 16) {
                  var6 = var6.substring(0, 16);
               }
            } else {
               var7 = var7.substring(0, var7.length() - 2);
               var6 = var2.substring(var7.length());
            }
         } else {
            var7 = var7.substring(0, var7.length() - 1);
            var6 = var2.substring(var7.length());
         }

         var1.setPrefix(var7);
         var1.setSuffix(var6);
      } else {
         var1.setPrefix(var2);
         var1.setSuffix("");
      }

   }

   public void refresh() {
      this.refresh.forEach(this::setLine);
   }

   public void destroy() {
      this.player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
   }

   private boolean containsPAPI(String var1) {
      return !BambooUtils.isPluginEnabled("PlaceholderAPI") ? false : PlaceholderAPI.containsPlaceholders(var1);
   }
}
