package com.github.zandy.bamboolib.versionsupport.entity.armorstand.support.v1_8_R3;

import com.github.zandy.bamboolib.versionsupport.entity.armorstand.BambooArmorStand;
import com.github.zandy.bamboolib.versionsupport.support.v1_8_R3;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class BambooArmorStandNMS extends BambooArmorStand {
   private final CraftArmorStand craftArmorStand;
   private final Player player;
   private final int entityId;

   public BambooArmorStandNMS(Player player, String customName, Location location) {
      super(customName, location);
      EntityArmorStand entityArmorStand = new EntityArmorStand(((CraftWorld) player.getWorld()).getHandle());
      entityArmorStand.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
      this.craftArmorStand = (CraftArmorStand) entityArmorStand.getBukkitEntity();
      this.player = player;
      this.entityId = entityArmorStand.getId();
      this.sendSpawnPacket();
   }

   public Player getOwningPlayer() {
      return this.player;
   }

   public ArmorStand getRawArmorStand() {
      return (ArmorStand) this.craftArmorStand.getHandle().getBukkitEntity();
   }

   public void setCustomName(String customName) {
      this.craftArmorStand.setCustomName(customName);
      this.sendMetadataPacket();
   }

   public String getCustomName() {
      return this.craftArmorStand.getCustomName();
   }

   public void setCustomNameVisible(boolean isVisible) {
      this.craftArmorStand.setCustomNameVisible(isVisible);
      this.sendMetadataPacket();
   }

   public boolean isCustomNameVisible() {
      return this.craftArmorStand.isCustomNameVisible();
   }

   public void setGravity(boolean hasGravity) {
      this.craftArmorStand.setGravity(hasGravity);
      this.sendMetadataPacket();
   }

   public void setInvulnerable(BambooArmorStand armorStand, boolean isInvulnerable) {
      this.sendMetadataPacket();
   }

   public void remove() {
      this.craftArmorStand.remove();
      ((CraftPlayer) this.player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(new int[]{this.entityId}));
   }

   @Override
   public void setInvulnerable() {
      // Implementation here
   }

   public ItemStack getItemInHand() {
      return this.craftArmorStand.getItemInHand();
   }

   public void setItemInHand(ItemStack itemStack) {
      this.craftArmorStand.setItemInHand(itemStack);
      this.sendMetadataPacket();
   }

   public ItemStack getBoots() {
      return this.craftArmorStand.getBoots();
   }

   public void setBoots(ItemStack boots) {
      this.craftArmorStand.setBoots(boots);
      this.sendMetadataPacket();
   }

   public ItemStack getLeggings() {
      return this.craftArmorStand.getLeggings();
   }

   public void setLeggings(ItemStack leggings) {
      this.craftArmorStand.setLeggings(leggings);
      this.sendMetadataPacket();
   }

   public ItemStack getChestplate() {
      return this.craftArmorStand.getChestplate();
   }

   public void setChestplate(ItemStack chestplate) {
      this.craftArmorStand.setChestplate(chestplate);
      this.sendMetadataPacket();
   }

   public ItemStack getHelmet() {
      return this.craftArmorStand.getHelmet();
   }

   public void setHelmet(ItemStack helmet) {
      this.craftArmorStand.setHelmet(helmet);
      this.sendMetadataPacket();
   }

   public EulerAngle getBodyPose() {
      return this.craftArmorStand.getBodyPose();
   }

   public void setBodyPose(EulerAngle bodyPose) {
      this.craftArmorStand.setBodyPose(bodyPose);
      this.sendMetadataPacket();
   }

   public EulerAngle getLeftArmPose() {
      return this.craftArmorStand.getLeftArmPose();
   }

   public void setLeftArmPose(EulerAngle leftArmPose) {
      this.craftArmorStand.setLeftArmPose(leftArmPose);
      this.sendMetadataPacket();
   }

   public EulerAngle getRightArmPose() {
      return this.craftArmorStand.getRightArmPose();
   }

   public void setRightArmPose(EulerAngle rightArmPose) {
      this.craftArmorStand.setRightArmPose(rightArmPose);
      this.sendMetadataPacket();
   }

   public EulerAngle getLeftLegPose() {
      return this.craftArmorStand.getLeftLegPose();
   }

   public void setLeftLegPose(EulerAngle leftLegPose) {
      this.craftArmorStand.setLeftLegPose(leftLegPose);
      this.sendMetadataPacket();
   }

   public EulerAngle getRightLegPose() {
      return this.craftArmorStand.getRightLegPose();
   }

   public void setRightLegPose(EulerAngle rightLegPose) {
      this.craftArmorStand.setRightLegPose(rightLegPose);
      this.sendMetadataPacket();
   }

   public EulerAngle getHeadPose() {
      return this.craftArmorStand.getHeadPose();
   }

   public void setHeadPose(EulerAngle headPose) {
      this.craftArmorStand.setHeadPose(headPose);
      this.sendMetadataPacket();
   }

   public boolean hasBasePlate() {
      return this.craftArmorStand.hasBasePlate();
   }

   public void setBasePlate(boolean hasBasePlate) {
      this.craftArmorStand.setBasePlate(hasBasePlate);
      this.sendMetadataPacket();
   }

   public boolean isVisible() {
      return this.craftArmorStand.isVisible();
   }

   public void setVisible(boolean isVisible) {
      this.craftArmorStand.setVisible(isVisible);
      this.sendMetadataPacket();
   }

   public boolean hasArms() {
      return this.craftArmorStand.hasArms();
   }

   public void setArms(boolean hasArms) {
      this.craftArmorStand.setArms(hasArms);
      this.sendMetadataPacket();
   }

   public boolean isSmall() {
      return this.craftArmorStand.isSmall();
   }

   public void setSmall(boolean isSmall) {
      this.craftArmorStand.setSmall(isSmall);
      this.sendMetadataPacket();
   }

   public boolean hasHitBox() {
      return this.craftArmorStand.isMarker();
   }

   public void setHitBox(boolean hasHitBox) {
      this.craftArmorStand.setMarker(hasHitBox);
      this.sendMetadataPacket();
   }

   private void sendMetadataPacket() {
      v1_8_R3.sendPacket(this.player, new PacketPlayOutEntityMetadata(this.entityId, this.craftArmorStand.getHandle().getDataWatcher(), false));
   }

   private void sendSpawnPacket() {
      v1_8_R3.sendPacket(this.player, new PacketPlayOutSpawnEntityLiving(this.craftArmorStand.getHandle()));
      this.sendMetadataPacket();
   }
}
