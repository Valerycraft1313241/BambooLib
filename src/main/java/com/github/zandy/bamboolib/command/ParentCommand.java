package com.github.zandy.bamboolib.command;

import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ParentCommand extends Command {
   private final List<SubCommand> subCommandList = new ArrayList();

   public ParentCommand(String var1) {
      super(var1);
   }

   public boolean execute(CommandSender var1, String var2, String[] var3) {
      if (var3.length == 0) {
         this.sendDefaultMessage(var1);
         return true;
      } else {
         Iterator var4 = this.getSubCommands().iterator();

         SubCommand var5;
         do {
            if (!var4.hasNext()) {
               this.sendDefaultMessage(var1);
               return true;
            }

            var5 = (SubCommand)var4.next();
         } while(!var5.getName().equalsIgnoreCase(var3[0]));

         if (var5.hasPermission(var1)) {
            var5.execute(var1, (String[])Arrays.copyOfRange(var3, 1, var3.length));
            return true;
         } else {
            var1.sendMessage(this.noPermissionMessage(var1));
            return true;
         }
      }
   }

   public abstract void sendDefaultMessage(CommandSender var1);

   public abstract String noPermissionMessage(CommandSender var1);

   public void showCommandsList(CommandSender var1) {
      this.getSubCommands().stream().filter((var1x) -> {
         return var1x.canSee(var1);
      }).forEach((var2) -> {
         String var3 = BambooUtils.colorize("&a⦁ &f/" + this.getName() + " " + var2.getName() + " &7&o- " + var2.getDescription());
         if (var1 instanceof Player) {
            BambooUtils.sendTextComponent((Player)var1, var3, "/" + this.getName() + " " + var2.getName(), var2.getDescription(), var2.getClickAction());
         } else {
            var1.sendMessage(var3);
         }

      });
   }

   public List<SubCommand> getSubCommands() {
      return new ArrayList(this.subCommandList);
   }

   public boolean hasSubCommand(String var1) {
      Iterator var2 = this.getSubCommands().iterator();

      SubCommand var3;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         var3 = (SubCommand)var2.next();
      } while(!var3.getName().equalsIgnoreCase(var1));

      return true;
   }

   public SubCommand getSubCommand(String var1) {
      Iterator var2 = this.getSubCommands().iterator();

      SubCommand var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         var3 = (SubCommand)var2.next();
      } while(!var3.getName().equalsIgnoreCase(var1));

      return var3;
   }

   public boolean addSubCommand(SubCommand var1) {
      if (this.hasSubCommand(var1.getName())) {
         return false;
      } else {
         this.subCommandList.add(var1);
         return true;
      }
   }

   public boolean removeSubCommand(String var1) {
      return this.subCommandList.remove(this.getSubCommand(var1));
   }

   public List<String> tabComplete(CommandSender var1, String var2, String[] var3, Location var4) {
      if (var3.length == 1) {
         ArrayList var5 = new ArrayList();
         this.getSubCommands().stream().filter((var1x) -> {
            return var1x.canSee(var1);
         }).forEach((var1x) -> {
            var5.add(var1x.getName() + " ");
         });
         return var5;
      } else {
         return var3.length == 2 && this.hasSubCommand(var3[0]) ? this.getSubCommand(var3[0]).tabComplete(var1, var2, var3, var4) : null;
      }
   }
}
