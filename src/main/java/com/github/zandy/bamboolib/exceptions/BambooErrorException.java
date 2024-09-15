package com.github.zandy.bamboolib.exceptions;

import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.Collections;
import java.util.List;

public class BambooErrorException extends RuntimeException {
   public static final String LINE = "&7&m----------------------------------";
   public static final String HEADER = "    &c&oError occurred in class [className]";
   public static final String CLASS_PATH = "Class path: &c[classPath]";
   public static final String DESCRIPTION = "&eDescription:";
   public static final String COMPLETE_ERROR = "&cComplete error:";

   public BambooErrorException(Exception exception, Class<?> clazz, String description) {
      this.execute(exception, clazz, Collections.singletonList(description));
   }

   public BambooErrorException(Exception exception, Class<?> clazz, List<String> descriptions) {
      this.execute(exception, clazz, descriptions);
   }

   private void execute(Exception exception, Class<?> clazz, List<String> descriptions) {
      BambooUtils.consolePrint(LINE);
      BambooUtils.consolePrint(HEADER.replace("[className]", clazz.getSimpleName()));
      BambooUtils.consolePrint(CLASS_PATH.replace("[classPath]", clazz.getName()));
      BambooUtils.consolePrint(DESCRIPTION);
      descriptions.forEach(description -> BambooUtils.consolePrint("&e" + description));
      BambooUtils.consolePrint(COMPLETE_ERROR);
      exception.printStackTrace();
      BambooUtils.consolePrint(LINE);
   }
}
