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

   public ItemBuilder(String mat) {
      this.customItem = new ItemStack(Material.valueOf(mat));
   }

   public ItemBuilder(Material material) {
      this.customItem = new ItemStack(material);
   }

   public ItemBuilder(ItemStack itemStack) {
      this.customItem = itemStack.clone();
   }

   public ItemBuilder(int type) {
      this.customItem = new ItemStack(type);
   }

   public ItemBuilder(int type, int damage) {
      this.customItem = new ItemStack(type, 1, (short) damage);
   }

   public ItemFlags flag() {
      if (this.flag == null) {
         this.flag = new ItemFlags() {
            public ItemBuilder add(ItemFlag... itemFlags) {
               ItemMeta itemMeta = ItemBuilder.this.getItemMeta();
               itemMeta.addItemFlags(itemFlags);
               ItemBuilder.this.customItem.setItemMeta(itemMeta);
               return ItemBuilder.this.item;
            }

            public ItemBuilder remove(ItemFlag... itemFlags) {
               ItemMeta itemMeta = ItemBuilder.this.getItemMeta();
               itemMeta.removeItemFlags(itemFlags);
               ItemBuilder.this.customItem.setItemMeta(itemMeta);
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
                  String itemType = ItemBuilder.this.customItem.getType().name();
                  if (itemType.equals("PLAYER_HEAD") || itemType.equals("SKULL_ITEM")) {
                     SkullMeta skullMeta = ItemBuilder.this.getSkullMeta();
                     skullMeta.setOwner(owner);
                     ItemBuilder.this.customItem.setItemMeta(skullMeta);
                  }
                  return ItemBuilder.this.item;
               }
            }

            public ItemBuilder setSkin(String skin) {
               String itemType = ItemBuilder.this.customItem.getType().name();
               if (!itemType.equals("PLAYER_HEAD") && !itemType.equals("SKULL_ITEM")) {
                  return ItemBuilder.this.item;
               } else if (skin == null) {
                  return ItemBuilder.this.item;
               } else {
                  ItemMeta itemMeta = ItemBuilder.this.getItemMeta();
                  GameProfile gameProfile = new GameProfile(ItemBuilder.randomUUID, null);
                  gameProfile.getProperties().put("textures", new Property("textures", VersionSupport.getInstance().getTexture(skin)));

                  try {
                     Field profileField = itemMeta.getClass().getDeclaredField("profile");
                     profileField.setAccessible(true);
                     profileField.set(itemMeta, gameProfile);
                  } catch (Exception e) {
                     e.printStackTrace();
                  }

                  ItemBuilder.this.customItem.setItemMeta(itemMeta);
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
            public ItemBuilder add(String metadataName, String metadataValue) {
               VersionSupport.getInstance().addMetaData(ItemBuilder.this.customItem, metadataName, metadataValue);
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
               BannerMeta bannerMeta = ItemBuilder.this.getBannerMeta();
               bannerMeta.setPatterns(patternList);
               ItemBuilder.this.customItem.setItemMeta(bannerMeta);
               return ItemBuilder.this.item;
            }

            public ItemBuilder setPattern(int index, Pattern pattern) {
               BannerMeta bannerMeta = ItemBuilder.this.getBannerMeta();
               bannerMeta.setPattern(index, pattern);
               ItemBuilder.this.customItem.setItemMeta(bannerMeta);
               return ItemBuilder.this.item;
            }

            public ItemBuilder addPattern(Pattern pattern) {
               BannerMeta bannerMeta = ItemBuilder.this.getBannerMeta();
               bannerMeta.addPattern(pattern);
               ItemBuilder.this.customItem.setItemMeta(bannerMeta);
               return ItemBuilder.this.item;
            }

            public ItemBuilder removePattern(int index) {
               BannerMeta bannerMeta = ItemBuilder.this.getBannerMeta();
               bannerMeta.removePattern(index);
               ItemBuilder.this.customItem.setItemMeta(bannerMeta);
               return ItemBuilder.this.item;
            }

            public List<Pattern> getPatterns() {
               return ItemBuilder.this.getBannerMeta().getPatterns();
            }

            public Pattern getPattern(int index) {
               return ItemBuilder.this.getBannerMeta().getPattern(index);
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
               BookMeta bookMeta = ItemBuilder.this.getBookMeta();
               bookMeta.setTitle(title);
               ItemBuilder.this.customItem.setItemMeta(bookMeta);
               return ItemBuilder.this.item;
            }

            public boolean hasAuthor() {
               return ItemBuilder.this.getBookMeta().hasAuthor();
            }

            public String getAuthor() {
               return ItemBuilder.this.getBookMeta().getAuthor();
            }

            public ItemBuilder setAuthor(String author) {
               BookMeta bookMeta = ItemBuilder.this.getBookMeta();
               bookMeta.setAuthor(author);
               ItemBuilder.this.customItem.setItemMeta(bookMeta);
               return ItemBuilder.this.item;
            }

            public boolean hasPages() {
               return ItemBuilder.this.getBookMeta().hasPages();
            }

            public String getPage(int page) {
               return ItemBuilder.this.getBookMeta().getPage(page);
            }

            public ItemBuilder setPage(int page, String content) {
               BookMeta bookMeta = ItemBuilder.this.getBookMeta();
               bookMeta.setPage(page, content);
               ItemBuilder.this.customItem.setItemMeta(bookMeta);
               return ItemBuilder.this.item;
            }

            public List<String> getPages() {
               return ItemBuilder.this.getBookMeta().getPages();
            }

            public ItemBuilder setPages(List<String> pages) {
               BookMeta bookMeta = ItemBuilder.this.getBookMeta();
               bookMeta.setPages(pages);
               ItemBuilder.this.customItem.setItemMeta(bookMeta);
               return ItemBuilder.this.item;
            }

            public ItemBuilder addPages(String... pages) {
               BookMeta bookMeta = ItemBuilder.this.getBookMeta();
               bookMeta.addPage(pages);
               ItemBuilder.this.customItem.setItemMeta(bookMeta);
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
               FireworkMeta fireworkMeta = ItemBuilder.this.getFireworkMeta();
               fireworkMeta.addEffect(fireworkEffect);
               ItemBuilder.this.customItem.setItemMeta(fireworkMeta);
               return ItemBuilder.this.item;
            }

            public ItemBuilder addEffects(FireworkEffect... fireworkEffects) {
               FireworkMeta fireworkMeta = ItemBuilder.this.getFireworkMeta();
               fireworkMeta.addEffects(fireworkEffects);
               ItemBuilder.this.customItem.setItemMeta(fireworkMeta);
               return ItemBuilder.this.item;
            }

            public List<FireworkEffect> getEffects() {
               return ItemBuilder.this.getFireworkMeta().getEffects();
            }

            public int getEffectsSize() {
               return ItemBuilder.this.getFireworkMeta().getEffectsSize();
            }

            public ItemBuilder removeEffect(int index) {
               FireworkMeta fireworkMeta = ItemBuilder.this.getFireworkMeta();
               fireworkMeta.removeEffect(index);
               ItemBuilder.this.customItem.setItemMeta(fireworkMeta);
               return ItemBuilder.this.item;
            }

            public ItemBuilder clearEffects() {
               FireworkMeta fireworkMeta = ItemBuilder.this.getFireworkMeta();
               fireworkMeta.clearEffects();
               ItemBuilder.this.customItem.setItemMeta(fireworkMeta);
               return ItemBuilder.this.item;
            }

            public boolean hasEffects() {
               return ItemBuilder.this.getFireworkMeta().hasEffects();
            }

            public int getPower() {
               return ItemBuilder.this.getFireworkMeta().getPower();
            }

            public ItemBuilder setPower(int power) {
               FireworkMeta fireworkMeta = ItemBuilder.this.getFireworkMeta();
               fireworkMeta.setPower(power);
               ItemBuilder.this.customItem.setItemMeta(fireworkMeta);
               return ItemBuilder.this.item;
            }

            public ItemBuilder setEffect(FireworkEffect fireworkEffect) {
               FireworkEffectMeta fireworkEffectMeta = ItemBuilder.this.getFireworkEffectMeta();
               fireworkEffectMeta.setEffect(fireworkEffect);
               ItemBuilder.this.customItem.setItemMeta(fireworkEffectMeta);
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
               LeatherArmorMeta leatherArmorMeta = ItemBuilder.this.getLeatherArmorMeta();
               leatherArmorMeta.setColor(color);
               ItemBuilder.this.customItem.setItemMeta(leatherArmorMeta);
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
               MapMeta mapMeta = ItemBuilder.this.getMapMeta();
               mapMeta.setScaling(scaling);
               ItemBuilder.this.customItem.setItemMeta(mapMeta);
               return ItemBuilder.this.item;
            }
         };
      }

      return this.map;
   }

   public ItemBuilder setDisplayName(String displayName) {
      ItemMeta itemMeta = this.getItemMeta();
      itemMeta.setDisplayName(BambooUtils.colorize(displayName));
      this.customItem.setItemMeta(itemMeta);
      return this.item;
   }

   public String getDisplayName() {
      return this.customItem.getItemMeta().getDisplayName();
   }

   public ItemBuilder setLore(List<String> lore) {
      ItemMeta itemMeta = this.getItemMeta();
      ArrayList<String> coloredLore = new ArrayList<>();

      for (String line : lore) {
         coloredLore.add(BambooUtils.colorize(line));
      }

      itemMeta.setLore(coloredLore);
      this.customItem.setItemMeta(itemMeta);
      return this.item;
   }

   public ItemBuilder setUnbreakable(boolean unbreakable) {
      ItemMeta itemMeta = this.getItemMeta();
      VersionSupport.getInstance().setUnbreakable(itemMeta, unbreakable);
      this.customItem.setItemMeta(itemMeta);
      return this.item;
   }

   public ItemBuilder setAmount(int amount) {
      this.customItem.setAmount(amount);
      return this.item;
   }

   public ItemBuilder setData(int data) {
      if (!BambooUtils.isVersion(17, 18, 19)) {
         this.customItem.setDurability((short) data);
      }
      return this.item;
   }

   public int getAmount() {
      return this.customItem.getAmount();
   }

   public ItemBuilder setMaterial(Material material) {
      this.customItem.setType(material);
      return this.item;
   }

   public Material getMaterial() {
      return this.customItem.getType();
   }

   public boolean equals(Object obj) {
      return this.customItem.equals(obj);
   }

   public boolean isSimilar(ItemStack itemStack) {
      return this.customItem.isSimilar(itemStack);
   }

   public ItemStack build() {
      return this.customItem;
   }

}
