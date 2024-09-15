package com.github.zandy.bamboolib.database.utils;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.events.database.DatabaseChangeEvent;
import com.github.zandy.bamboolib.exceptions.BambooErrorException;
import com.github.zandy.bamboolib.utils.BambooUtils;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DatabaseLog implements Listener {
   private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
   private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   private final String pluginName = BambooLib.getPluginInstance().getDescription().getName();
   private File file = null;

   public DatabaseLog() {
      BambooUtils.registerEvent(this);
   }

   @EventHandler
   private void onDatabaseChange(DatabaseChangeEvent var1) {
      String var2 = "MySQL-" + this.dateFormat.format(new Date());
      (new File("plugins/" + this.pluginName + "/Logs")).mkdirs();
      if (this.file == null || !this.file.getName().contains(var2)) {
         this.file = new File("plugins/" + this.pluginName + "/Logs", var2 + ".txt");
      }

      LocalDateTime var3 = LocalDateTime.now();

      try {
         FileWriter var4 = new FileWriter(this.file, true);
         var4.write("[" + this.dateTimeFormatter.format(var3) + "] Database Action: " + var1.getDatabaseAction() + " | Table: " + var1.getTable() + " | Column: " + var1.getColumn());
         var4.write("\r\n");
         var4.close();
      } catch (Exception var5) {
         throw new BambooErrorException(var5, this.getClass(), Arrays.asList("Cannot write in the MySQL log.", "Probably, the 'Logs' directory or file is missing."));
      }
   }
}
