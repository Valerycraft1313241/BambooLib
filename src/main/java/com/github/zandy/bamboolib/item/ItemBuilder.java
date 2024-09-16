package com.github.zandy.bamboolib.item;

import com.github.zandy.bamboolib.item.itemmeta.ItemBanner;
import com.github.zandy.bamboolib.item.itemmeta.ItemBook;
import com.github.zandy.bamboolib.item.itemmeta.ItemEnchantment;
import com.github.zandy.bamboolib.item.itemmeta.ItemFirework;
import com.github.zandy.bamboolib.item.itemmeta.ItemFlags;
import com.github.zandy.bamboolib.item.itemmeta.ItemLeatherArmor;
import com.github.zandy.bamboolib.item.itemmeta.ItemMap;
import com.github.zandy.bamboolib.item.itemmeta.ItemMetaData;
import com.github.zandy.bamboolib.item.itemmeta.ItemPotion;
import com.github.zandy.bamboolib.item.itemmeta.ItemSkull;
import com.github.zandy.bamboolib.item.itemmeta.ItemSpawnEgg;
import com.github.zandy.bamboolib.utils.BambooUtils;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.skinsrestorer.api.SkinsRestorerAPI;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
   private static final UUID randomUUID = UUID.fromString("eb38bdd9-a3d9-41c5-9f03-2273ed0e8bc7");
   private final ItemBuilder item = this;
   private final ItemStack customItem;
   private ItemFlags flag = null;
   private ItemSkull skull = null;
   private ItemMetaData metaData = null;
   private ItemEnchantment enchantment = null;
   private ItemBanner banner = null;
   private ItemPotion potion = null;
   private ItemBook book = null;
   private ItemFirework firework = null;
   private ItemLeatherArmor leatherArmor = null;
   private ItemMap map = null;
   private ItemSpawnEgg spawnEgg = null;

   private ItemMeta getItemMeta() {
      return this.customItem.getItemMeta();
   }

   private BannerMeta getBannerMeta() {
      return (BannerMeta)this.getItemMeta();
   }

   private BookMeta getBookMeta() {
      return (BookMeta)this.getItemMeta();
   }

   private FireworkMeta getFireworkMeta() {
      return (FireworkMeta)this.getItemMeta();
   }

   private FireworkEffectMeta getFireworkEffectMeta() {
      return (FireworkEffectMeta)this.getItemMeta();
   }

   private LeatherArmorMeta getLeatherArmorMeta() {
      return (LeatherArmorMeta)this.getItemMeta();
   }

   private MapMeta getMapMeta() {
      return (MapMeta)this.getItemMeta();
   }

   private SkullMeta getSkullMeta() {
      return (SkullMeta)this.getItemMeta();
   }

   private PotionMeta getPotionMeta() {
      return (PotionMeta)this.getItemMeta();
   }

   public ItemBuilder clone() {
      return new ItemBuilder(this.build());
   }

   public ItemBuilder(String var1) {
      this.customItem = new ItemStack(Material.valueOf(var1));
   }

   public ItemBuilder(Material var1) {
      this.customItem = new ItemStack(var1);
   }

   public ItemBuilder(ItemStack var1) {
      this.customItem = var1.clone();
   }

   public ItemBuilder(int var1) {
      this.customItem = new ItemStack(var1);
   }

   public ItemBuilder(int var1, int var2) {
      this.customItem = new ItemStack(var1, 1, (short)var2);
   }

   public ItemFlags flag() {
      if (this.flag == null) {
         this.flag = new ItemFlags() {
            public ItemBuilder add(ItemFlag... itemFlags) {
               ItemMeta var2 = ItemBuilder.this.getItemMeta();
               var2.addItemFlags(itemFlags);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public ItemBuilder remove(ItemFlag... itemFlags) {
               ItemMeta var2 = ItemBuilder.this.getItemMeta();
               var2.removeItemFlags(new ItemFlag[0]);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public boolean has(ItemFlag itemFlags) {
               return ItemBuilder.this.getItemMeta().hasItemFlag(itemFlags);
            }
         };
      }

      return this.flag;
   }

   public ItemSkull skull() {
      if (this.skull == null) {
         this.skull = new ItemSkull() {
            public UUID getOwner() {
               return VersionSupport.getInstance().getSkullOwner(ItemBuilder.this.getSkullMeta());
            }

            public boolean hasOwner() {
               return ItemBuilder.this.getSkullMeta().hasOwner();
            }

            public ItemBuilder setOwner(String owner) {
               if (!Bukkit.getServer().getOnlineMode() && BambooUtils.isPluginEnabled("SkinsRestorer")) {
                  return this.setSkin(SkinsRestorerAPI.getApi().getSkinTextureUrl(owner));
               } else {
                  String var2 = ItemBuilder.this.customItem.getType().name();
                   if (var2.equals("PLAYER_HEAD") || var2.equals("SKULL_ITEM")) {
                       SkullMeta var3 = ItemBuilder.this.getSkullMeta();
                       var3.setOwner(owner);
                       ItemBuilder.this.customItem.setItemMeta(var3);
                   }
                   return ItemBuilder.this.item;
               }
            }

            public ItemBuilder setSkin(String skin) {
               String var2 = ItemBuilder.this.customItem.getType().name();
               if (!var2.equals("PLAYER_HEAD") && !var2.equals("SKULL_ITEM")) {
                  return ItemBuilder.this.item;
               } else if (skin == null) {
                  return ItemBuilder.this.item;
               } else {
                  ItemMeta var3 = ItemBuilder.this.getItemMeta();
                  GameProfile var4 = new GameProfile(ItemBuilder.randomUUID, (String)null);
                  var4.getProperties().put("textures", new Property("textures", VersionSupport.getInstance().getTexture(skin)));

                  try {
                     Field var5 = var3.getClass().getDeclaredField("profile");
                     var5.setAccessible(true);
                     var5.set(var3, var4);
                  } catch (Exception var6) {
                     var6.printStackTrace();
                  }

                  ItemBuilder.this.customItem.setItemMeta(var3);
                  return ItemBuilder.this.item;
               }
            }
         };
      }

      return this.skull;
   }

   public ItemMetaData metadata() {
      if (this.metaData == null) {
         this.metaData = new ItemMetaData() {
            public ItemBuilder add(String metadataName, String var2) {
               VersionSupport.getInstance().addMetaData(ItemBuilder.this.customItem, metadataName, var2);
               return ItemBuilder.this.item;
            }

            public String get(String metadata) {
               return VersionSupport.getInstance().getMetaData(ItemBuilder.this.customItem, metadata);
            }

            public boolean has(String metadata) {
               return VersionSupport.getInstance().hasMetaData(ItemBuilder.this.customItem, metadata);
            }

            public ItemBuilder remove(String metadata) {
               VersionSupport.getInstance().removeMetaData(ItemBuilder.this.customItem, metadata);
               return ItemBuilder.this.item;
            }
         };
      }

      return this.metaData;
   }

   public ItemEnchantment enchantment() {
      if (this.enchantment == null) {
         this.enchantment = new ItemEnchantment() {
            public ItemBuilder add(Enchantment enchantment, int level) {
               ItemBuilder.this.customItem.addEnchantment(enchantment, level);
               return ItemBuilder.this.item;
            }

            public ItemBuilder addUnsafe(Enchantment enchantment, int level) {
               ItemBuilder.this.customItem.addUnsafeEnchantment(enchantment, level);
               return ItemBuilder.this.item;
            }

            public ItemBuilder remove(Enchantment enchantment) {
               ItemBuilder.this.customItem.removeEnchantment(enchantment);
               return ItemBuilder.this.item;
            }

            public int getLevel(Enchantment enchantment) {
               return ItemBuilder.this.customItem.getEnchantmentLevel(enchantment);
            }

            public Map<Enchantment, Integer> getList() {
               return ItemBuilder.this.customItem.getEnchantments();
            }

            public boolean has(Enchantment enchantment) {
               return ItemBuilder.this.customItem.containsEnchantment(enchantment);
            }
         };
      }

      return this.enchantment;
   }

   public ItemBanner banner() {
      if (this.banner == null) {
         this.banner = new ItemBanner() {
            public ItemBuilder setPatterns(List<Pattern> patternList) {
               BannerMeta var2 = ItemBuilder.this.getBannerMeta();
               var2.setPatterns(patternList);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public ItemBuilder setPattern(int i, Pattern pattern) {
               BannerMeta var3 = ItemBuilder.this.getBannerMeta();
               var3.setPattern(i, pattern);
               ItemBuilder.this.customItem.setItemMeta(var3);
               return ItemBuilder.this.item;
            }

            public ItemBuilder addPattern(Pattern pattern) {
               BannerMeta var2 = ItemBuilder.this.getBannerMeta();
               var2.addPattern(pattern);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public ItemBuilder removePattern(int i) {
               BannerMeta var2 = ItemBuilder.this.getBannerMeta();
               var2.removePattern(i);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public List<Pattern> getPatterns() {
               return ItemBuilder.this.getBannerMeta().getPatterns();
            }

            public Pattern getPattern(int i) {
               return ItemBuilder.this.getBannerMeta().getPattern(i);
            }

            public int getNumberOfPatterns() {
               return ItemBuilder.this.getBannerMeta().numberOfPatterns();
            }
         };
      }

      return this.banner;
   }


   public ItemBook book() {
      if (this.book == null) {
         this.book = new ItemBook() {
            public boolean hasTitle() {
               return ItemBuilder.this.getBookMeta().hasTitle();
            }

            public String getTitle() {
               return ItemBuilder.this.getBookMeta().getTitle();
            }

            public ItemBuilder setTitle(String title) {
               BookMeta var2 = ItemBuilder.this.getBookMeta();
               var2.setTitle(title);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public boolean hasAuthor() {
               return ItemBuilder.this.getBookMeta().hasAuthor();
            }

            public String getAuthor() {
               return ItemBuilder.this.getBookMeta().getAuthor();
            }

            public ItemBuilder setAuthor(String author) {
               BookMeta var2 = ItemBuilder.this.getBookMeta();
               var2.setAuthor(author);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public boolean hasPages() {
               return ItemBuilder.this.getBookMeta().hasPages();
            }

            public String getPage(int page) {
               return ItemBuilder.this.getBookMeta().getPage(page);
            }

            public ItemBuilder setPage(int page, String s) {
               BookMeta var3 = ItemBuilder.this.getBookMeta();
               var3.setPage(page, s);
               ItemBuilder.this.customItem.setItemMeta(var3);
               return ItemBuilder.this.item;
            }

            public List<String> getPages() {
               return ItemBuilder.this.getBookMeta().getPages();
            }

            public ItemBuilder setPages(List<String> pages) {
               BookMeta var2 = ItemBuilder.this.getBookMeta();
               var2.setPages(pages);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public ItemBuilder addPages(String... pages) {
               BookMeta var2 = ItemBuilder.this.getBookMeta();
               var2.addPage(pages);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public int getPageCount() {
               return ItemBuilder.this.getBookMeta().getPageCount();
            }
         };
      }

      return this.book;
   }

   public ItemFirework firework() {
      if (this.firework == null) {
         this.firework = new ItemFirework() {
            public ItemBuilder addEffect(FireworkEffect fireworkEffect) {
               FireworkMeta var2 = ItemBuilder.this.getFireworkMeta();
               var2.addEffect(fireworkEffect);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public ItemBuilder addEffects(FireworkEffect... fireworkEffect) {
               FireworkMeta var2 = ItemBuilder.this.getFireworkMeta();
               var2.addEffects(fireworkEffect);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public List<FireworkEffect> getEffects() {
               return ItemBuilder.this.getFireworkMeta().getEffects();
            }

            public int getEffectsSize() {
               return ItemBuilder.this.getFireworkMeta().getEffectsSize();
            }

            public ItemBuilder removeEffect(int number) {
               FireworkMeta var2 = ItemBuilder.this.getFireworkMeta();
               var2.removeEffect(number);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public ItemBuilder clearEffects() {
               FireworkMeta var1 = ItemBuilder.this.getFireworkMeta();
               var1.clearEffects();
               ItemBuilder.this.customItem.setItemMeta(var1);
               return ItemBuilder.this.item;
            }

            public boolean hasEffects() {
               return ItemBuilder.this.getFireworkMeta().hasEffects();
            }

            public int getPower() {
               return ItemBuilder.this.getFireworkMeta().getPower();
            }

            public ItemBuilder setPower(int power) {
               FireworkMeta var2 = ItemBuilder.this.getFireworkMeta();
               var2.setPower(power);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public ItemBuilder setEffect(FireworkEffect fireworkEffect) {
               FireworkEffectMeta var2 = ItemBuilder.this.getFireworkEffectMeta();
               var2.setEffect(fireworkEffect);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }

            public boolean hasEffect() {
               return ItemBuilder.this.getFireworkEffectMeta().hasEffect();
            }

            public FireworkEffect getEffect() {
               return ItemBuilder.this.getFireworkEffectMeta().getEffect();
            }
         };
      }

      return this.firework;
   }

   public ItemLeatherArmor leatherArmor() {
      if (this.leatherArmor == null) {
         this.leatherArmor = new ItemLeatherArmor() {
            public Color getColor() {
               return ItemBuilder.this.getLeatherArmorMeta().getColor();
            }

            public ItemBuilder setColor(Color color) {
               LeatherArmorMeta var2 = ItemBuilder.this.getLeatherArmorMeta();
               var2.setColor(color);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }
         };
      }

      return this.leatherArmor;
   }

   public ItemMap map() {
      if (this.map == null) {
         this.map = new ItemMap() {
            public boolean isScaling() {
               return ItemBuilder.this.getMapMeta().isScaling();
            }

            public ItemBuilder setScaling(boolean scaling) {
               MapMeta var2 = ItemBuilder.this.getMapMeta();
               var2.setScaling(scaling);
               ItemBuilder.this.customItem.setItemMeta(var2);
               return ItemBuilder.this.item;
            }
         };
      }

      return this.map;
   }


   public ItemBuilder setDisplayName(String var1) {
      ItemMeta var2 = this.getItemMeta();
      var2.setDisplayName(BambooUtils.colorize(var1));
      this.customItem.setItemMeta(var2);
      return this.item;
   }

   public String getDisplayName() {
      return this.customItem.getItemMeta().getDisplayName();
   }

   public ItemBuilder setLore(List<String> var1) {
      ItemMeta var2 = this.getItemMeta();
      ArrayList var3 = new ArrayList();
      Iterator var4 = var1.iterator();

      while(var4.hasNext()) {
         String var5 = (String)var4.next();
         var3.add(BambooUtils.colorize(var5));
      }

      var2.setLore(var3);
      this.customItem.setItemMeta(var2);
      return this.item;
   }

   public ItemBuilder setUnbreakable(boolean var1) {
      ItemMeta var2 = this.getItemMeta();
      VersionSupport.getInstance().setUnbreakable(var2, var1);
      this.customItem.setItemMeta(var2);
      return this.item;
   }

   public ItemBuilder setAmount(int var1) {
      this.customItem.setAmount(var1);
      return this.item;
   }

   public ItemBuilder setData(int var1) {
      if (BambooUtils.isVersion(17, 18, 19)) {
         return this.item;
      } else {
         this.customItem.setDurability((short)var1);
         return this.item;
      }
   }

   public int getAmount() {
      return this.customItem.getAmount();
   }

   public ItemBuilder setMaterial(Material var1) {
      this.customItem.setType(var1);
      return this.item;
   }

   public Material getMaterial() {
      return this.customItem.getType();
   }

   public boolean equals(Object var1) {
      return this.customItem.equals(var1);
   }

   public boolean isSimilar(ItemStack var1) {
      return this.customItem.isSimilar(var1);
   }

   public ItemStack build() {
      return this.customItem;
   }
}
