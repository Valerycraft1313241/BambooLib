package com.github.zandy.bamboolib.command;

import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ParentCommand extends Command {
   private final List<SubCommand> subCommandList = new ArrayList<>();

   public ParentCommand(String name) {
      super(name);
   }

   @Override
   public boolean execute(CommandSender sender, String label, String[] args) {
      if (args.length == 0) {
         sendDefaultMessage(sender);
         return true;
      }

      SubCommand subCommand = getSubCommand(args[0]);
      if (subCommand == null) {
         sendDefaultMessage(sender);
         return true;
      }

      if (subCommand.hasPermission(sender)) {
         subCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
      } else {
         sender.sendMessage(noPermissionMessage(sender));
      }
      return true;
   }

   public abstract void sendDefaultMessage(CommandSender sender);

   public abstract String noPermissionMessage(CommandSender sender);

   public void showCommandsList(CommandSender sender) {
      getSubCommands().stream()
              .filter(subCommand -> subCommand.canSee(sender))
              .forEach(subCommand -> {
                 String message = BambooUtils.colorize("&a⦁ &f/" + getName() + " " + subCommand.getName() + " &7&o- " + subCommand.getDescription());
                 if (sender instanceof Player) {
                    BambooUtils.sendTextComponent((Player) sender, message, "/" + getName() + " " + subCommand.getName(), subCommand.getDescription(), subCommand.getClickAction());
                 } else {
                    sender.sendMessage(message);
                 }
              });
   }

   public List<SubCommand> getSubCommands() {
      return new ArrayList<>(subCommandList);
   }

   public boolean hasSubCommand(String name) {
      return getSubCommand(name) != null;
   }

   public SubCommand getSubCommand(String name) {
      return subCommandList.stream()
              .filter(subCommand -> subCommand.getName().equalsIgnoreCase(name))
              .findFirst()
              .orElse(null);
   }

   public boolean addSubCommand(SubCommand subCommand) {
      if (hasSubCommand(subCommand.getName())) {
         return false;
      }
      subCommandList.add(subCommand);
      return true;
   }

   public boolean removeSubCommand(String name) {
      return subCommandList.removeIf(subCommand -> subCommand.getName().equalsIgnoreCase(name));
   }

   public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) {
      if (args.length == 1) {
         return getSubCommands().stream()
                 .filter(subCommand -> subCommand.canSee(sender))
                 .map(SubCommand::getName)
                 .collect(Collectors.toList());
      } else if (args.length == 2 && hasSubCommand(args[0])) {
         return getSubCommand(args[0]).tabComplete(sender, alias, args, location);
      }
      return null;
   }
}
