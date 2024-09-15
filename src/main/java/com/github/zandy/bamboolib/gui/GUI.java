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

   public GUI(Player owner, InventoryType type, String title) {
      this.title = title;
      this.owner = owner;
      this.inventory = Bukkit.createInventory(owner, type, BambooUtils.colorize(title));
      this.items = new HashMap<>();
      this.closeable = true;
      this.opened = false;
      this.externalInteract = false;
      BambooUtils.registerEvent(this);
      this.eventsRegistered = true;
   }

   public GUI(Player owner, int size, String title) {
      this.title = title;
      this.owner = owner;
      this.inventory = Bukkit.createInventory(owner, size, BambooUtils.colorize(title));
      this.items = new HashMap<>();
      this.closeable = true;
      this.opened = false;
      this.externalInteract = false;
      BambooUtils.registerEvent(this);
      this.eventsRegistered = true;
   }

   public void setTitle(String title) {
      this.title = BambooUtils.colorize(title);
      this.inventory = Bukkit.createInventory(this.owner, this.inventory.getSize(), title);
      if (this.opened) {
         this.open(this.owner);
      }
   }

   public void setTitle(String title, boolean useType) {
      if (!useType) {
         this.setTitle(title);
      } else {
         this.title = BambooUtils.colorize(title);
         this.inventory = Bukkit.createInventory(this.owner, this.inventory.getType(), title);
         if (this.opened) {
            this.open();
         }
      }
   }

   public void open() {
      registerEventsIfNeeded();
      setItems();
      refreshItems();
      this.opened = true;
      this.owner.openInventory(this.inventory);
   }

   public void open(Player player) {
      registerEventsIfNeeded();
      setItems();
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
      unregisterEvent();
      if (this.owner.getOpenInventory().getTopInventory().equals(this.inventory)) {
         this.opened = false;
         this.owner.closeInventory();
      }
   }

   public void setItems() {
      this.items.forEach((slot, item) -> this.inventory.setItem(slot, item.getItemStack()));
   }

   public GUIItem getItem(int slot) {
      return this.items.get(slot);
   }

   public void addItem(GUIItem item) {
      if (this.getItem(item.getSlot()) != null) {
         throw new BambooSlotOccupiedException();
      } else {
         if (this.isOpened()) {
            this.inventory.setItem(item.getSlot(), item.getItemStack());
         }
         this.items.put(item.getSlot(), item);
      }
   }

   public void removeItem(int slot) {
      if (this.isOpened() && this.items.containsKey(slot)) {
         this.inventory.setItem(slot, null);
      }
      this.items.remove(slot);
   }

   public void setExternalInteract(boolean externalInteract) {
      this.externalInteract = externalInteract;
   }

   private void unregisterEvent() {
      InventoryClickEvent.getHandlerList().unregister(this);
      InventoryCloseEvent.getHandlerList().unregister(this);
      this.eventsRegistered = false;
   }

   private void registerEventsIfNeeded() {
      if (!this.eventsRegistered) {
         BambooUtils.registerEvent(this);
         this.eventsRegistered = true;
      }
   }

   public void refreshItems() {
      this.items.forEach((slot, item) -> {
         ItemStack itemStack = item.getItemStack().clone();
         if (PlaceholderManager.getInstance().hasPlaceholders(itemStack)) {
            this.inventory.setItem(slot, PlaceholderManager.getInstance().setPlaceholders(this.owner, itemStack));
         }
      });
   }

   public List<GUIItem> getGUIItems() {
      return new ArrayList<>(this.items.values());
   }

   @EventHandler
   private void onInventoryClick(InventoryClickEvent event) {
      if (this.inventory.equals(event.getView().getTopInventory())) {
         if (this.inventory.equals(event.getClickedInventory()) || !this.externalInteract) {
            event.setCancelled(true);
         }

         if (this.owner != null && event.getWhoClicked().getUniqueId().equals(this.owner.getUniqueId())) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
               GUIItem item = this.getItem(event.getSlot());
               if (item != null) {
                  item.executeClickAction(this, event.getClick());
               }
            }
         }
      }
   }

   @EventHandler
   private void onInventoryClose(InventoryCloseEvent event) {
      if (event.getInventory().equals(this.inventory) && this.owner != null) {
         if (event.getPlayer().getUniqueId().equals(this.owner.getUniqueId())) {
            if (!this.closeable && this.opened) {
               Bukkit.getScheduler().runTaskLater(BambooLib.getPluginInstance(), () -> this.owner.openInventory(this.inventory), 5L);
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

   public void setCloseable(boolean closeable) {
      this.closeable = closeable;
   }

   public boolean isOpened() {
      return this.opened;
   }

   public boolean isExternalInteract() {
      return this.externalInteract;
   }
}
