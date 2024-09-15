package com.github.zandy.bamboolib.command;

import java.util.List;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
   private final String name;
   private final String description;
   private String[] permissions = null;
   private Action clickAction;

   public SubCommand(String var1, String var2, String[] var3) {
      this.clickAction = Action.SUGGEST_COMMAND;
      this.name = var1;
      this.permissions = var3;
      this.description = var2;
   }

   public SubCommand(String var1, String var2) {
      this.clickAction = Action.SUGGEST_COMMAND;
      this.name = var1;
      this.description = var2;
   }

   public boolean hasPermission(CommandSender var1) {
      if (this.permissions == null) {
         return true;
      } else {
         String[] var2 = this.permissions;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            if (var1.hasPermission(var5)) {
               return true;
            }
         }

         return false;
      }
   }

   public List<String> tabComplete(CommandSender var1, String var2, String[] var3, Location var4) {
      return null;
   }

   public abstract void execute(CommandSender var1, String[] var2);

   public abstract boolean canSee(CommandSender var1);

   public String getName() {
      return this.name;
   }

   public String getDescription() {
      return this.description;
   }

   public String[] getPermissions() {
      return this.permissions;
   }

   public Action getClickAction() {
      return this.clickAction;
   }

   public void setClickAction(Action var1) {
      this.clickAction = var1;
   }
}
