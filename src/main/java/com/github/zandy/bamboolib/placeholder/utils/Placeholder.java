package com.github.zandy.bamboolib.placeholder.utils;

import org.bukkit.entity.Player;

public class Placeholder {
   private final String name;

   public Placeholder(String name) {
      this.name = name;
   }

   public String request(Player player) {
      return null;
   }

   public String request() {
      return null;
   }

   public String getName() {
      return this.name;
   }
}
