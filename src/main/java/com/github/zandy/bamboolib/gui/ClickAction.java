package com.github.zandy.bamboolib.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.event.inventory.ClickType;

public abstract class ClickAction {
   private final List<ClickType> clickTypeList;

   public ClickAction(ClickType var1) {
      this.clickTypeList = new ArrayList();
      this.clickTypeList.add(var1);
   }

   public ClickAction() {
      this.clickTypeList = new ArrayList(Arrays.asList(ClickType.values()));
   }

   public ClickAction(List<ClickType> var1) {
      this.clickTypeList = var1;
   }

   public abstract void onClick(GUIItem var1, GUI var2);

   public List<ClickType> getClickTypeList() {
      return this.clickTypeList;
   }
}
