package com.github.zandy.bamboolib.exceptions;

import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.Collections;
import java.util.List;

public class BambooException extends RuntimeException {
   private static final String line = "&7&m----------------------------------";
   private static final String space = " ";
   private static final String title = "&c&lERROR OCCURRED!";
   private static final String description = "&eDescription:";

   public BambooException(String var1) {
      this.execute(Collections.singletonList(var1));
   }

   public BambooException(List<String> var1) {
      this.execute(var1);
   }

   private void execute(List<String> var1) {
      BambooUtils.consolePrint("&7&m----------------------------------");
      BambooUtils.consolePrint("&c&lERROR OCCURRED!");
      BambooUtils.consolePrint(" ");
      BambooUtils.consolePrint("&eDescription:");
      var1.forEach((var0) -> {
         BambooUtils.consolePrint("&b" + var0);
      });
      BambooUtils.consolePrint("&7&m----------------------------------");
   }
}
