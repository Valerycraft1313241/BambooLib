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

   public GUIItem(ItemStack itemStack, int slot) {
      this.itemStack = itemStack;
      this.slot = slot;
      this.clickActionList = new ArrayList<>();
   }

   public GUIItem(ItemStack itemStack, int slot, ClickAction clickAction) {
      this.itemStack = itemStack;
      this.slot = slot;
      this.clickActionList = new ArrayList<>();
      this.clickActionList.add(clickAction);
   }

   public GUIItem(ItemStack itemStack, int slot, List<ClickAction> clickActionList) {
      this.itemStack = itemStack;
      this.slot = slot;
      this.clickActionList = clickActionList;
   }

   public GUIItem addClickAction(ClickAction clickAction) {
      this.clickActionList.add(clickAction);
      return this;
   }

   public void editItemStack(Inventory inventory, ItemStack itemStack) {
      this.itemStack = itemStack;
      inventory.setItem(this.slot, itemStack);
   }

   public void editItemStack(ItemStack var1) {
      this.itemStack = var1;
   }

   public void executeClickAction(GUI gui, ClickType clickType) {
      if (!this.clickActionList.isEmpty()) {
         this.clickActionList.stream().filter((var1x) -> var1x.getClickTypeList().contains(clickType)).forEach((var2x) -> {
            var2x.onClick(this, gui);
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
