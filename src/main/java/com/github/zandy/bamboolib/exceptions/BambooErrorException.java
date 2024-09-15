package com.github.zandy.bamboolib.exceptions;

import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.Collections;
import java.util.List;

public class BambooErrorException extends RuntimeException {
   public static final String line = "&7&m----------------------------------";
   public static final String header = "    &c&oError occurred in class [className]";
   public static final String classPath = "Class path: &c[classPath]";
   public static final String description = "&eDescription:";
   public static final String completeError = "&cComplete error:";

   public BambooErrorException(Exception var1, Class var2, String var3) {
      this.execute(var1, var2, Collections.singletonList(var3));
   }

   public BambooErrorException(Exception var1, Class var2, List<String> var3) {
      this.execute(var1, var2, var3);
   }

   private void execute(Exception var1, Class var2, List<String> var3) {
      BambooUtils.consolePrint("&7&m----------------------------------");
      BambooUtils.consolePrint("    &c&oError occurred in class [className]".replace("[className]", var2.getSimpleName()));
      BambooUtils.consolePrint("Class path: &c[classPath]".replace("[classPath]", var2.getName()));
      BambooUtils.consolePrint("&eDescription:");
      var3.forEach((var0) -> {
         BambooUtils.consolePrint("&e" + var0);
      });
      BambooUtils.consolePrint("&cComplete error:");
      var1.printStackTrace();
      BambooUtils.consolePrint("&7&m----------------------------------");
   }
}
