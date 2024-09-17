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

   public SubCommand(String name, String description, String[] permissions) {
      this.clickAction = Action.SUGGEST_COMMAND;
      this.name = name;
      this.permissions = permissions;
      this.description = description;
   }

   public SubCommand(String name, String description) {
      this.clickAction = Action.SUGGEST_COMMAND;
      this.name = name;
      this.description = description;
   }

   public boolean hasPermission(CommandSender sender) {
      if (this.permissions == null) {
         return true;
      } else {
         String[] permissionsArray = this.permissions;

         for (String permission : permissionsArray) {
            if (sender.hasPermission(permission)) {
               return true;
            }
         }

         return false;
      }
   }

   public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) {
      return null;
   }

   public abstract void execute(CommandSender sender, String[] args);

   public abstract boolean canSee(CommandSender sender);

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

   public void setClickAction(Action clickAction) {
      this.clickAction = clickAction;
   }
}
