package com.github.zandy.bamboolib.database.utils;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.events.database.DatabaseChangeEvent;
import com.github.zandy.bamboolib.exceptions.BambooErrorException;
import com.github.zandy.bamboolib.utils.BambooUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
   private void onDatabaseChange(DatabaseChangeEvent event) {
      String fileName = "MySQL-" + this.dateFormat.format(new Date());
      File logDir = new File("plugins/" + this.pluginName + "/Logs");
      logDir.mkdirs();
      if (this.file == null || !this.file.getName().contains(fileName)) {
         this.file = new File(logDir, fileName + ".txt");
      }

      LocalDateTime now = LocalDateTime.now();

      try (FileWriter writer = new FileWriter(this.file, true)) {
         writer.write("[" + this.dateTimeFormatter.format(now) + "] Database Action: " + event.getDatabaseAction() + " | Table: " + event.getTable() + " | Column: " + event.getColumn());
         writer.write(System.lineSeparator());
      } catch (IOException e) {
         throw new BambooErrorException(e, this.getClass(), Arrays.asList("Cannot write in the MySQL log.", "Probably, the 'Logs' directory or file is missing."));
      }
   }
}
