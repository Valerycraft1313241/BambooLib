package com.github.zandy.bamboolib.utils;

import com.github.zandy.bamboolib.BambooLib;
import com.github.zandy.bamboolib.exceptions.BambooException;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import com.github.zandy.bamboolib.versionsupport.material.Materials;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

public class BambooFile {
    private static final UUID uuid = UUID.fromString("eb38bdd9-a3d9-41c5-9f03-2273ed0e8bc7");
    private YamlConfiguration yml;
    private File file;

    public BambooFile(String fileName, String directory, boolean isAbsolutePath) {
        this.init(fileName, directory, isAbsolutePath);
    }

    public BambooFile(String fileName, String directory) {
        this.init(fileName, directory, false);
    }

    public BambooFile(String fileName) {
        this.init(fileName, "", false);
    }

    public static boolean exists(String fileName, String directory) {
        return (new File("plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/" + directory + "/" + fileName + ".yml")).exists();
    }

    private void init(String fileName, String directory, boolean isAbsolutePath) {
        if (!isAbsolutePath) {
            directory = "plugins/" + BambooLib.getPluginInstance().getDescription().getName() + "/" + directory;
        }

        (new File(directory)).mkdirs();
        this.file = new File(directory, fileName + ".yml");
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (Exception e) {
                throw new BambooException(Arrays.asList("Cannot create file '" + fileName + ".yml'", "with path '" + directory + "'"));
            }
        }

        this.yml = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save() {
        try {
            this.yml.save(this.file);
        } catch (Exception e) {
            throw new BambooException("Cannot save file '" + this.file.getName() + "'");
        }
    }

    public String getName() {
        return this.file.getName();
    }

    public void addDefault(String path, Object value) {
        this.yml.addDefault(path, value);
    }

    public void copyDefaults() {
        this.yml.options().copyDefaults(true);
    }

    public void set(String path, Object value) {
        this.yml.set(path, value);
        this.save();
    }

    public Object get(String path) {
        return this.yml.get(path);
    }

    public boolean contains(String path) {
        return this.yml.contains(path);
    }

    public String getString(String path) {
        return BambooUtils.colorize(this.yml.getString(path));
    }

    public String getRawString(String path) {
        return this.yml.getString(path);
    }

    public List<String> getStringList(String path) {
        ArrayList<String> coloredList = new ArrayList<>();
        this.yml.getStringList(path).forEach((item) -> coloredList.add(BambooUtils.colorize(item)));
        return coloredList;
    }

    public List<String> getRawStringList(String path) {
        return this.yml.getStringList(path);
    }

    public boolean getBoolean(String path) {
        return this.yml.getBoolean(path);
    }

    public int getInt(String path) {
        return this.yml.getInt(path);
    }

    public double getDouble(String path) {
        return this.yml.getDouble(path);
    }

    public float getFloat(String path) {
        return (float) this.yml.getDouble(path);
    }

    public long getLong(String path) {
        return this.yml.getLong(path);
    }

    public Materials getMaterial(String path) {
        return Materials.valueOf(this.getRawString(path));
    }

    public List<?> getList(String path) {
        return this.yml.getList(path);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return this.yml.getConfigurationSection(path);
    }

    public void setLocation(String locationKey, Location location) {
        this.yml.set(locationKey + ".World", location.getWorld().getName());
        this.yml.set(locationKey + ".X", location.getX());
        this.yml.set(locationKey + ".Y", location.getY());
        this.yml.set(locationKey + ".Z", location.getZ());
        this.yml.set(locationKey + ".Yaw", location.getYaw());
        this.yml.set(locationKey + ".Pitch", location.getPitch());
        this.save();
    }


    public Location getLocation(String locationKey) {
        return new Location(
                Bukkit.getWorld(this.getString(locationKey + ".World")),
                this.getDouble(locationKey + ".X"),
                this.getDouble(locationKey + ".Y"),
                this.getDouble(locationKey + ".Z"),
                this.getFloat(locationKey + ".Yaw"),
                this.getFloat(locationKey + ".Pitch")
        );
    }

    public void setItemStack(String itemKey, ItemStack itemStack) {
        this.yml.set(itemKey + ".Material", itemStack.getType().name());
        if (itemStack.getAmount() > 1) {
            this.yml.set(itemKey + ".Amount", itemStack.getAmount());
        }

        if (itemStack.getDurability() != itemStack.getType().getMaxDurability()) {
            this.yml.set(itemKey + ".Durability", itemStack.getDurability());
        }

        if (VersionSupport.getInstance().hasMetaData(itemStack, "SkullPlayer")) {
            this.yml.set(itemKey + ".SkullPlayer", VersionSupport.getInstance().getMetaData(itemStack, "SkullPlayer"));
        }

        if (VersionSupport.getInstance().hasMetaData(itemStack, "SkullSkin")) {
            this.yml.set(itemKey + ".SkullSkin", VersionSupport.getInstance().getMetaData(itemStack, "SkullSkin"));
        }

        if (itemStack.hasItemMeta()) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta.hasDisplayName()) {
                this.yml.set(itemKey + ".DisplayName", BambooUtils.decolorize(itemMeta.getDisplayName()));
            }

            if (itemMeta.hasLore()) {
                this.yml.set(itemKey + ".Lore", BambooUtils.decolorize(itemMeta.getLore()));
            }

            if (itemMeta.hasEnchants()) {
                itemMeta.getEnchants().keySet().forEach((enchantment) -> {
                    this.yml.set(itemKey + ".Enchantments." + enchantment.getName(), itemMeta.getEnchants().get(enchantment));
                });
            }

            ArrayList<String> itemFlags = new ArrayList<>();
            Stream<ItemFlag> itemFlagStream = Arrays.stream(ItemFlag.values());
            Objects.requireNonNull(itemMeta);
            itemFlagStream.filter(itemMeta::hasItemFlag).forEach((itemFlag) -> {
                itemFlags.add(itemFlag.name());
            });
            if (!itemFlags.isEmpty()) {
                this.yml.set(itemKey + ".ItemFlags", itemFlags);
            }
        }

        this.save();
    }

    public ItemStack getItemStack(String itemKey) {
        if (!this.contains(itemKey)) {
            return null;
        } else {
            ItemStack itemStack = new ItemStack(Material.valueOf(this.getString(itemKey + ".Material")));
            ItemMeta itemMeta = itemStack.getItemMeta();

            if (this.contains(itemKey + ".Amount")) {
                itemStack.setAmount(this.getInt(itemKey + ".Amount"));
            }

            if (this.contains(itemKey + ".Durability")) {
                itemStack.setDurability((short) this.getInt(itemKey + ".Durability"));
            }

            if (this.contains(itemKey + ".DisplayName")) {
                itemMeta.setDisplayName(BambooUtils.colorize(this.getString(itemKey + ".DisplayName")));
            }

            if (this.contains(itemKey + ".Lore")) {
                itemMeta.setLore(BambooUtils.colorize(this.getStringList(itemKey + ".Lore")));
            }

            if (this.contains(itemKey + ".Enchantments")) {
                this.getConfigurationSection(itemKey + ".Enchantments").getKeys(false).forEach((enchantmentKey) -> {
                    itemMeta.addEnchant(Enchantment.getByName(enchantmentKey), this.getInt(itemKey + ".Enchantments." + enchantmentKey), true);
                });
            }

            String itemFlag;
            if (this.contains(itemKey + ".ItemFlags")) {
                for (String flag : this.getStringList(itemKey + ".ItemFlags")) {
                    itemFlag = flag;
                    itemMeta.addItemFlags(ItemFlag.valueOf(itemFlag));
                }
            }

            itemStack.setItemMeta(itemMeta);
            String skullPlayer;
            if (this.contains(itemKey + ".SkullPlayer")) {
                skullPlayer = this.getString(itemKey + ".SkullPlayer");
                VersionSupport.getInstance().setSkullOwner(itemStack, skullPlayer);
                VersionSupport.getInstance().addMetaData(itemStack, "SkullPlayer", skullPlayer);
            }

            if (this.contains(itemKey + ".SkullSkin")) {
                String itemType = itemStack.getType().name();
                if (!itemType.equals("PLAYER_HEAD") && !itemType.equals("SKULL_ITEM")) {
                    return itemStack;
                }

                String skullSkin = this.getString(itemKey + ".SkullSkin");
                if (skullSkin == null) {
                    return itemStack;
                }

                GameProfile gameProfile = new GameProfile(uuid, null);
                gameProfile.getProperties().put("textures", new Property("textures", VersionSupport.getInstance().getTexture(skullSkin)));

                try {
                    Field profileField = itemMeta.getClass().getDeclaredField("profile");
                    profileField.setAccessible(true);
                    profileField.set(itemMeta, gameProfile);
                } catch (Exception e) {
                    throw new BambooException("Cannot set skull's skin.");
                }

                itemStack.setItemMeta(itemMeta);
                VersionSupport.getInstance().addMetaData(itemStack, "SkullSkin", skullSkin);
            }

            return itemStack;
        }
    }


    public void delete() {
        this.file.delete();
    }
}
