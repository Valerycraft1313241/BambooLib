package com.github.zandy.bamboolib.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.event.inventory.ClickType;

public abstract class ClickAction {
   private final List<ClickType> clickTypeList;

   public ClickAction(ClickType clickType) {
      this.clickTypeList = new ArrayList<>();
      this.clickTypeList.add(clickType);
   }

   public ClickAction() {
      this.clickTypeList = new ArrayList<>(Arrays.asList(ClickType.values()));
   }

   public ClickAction(List<ClickType> clickTypes) {
      this.clickTypeList = clickTypes;
   }

   public abstract void onClick(GUIItem guiItem, GUI gui);

   public List<ClickType> getClickTypeList() {
      return this.clickTypeList;
   }
}
