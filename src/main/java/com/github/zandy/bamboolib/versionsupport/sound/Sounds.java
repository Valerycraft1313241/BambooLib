package com.github.zandy.bamboolib.versionsupport.sound;

import com.github.zandy.bamboolib.versionsupport.VersionSupport;

public enum Sounds {
   EXPLODE((new Sound("explode")).addSupport(VersionSupport.VersionType.v1_8_R3, "EXPLODE").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_GENERIC_EXPLODE").save()),
   DRAGON_WINGS((new Sound("dragonWings")).addSupport(VersionSupport.VersionType.v1_8_R3, "ENDERDRAGON_WINGS").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_ENDERDRAGON_FLAP").addSupport(VersionSupport.VersionType.v1_17_R1, "ENTITY_ENDER_DRAGON_FLAP").save()),
   WITHER_SHOOT((new Sound("witherShoot")).addSupport(VersionSupport.VersionType.v1_8_R3, "WITHER_SHOOT").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_WITHER_SHOOT").save()),
   ORB_PICKUP((new Sound("orbPickup")).addSupport(VersionSupport.VersionType.v1_8_R3, "ORB_PICKUP").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_EXPERIENCE_ORB_PICKUP").save()),
   EQUIP_ARMOR((new Sound("equipArmor")).addSupport(VersionSupport.VersionType.v1_8_R3, "HORSE_ARMOR").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_HORSE_ARMOR").save()),
   GENERIC_EAT((new Sound("genericEat")).addSupport(VersionSupport.VersionType.v1_8_R3, "EAT").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_GENERIC_EAT").save()),
   ARROW_HIT_PLAYER((new Sound("arrowHitPlayer")).addSupport(VersionSupport.VersionType.v1_8_R3, "SUCCESSFUL_HIT").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_ARROW_HIT_PLAYER").save()),
   PLAYER_LEVELUP((new Sound("levelUp")).addSupport(VersionSupport.VersionType.v1_8_R3, "LEVEL_UP").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_PLAYER_LEVELUP").save()),
   ENDERMAN_TELEPORT((new Sound("endermanTeleport")).addSupport(VersionSupport.VersionType.v1_8_R3, "ENDERMAN_TELEPORT").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_ENDERMEN_TELEPORT").addSupport(VersionSupport.VersionType.v1_17_R1, "ENTITY_ENDERMAN_TELEPORT").save()),
   VILLAGER_NO((new Sound("villagerNo")).addSupport(VersionSupport.VersionType.v1_8_R3, "VILLAGER_NO").addSupport(VersionSupport.VersionType.v1_12_R1, "ENTITY_VILLAGER_NO").save()),
   NOTE_PLING((new Sound("notePling")).addSupport(VersionSupport.VersionType.v1_8_R3, "NOTE_PLING").addSupport(VersionSupport.VersionType.v1_12_R1, "BLOCK_NOTE_PLING").addSupport(VersionSupport.VersionType.v1_17_R1, "BLOCK_NOTE_BLOCK_PLING").save()),
   CLICK((new Sound("click")).addSupport(VersionSupport.VersionType.v1_8_R3, "CLICK").addSupport(VersionSupport.VersionType.v1_12_R1, "UI_BUTTON_CLICK").addSupport(VersionSupport.VersionType.v1_17_R1, "UI_BUTTON_CLICK").addSupport(VersionSupport.VersionType.v1_18_R2, "UI_BUTTON_CLICK").addSupport(VersionSupport.VersionType.v1_19_R1, "UI_BUTTON_CLICK")),
   DIG_GRASS((new Sound("digGrass")).addSupport(VersionSupport.VersionType.v1_8_R3, "DIG_GRASS").addSupport(VersionSupport.VersionType.v1_12_R1, "BLOCK_GRASS_BREAK").addSupport(VersionSupport.VersionType.v1_17_R1, "BLOCK_GRASS_BREAK").addSupport(VersionSupport.VersionType.v1_18_R2, "BLOCK_GRASS_BREAK").addSupport(VersionSupport.VersionType.v1_19_R1, "BLOCK_GRASS_BREAK"));

   private final Sound sound;

   private Sounds(Sound var3) {
      this.sound = var3;
   }

   public Sound getSound() {
      return this.sound;
   }

}
