package com.github.zandy.bamboolib.versionsupport.entity.armorstand.support;

import com.github.zandy.bamboolib.versionsupport.entity.armorstand.BambooArmorStand;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class BambooArmorStandBukkit extends BambooArmorStand {
   private final ArmorStand armorStand;

   public BambooArmorStandBukkit(String var1, Location var2) {
      super(var1, var2);
      this.armorStand = var2.getWorld().spawn(var2, ArmorStand.class);
   }

   public ArmorStand getRawArmorStand() {
      return this.armorStand;
   }

   public void setCustomName(String var1) {
      this.armorStand.setCustomName(var1);
   }

   public String getCustomName() {
      return this.armorStand.getCustomName();
   }

   public void setCustomNameVisible(boolean var1) {
      this.armorStand.setCustomNameVisible(var1);
   }

   public boolean isCustomNameVisible() {
      return this.armorStand.isCustomNameVisible();
   }

   public void setGravity(boolean var1) {
      this.armorStand.setGravity(var1);
   }


   public void remove() {
      this.armorStand.remove();
   }

   @Override
   public void setInvulnerable() {

   }

   public ItemStack getItemInHand() {
      return this.armorStand.getItemInHand();
   }

   public void setItemInHand(ItemStack var1) {
      this.armorStand.setItemInHand(var1);
   }

   public ItemStack getBoots() {
      return this.armorStand.getBoots();
   }

   public void setBoots(ItemStack var1) {
      this.armorStand.setBoots(var1);
   }

   public ItemStack getLeggings() {
      return this.armorStand.getLeggings();
   }

   public void setLeggings(ItemStack var1) {
      this.armorStand.setLeggings(var1);
   }

   public ItemStack getChestplate() {
      return this.armorStand.getChestplate();
   }

   public void setChestplate(ItemStack var1) {
      this.armorStand.setChestplate(var1);
   }

   public ItemStack getHelmet() {
      return this.armorStand.getHelmet();
   }

   public void setHelmet(ItemStack var1) {
      this.armorStand.setHelmet(var1);
   }

   public EulerAngle getBodyPose() {
      return this.armorStand.getBodyPose();
   }

   public void setBodyPose(EulerAngle var1) {
      this.armorStand.setBodyPose(var1);
   }

   public EulerAngle getLeftArmPose() {
      return this.armorStand.getLeftArmPose();
   }

   public void setLeftArmPose(EulerAngle var1) {
      this.armorStand.setLeftArmPose(var1);
   }

   public EulerAngle getRightArmPose() {
      return this.armorStand.getRightArmPose();
   }

   public void setRightArmPose(EulerAngle var1) {
      this.armorStand.setRightArmPose(var1);
   }

   public EulerAngle getLeftLegPose() {
      return this.armorStand.getLeftLegPose();
   }

   public void setLeftLegPose(EulerAngle var1) {
      this.armorStand.setLeftLegPose(var1);
   }

   public EulerAngle getRightLegPose() {
      return this.armorStand.getRightLegPose();
   }

   public void setRightLegPose(EulerAngle var1) {
      this.armorStand.setRightLegPose(var1);
   }

   public EulerAngle getHeadPose() {
      return this.armorStand.getHeadPose();
   }

   public void setHeadPose(EulerAngle var1) {
      this.armorStand.setHeadPose(var1);
   }

   public boolean hasBasePlate() {
      return this.armorStand.hasBasePlate();
   }

   public void setBasePlate(boolean var1) {
      this.armorStand.setBasePlate(var1);
   }

   public boolean isVisible() {
      return this.armorStand.isVisible();
   }

   public void setVisible(boolean var1) {
      this.armorStand.setVisible(var1);
   }

   public boolean hasArms() {
      return this.armorStand.hasArms();
   }

   public void setArms(boolean var1) {
      this.armorStand.setArms(var1);
   }

   public boolean isSmall() {
      return this.armorStand.isSmall();
   }

   public void setSmall(boolean var1) {
      this.armorStand.setSmall(var1);
   }

   public boolean hasHitBox() {
      return this.armorStand.isMarker();
   }

   public void setHitBox(boolean var1) {
      this.armorStand.setMarker(var1);
   }
}
