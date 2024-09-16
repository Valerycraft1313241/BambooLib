package com.github.zandy.bamboolib.versionsupport.entity.armorstand.support;

import com.github.zandy.bamboolib.versionsupport.entity.armorstand.BambooArmorStand;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class BambooArmorStandBukkit extends BambooArmorStand {
   private final ArmorStand armorStand;

   public BambooArmorStandBukkit(String customName, Location location) {
      super(customName, location);
      this.armorStand = location.getWorld().spawn(location, ArmorStand.class);
   }

   public ArmorStand getRawArmorStand() {
      return this.armorStand;
   }

   public void setCustomName(String customName) {
      this.armorStand.setCustomName(customName);
   }

   public String getCustomName() {
      return this.armorStand.getCustomName();
   }

   public void setCustomNameVisible(boolean isVisible) {
      this.armorStand.setCustomNameVisible(isVisible);
   }

   public boolean isCustomNameVisible() {
      return this.armorStand.isCustomNameVisible();
   }

   public void setGravity(boolean hasGravity) {
      this.armorStand.setGravity(hasGravity);
   }

   public void remove() {
      this.armorStand.remove();
   }

   @Override
   public void setInvulnerable() {
      // Implementation here
   }

   public ItemStack getItemInHand() {
      return this.armorStand.getItemInHand();
   }

   public void setItemInHand(ItemStack itemStack) {
      this.armorStand.setItemInHand(itemStack);
   }

   public ItemStack getBoots() {
      return this.armorStand.getBoots();
   }

   public void setBoots(ItemStack boots) {
      this.armorStand.setBoots(boots);
   }

   public ItemStack getLeggings() {
      return this.armorStand.getLeggings();
   }

   public void setLeggings(ItemStack leggings) {
      this.armorStand.setLeggings(leggings);
   }

   public ItemStack getChestplate() {
      return this.armorStand.getChestplate();
   }

   public void setChestplate(ItemStack chestplate) {
      this.armorStand.setChestplate(chestplate);
   }

   public ItemStack getHelmet() {
      return this.armorStand.getHelmet();
   }

   public void setHelmet(ItemStack helmet) {
      this.armorStand.setHelmet(helmet);
   }

   public EulerAngle getBodyPose() {
      return this.armorStand.getBodyPose();
   }

   public void setBodyPose(EulerAngle bodyPose) {
      this.armorStand.setBodyPose(bodyPose);
   }

   public EulerAngle getLeftArmPose() {
      return this.armorStand.getLeftArmPose();
   }

   public void setLeftArmPose(EulerAngle leftArmPose) {
      this.armorStand.setLeftArmPose(leftArmPose);
   }

   public EulerAngle getRightArmPose() {
      return this.armorStand.getRightArmPose();
   }

   public void setRightArmPose(EulerAngle rightArmPose) {
      this.armorStand.setRightArmPose(rightArmPose);
   }

   public EulerAngle getLeftLegPose() {
      return this.armorStand.getLeftLegPose();
   }

   public void setLeftLegPose(EulerAngle leftLegPose) {
      this.armorStand.setLeftLegPose(leftLegPose);
   }

   public EulerAngle getRightLegPose() {
      return this.armorStand.getRightLegPose();
   }

   public void setRightLegPose(EulerAngle rightLegPose) {
      this.armorStand.setRightLegPose(rightLegPose);
   }

   public EulerAngle getHeadPose() {
      return this.armorStand.getHeadPose();
   }

   public void setHeadPose(EulerAngle headPose) {
      this.armorStand.setHeadPose(headPose);
   }

   public boolean hasBasePlate() {
      return this.armorStand.hasBasePlate();
   }

   public void setBasePlate(boolean hasBasePlate) {
      this.armorStand.setBasePlate(hasBasePlate);
   }

   public boolean isVisible() {
      return this.armorStand.isVisible();
   }

   public void setVisible(boolean isVisible) {
      this.armorStand.setVisible(isVisible);
   }

   public boolean hasArms() {
      return this.armorStand.hasArms();
   }

   public void setArms(boolean hasArms) {
      this.armorStand.setArms(hasArms);
   }

   public boolean isSmall() {
      return this.armorStand.isSmall();
   }

   public void setSmall(boolean isSmall) {
      this.armorStand.setSmall(isSmall);
   }

   public boolean hasHitBox() {
      return this.armorStand.isMarker();
   }

   public void setHitBox(boolean hasHitBox) {
      this.armorStand.setMarker(hasHitBox);
   }
}
