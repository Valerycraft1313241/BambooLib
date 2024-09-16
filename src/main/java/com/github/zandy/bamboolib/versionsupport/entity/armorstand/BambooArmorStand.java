package com.github.zandy.bamboolib.versionsupport.entity.armorstand;

import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public abstract class BambooArmorStand {
   private static final HashMap<String, BambooArmorStand> armorStandMap = new HashMap<>();
   private final Location location;

   public BambooArmorStand(String customName, Location location) {
      this.location = location;
      armorStandMap.put(customName, this);
   }

   public static boolean isCreated(String customName) {
      return armorStandMap.containsKey(customName);
   }

   public static BambooArmorStand get(String customName) {
      return armorStandMap.get(customName);
   }

   public ArmorStand getRawArmorStand() {
      return null;
   }

   public abstract void setCustomName(String customName);

   public abstract String getCustomName();

   public abstract void setCustomNameVisible(boolean isVisible);

   public abstract boolean isCustomNameVisible();

   public abstract void setGravity(boolean hasGravity);

   public abstract void remove();

   public abstract void setInvulnerable();

   public abstract ItemStack getItemInHand();

   public abstract void setItemInHand(ItemStack itemStack);

   public abstract ItemStack getBoots();

   public abstract void setBoots(ItemStack boots);

   public abstract ItemStack getLeggings();

   public abstract void setLeggings(ItemStack leggings);

   public abstract ItemStack getChestplate();

   public abstract void setChestplate(ItemStack chestplate);

   public abstract ItemStack getHelmet();

   public abstract void setHelmet(ItemStack helmet);

   public abstract EulerAngle getBodyPose();

   public abstract void setBodyPose(EulerAngle bodyPose);

   public abstract EulerAngle getLeftArmPose();

   public abstract void setLeftArmPose(EulerAngle leftArmPose);

   public abstract EulerAngle getRightArmPose();

   public abstract void setRightArmPose(EulerAngle rightArmPose);

   public abstract EulerAngle getLeftLegPose();

   public abstract void setLeftLegPose(EulerAngle leftLegPose);

   public abstract EulerAngle getRightLegPose();

   public abstract void setRightLegPose(EulerAngle rightLegPose);

   public abstract EulerAngle getHeadPose();

   public abstract void setHeadPose(EulerAngle headPose);

   public abstract boolean hasBasePlate();

   public abstract void setBasePlate(boolean hasBasePlate);

   public abstract boolean isVisible();

   public abstract void setVisible(boolean isVisible);

   public abstract boolean hasArms();

   public abstract void setArms(boolean hasArms);

   public abstract boolean isSmall();

   public abstract void setSmall(boolean isSmall);

   public abstract boolean hasHitBox();

   public abstract void setHitBox(boolean hasHitBox);

   public Location getLocation() {
      return this.location;
   }
}
