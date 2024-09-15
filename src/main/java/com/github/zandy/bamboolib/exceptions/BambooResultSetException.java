package com.github.zandy.bamboolib.exceptions;

import com.github.zandy.bamboolib.utils.BambooUtils;

public class BambooResultSetException extends RuntimeException {
   private static final String line = "&7&m----------------------------------";
   private static final String space = " ";
   private static final String header = "    &c&oAn error occurred while gathering ResultSet from Database";
   private static final String description = "&eReason why the error occurred:";
   private static final String reason = "&eResultSet is null or can't reach the MySQL server.";

   public BambooResultSetException() {
      BambooUtils.consolePrint("&7&m----------------------------------");
      BambooUtils.consolePrint("    &c&oAn error occurred while gathering ResultSet from Database");
      BambooUtils.consolePrint(" ");
      BambooUtils.consolePrint("&eReason why the error occurred:");
      BambooUtils.consolePrint("&eResultSet is null or can't reach the MySQL server.");
      BambooUtils.consolePrint("&7&m----------------------------------");
   }
}
