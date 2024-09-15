package com.github.zandy.bamboolib.exceptions;

import com.github.zandy.bamboolib.utils.BambooUtils;

public class BambooResultSetException extends RuntimeException {
   private static final String LINE = "&7&m----------------------------------";
   private static final String SPACE = " ";
   private static final String HEADER = "    &c&oAn error occurred while gathering ResultSet from Database";
   private static final String DESCRIPTION = "&eReason why the error occurred:";
   private static final String REASON = "&eResultSet is null or can't reach the MySQL server.";

   public BambooResultSetException() {
      BambooUtils.consolePrint(LINE);
      BambooUtils.consolePrint(HEADER);
      BambooUtils.consolePrint(SPACE);
      BambooUtils.consolePrint(DESCRIPTION);
      BambooUtils.consolePrint(REASON);
      BambooUtils.consolePrint(LINE);
   }
}
