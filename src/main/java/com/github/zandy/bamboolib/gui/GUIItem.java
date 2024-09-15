package com.github.zandy.bamboolib.gui;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIItem {
   private final List<ClickAction> clickActionList;
   private final int slot;
   private ItemStack itemStack;

   public GUIItem(ItemStack var1, int var2) {
      this.itemStack = var1;
      this.slot = var2;
      this.clickActionList = new ArrayList();
   }

   public GUIItem(ItemStack var1, int var2, ClickAction var3) {
      this.itemStack = var1;
      this.slot = var2;
      this.clickActionList = new ArrayList();
      this.clickActionList.add(var3);
   }

   public GUIItem(ItemStack var1, int var2, List<ClickAction> var3) {
      this.itemStack = var1;
      this.slot = var2;
      this.clickActionList = var3;
   }

   public GUIItem addClickAction(ClickAction var1) {
      this.clickActionList.add(var1);
      return this;
   }

   public void editItemStack(Inventory var1, ItemStack var2) {
      this.itemStack = var2;
      var1.setItem(this.slot, var2);
   }

   public void editItemStack(ItemStack var1) {
      this.itemStack = var1;
   }

   public void executeClickAction(GUI var1, ClickType var2) {
      if (this.clickActionList.size() != 0) {
         this.clickActionList.stream().filter((var1x) -> {
            return var1x.getClickTypeList().contains(var2);
         }).forEach((var2x) -> {
            var2x.onClick(this, var1);
         });
      }
   }

   public GUIItem clone() {
      return new GUIItem(this.itemStack.clone(), this.slot, this.clickActionList);
   }

   public List<ClickAction> getClickActionList() {
      return this.clickActionList;
   }

   public int getSlot() {
      return this.slot;
   }

   public ItemStack getItemStack() {
      return this.itemStack;
   }
}
