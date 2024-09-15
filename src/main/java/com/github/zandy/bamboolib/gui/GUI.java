package com.github.zandy.bamboolib.gui;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.exceptions.BambooSlotOccupiedException;
import com.github.zandy.bamboolib.placeholder.PlaceholderManager;
import com.github.zandy.bamboolib.utils.BambooUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUI implements Listener {
   private final Player owner;
   private final HashMap<Integer, GUIItem> items;
   private String title;
   private Inventory inventory;
   private boolean closeable;
   private boolean opened;
   private boolean externalInteract;
   private boolean eventsRegistered;

   public GUI(Player var1, InventoryType var2, String var3) {
      this.title = var3;
      this.owner = var1;
      this.inventory = Bukkit.createInventory(var1, var2, BambooUtils.colorize(var3));
      this.items = new HashMap();
      this.closeable = true;
      this.opened = false;
      this.externalInteract = false;
      BambooUtils.registerEvent(this);
      this.eventsRegistered = true;
   }

   public GUI(Player var1, int var2, String var3) {
      this.title = var3;
      this.owner = var1;
      this.inventory = Bukkit.createInventory(var1, var2, BambooUtils.colorize(var3));
      this.items = new HashMap();
      this.closeable = true;
      this.opened = false;
      this.externalInteract = false;
      BambooUtils.registerEvent(this);
      this.eventsRegistered = true;
   }

   public void setTitle(String var1) {
      this.title = BambooUtils.colorize(var1);
      this.inventory = Bukkit.createInventory(this.owner, this.inventory.getSize(), var1);
      if (this.opened) {
         this.open(this.owner);
      }

   }

   public void setTitle(String var1, boolean var2) {
      if (!var2) {
         this.setTitle(var1);
      } else {
         this.title = BambooUtils.colorize(var1);
         this.inventory = Bukkit.createInventory(this.owner, this.inventory.getType(), var1);
         if (this.opened) {
            this.open();
         }

      }
   }

   public void open() {
      if (!this.eventsRegistered) {
         BambooUtils.registerEvent(this);
      }

      this.eventsRegistered = true;
      this.setItems();
      this.refreshItems();
      this.opened = true;
      this.owner.openInventory(this.inventory);
   }

   public void open(Player var1) {
      if (!this.eventsRegistered) {
         BambooUtils.registerEvent(this);
      }

      this.eventsRegistered = true;
      this.setItems();
      this.opened = true;
      this.owner.openInventory(this.inventory);
   }

   public void close() {
      if (this.owner.getOpenInventory().getTopInventory().equals(this.inventory)) {
         this.opened = false;
         this.owner.closeInventory();
         this.owner.updateInventory();
      }
   }

   public void definitivelyClose() {
      this.unregisterEvent();
      if (this.owner.getOpenInventory().getTopInventory().equals(this.inventory)) {
         this.opened = false;
         this.owner.closeInventory();
      }

   }

   public void setItems() {
      this.items.keySet().forEach((var1) -> {
         this.inventory.setItem(var1, ((GUIItem)this.items.get(var1)).getItemStack());
      });
   }

   public GUIItem getItem(int var1) {
      return (GUIItem)this.items.get(var1);
   }

   public void addItem(GUIItem var1) {
      if (this.getItem(var1.getSlot()) != null) {
         throw new BambooSlotOccupiedException();
      } else {
         if (this.isOpened()) {
            this.inventory.setItem(var1.getSlot(), var1.getItemStack());
         }

         this.items.put(var1.getSlot(), var1);
      }
   }

   public void removeItem(int var1) {
      if (this.isOpened() && this.items.containsKey(var1)) {
         this.inventory.setItem(var1, (ItemStack)null);
      }

      this.items.remove(var1);
   }

   public void setExternalInteract(boolean var1) {
      this.externalInteract = var1;
   }

   private void unregisterEvent() {
      InventoryClickEvent.getHandlerList().unregister(this);
      InventoryCloseEvent.getHandlerList().unregister(this);
      this.eventsRegistered = false;
   }

   public void refreshItems() {
      this.items.keySet().forEach((var1) -> {
         ItemStack var2 = ((GUIItem)this.items.get(var1)).getItemStack().clone();
         if (PlaceholderManager.getInstance().hasPlaceholders(var2)) {
            this.inventory.setItem(var1, PlaceholderManager.getInstance().setPlaceholders(this.owner, var2));
         }

      });
   }

   public List<GUIItem> getGUIItems() {
      return new ArrayList(this.items.values());
   }

   @EventHandler
   private void onInventoryClick(InventoryClickEvent var1) {
      if (this.inventory.equals(var1.getView().getTopInventory())) {
         if (this.inventory.equals(var1.getClickedInventory()) || !this.externalInteract) {
            var1.setCancelled(true);
         }

         if (this.owner != null && var1.getWhoClicked().getUniqueId().equals(this.owner.getUniqueId())) {
            if (var1.getCurrentItem() != null && var1.getCurrentItem().getType() != Material.AIR) {
               GUIItem var2 = this.getItem(var1.getSlot());
               if (var2 != null) {
                  var2.executeClickAction(this, var1.getClick());
               }
            }
         }
      }
   }

   @EventHandler
   private void onInventoryClose(InventoryCloseEvent var1) {
      if (var1.getInventory().equals(this.inventory) && this.owner != null) {
         if (var1.getPlayer().getUniqueId().equals(this.owner.getUniqueId())) {
            if (!this.closeable && this.opened) {
               Bukkit.getScheduler().runTaskLater(BambooLib.getPluginInstance(), () -> {
                  this.owner.openInventory(this.inventory);
               }, 5L);
            }
         }
      }
   }

   public Player getOwner() {
      return this.owner;
   }

   public String getTitle() {
      return this.title;
   }

   public Inventory getInventory() {
      return this.inventory;
   }

   public boolean isCloseable() {
      return this.closeable;
   }

   public void setCloseable(boolean var1) {
      this.closeable = var1;
   }

   public boolean isOpened() {
      return this.opened;
   }

   public boolean isExternalInteract() {
      return this.externalInteract;
   }
}
