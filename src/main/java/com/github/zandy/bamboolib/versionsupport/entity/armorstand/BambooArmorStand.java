package com.github.zandy.bamboolib.versionsupport.entity.armorstand;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public abstract class BambooArmorStand {
   private static final HashMap<String, BambooArmorStand> armorStandMap = new HashMap();
   private final Location location;

   public BambooArmorStand(String var1, Location var2) {
      this.location = var2;
      armorStandMap.put(var1, this);
   }

   public static boolean isCreated(String var0) {
      return armorStandMap.containsKey(var0);
   }

   public static BambooArmorStand get(String var0) {
      return (BambooArmorStand)armorStandMap.get(var0);
   }

   public ArmorStand getRawArmorStand() {
      return null;
   }

   public abstract void setCustomName(String var1);

   public abstract String getCustomName();

   public abstract void setCustomNameVisible(boolean var1);

   public abstract boolean isCustomNameVisible();

   public abstract void setGravity(boolean var1);

   public abstract void remove();
   public abstract void setInvulnerable();

   public abstract ItemStack getItemInHand();

   public abstract void setItemInHand(ItemStack var1);

   public abstract ItemStack getBoots();

   public abstract void setBoots(ItemStack var1);

   public abstract ItemStack getLeggings();

   public abstract void setLeggings(ItemStack var1);

   public abstract ItemStack getChestplate();

   public abstract void setChestplate(ItemStack var1);

   public abstract ItemStack getHelmet();

   public abstract void setHelmet(ItemStack var1);

   public abstract EulerAngle getBodyPose();

   public abstract void setBodyPose(EulerAngle var1);

   public abstract EulerAngle getLeftArmPose();

   public abstract void setLeftArmPose(EulerAngle var1);

   public abstract EulerAngle getRightArmPose();

   public abstract void setRightArmPose(EulerAngle var1);

   public abstract EulerAngle getLeftLegPose();

   public abstract void setLeftLegPose(EulerAngle var1);

   public abstract EulerAngle getRightLegPose();

   public abstract void setRightLegPose(EulerAngle var1);

   public abstract EulerAngle getHeadPose();

   public abstract void setHeadPose(EulerAngle var1);

   public abstract boolean hasBasePlate();

   public abstract void setBasePlate(boolean var1);

   public abstract boolean isVisible();

   public abstract void setVisible(boolean var1);

   public abstract boolean hasArms();

   public abstract void setArms(boolean var1);

   public abstract boolean isSmall();

   public abstract void setSmall(boolean var1);

   public abstract boolean hasHitBox();

   public abstract void setHitBox(boolean var1);

   public Location getLocation() {
      return this.location;
   }
}
