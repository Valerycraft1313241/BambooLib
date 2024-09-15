package com.github.zandy.bamboolib.exceptions;

import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.Collections;
import java.util.List;

public class BambooException extends RuntimeException {
   private static final String LINE = "&7&m----------------------------------";
   private static final String SPACE = " ";
   private static final String TITLE = "&c&lERROR OCCURRED!";
   private static final String DESCRIPTION = "&eDescription:";

   public BambooException(String message) {
      this.execute(Collections.singletonList(message));
   }

   public BambooException(List<String> messages) {
      this.execute(messages);
   }

   private void execute(List<String> messages) {
      BambooUtils.consolePrint(LINE);
      BambooUtils.consolePrint(TITLE);
      BambooUtils.consolePrint(SPACE);
      BambooUtils.consolePrint(DESCRIPTION);
      messages.forEach(message -> BambooUtils.consolePrint("&b" + message));
      BambooUtils.consolePrint(LINE);
   }
}
