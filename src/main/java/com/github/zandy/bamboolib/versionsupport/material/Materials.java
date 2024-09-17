package com.github.zandy.bamboolib.versionsupport.material;

import com.github.zandy.bamboolib.item.ItemBuilder;
import com.github.zandy.bamboolib.utils.BambooUtils;
import com.github.zandy.bamboolib.versionsupport.VersionSupport;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum Materials {
    ACACIA_BOAT(new MP(333), new MP(447)),
    ACACIA_BUTTON(new MP(143)),
    ACACIA_DOOR(new MP(430)),
    ACACIA_FENCE(new MP(192)),
    ACACIA_FENCE_GATE(new MP(187)),
    ACACIA_LEAVES(new MP(161)),
    ACACIA_LOG(new MP(162)),
    ACACIA_PLANKS(new MP(5, 4)),
    ACACIA_PRESSURE_PLATE(new MP(72)),
    ACACIA_SAPLING(new MP(6, 4)),
    ACACIA_SIGN(new MP(323), new MP(323)),
    ACACIA_SLAB(new MP(126, 4)),
    ACACIA_STAIRS(new MP(163)),
    ACACIA_TRAPDOOR(new MP(96)),
    ACACIA_WALL_SIGN(new MP(68)),
    ACACIA_WOOD(new MP(162, true)),
    ACTIVATOR_RAIL(new MP(157)),
    AIR(new MP(0)),
    ALLIUM(new MP(38, 2)),
    ANDESITE(new MP(1, 5)),
    ANDESITE_SLAB(new MP(44, true)),
    ANDESITE_STAIRS(new MP(67, true)),
    ANDESITE_WALL(new MP(139, true)),
    ANVIL(new MP(145)),
    APPLE(new MP(260)),
    ARMOR_STAND(new MP(416)),
    ARROW(new MP(262)),
    ATTACHED_MELON_STEM(new MP(105, 7)),
    ATTACHED_PUMPKIN_STEM(new MP(104, 7)),
    AZURE_BLUET(new MP(38, 3)),
    BAKED_POTATO(new MP(393)),
    BAMBOO(new MP(338, true)),
    BAMBOO_SAPLING(new MP(338, true)),
    BARREL(new MP(17, true)),
    BARRIER(new MP(166)),
    BAT_SPAWN_EGG(new MP(383, 65)),
    BEACON(new MP(138)),
    BEDROCK(new MP(7)),
    BEEF(new MP(363)),
    BEEHIVE(new MP(0, true)),
    BEETROOT(new MP(0, true)),
    BEETROOTS(new MP(0, true)),
    BEETROOT_SEEDS(new MP(295, true), new MP(435)),
    BEETROOT_SOUP(new MP(282, true), new MP(436)),
    BEE_NEST(new MP(0, true)),
    BEE_SPAWN_EGG(new MP(383, 65, true)),
    BELL(new MP(0, true)),
    BIRCH_BOAT(new MP(333), new MP(445)),
    BIRCH_BUTTON(new MP(143, true)),
    BIRCH_DOOR(new MP(428)),
    BIRCH_FENCE(new MP(189)),
    BIRCH_FENCE_GATE(new MP(184)),
    BIRCH_LEAVES(new MP(18, 2)),
    BIRCH_LOG(new MP(17, 2)),
    BIRCH_PLANKS(new MP(5, 2)),
    BIRCH_PRESSURE_PLATE(new MP(72, true)),
    BIRCH_SAPLING(new MP(6, 2)),
    BIRCH_SIGN(new MP(323)),
    BIRCH_SLAB(new MP(126, 2)),
    BIRCH_STAIRS(new MP(135)),
    BIRCH_TRAPDOOR(new MP(96, true)),
    BIRCH_WALL_SIGN(new MP(68)),
    BIRCH_WOOD(new MP(17, 2, true)),
    BLACK_BANNER(new MP(425)),
    BLACK_BED(new MP(355, true), new MP(355, 15)),
    BLACK_CARPET(new MP(171, 15)),
    BLACK_CONCRETE(new MP(159, 15, true), new MP(251, 15)),
    BLACK_CONCRETE_POWDER(new MP(12, true), new MP(252, 15)),
    BLACK_DYE(new MP(351)),
    BLACK_GLAZED_TERRACOTTA(new MP(159, 15, true), new MP(250)),
    BLACK_SHULKER_BOX(new MP(130, true), new MP(234)),
    BLACK_STAINED_GLASS(new MP(95, 15)),
    BLACK_STAINED_GLASS_PANE(new MP(160, 15)),
    BLACK_TERRACOTTA(new MP(159, 15)),
    BLACK_WALL_BANNER(new MP(425)),
    BLACK_WOOL(new MP(35, 15)),
    BLAST_FURNACE(new MP(61, true)),
    BLAZE_POWDER(new MP(377)),
    BLAZE_ROD(new MP(369)),
    BLAZE_SPAWN_EGG(new MP(383, 61)),
    BLUE_BANNER(new MP(425, 4)),
    BLUE_BED(new MP(355, true), new MP(355, 11)),
    BLUE_CARPET(new MP(171, 11)),
    BLUE_CONCRETE(new MP(159, 11, true), new MP(251, 11)),
    BLUE_CONCRETE_POWDER(new MP(12, true), new MP(252, 11)),
    BLUE_DYE(new MP(351, 4)),
    BLUE_GLAZED_TERRACOTTA(new MP(159, 11, true), new MP(246)),
    BLUE_ICE(new MP(174)),
    BLUE_ORCHID(new MP(38, 1)),
    BLUE_SHULKER_BOX(new MP(130, true), new MP(230)),
    BLUE_STAINED_GLASS(new MP(95, 11)),
    BLUE_STAINED_GLASS_PANE(new MP(160, 11)),
    BLUE_TERRACOTTA(new MP(159, 11)),
    BLUE_WALL_BANNER(new MP(425, 4)),
    BLUE_WOOL(new MP(35, 11)),
    BONE(new MP(352)),
    BONE_BLOCK(new MP(0, true), new MP(216)),
    BONE_MEAL(new MP(351, 15)),
    BOOK(new MP(340)),
    BOOKSHELF(new MP(47)),
    BOW(new MP(261)),
    BOWL(new MP(281)),
    BRAIN_CORAL(new MP(0, true)),
    BRAIN_CORAL_BLOCK(new MP(0, true)),
    BRAIN_CORAL_FAN(new MP(0, true)),
    BRAIN_CORAL_WALL_FAN(new MP(0, true)),
    BREAD(new MP(297)),
    BREWING_STAND(new MP(379)),
    BRICK(new MP(336)),
    BRICKS(new MP(45)),
    BRICK_SLAB(new MP(44, 4)),
    BRICK_STAIRS(new MP(108)),
    BRICK_WALL(new MP(139, true)),
    BROWN_BANNER(new MP(425, 3)),
    BROWN_BED(new MP(355, true), new MP(355, 12)),
    BROWN_CARPET(new MP(171, 12)),
    BROWN_CONCRETE(new MP(159, 12, true), new MP(251, 12)),
    BROWN_CONCRETE_POWDER(new MP(12, true), new MP(252, 12)),
    BROWN_DYE(new MP(351, 3)),
    BROWN_GLAZED_TERRACOTTA(new MP(159, 12, true), new MP(247)),
    BROWN_MUSHROOM(new MP(39)),
    BROWN_MUSHROOM_BLOCK(new MP(99, true)),
    BROWN_SHULKER_BOX(new MP(130, true), new MP(231)),
    BROWN_STAINED_GLASS(new MP(95, 12)),
    BROWN_STAINED_GLASS_PANE(new MP(160, 12)),
    BROWN_TERRACOTTA(new MP(159, 12)),
    BROWN_WALL_BANNER(new MP(425, 3)),
    BROWN_WOOL(new MP(35, 12)),
    BUBBLE_COLUMN(new MP(0, true)),
    BUBBLE_CORAL(new MP(0, true)),
    BUBBLE_CORAL_BLOCK(new MP(0, true)),
    BUBBLE_CORAL_FAN(new MP(0, true)),
    BUBBLE_CORAL_WALL_FAN(new MP(0, true)),
    BUCKET(new MP(325)),
    CACTUS(new MP(81)),
    CAKE(new MP(354)),
    CAMPFIRE(new MP(0, true)),
    CARROT(new MP(391)),
    CARROTS(new MP(141)),
    CARROT_ON_A_STICK(new MP(398)),
    CARTOGRAPHY_TABLE(new MP(58, true)),
    CARVED_PUMPKIN(new MP(86)),
    CAT_SPAWN_EGG(new MP(383, 98, true)),
    CAULDRON(new MP(380)),
    CAVE_AIR(new MP(0, true)),
    CAVE_SPIDER_SPAWN_EGG(new MP(383, 59)),
    CHAINMAIL_BOOTS(new MP(305)),
    CHAINMAIL_CHESTPLATE(new MP(303)),
    CHAINMAIL_HELMET(new MP(302)),
    CHAINMAIL_LEGGINGS(new MP(304)),
    CHAIN_COMMAND_BLOCK(new MP(137, true)),
    CHARCOAL(new MP(263, 1)),
    CHEST(new MP(54)),
    CHEST_MINECART(new MP(342)),
    CHICKEN(new MP(365)),
    CHICKEN_SPAWN_EGG(new MP(383, 93)),
    CHIPPED_ANVIL(new MP(145, 1)),
    CHISELED_QUARTZ_BLOCK(new MP(155, 1)),
    CHISELED_RED_SANDSTONE(new MP(179, 1)),
    CHISELED_SANDSTONE(new MP(24, 1)),
    CHISELED_STONE_BRICKS(new MP(98, 3)),
    CHORUS_FLOWER(new MP(0, true), new MP(200)),
    CHORUS_FRUIT(new MP(0, true), new MP(432)),
    CHORUS_PLANT(new MP(0, true), new MP(199)),
    CLAY(new MP(82)),
    CLAY_BALL(new MP(337)),
    CLOCK(new MP(347)),
    COAL(new MP(263)),
    COAL_BLOCK(new MP(173)),
    COAL_ORE(new MP(16)),
    COARSE_DIRT(new MP(3, 1)),
    COBBLESTONE(new MP(4)),
    COBBLESTONE_SLAB(new MP(44, 3)),
    COBBLESTONE_STAIRS(new MP(67)),
    COBBLESTONE_WALL(new MP(139)),
    COBWEB(new MP(30)),
    COCOA(new MP(351, 3)),
    COCOA_BEANS(new MP(351, 3)),
    COD(new MP(349, 2, true)),
    COD_BUCKET(new MP(326, true)),
    COD_SPAWN_EGG(new MP(383, 94, true)),
    COMMAND_BLOCK(new MP(137)),
    COMMAND_BLOCK_MINECART(new MP(422)),
    COMPARATOR(new MP(404)),
    COMPASS(new MP(345)),
    COMPOSTER(new MP(0, true)),
    CONDUIT(new MP(0, true)),
    COOKED_BEEF(new MP(364)),
    COOKED_CHICKEN(new MP(366)),
    COOKED_COD(new MP(350)),
    COOKED_MUTTON(new MP(424)),
    COOKED_PORKCHOP(new MP(320)),
    COOKED_RABBIT(new MP(412)),
    COOKED_SALMON(new MP(350, 1)),
    COOKIE(new MP(357)),
    CORNFLOWER(new MP(38, 1, true)),
    COW_SPAWN_EGG(new MP(383, 92)),
    CRACKED_STONE_BRICKS(new MP(98, 2)),
    CRAFTING_TABLE(new MP(58)),
    CREEPER_BANNER_PATTERN(new MP(0, true)),
    CREEPER_HEAD(new MP(397, 4)),
    CREEPER_SPAWN_EGG(new MP(383, 50)),
    CREEPER_WALL_HEAD(new MP(397, 4)),
    CROSSBOW(new MP(261, true)),
    CUT_RED_SANDSTONE(new MP(179, 2)),
    CUT_RED_SANDSTONE_SLAB(new MP(182, true)),
    CUT_SANDSTONE(new MP(24, 2)),
    CUT_SANDSTONE_SLAB(new MP(44, 1, true)),
    CYAN_BANNER(new MP(425, 6)),
    CYAN_BED(new MP(355, true), new MP(355, 9)),
    CYAN_CARPET(new MP(171, 9)),
    CYAN_CONCRETE(new MP(159, 9, true), new MP(251, 9)),
    CYAN_CONCRETE_POWDER(new MP(12, true), new MP(252, 9)),
    CYAN_DYE(new MP(351, 6)),
    CYAN_GLAZED_TERRACOTTA(new MP(159, 9, true), new MP(244)),
    CYAN_SHULKER_BOX(new MP(130, true), new MP(228)),
    CYAN_STAINED_GLASS(new MP(95, 9)),
    CYAN_STAINED_GLASS_PANE(new MP(160, 9)),
    CYAN_TERRACOTTA(new MP(159, 9)),
    CYAN_WALL_BANNER(new MP(425, 6)),
    CYAN_WOOL(new MP(35, 9)),
    DAMAGED_ANVIL(new MP(145, 2)),
    DANDELION(new MP(37)),
    DARK_OAK_BOAT(new MP(333), new MP(448)),
    DARK_OAK_BUTTON(new MP(143, true)),
    DARK_OAK_DOOR(new MP(431)),
    DARK_OAK_FENCE(new MP(191)),
    DARK_OAK_FENCE_GATE(new MP(186)),
    DARK_OAK_LEAVES(new MP(161, 1)),
    DARK_OAK_LOG(new MP(162, 1)),
    DARK_OAK_PLANKS(new MP(5, 5)),
    DARK_OAK_PRESSURE_PLATE(new MP(72, true)),
    DARK_OAK_SAPLING(new MP(6, 5)),
    DARK_OAK_SIGN(new MP(323)),
    DARK_OAK_SLAB(new MP(126, 5)),
    DARK_OAK_STAIRS(new MP(164)),
    DARK_OAK_TRAPDOOR(new MP(96, true)),
    DARK_OAK_WALL_SIGN(new MP(68)),
    DARK_OAK_WOOD(new MP(162, 1, true)),
    DARK_PRISMARINE(new MP(168, 2)),
    DARK_PRISMARINE_SLAB(new MP(44, 5, true)),
    DARK_PRISMARINE_STAIRS(new MP(109, true)),
    DAYLIGHT_DETECTOR(new MP(151)),
    DEAD_BRAIN_CORAL(new MP(0, true)),
    DEAD_BRAIN_CORAL_BLOCK(new MP(0, true)),
    DEAD_BRAIN_CORAL_FAN(new MP(0, true)),
    DEAD_BRAIN_CORAL_WALL_FAN(new MP(0, true)),
    DEAD_BUBBLE_CORAL(new MP(0, true)),
    DEAD_BUBBLE_CORAL_BLOCK(new MP(0, true)),
    DEAD_BUBBLE_CORAL_FAN(new MP(0, true)),
    DEAD_BUBBLE_CORAL_WALL_FAN(new MP(0, true)),
    DEAD_BUSH(new MP(32)),
    DEAD_FIRE_CORAL(new MP(0, true)),
    DEAD_FIRE_CORAL_BLOCK(new MP(0, true)),
    DEAD_FIRE_CORAL_FAN(new MP(0, true)),
    DEAD_FIRE_CORAL_WALL_FAN(new MP(0, true)),
    DEAD_HORN_CORAL(new MP(0, true)),
    DEAD_HORN_CORAL_BLOCK(new MP(0, true)),
    DEAD_HORN_CORAL_FAN(new MP(0, true)),
    DEAD_HORN_CORAL_WALL_FAN(new MP(0, true)),
    DEAD_TUBE_CORAL(new MP(0, true)),
    DEAD_TUBE_CORAL_BLOCK(new MP(0, true)),
    DEAD_TUBE_CORAL_FAN(new MP(0, true)),
    DEAD_TUBE_CORAL_WALL_FAN(new MP(0, true)),
    DEBUG_STICK(new MP(280, true)),
    DETECTOR_RAIL(new MP(28)),
    DIAMOND(new MP(264)),
    DIAMOND_AXE(new MP(279)),
    DIAMOND_BLOCK(new MP(57)),
    DIAMOND_BOOTS(new MP(313)),
    DIAMOND_CHESTPLATE(new MP(311)),
    DIAMOND_HELMET(new MP(310)),
    DIAMOND_HOE(new MP(293)),
    DIAMOND_HORSE_ARMOR(new MP(419)),
    DIAMOND_LEGGINGS(new MP(312)),
    DIAMOND_ORE(new MP(56)),
    DIAMOND_PICKAXE(new MP(278)),
    DIAMOND_SHOVEL(new MP(277)),
    DIAMOND_SWORD(new MP(276)),
    DIORITE(new MP(1, 3)),
    DIORITE_SLAB(new MP(44, 3, true)),
    DIORITE_STAIRS(new MP(67, true)),
    DIORITE_WALL(new MP(139, true)),
    DIRT(new MP(3)),
    DISPENSER(new MP(23)),
    DOLPHIN_SPAWN_EGG(new MP(383, 94, true)),
    DONKEY_SPAWN_EGG(new MP(383, 100, true)),
    DRAGON_BREATH(new MP(0, true), new MP(437)),
    DRAGON_EGG(new MP(122)),
    DRAGON_HEAD(new MP(0, true), new MP(397, 5)),
    DRAGON_WALL_HEAD(new MP(397, true), new MP(397, 5)),
    DRIED_KELP(new MP(392, true)),
    DRIED_KELP_BLOCK(new MP(0, true)),
    DROPPER(new MP(158)),
    DROWNED_SPAWN_EGG(new MP(383, 54, true)),
    EGG(new MP(344)),
    ELDER_GUARDIAN_SPAWN_EGG(new MP(383, 68, true)),
    ELYTRA(new MP(299, true), new MP(443)),
    EMERALD(new MP(388)),
    EMERALD_BLOCK(new MP(133)),
    EMERALD_ORE(new MP(129)),
    ENCHANTED_BOOK(new MP(403)),
    ENCHANTED_GOLDEN_APPLE(new MP(322, 1)),
    ENCHANTING_TABLE(new MP(116)),
    ENDERMAN_SPAWN_EGG(new MP(383, 58)),
    ENDERMITE_SPAWN_EGG(new MP(383, 67)),
    ENDER_CHEST(new MP(130)),
    ENDER_EYE(new MP(381)),
    ENDER_PEARL(new MP(368)),
    END_CRYSTAL(new MP(46, true), new MP(426)),
    END_GATEWAY(new MP(209)),
    END_PORTAL(new MP(119)),
    END_PORTAL_FRAME(new MP(120)),
    END_ROD(new MP(50, true), new MP(198)),
    END_STONE(new MP(121)),
    END_STONE_BRICKS(new MP(121, true), new MP(206)),
    END_STONE_BRICK_SLAB(new MP(44, 5, true)),
    END_STONE_BRICK_STAIRS(new MP(109, true)),
    END_STONE_BRICK_WALL(new MP(139, true)),
    EVOKER_SPAWN_EGG(new MP(383, 120, true)),
    EXPERIENCE_BOTTLE(new MP(384)),
    FARMLAND(new MP(60)),
    FEATHER(new MP(288)),
    FERMENTED_SPIDER_EYE(new MP(376)),
    FERN(new MP(31, 2)),
    FILLED_MAP(new MP(358)),
    FIRE,
    FIREWORK_ROCKET(new MP(401)),
    FIREWORK_STAR(new MP(402)),
    FIRE_CHARGE(new MP(385)),
    FIRE_CORAL(new MP(0, true)),
    FIRE_CORAL_BLOCK(new MP(0, true)),
    FIRE_CORAL_FAN(new MP(0, true)),
    FIRE_CORAL_WALL_FAN(new MP(0, true)),
    FISHING_ROD(new MP(346)),
    FLETCHING_TABLE(new MP(58, true)),
    FLINT(new MP(318)),
    FLINT_AND_STEEL(new MP(259)),
    FLOWER_BANNER_PATTERN(new MP(0, true)),
    FLOWER_POT(new MP(140)),
    FOX_SPAWN_EGG(new MP(383, 95, true)),
    FROSTED_ICE(new MP(212)),
    FURNACE(new MP(61)),
    FURNACE_MINECART(new MP(343)),
    GHAST_SPAWN_EGG(new MP(383, 56)),
    GHAST_TEAR(new MP(370)),
    GLASS(new MP(20)),
    GLASS_BOTTLE(new MP(374)),
    GLASS_PANE(new MP(102)),
    GLISTERING_MELON_SLICE(new MP(382)),
    GLOBE_BANNER_PATTERN(new MP(0, true)),
    GLOWSTONE(new MP(89)),
    GLOWSTONE_DUST(new MP(348)),
    GOLDEN_APPLE(new MP(322)),
    GOLDEN_AXE(new MP(286)),
    GOLDEN_BOOTS(new MP(317)),
    GOLDEN_CARROT(new MP(396)),
    GOLDEN_CHESTPLATE(new MP(315)),
    GOLDEN_HELMET(new MP(314)),
    GOLDEN_HOE(new MP(294)),
    GOLDEN_HORSE_ARMOR(new MP(418)),
    GOLDEN_LEGGINGS(new MP(316)),
    GOLDEN_PICKAXE(new MP(285)),
    GOLDEN_SHOVEL(new MP(284)),
    GOLDEN_SWORD(new MP(283)),
    GOLD_BLOCK(new MP(41)),
    GOLD_INGOT(new MP(266)),
    GOLD_NUGGET(new MP(371)),
    GOLD_ORE(new MP(14)),
    GRANITE(new MP(1, 1)),
    GRANITE_SLAB(new MP(44, 5, true)),
    GRANITE_STAIRS(new MP(109, true)),
    GRANITE_WALL(new MP(139, true)),
    GRASS(new MP(2)),
    GRASS_BLOCK(new MP(2)),
    DIRT_PATH(new MP(2, true), new MP(208)),
    GRAVEL(new MP(13)),
    GRAY_BANNER(new MP(425, 8)),
    GRAY_BED(new MP(355, true), new MP(355, 7)),
    GRAY_CARPET(new MP(171, 7)),
    GRAY_CONCRETE(new MP(159, 7, true), new MP(251, 7)),
    GRAY_CONCRETE_POWDER(new MP(12, true), new MP(252, 7)),
    GRAY_DYE(new MP(351, 8)),
    GRAY_GLAZED_TERRACOTTA(new MP(159, 7, true), new MP(242)),
    GRAY_SHULKER_BOX(new MP(130, true), new MP(226)),
    GRAY_STAINED_GLASS(new MP(95, 7)),
    GRAY_STAINED_GLASS_PANE(new MP(160, 7)),
    GRAY_TERRACOTTA(new MP(159, 7)),
    GRAY_WALL_BANNER(new MP(425, 8)),
    GRAY_WOOL(new MP(35, 7)),
    GREEN_BANNER(new MP(425, 2)),
    GREEN_BED(new MP(355, true), new MP(355, 13)),
    GREEN_CARPET(new MP(171, 13)),
    GREEN_CONCRETE(new MP(159, 13, true), new MP(251, 13)),
    GREEN_CONCRETE_POWDER(new MP(12, true), new MP(252, 13)),
    GREEN_DYE(new MP(351, 2)),
    GREEN_GLAZED_TERRACOTTA(new MP(159, 13, true), new MP(248)),
    GREEN_SHULKER_BOX(new MP(130, true), new MP(232)),
    GREEN_STAINED_GLASS(new MP(95, 13)),
    GREEN_STAINED_GLASS_PANE(new MP(160, 13)),
    GREEN_TERRACOTTA(new MP(159, 13)),
    GREEN_WALL_BANNER(new MP(425, 2)),
    GREEN_WOOL(new MP(35, 13)),
    GRINDSTONE(new MP(0, true)),
    GUARDIAN_SPAWN_EGG(new MP(383, 68)),
    GUNPOWDER(new MP(289)),
    HAY_BLOCK(new MP(170)),
    HEART_OF_THE_SEA(new MP(0, true)),
    HEAVY_WEIGHTED_PRESSURE_PLATE(new MP(148)),
    HONEYCOMB(new MP(0, true)),
    HONEYCOMB_BLOCK(new MP(0, true)),
    HONEY_BLOCK(new MP(0, true)),
    HONEY_BOTTLE(new MP(374, true)),
    HOPPER(new MP(154)),
    HOPPER_MINECART(new MP(408)),
    HORN_CORAL(new MP(0, true)),
    HORN_CORAL_BLOCK(new MP(0, true)),
    HORN_CORAL_FAN(new MP(0, true)),
    HORN_CORAL_WALL_FAN(new MP(0, true)),
    HORSE_SPAWN_EGG(new MP(383, 100)),
    HUSK_SPAWN_EGG(new MP(383, 54, true)),
    ICE(new MP(79)),
    INFESTED_CHISELED_STONE_BRICKS(new MP(97, 5)),
    INFESTED_COBBLESTONE(new MP(97, 1)),
    INFESTED_CRACKED_STONE_BRICKS(new MP(97, 4)),
    INFESTED_MOSSY_STONE_BRICKS(new MP(97, 3)),
    INFESTED_STONE(new MP(97)),
    INFESTED_STONE_BRICKS(new MP(97, 2)),
    INK_SAC(new MP(351)),
    IRON_AXE(new MP(258)),
    IRON_BARS(new MP(101)),
    IRON_BLOCK(new MP(42)),
    IRON_BOOTS(new MP(309)),
    IRON_CHESTPLATE(new MP(307)),
    IRON_DOOR(new MP(330)),
    IRON_HELMET(new MP(306)),
    IRON_HOE(new MP(292)),
    IRON_HORSE_ARMOR(new MP(417)),
    IRON_INGOT(new MP(265)),
    IRON_LEGGINGS(new MP(308)),
    IRON_NUGGET(new MP(0, true), new MP(452)),
    IRON_ORE(new MP(15)),
    IRON_PICKAXE(new MP(257)),
    IRON_SHOVEL(new MP(256)),
    IRON_SWORD(new MP(267)),
    IRON_TRAPDOOR(new MP(167)),
    ITEM_FRAME(new MP(389)),
    JACK_O_LANTERN(new MP(91)),
    JIGSAW(new MP(0, true)),
    JUKEBOX(new MP(84)),
    JUNGLE_BOAT(new MP(333), new MP(446)),
    JUNGLE_BUTTON(new MP(143, true)),
    JUNGLE_DOOR(new MP(429)),
    JUNGLE_FENCE(new MP(190)),
    JUNGLE_FENCE_GATE(new MP(185)),
    JUNGLE_LEAVES(new MP(18, 3)),
    JUNGLE_LOG(new MP(17, 3)),
    JUNGLE_PLANKS(new MP(5, 3)),
    JUNGLE_PRESSURE_PLATE(new MP(72, true)),
    JUNGLE_SAPLING(new MP(6, 3)),
    JUNGLE_SIGN(new MP(323)),
    JUNGLE_SLAB(new MP(126, 3)),
    JUNGLE_STAIRS(new MP(136)),
    JUNGLE_TRAPDOOR(new MP(96, true)),
    JUNGLE_WALL_SIGN(new MP(68)),
    JUNGLE_WOOD(new MP(17, 3, true)),
    KELP(new MP(295, true)),
    KELP_PLANT(new MP(83, true)),
    KNOWLEDGE_BOOK(new MP(340, true)),
    LADDER(new MP(65)),
    LANTERN(new MP(0, true)),
    LAPIS_BLOCK(new MP(22)),
    LAPIS_LAZULI(new MP(351, 4)),
    LAPIS_ORE(new MP(21)),
    LARGE_FERN(new MP(175, 3)),
    LAVA,
    LAVA_BUCKET(new MP(327)),
    LEAD(new MP(420)),
    LEATHER(new MP(334)),
    LEATHER_BOOTS(new MP(301)),
    LEATHER_CHESTPLATE(new MP(299)),
    LEATHER_HELMET(new MP(298)),
    LEATHER_HORSE_ARMOR(new MP(418, true)),
    LEATHER_LEGGINGS(new MP(300)),
    LECTERN(new MP(0, true)),
    LEVER(new MP(69)),
    LIGHT_BLUE_BANNER(new MP(425, 12)),
    LIGHT_BLUE_BED(new MP(355, true), new MP(355, 3)),
    LIGHT_BLUE_CARPET(new MP(171, 3)),
    LIGHT_BLUE_CONCRETE(new MP(159, 3, true), new MP(251, 3)),
    LIGHT_BLUE_CONCRETE_POWDER(new MP(12, true), new MP(252, 3)),
    LIGHT_BLUE_DYE(new MP(351, 12)),
    LIGHT_BLUE_GLAZED_TERRACOTTA(new MP(159, 3, true), new MP(238)),
    LIGHT_BLUE_SHULKER_BOX(new MP(130, true), new MP(222)),
    LIGHT_BLUE_STAINED_GLASS(new MP(95, 3)),
    LIGHT_BLUE_STAINED_GLASS_PANE(new MP(160, 3)),
    LIGHT_BLUE_TERRACOTTA(new MP(159, 3)),
    LIGHT_BLUE_WALL_BANNER(new MP(425, 12)),
    LIGHT_BLUE_WOOL(new MP(35, 3)),
    LIGHT_GRAY_BANNER(new MP(425, 7)),
    LIGHT_GRAY_BED(new MP(335, true), new MP(355, 8)),
    LIGHT_GRAY_CARPET(new MP(171, 8)),
    LIGHT_GRAY_CONCRETE(new MP(159, 8, true), new MP(251, 8)),
    LIGHT_GRAY_CONCRETE_POWDER(new MP(12, true), new MP(252, 8)),
    LIGHT_GRAY_DYE(new MP(351, 7)),
    LIGHT_GRAY_GLAZED_TERRACOTTA(new MP(159, 8, true), new MP(243)),
    LIGHT_GRAY_SHULKER_BOX(new MP(130, true), new MP(227)),
    LIGHT_GRAY_STAINED_GLASS(new MP(95, 8)),
    LIGHT_GRAY_STAINED_GLASS_PANE(new MP(160, 8)),
    LIGHT_GRAY_TERRACOTTA(new MP(159, 8)),
    LIGHT_GRAY_WALL_BANNER(new MP(425, 7)),
    LIGHT_GRAY_WOOL(new MP(35, 8)),
    LIGHT_WEIGHTED_PRESSURE_PLATE(new MP(147)),
    LILAC(new MP(175, 1)),
    LILY_OF_THE_VALLEY(new MP(175, 1, true)),
    LILY_PAD(new MP(111)),
    LIME_BANNER(new MP(425, 10)),
    LIME_BED(new MP(355, true), new MP(355, 5)),
    LIME_CARPET(new MP(171, 5)),
    LIME_CONCRETE(new MP(159, 10, true), new MP(251, 10)),
    LIME_CONCRETE_POWDER(new MP(12, true), new MP(252, 5)),
    LIME_DYE(new MP(351, 10)),
    LIME_GLAZED_TERRACOTTA(new MP(159, 5, true), new MP(240)),
    LIME_SHULKER_BOX(new MP(130, true), new MP(224)),
    LIME_STAINED_GLASS(new MP(95, 5)),
    LIME_STAINED_GLASS_PANE(new MP(160, 5)),
    LIME_TERRACOTTA(new MP(159, 5)),
    LIME_WALL_BANNER(new MP(425, 10)),
    LIME_WOOL(new MP(35, 5)),
    LINGERING_POTION(new MP(373, true), new MP(441)),
    LLAMA_SPAWN_EGG(new MP(383, 100, true), new MP(383, 103)),
    LOOM(new MP(0, true)),
    MAGENTA_BANNER(new MP(425, 13)),
    MAGENTA_BED(new MP(355, true), new MP(355, 2)),
    MAGENTA_CARPET(new MP(171, 2)),
    MAGENTA_CONCRETE(new MP(159, 2, true), new MP(251, 2)),
    MAGENTA_CONCRETE_POWDER(new MP(12, true), new MP(252, 2)),
    MAGENTA_DYE(new MP(351, 13)),
    MAGENTA_GLAZED_TERRACOTTA(new MP(159, 2, true), new MP(237)),
    MAGENTA_SHULKER_BOX(new MP(130, true), new MP(221)),
    MAGENTA_STAINED_GLASS(new MP(95, 2)),
    MAGENTA_STAINED_GLASS_PANE(new MP(160, 2)),
    MAGENTA_TERRACOTTA(new MP(159, 2)),
    MAGENTA_WALL_BANNER(new MP(425, 13)),
    MAGENTA_WOOL(new MP(35, 2)),
    MAGMA_BLOCK(new MP(0, true), new MP(213)),
    MAGMA_CREAM(new MP(378)),
    MAGMA_CUBE_SPAWN_EGG(new MP(383, 62)),
    MAP(new MP(395)),
    MELON(new MP(360), new MP(103)),
    MELON_SEEDS(new MP(362)),
    MELON_SLICE(new MP(360)),
    MELON_STEM(new MP(105)),
    MILK_BUCKET(new MP(335)),
    MINECART(new MP(328)),
    MOJANG_BANNER_PATTERN(new MP(0, true)),
    MOOSHROOM_SPAWN_EGG(new MP(383, 96)),
    MOSSY_COBBLESTONE(new MP(48)),
    MOSSY_COBBLESTONE_SLAB(new MP(44, 3, true)),
    MOSSY_COBBLESTONE_STAIRS(new MP(67, true)),
    MOSSY_COBBLESTONE_WALL(new MP(139, 1, true)),
    MOSSY_STONE_BRICKS(new MP(98, 1)),
    MOSSY_STONE_BRICK_SLAB(new MP(44, 5, true)),
    MOSSY_STONE_BRICK_STAIRS(new MP(109, true)),
    MOSSY_STONE_BRICK_WALL(new MP(139, true)),
    MOVING_PISTON(new MP(0, true)),
    MULE_SPAWN_EGG(new MP(383, 100, true), new MP(383, 32)),
    MUSHROOM_STEM(new MP(99)),
    MUSHROOM_STEW(new MP(282)),
    MUSIC_DISC_11(new MP(2266)),
    MUSIC_DISC_13(new MP(2256)),
    MUSIC_DISC_BLOCKS(new MP(2258)),
    MUSIC_DISC_CAT(new MP(2257)),
    MUSIC_DISC_CHIRP(new MP(2259)),
    MUSIC_DISC_FAR(new MP(2260)),
    MUSIC_DISC_MALL(new MP(2261)),
    MUSIC_DISC_MELLOHI(new MP(2262)),
    MUSIC_DISC_STAL(new MP(2263)),
    MUSIC_DISC_STRAD(new MP(2264)),
    MUSIC_DISC_WAIT(new MP(2267)),
    MUSIC_DISC_WARD(new MP(2265)),
    MUTTON(new MP(423)),
    MYCELIUM(new MP(110)),
    NAME_TAG(new MP(421)),
    NAUTILUS_SHELL(new MP(0, true)),
    NETHERRACK(new MP(87)),
    NETHER_BRICK(new MP(405)),
    NETHER_BRICKS(new MP(112)),
    NETHER_BRICK_FENCE(new MP(113)),
    NETHER_BRICK_SLAB(new MP(44, 6)),
    NETHER_BRICK_STAIRS(new MP(114)),
    NETHER_BRICK_WALL(new MP(139, true)),
    NETHER_PORTAL(new MP(90)),
    NETHER_QUARTZ_ORE(new MP(153)),
    NETHER_STAR(new MP(399)),
    NETHER_WART(new MP(372)),
    NETHER_WART_BLOCK(new MP(0, true), new MP(214)),
    NOTE_BLOCK(new MP(25)),
    OAK_BOAT(new MP(333)),
    OAK_BUTTON(new MP(143)),
    OAK_DOOR(new MP(324)),
    OAK_FENCE(new MP(85)),
    OAK_FENCE_GATE(new MP(107)),
    OAK_LEAVES(new MP(18)),
    OAK_LOG(new MP(17)),
    OAK_PLANKS(new MP(5)),
    OAK_PRESSURE_PLATE(new MP(72)),
    OAK_SAPLING(new MP(6)),
    OAK_SIGN(new MP(323)),
    OAK_SLAB(new MP(126)),
    OAK_STAIRS(new MP(53)),
    OAK_TRAPDOOR(new MP(96)),
    OAK_WALL_SIGN(new MP(68)),
    OAK_WOOD(new MP(17, true)),
    OBSERVER(new MP(0, true), new MP(218)),
    OBSIDIAN(new MP(49)),
    OCELOT_SPAWN_EGG(new MP(383, 98)),
    ORANGE_BANNER(new MP(425, 14)),
    ORANGE_BED(new MP(355, true), new MP(355, 1)),
    ORANGE_CARPET(new MP(171, 1)),
    ORANGE_CONCRETE(new MP(159, 1, true), new MP(251, 1)),
    ORANGE_CONCRETE_POWDER(new MP(12, true), new MP(252, 1)),
    ORANGE_DYE(new MP(351, 14)),
    ORANGE_GLAZED_TERRACOTTA(new MP(159, 1, true), new MP(236)),
    ORANGE_SHULKER_BOX(new MP(130, true), new MP(220)),
    ORANGE_STAINED_GLASS(new MP(95, 1)),
    ORANGE_STAINED_GLASS_PANE(new MP(160, 1)),
    ORANGE_TERRACOTTA(new MP(159, 1)),
    ORANGE_TULIP(new MP(38, 5)),
    ORANGE_WALL_BANNER(new MP(425, 14)),
    ORANGE_WOOL(new MP(35, 1)),
    OXEYE_DAISY(new MP(38, 8)),
    PACKED_ICE(new MP(174)),
    PAINTING(new MP(321)),
    PANDA_SPAWN_EGG(new MP(0, true), new MP(383, 102)),
    PAPER(new MP(339)),
    PARROT_SPAWN_EGG(new MP(383, 65, true), new MP(383, 105)),
    PEONY(new MP(175, 5)),
    PETRIFIED_OAK_SLAB(new MP(126, true)),
    PHANTOM_MEMBRANE(new MP(0, true)),
    PHANTOM_SPAWN_EGG(new MP(383, 65, true)),
    PIG_SPAWN_EGG(new MP(383, 90)),
    PILLAGER_SPAWN_EGG(new MP(383, 120, true)),
    PINK_BANNER(new MP(425, 9)),
    PINK_BED(new MP(355, true), new MP(355, 6)),
    PINK_CARPET(new MP(171, 6)),
    PINK_CONCRETE(new MP(159, 6, true), new MP(251, 6)),
    PINK_CONCRETE_POWDER(new MP(12, true), new MP(252, 6)),
    PINK_DYE(new MP(351, 9)),
    PINK_GLAZED_TERRACOTTA(new MP(159, 6, true), new MP(241)),
    PINK_SHULKER_BOX(new MP(130, true), new MP(225)),
    PINK_STAINED_GLASS(new MP(95, 6)),
    PINK_STAINED_GLASS_PANE(new MP(160, 6)),
    PINK_TERRACOTTA(new MP(159, 6)),
    PINK_TULIP(new MP(38, 7)),
    PINK_WALL_BANNER(new MP(425, 9)),
    PINK_WOOL(new MP(35, 6)),
    PISTON(new MP(33)),
    PISTON_HEAD(new MP(34, 2)),
    PLAYER_HEAD(new MP(397, 3)),
    PLAYER_WALL_HEAD(new MP(397, 3)),
    PODZOL(new MP(3, 2)),
    POISONOUS_POTATO(new MP(394)),
    POLAR_BEAR_SPAWN_EGG(new MP(383, 101, true), new MP(383, 102)),
    POLISHED_ANDESITE(new MP(1, 6)),
    POLISHED_ANDESITE_SLAB(new MP(44, true)),
    POLISHED_ANDESITE_STAIRS(new MP(67, true)),
    POLISHED_DIORITE(new MP(1, 4)),
    POLISHED_DIORITE_SLAB(new MP(44, true)),
    POLISHED_DIORITE_STAIRS(new MP(67, true)),
    POLISHED_GRANITE(new MP(1, 2)),
    POLISHED_GRANITE_SLAB(new MP(44, true)),
    POLISHED_GRANITE_STAIRS(new MP(67, true)),
    POPPED_CHORUS_FRUIT(new MP(0, true), new MP(433)),
    POPPY(new MP(38)),
    PORKCHOP(new MP(319)),
    POTATO(new MP(392)),
    POTATOES(new MP(142)),
    POTION(new MP(373)),
    POTTED_ACACIA_SAPLING(new MP(390, true)),
    POTTED_ALLIUM(new MP(390, true)),
    POTTED_AZURE_BLUET(new MP(390, true)),
    POTTED_BAMBOO(new MP(390, true)),
    POTTED_BIRCH_SAPLING(new MP(390, true)),
    POTTED_BLUE_ORCHID(new MP(390, true)),
    POTTED_BROWN_MUSHROOM(new MP(390, true)),
    POTTED_CACTUS(new MP(390, true)),
    POTTED_CORNFLOWER(new MP(390, true)),
    POTTED_DANDELION(new MP(390, true)),
    POTTED_DARK_OAK_SAPLING(new MP(390, true)),
    POTTED_DEAD_BUSH(new MP(390, true)),
    POTTED_FERN(new MP(390, true)),
    POTTED_JUNGLE_SAPLING(new MP(390, true)),
    POTTED_LILY_OF_THE_VALLEY(new MP(390, true)),
    POTTED_OAK_SAPLING(new MP(390, true)),
    POTTED_ORANGE_TULIP(new MP(390, true)),
    POTTED_OXEYE_DAISY(new MP(390, true)),
    POTTED_PINK_TULIP(new MP(390, true)),
    POTTED_POPPY(new MP(390, true)),
    POTTED_RED_MUSHROOM(new MP(390, true)),
    POTTED_RED_TULIP(new MP(390, true)),
    POTTED_SPRUCE_SAPLING(new MP(390, true)),
    POTTED_WHITE_TULIP(new MP(390, true)),
    POTTED_WITHER_ROSE(new MP(390, true)),
    POWERED_RAIL(new MP(27)),
    PRISMARINE(new MP(168)),
    PRISMARINE_BRICKS(new MP(168, 1)),
    PRISMARINE_BRICK_SLAB(new MP(44, 5, true)),
    PRISMARINE_BRICK_STAIRS(new MP(109, true)),
    PRISMARINE_CRYSTALS(new MP(410)),
    PRISMARINE_SHARD(new MP(409)),
    PRISMARINE_SLAB(new MP(44, 5, true)),
    PRISMARINE_STAIRS(new MP(109, true)),
    PRISMARINE_WALL(new MP(139, true)),
    PUFFERFISH(new MP(349, 3)),
    PUFFERFISH_BUCKET(new MP(326, true)),
    PUFFERFISH_SPAWN_EGG(new MP(383, 94, true)),
    PUMPKIN(new MP(86)),
    PUMPKIN_PIE(new MP(400)),
    PUMPKIN_SEEDS(new MP(361)),
    PUMPKIN_STEM(new MP(104)),
    PURPLE_BANNER(new MP(425, 5)),
    PURPLE_BED(new MP(355, true), new MP(355, 10)),
    PURPLE_CARPET(new MP(171, 10)),
    PURPLE_CONCRETE(new MP(159, 10, true), new MP(251, 10)),
    PURPLE_CONCRETE_POWDER(new MP(12, true), new MP(252, 10)),
    PURPLE_DYE(new MP(351, 5)),
    PURPLE_GLAZED_TERRACOTTA(new MP(159, 10, true), new MP(245)),
    PURPLE_SHULKER_BOX(new MP(130, true), new MP(229)),
    PURPLE_STAINED_GLASS(new MP(95, 10)),
    PURPLE_STAINED_GLASS_PANE(new MP(160, 10)),
    PURPLE_TERRACOTTA(new MP(159, 10)),
    PURPLE_WALL_BANNER(new MP(425, 5)),
    PURPLE_WOOL(new MP(35, 10)),
    PURPUR_BLOCK(new MP(155, true), new MP(201)),
    PURPUR_PILLAR(new MP(155, 2, true), new MP(202)),
    PURPUR_SLAB(new MP(44, 7, true), new MP(205)),
    PURPUR_STAIRS(new MP(156, true), new MP(203)),
    QUARTZ(new MP(406)),
    QUARTZ_BLOCK(new MP(155)),
    QUARTZ_PILLAR(new MP(155, 2)),
    QUARTZ_SLAB(new MP(44, 7)),
    QUARTZ_STAIRS(new MP(156)),
    RABBIT(new MP(411)),
    RABBIT_FOOT(new MP(414)),
    RABBIT_HIDE(new MP(415)),
    RABBIT_SPAWN_EGG(new MP(383, 101)),
    RABBIT_STEW(new MP(413)),
    RAIL(new MP(66)),
    RAVAGER_SPAWN_EGG(new MP(383, 62, true)),
    REDSTONE(new MP(331)),
    REDSTONE_BLOCK(new MP(152)),
    REDSTONE_LAMP(new MP(123)),
    REDSTONE_ORE(new MP(73)),
    REDSTONE_TORCH(new MP(76)),
    REDSTONE_WALL_TORCH(new MP(76)),
    REDSTONE_WIRE(new MP(331)),
    RED_BANNER(new MP(425, 1)),
    RED_BED(new MP(355), new MP(355, 14)),
    RED_CARPET(new MP(171, 14)),
    RED_CONCRETE(new MP(159, 14, true), new MP(251, 14)),
    RED_CONCRETE_POWDER(new MP(12, true), new MP(252, 14)),
    RED_DYE(new MP(351, 1)),
    RED_GLAZED_TERRACOTTA(new MP(159, 14, true), new MP(249)),
    RED_MUSHROOM(new MP(40)),
    RED_MUSHROOM_BLOCK(new MP(100)),
    RED_NETHER_BRICKS(new MP(112, true), new MP(215)),
    RED_NETHER_BRICK_SLAB(new MP(44, 6, true)),
    RED_NETHER_BRICK_STAIRS(new MP(114, true)),
    RED_NETHER_BRICK_WALL(new MP(139, true)),
    RED_SAND(new MP(12, 1)),
    RED_SANDSTONE(new MP(179)),
    RED_SANDSTONE_SLAB(new MP(182)),
    RED_SANDSTONE_STAIRS(new MP(180)),
    RED_SANDSTONE_WALL(new MP(139, true)),
    RED_SHULKER_BOX(new MP(130, true), new MP(233)),
    RED_STAINED_GLASS(new MP(95, 14)),
    RED_STAINED_GLASS_PANE(new MP(160, 14)),
    RED_TERRACOTTA(new MP(159, 14)),
    RED_TULIP(new MP(38, 4)),
    RED_WALL_BANNER(new MP(425, 1)),
    RED_WOOL(new MP(35, 14)),
    REPEATER(new MP(356)),
    REPEATING_COMMAND_BLOCK(new MP(137, true)),
    ROSE_BUSH(new MP(175, 4)),
    ROTTEN_FLESH(new MP(367)),
    SADDLE(new MP(329)),
    SALMON(new MP(349, 1)),
    SALMON_BUCKET(new MP(326, true)),
    SALMON_SPAWN_EGG(new MP(383, 94, true)),
    SAND(new MP(12)),
    SANDSTONE(new MP(24)),
    SANDSTONE_SLAB(new MP(44, 1)),
    SANDSTONE_STAIRS(new MP(128)),
    SANDSTONE_WALL(new MP(139, true)),
    SCAFFOLDING(new MP(65, true)),
    SCUTE(new MP(0, true)),
    SEAGRASS(new MP(0, true)),
    SEA_LANTERN(new MP(169)),
    SEA_PICKLE(new MP(169, true)),
    SHEARS(new MP(359)),
    SHEEP_SPAWN_EGG(new MP(383, 91)),
    SHIELD(new MP(0, true), new MP(442)),
    SHULKER_BOX(new MP(130, true), new MP(219)),
    SHULKER_SHELL(new MP(0, true), new MP(450)),
    SHULKER_SPAWN_EGG(new MP(383, 60, true), new MP(383, 69)),
    SILVERFISH_SPAWN_EGG(new MP(383, 60)),
    SKELETON_HORSE_SPAWN_EGG(new MP(383, 100, true)),
    SKELETON_SKULL(new MP(397)),
    SKELETON_SPAWN_EGG(new MP(383, 51)),
    SKELETON_WALL_SKULL(new MP(397)),
    SKULL_BANNER_PATTERN(new MP(0, true)),
    SLIME_BALL(new MP(341)),
    SLIME_BLOCK(new MP(165)),
    SLIME_SPAWN_EGG(new MP(383, 55)),
    SMITHING_TABLE(new MP(0, true)),
    SMOKER(new MP(61, true)),
    SMOOTH_QUARTZ(new MP(155, true)),
    SMOOTH_QUARTZ_SLAB(new MP(44, 7, true)),
    SMOOTH_QUARTZ_STAIRS(new MP(156, true)),
    SMOOTH_RED_SANDSTONE(new MP(179, 2)),
    SMOOTH_RED_SANDSTONE_SLAB(new MP(182, true)),
    SMOOTH_RED_SANDSTONE_STAIRS(new MP(180, true)),
    SMOOTH_SANDSTONE(new MP(24, 2)),
    SMOOTH_SANDSTONE_SLAB(new MP(44, 1, true)),
    SMOOTH_SANDSTONE_STAIRS(new MP(128, true)),
    SMOOTH_STONE(new MP(1, true)),
    SMOOTH_STONE_SLAB(new MP(44, true)),
    SNOW(new MP(78)),
    SNOWBALL(new MP(332)),
    SNOW_BLOCK(new MP(80)),
    SOUL_SAND(new MP(88)),
    SPAWNER(new MP(52)),
    SPECTRAL_ARROW(new MP(262, true), new MP(439)),
    SPIDER_EYE(new MP(375)),
    SPIDER_SPAWN_EGG(new MP(383, 52)),
    SPLASH_POTION(new MP(373, true), new MP(438)),
    SPONGE(new MP(19)),
    SPRUCE_BOAT(new MP(333, true), new MP(444)),
    SPRUCE_BUTTON(new MP(143, true)),
    SPRUCE_DOOR(new MP(427)),
    SPRUCE_FENCE(new MP(188)),
    SPRUCE_FENCE_GATE(new MP(183)),
    SPRUCE_LEAVES(new MP(18, 1)),
    SPRUCE_LOG(new MP(17, 1)),
    SPRUCE_PLANKS(new MP(5, 1)),
    SPRUCE_PRESSURE_PLATE(new MP(72, true)),
    SPRUCE_SAPLING(new MP(6, 1)),
    SPRUCE_SIGN(new MP(323)),
    SPRUCE_SLAB(new MP(126, 1)),
    SPRUCE_STAIRS(new MP(134)),
    SPRUCE_TRAPDOOR(new MP(96, true)),
    SPRUCE_WALL_SIGN(new MP(68)),
    SPRUCE_WOOD(new MP(17, 1, true)),
    SQUID_SPAWN_EGG(new MP(383, 94)),
    STATIONARY_LAVA(new MP("STATIONARY_LAVA"), new MP("LEGACY_STATIONARY_LAVA"), true, true),
    STATIONARY_WATER(new MP("STATIONARY_WATER"), new MP("LEGACY_STATIONARY_WATER"), true, true),
    STICK(new MP(280)),
    STICKY_PISTON(new MP(29)),
    STONE(new MP(1)),
    STONECUTTER(new MP(0, true)),
    STONE_AXE(new MP(275)),
    STONE_BRICKS(new MP(98)),
    STONE_BRICK_SLAB(new MP(44, 5)),
    STONE_BRICK_STAIRS(new MP(109)),
    STONE_BRICK_WALL(new MP(139, true)),
    STONE_BUTTON(new MP(77)),
    STONE_HOE(new MP(291)),
    STONE_PICKAXE(new MP(274)),
    STONE_PRESSURE_PLATE(new MP(70)),
    STONE_SHOVEL(new MP(273)),
    STONE_SLAB(new MP(44)),
    STONE_STAIRS(new MP(67, true)),
    STONE_SWORD(new MP(272)),
    STRAY_SPAWN_EGG(new MP(383, 51, true)),
    STRING(new MP(287)),
    STRIPPED_ACACIA_LOG(new MP(162, true)),
    STRIPPED_ACACIA_WOOD(new MP(5, 4, true)),
    STRIPPED_BIRCH_LOG(new MP(17, 2, true)),
    STRIPPED_BIRCH_WOOD(new MP(5, 2, true)),
    STRIPPED_DARK_OAK_LOG(new MP(162, 1, true)),
    STRIPPED_DARK_OAK_WOOD(new MP(5, 5, true)),
    STRIPPED_JUNGLE_LOG(new MP(17, 3, true)),
    STRIPPED_JUNGLE_WOOD(new MP(5, 3, true)),
    STRIPPED_OAK_LOG(new MP(17, true)),
    STRIPPED_OAK_WOOD(new MP(5, true)),
    STRIPPED_SPRUCE_LOG(new MP(17, 1, true)),
    STRIPPED_SPRUCE_WOOD(new MP(5, 1, true)),
    STRUCTURE_BLOCK(new MP(0, true)),
    STRUCTURE_VOID(new MP(0, true)),
    SUGAR(new MP(353)),
    SUGAR_CANE(new MP(338)),
    SUNFLOWER(new MP(175)),
    SUSPICIOUS_STEW(new MP(349, 3, true)),
    SWEET_BERRIES(new MP(0, true), new MP(434)),
    SWEET_BERRY_BUSH(new MP(0, true)),
    TALL_GRASS(new MP(31, 1)),
    TALL_SEAGRASS(new MP(0, true)),
    TERRACOTTA(new MP(172)),
    TIPPED_ARROW(new MP(262, true), new MP(440)),
    TNT(new MP(46)),
    TNT_MINECART(new MP(407)),
    TORCH(new MP(50)),
    TOTEM_OF_UNDYING(new MP(0, true), new MP(449)),
    TRADER_LLAMA_SPAWN_EGG(new MP(0, true), new MP(383, 103)),
    TRAPPED_CHEST(new MP(146)),
    TRIDENT(new MP(0, true)),
    TRIPWIRE(new MP(131)),
    TRIPWIRE_HOOK(new MP(131)),
    TROPICAL_FISH(new MP(349)),
    TROPICAL_FISH_BUCKET(new MP(326, true)),
    TROPICAL_FISH_SPAWN_EGG(new MP(383, 94, true)),
    TUBE_CORAL(new MP(175, 2, true)),
    TUBE_CORAL_BLOCK(new MP(175, 2, true)),
    TUBE_CORAL_FAN(new MP(175, 2, true)),
    TUBE_CORAL_WALL_FAN(new MP(175, 2, true)),
    TURTLE_EGG(new MP(0, true)),
    TURTLE_HELMET(new MP(298, true)),
    TURTLE_SPAWN_EGG(new MP(383, 94, true)),
    VEX_SPAWN_EGG(new MP(383, 54, true)),
    VILLAGER_SPAWN_EGG(new MP(383, 120)),
    VINDICATOR_SPAWN_EGG(new MP(383, 54, true)),
    VINE(new MP(106)),
    VOID_AIR(new MP(0)),
    WALL_TORCH(new MP(50, 4)),
    WANDERING_TRADER_SPAWN_EGG(new MP(383, 120, true)),
    WATER,
    WATER_BUCKET(new MP(326)),
    WET_SPONGE(new MP(19, 1)),
    WHEAT(new MP(296)),
    WHEAT_SEEDS(new MP(295)),
    WHITE_BANNER(new MP(425, 15)),
    WHITE_BED(new MP(355, true), new MP(335)),
    WHITE_CARPET(new MP(171)),
    WHITE_CONCRETE(new MP(159, true)),
    WHITE_CONCRETE_POWDER(new MP(12, true), new MP(252)),
    WHITE_DYE(new MP(351, 15)),
    WHITE_GLAZED_TERRACOTTA(new MP(159, true), new MP(235)),
    WHITE_SHULKER_BOX(new MP(130, true)),
    WHITE_STAINED_GLASS(new MP(95)),
    WHITE_STAINED_GLASS_PANE(new MP(160)),
    WHITE_TERRACOTTA(new MP(159)),
    WHITE_TULIP(new MP(38, 6)),
    WHITE_WALL_BANNER(new MP(425, 15)),
    WHITE_WOOL(new MP(35)),
    WITCH_SPAWN_EGG(new MP(383, 66)),
    WITHER_ROSE(new MP(38, 4, true)),
    WITHER_SKELETON_SKULL(new MP(397, 1)),
    WITHER_SKELETON_SPAWN_EGG(new MP(383, 51, true)),
    WITHER_SKELETON_WALL_SKULL(new MP(397, 1)),
    WOLF_SPAWN_EGG(new MP(383, 95)),
    WOODEN_AXE(new MP(271)),
    WOODEN_HOE(new MP(290)),
    WOODEN_PICKAXE(new MP(270)),
    WOODEN_SHOVEL(new MP(269)),
    WOODEN_SWORD(new MP(268)),
    WRITABLE_BOOK(new MP(386)),
    WRITTEN_BOOK(new MP(387)),
    YELLOW_BANNER(new MP(425, 11)),
    YELLOW_BED(new MP(355, true), new MP(355, 4)),
    YELLOW_CARPET(new MP(171, 4)),
    YELLOW_CONCRETE(new MP(159, 4, true), new MP(251, 4)),
    YELLOW_CONCRETE_POWDER(new MP(12, true), new MP(252, 4)),
    YELLOW_DYE(new MP(351, 11)),
    YELLOW_GLAZED_TERRACOTTA(new MP(159, 4, true), new MP(239)),
    YELLOW_SHULKER_BOX(new MP(130, true), new MP(223)),
    YELLOW_STAINED_GLASS(new MP(95, 4)),
    YELLOW_STAINED_GLASS_PANE(new MP(160, 4)),
    YELLOW_TERRACOTTA(new MP(159, 4)),
    YELLOW_WALL_BANNER(new MP(425, 11)),
    YELLOW_WOOL(new MP(35, 4)),
    ZOMBIE_HEAD(new MP(397, 2)),
    ZOMBIE_HORSE_SPAWN_EGG(new MP(383, 29)),
    ZOMBIE_SPAWN_EGG(new MP(383, 54)),
    ZOMBIE_VILLAGER_SPAWN_EGG(new MP(383, 27)),
    ZOMBIE_WALL_HEAD(new MP(397, 2)),
    CRIMSON_NYLIUM(NETHERRACK, true),
    WARPED_NYLIUM(NETHERRACK, true),
    CRIMSON_PLANKS(OAK_WOOD, true),
    WARPED_PLANKS(DARK_OAK_WOOD, true),
    NETHER_GOLD_ORE(NETHER_QUARTZ_ORE, true),
    CRIMSON_STEM(OAK_LOG, true),
    WARPED_STEM(DARK_OAK_LOG, true),
    STRIPPED_CRIMSON_STEM(STRIPPED_OAK_LOG, true),
    STRIPPED_WARPED_STEM(STRIPPED_DARK_OAK_LOG, true),
    STRIPPED_CRIMSON_HYPHAE(STRIPPED_OAK_WOOD, true),
    STRIPPED_WARPED_HYPHAE(STRIPPED_DARK_OAK_WOOD, true),
    CRIMSON_HYPHAE(OAK_WOOD, true),
    WARPED_HYPHAE(DARK_OAK_WOOD, true),
    CRIMSON_FUNGUS(NETHER_WART, true),
    WARPED_FUNGUS(NETHER_WART, true),
    CRIMSON_ROOTS(NETHER_WART, true),
    WARPED_ROOTS(NETHER_WART, true),
    NETHER_SPROUTS(AIR, true),
    WEEPING_VINES(VINE, true),
    TWISTING_VINES(VINE, true),
    CRIMSON_SLAB(VINE, true),
    WARPED_SLAB(NETHER_BRICK_SLAB, true),
    CRIMSON_PRESSURE_PLATE(OAK_PRESSURE_PLATE, true),
    WARPED_PRESSURE_PLATE(DARK_OAK_PRESSURE_PLATE, true),
    POLISHED_BLACKSTONE_PRESSURE_PLATE(OAK_PRESSURE_PLATE, true),
    CRIMSON_FENCE(OAK_FENCE, true),
    WARPED_FENCE(DARK_OAK_FENCE, true),
    SOUL_SOIL(SOUL_SAND, true),
    BASALT(COBBLESTONE, true),
    POLISHED_BASALT(STONE_BRICKS, true),
    SOUL_TORCH(TORCH, true),
    CRIMSON_TRAPDOOR(OAK_TRAPDOOR, true),
    WARPED_TRAPDOOR(DARK_OAK_TRAPDOOR, true),
    CHAIN(IRON_BARS, true),
    CRIMSON_FENCE_GATE(OAK_FENCE_GATE, true),
    WARPED_FENCE_GATE(DARK_OAK_FENCE_GATE, true),
    CRACKED_NETHER_BRICKS(NETHER_BRICKS, true),
    CHISELED_NETHER_BRICKS(NETHER_BRICKS, true),
    CRIMSON_STAIRS(OAK_STAIRS, true),
    WARPED_STAIRS(DARK_OAK_STAIRS, true),
    BLACKSTONE_WALL(COBBLESTONE_WALL, true),
    POLISHED_BLACKSTONE_WALL(COBBLESTONE_WALL, true),
    POLISHED_BLACKSTONE_BRICK_WALL(COBBLESTONE_WALL, true),
    CRIMSON_BUTTON(OAK_BUTTON, true),
    WARPED_BUTTON(DARK_OAK_BUTTON, true),
    POLISHED_BLACKSTONE_BUTTON(STONE_BUTTON, true),
    QUARTZ_BRICKS(QUARTZ_BLOCK, true),
    WARPED_WART_BLOCK(AIR, true),
    CRIMSON_DOOR(OAK_DOOR, true),
    WARPED_DOOR(DARK_OAK_DOOR, true),
    NETHERITE_INGOT(DIAMOND, true),
    NETHERITE_SCRAP(AIR, true),
    NETHERITE_SWORD(DIAMOND_SWORD, true),
    NETHERITE_SHOVEL(DIAMOND_SHOVEL, true),
    NETHERITE_PICKAXE(DIAMOND_PICKAXE, true),
    NETHERITE_AXE(DIAMOND_AXE, true),
    NETHERITE_HOE(DIAMOND_HOE, true),
    NETHERITE_HELMET(DIAMOND_HELMET, true),
    NETHERITE_CHESTPLATE(DIAMOND_CHESTPLATE, true),
    NETHERITE_LEGGINGS(DIAMOND_LEGGINGS, true),
    NETHERITE_BOOTS(DIAMOND_BOOTS, true),
    CRIMSON_SIGN(OAK_SIGN, true),
    WARPED_SIGN(DARK_OAK_SIGN, true),
    HOGLIN_SPAWN_EGG(AIR, true),
    PIGLIN_SPAWN_EGG(new MP(383, 57)),
    PIGLIN_BRUTE_SPAWN_EGG(new MP(383, 57)),
    STRIDER_SPAWN_EGG(AIR, true),
    ZOGLIN_SPAWN_EGG(AIR, true),
    ZOMBIFIED_PIGLIN_SPAWN_EGG(new MP(383, 57)),
    WARPED_FUNGUS_ON_A_STICK(CARROT_ON_A_STICK, true),
    MUSIC_DISC_PIGSTEP(MUSIC_DISC_13, true),
    PIGLIN_BANNER_PATTERN(CREEPER_BANNER_PATTERN, true),
    SOUL_LANTERN(SEA_LANTERN, true),
    SOUL_CAMPFIRE(AIR, true),
    SHROOMLIGHT(GLOWSTONE, true),
    LODESTONE(CHISELED_STONE_BRICKS, true),
    NETHERITE_BLOCK(DIAMOND_BLOCK, true),
    ANCIENT_DEBRIS(GRANITE, true),
    TARGET(AIR, true),
    CRYING_OBSIDIAN(OBSIDIAN, true),
    BLACKSTONE(COBBLESTONE, true),
    BLACKSTONE_SLAB(COBBLESTONE_SLAB, true),
    BLACKSTONE_STAIRS(COBBLESTONE_STAIRS, true),
    GILDED_BLACKSTONE(MOSSY_COBBLESTONE, true),
    POLISHED_BLACKSTONE(POLISHED_DIORITE, true),
    POLISHED_BLACKSTONE_SLAB(POLISHED_DIORITE_SLAB, true),
    POLISHED_BLACKSTONE_STAIRS(POLISHED_DIORITE_STAIRS, true),
    CHISELED_POLISHED_BLACKSTONE(POLISHED_DIORITE, true),
    POLISHED_BLACKSTONE_BRICKS(POLISHED_DIORITE, true),
    POLISHED_BLACKSTONE_BRICK_SLAB(POLISHED_DIORITE_SLAB, true),
    POLISHED_BLACKSTONE_BRICK_STAIRS(POLISHED_DIORITE_SLAB, true),
    CRACKED_POLISHED_BLACKSTONE_BRICKS(POLISHED_DIORITE_SLAB, true),
    RESPAWN_ANCHOR(AIR, true),
    SOUL_FIRE(FIRE, true),
    SOUL_WALL_TORCH(WALL_TORCH, true),
    WEEPING_VINES_PLANT(VINE, true),
    TWISTING_VINES_PLANT(VINE, true),
    CRIMSON_WALL_SIGN(OAK_WALL_SIGN, true),
    WARPED_WALL_SIGN(DARK_OAK_WALL_SIGN, true),
    POTTED_CRIMSON_FUNGUS(AIR, true),
    POTTED_WARPED_FUNGUS(AIR, true),
    POTTED_CRIMSON_ROOTS(AIR, true),
    POTTED_WARPED_ROOTS(AIR, true),
    DEEPSLATE(STONE_BRICKS, true),
    COBBLED_DEEPSLATE(COBBLESTONE, true),
    POLISHED_DEEPSLATE(STONE, true),
    CALCITE(QUARTZ, true),
    TUFF(MOSSY_COBBLESTONE, true),
    DRIPSTONE_BLOCK(COBBLESTONE, true),
    ROOTED_DIRT(COARSE_DIRT, true),
    DEEPSLATE_COAL_ORE(COAL_ORE, true),
    DEEPSLATE_IRON_ORE(IRON_ORE, true),
    COPPER_ORE(IRON_ORE, true),
    DEEPSLATE_COPPER_ORE(IRON_ORE, true),
    DEEPSLATE_GOLD_ORE(GOLD_ORE, true),
    DEEPSLATE_REDSTONE_ORE(REDSTONE_ORE, true),
    DEEPSLATE_EMERALD_ORE(EMERALD_ORE, true),
    DEEPSLATE_LAPIS_ORE(LAPIS_ORE, true),
    DEEPSLATE_DIAMOND_ORE(DIAMOND_ORE, true),
    RAW_IRON_BLOCK(IRON_BLOCK, true),
    RAW_COPPER_BLOCK(IRON_BLOCK, true),
    RAW_GOLD_BLOCK(GOLD_BLOCK, true),
    AMETHYST_BLOCK(PURPUR_BLOCK, true),
    BUDDING_AMETHYST(PURPUR_BLOCK, true),
    COPPER_BLOCK(IRON_BLOCK, true),
    EXPOSED_COPPER(IRON_BLOCK, true),
    WEATHERED_COPPER(IRON_BLOCK, true),
    OXIDIZED_COPPER(IRON_BLOCK, true),
    CUT_COPPER(IRON_BLOCK, true),
    EXPOSED_CUT_COPPER(IRON_BLOCK, true),
    WEATHERED_CUT_COPPER(IRON_BLOCK, true),
    OXIDIZED_CUT_COPPER(IRON_BLOCK, true),
    CUT_COPPER_STAIRS(RED_SANDSTONE_STAIRS, true),
    EXPOSED_CUT_COPPER_STAIRS(RED_SANDSTONE_STAIRS, true),
    WEATHERED_CUT_COPPER_STAIRS(RED_SANDSTONE_STAIRS, true),
    OXIDIZED_CUT_COPPER_STAIRS(RED_SANDSTONE_STAIRS, true),
    CUT_COPPER_SLAB(RED_SANDSTONE_SLAB, true),
    EXPOSED_CUT_COPPER_SLAB(RED_SANDSTONE_SLAB, true),
    WEATHERED_CUT_COPPER_SLAB(RED_SANDSTONE_SLAB, true),
    OXIDIZED_CUT_COPPER_SLAB(RED_SANDSTONE_SLAB, true),
    WAXED_COPPER_BLOCK(IRON_BLOCK, true),
    WAXED_EXPOSED_COPPER(IRON_BLOCK, true),
    WAXED_WEATHERED_COPPER(IRON_BLOCK, true),
    WAXED_OXIDIZED_COPPER(IRON_BLOCK, true),
    WAXED_CUT_COPPER(IRON_BLOCK, true),
    WAXED_EXPOSED_CUT_COPPER(IRON_BLOCK, true),
    WAXED_WEATHERED_CUT_COPPER(IRON_BLOCK, true),
    WAXED_OXIDIZED_CUT_COPPER(IRON_BLOCK, true),
    WAXED_CUT_COPPER_STAIRS(RED_SANDSTONE_STAIRS, true),
    WAXED_EXPOSED_CUT_COPPER_STAIRS(RED_SANDSTONE_STAIRS, true),
    WAXED_WEATHERED_CUT_COPPER_STAIRS(RED_SANDSTONE_STAIRS, true),
    WAXED_OXIDIZED_CUT_COPPER_STAIRS(RED_SANDSTONE_STAIRS, true),
    WAXED_CUT_COPPER_SLAB(RED_SANDSTONE_SLAB, true),
    WAXED_EXPOSED_CUT_COPPER_SLAB(RED_SANDSTONE_SLAB, true),
    WAXED_WEATHERED_CUT_COPPER_SLAB(RED_SANDSTONE_SLAB, true),
    WAXED_OXIDIZED_CUT_COPPER_SLAB(RED_SANDSTONE_SLAB, true),
    AZALEA_LEAVES(OAK_LEAVES, true),
    FLOWERING_AZALEA_LEAVES(OAK_LEAVES, true),
    TINTED_GLASS(GLASS, true),
    AZALEA(AIR, true),
    FLOWERING_AZALEA(AIR, true),
    SPORE_BLOSSOM(AIR, true),
    MOSS_CARPET(GREEN_CARPET, true),
    MOSS_BLOCK(MOSSY_COBBLESTONE, true),
    HANGING_ROOTS(AIR, true),
    BIG_DRIPLEAF(AIR, true),
    SMALL_DRIPLEAF(AIR, true),
    SMOOTH_BASALT(STONE_BRICKS, true),
    INFESTED_DEEPSLATE(INFESTED_STONE_BRICKS, true),
    DEEPSLATE_BRICKS(STONE_BRICKS, true),
    CRACKED_DEEPSLATE_BRICKS(CRACKED_STONE_BRICKS, true),
    DEEPSLATE_TILES(STONE_BRICKS, true),
    CRACKED_DEEPSLATE_TILES(CRACKED_STONE_BRICKS, true),
    CHISELED_DEEPSLATE(STONE_BRICKS, true),
    GLOW_LICHEN(AIR, true),
    COBBLED_DEEPSLATE_WALL(COBBLESTONE_WALL, true),
    POLISHED_DEEPSLATE_WALL(DIORITE_WALL, true),
    DEEPSLATE_BRICK_WALL(STONE_BRICK_WALL, true),
    DEEPSLATE_TILE_WALL(STONE_BRICK_WALL, true),
    LIGHT(GLOWSTONE, true),
    COBBLED_DEEPSLATE_STAIRS(COBBLESTONE_STAIRS, true),
    POLISHED_DEEPSLATE_STAIRS(STONE_STAIRS, true),
    DEEPSLATE_BRICK_STAIRS(BRICK_STAIRS, true),
    DEEPSLATE_TILE_STAIRS(STONE_STAIRS, true),
    COBBLED_DEEPSLATE_SLAB(COBBLESTONE_SLAB, true),
    POLISHED_DEEPSLATE_SLAB(STONE_BRICK_SLAB, true),
    DEEPSLATE_BRICK_SLAB(STONE_SLAB, true),
    DEEPSLATE_TILE_SLAB(STONE_SLAB, true),
    LIGHTNING_ROD(AIR, true),
    SCULK_SENSOR(AIR, true),
    AMETHYST_SHARD(AIR, true),
    RAW_IRON(IRON_INGOT, true),
    RAW_COPPER(IRON_INGOT, true),
    COPPER_INGOT(IRON_INGOT, true),
    RAW_GOLD(GOLD_INGOT, true),
    POWDER_SNOW_BUCKET(WATER_BUCKET, true),
    AXOLOTL_BUCKET(WATER_BUCKET, true),
    BUNDLE(SHULKER_BOX, true),
    SPYGLASS(AIR, true),
    GLOW_INK_SAC(INK_SAC, true),
    AXOLOTL_SPAWN_EGG(AIR, true),
    GLOW_SQUID_SPAWN_EGG(SQUID_SPAWN_EGG, true),
    GOAT_SPAWN_EGG(AIR, true),
    GLOW_ITEM_FRAME(ITEM_FRAME, true),
    GLOW_BERRIES(AIR, true),
    CANDLE(AIR, true),
    WHITE_CANDLE(AIR, true),
    ORANGE_CANDLE(AIR, true),
    MAGENTA_CANDLE(AIR, true),
    LIGHT_BLUE_CANDLE(AIR, true),
    YELLOW_CANDLE(AIR, true),
    LIME_CANDLE(AIR, true),
    PINK_CANDLE(AIR, true),
    GRAY_CANDLE(AIR, true),
    LIGHT_GRAY_CANDLE(AIR, true),
    CYAN_CANDLE(AIR, true),
    PURPLE_CANDLE(AIR, true),
    BLUE_CANDLE(AIR, true),
    BROWN_CANDLE(AIR, true),
    GREEN_CANDLE(AIR, true),
    RED_CANDLE(AIR, true),
    BLACK_CANDLE(AIR, true),
    SMALL_AMETHYST_BUD(PURPUR_BLOCK, true),
    MEDIUM_AMETHYST_BUD(PURPUR_BLOCK, true),
    LARGE_AMETHYST_BUD(AIR, true),
    AMETHYST_CLUSTER(AIR, true),
    POINTED_DRIPSTONE(AIR, true),
    WATER_CAULDRON(CAULDRON, true),
    LAVA_CAULDRON(CAULDRON, true),
    POWDER_SNOW_CAULDRON(CAULDRON, true),
    CANDLE_CAKE(AIR, true),
    WHITE_CANDLE_CAKE(AIR, true),
    ORANGE_CANDLE_CAKE(AIR, true),
    MAGENTA_CANDLE_CAKE(AIR, true),
    LIGHT_BLUE_CANDLE_CAKE(AIR, true),
    YELLOW_CANDLE_CAKE(AIR, true),
    LIME_CANDLE_CAKE(AIR, true),
    PINK_CANDLE_CAKE(AIR, true),
    GRAY_CANDLE_CAKE(AIR, true),
    LIGHT_GRAY_CANDLE_CAKE(AIR, true),
    CYAN_CANDLE_CAKE(AIR, true),
    PURPLE_CANDLE_CAKE(AIR, true),
    BLUE_CANDLE_CAKE(AIR, true),
    BROWN_CANDLE_CAKE(AIR, true),
    GREEN_CANDLE_CAKE(AIR, true),
    RED_CANDLE_CAKE(AIR, true),
    BLACK_CANDLE_CAKE(AIR, true),
    POWDER_SNOW(AIR, true),
    CAVE_VINES(AIR, true),
    CAVE_VINES_PLANT(AIR, true),
    BIG_DRIPLEAF_STEM(AIR, true),
    POTTED_AZALEA_BUSH(POTTED_LILY_OF_THE_VALLEY, true),
    POTTED_FLOWERING_AZALEA_BUSH(AIR, true),
    MUSIC_DISC_OTHERSIDE(MUSIC_DISC_13, true, true),
    MUD(STONE, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_PLANKS(JUNGLE_PLANKS, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_PROPAGULE(DEAD_BUSH, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_LOG(JUNGLE_LOG, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_ROOTS(OAK_LEAVES, true, VersionSupport.VersionType.v1_19_R1),
    MUDDY_MANGROVE_ROOTS(OAK_LEAVES, true, VersionSupport.VersionType.v1_19_R1),
    STRIPPED_MANGROVE_LOG(STRIPPED_JUNGLE_LOG, true, VersionSupport.VersionType.v1_19_R1),
    STRIPPED_MANGROVE_WOOD(STRIPPED_JUNGLE_WOOD, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_WOOD(JUNGLE_WOOD, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_LEAVES(JUNGLE_LEAVES, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_SLAB(JUNGLE_SLAB, true, VersionSupport.VersionType.v1_19_R1),
    MUD_BRICK_SLAB(JUNGLE_SLAB, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_FENCE(JUNGLE_FENCE, true, VersionSupport.VersionType.v1_19_R1),
    PACKED_MUD(DIRT, true, VersionSupport.VersionType.v1_19_R1),
    MUD_BRICKS(DIRT, true, VersionSupport.VersionType.v1_19_R1),
    REINFORCED_DEEPSLATE(OBSIDIAN, true, VersionSupport.VersionType.v1_19_R1),
    MUD_BRICK_STAIRS(POLISHED_GRANITE_STAIRS, true, VersionSupport.VersionType.v1_19_R1),
    SCULK(COBBLESTONE, true, VersionSupport.VersionType.v1_19_R1),
    SCULK_VEIN(AIR, true, VersionSupport.VersionType.v1_19_R1),
    SCULK_CATALYST(AIR, true, VersionSupport.VersionType.v1_19_R1),
    SCULK_SHRIEKER(AIR, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_STAIRS(JUNGLE_STAIRS, true, VersionSupport.VersionType.v1_19_R1),
    MUD_BRICK_WALL(BRICK_WALL, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_BUTTON(JUNGLE_BUTTON, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_PRESSURE_PLATE(JUNGLE_PRESSURE_PLATE, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_DOOR(JUNGLE_DOOR, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_TRAPDOOR(JUNGLE_TRAPDOOR, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_FENCE_GATE(JUNGLE_FENCE_GATE, true, VersionSupport.VersionType.v1_19_R1),
    OAK_CHEST_BOAT(OAK_BOAT, true, VersionSupport.VersionType.v1_19_R1),
    SPRUCE_CHEST_BOAT(SPRUCE_BOAT, true, VersionSupport.VersionType.v1_19_R1),
    BIRCH_CHEST_BOAT(BIRCH_BOAT, true, VersionSupport.VersionType.v1_19_R1),
    JUNGLE_CHEST_BOAT(JUNGLE_BOAT, true, VersionSupport.VersionType.v1_19_R1),
    ACACIA_CHEST_BOAT(ACACIA_BOAT, true, VersionSupport.VersionType.v1_19_R1),
    DARK_OAK_CHEST_BOAT(DARK_OAK_BOAT, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_BOAT(JUNGLE_BOAT, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_CHEST_BOAT(JUNGLE_BOAT, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_SIGN(JUNGLE_SIGN, true, VersionSupport.VersionType.v1_19_R1),
    TADPOLE_BUCKET(BUCKET, true, VersionSupport.VersionType.v1_19_R1),
    RECOVERY_COMPASS(COMPASS, true, VersionSupport.VersionType.v1_19_R1),
    ALLAY_SPAWN_EGG(AIR, true, VersionSupport.VersionType.v1_19_R1),
    FROG_SPAWN_EGG(TURTLE_SPAWN_EGG, true, VersionSupport.VersionType.v1_19_R1),
    TADPOLE_SPAWN_EGG(AIR, true, VersionSupport.VersionType.v1_19_R1),
    WARDEN_SPAWN_EGG(AIR, true, VersionSupport.VersionType.v1_19_R1),
    MUSIC_DISC_5(MUSIC_DISC_MELLOHI, true, VersionSupport.VersionType.v1_19_R1),
    DISC_FRAGMENT_5(AIR, true, VersionSupport.VersionType.v1_19_R1),
    GOAT_HORN(AIR, true, VersionSupport.VersionType.v1_19_R1),
    OCHRE_FROGLIGHT(AIR, true, VersionSupport.VersionType.v1_19_R1),
    VERDANT_FROGLIGHT(AIR, true, VersionSupport.VersionType.v1_19_R1),
    PEARLESCENT_FROGLIGHT(AIR, true, VersionSupport.VersionType.v1_19_R1),
    FROGSPAWN(AIR, true, VersionSupport.VersionType.v1_19_R1),
    ECHO_SHARD(AIR, true, VersionSupport.VersionType.v1_19_R1),
    MANGROVE_WALL_SIGN(JUNGLE_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R1),
    POTTED_MANGROVE_PROPAGULE(POTTED_JUNGLE_SAPLING, true, VersionSupport.VersionType.v1_19_R1),
    BAMBOO_PLANKS(BIRCH_PLANKS, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_MOSAIC(BIRCH_PLANKS, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_BLOCK(BIRCH_LOG, true, VersionSupport.VersionType.v1_19_R2),
    STRIPPED_BAMBOO_BLOCK(STRIPPED_BIRCH_LOG, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_SLAB(BIRCH_SLAB, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_MOSAIC_SLAB(BIRCH_SLAB, true, VersionSupport.VersionType.v1_19_R2),
    CHISELED_BOOKSHELF(BOOKSHELF, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_FENCE(BIRCH_FENCE, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_STAIRS(BIRCH_STAIRS, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_MOSAIC_STAIRS(BIRCH_STAIRS, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_BUTTON(BIRCH_BUTTON, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_PRESSURE_PLATE(BIRCH_PRESSURE_PLATE, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_DOOR(BIRCH_DOOR, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_TRAPDOOR(BIRCH_TRAPDOOR, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_FENCE_GATE(BIRCH_FENCE_GATE, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_RAFT(BIRCH_BOAT, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_CHEST_RAFT(BIRCH_CHEST_BOAT, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_SIGN(BIRCH_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    OAK_HANGING_SIGN(OAK_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    SPRUCE_HANGING_SIGN(SPRUCE_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    BIRCH_HANGING_SIGN(BIRCH_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    JUNGLE_HANGING_SIGN(JUNGLE_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    ACACIA_HANGING_SIGN(ACACIA_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    DARK_OAK_HANGING_SIGN(DARK_OAK_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    MANGROVE_HANGING_SIGN(MANGROVE_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_HANGING_SIGN(BAMBOO_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    CRIMSON_HANGING_SIGN(CRIMSON_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    WARPED_HANGING_SIGN(WARPED_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    CAMEL_SPAWN_EGG(LLAMA_SPAWN_EGG, true, VersionSupport.VersionType.v1_19_R2),
    ENDER_DRAGON_SPAWN_EGG(AIR, true, VersionSupport.VersionType.v1_19_R2),
    IRON_GOLEM_SPAWN_EGG(AIR, true, VersionSupport.VersionType.v1_19_R2),
    SNOW_GOLEM_SPAWN_EGG(AIR, true, VersionSupport.VersionType.v1_19_R2),
    WITHER_SPAWN_EGG(AIR, true, VersionSupport.VersionType.v1_19_R2),
    PIGLIN_HEAD(CREEPER_HEAD, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_WALL_SIGN(BIRCH_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    OAK_WALL_HANGING_SIGN(OAK_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    SPRUCE_WALL_HANGING_SIGN(SPRUCE_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    BIRCH_WALL_HANGING_SIGN(BIRCH_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    ACACIA_WALL_HANGING_SIGN(ACACIA_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    JUNGLE_WALL_HANGING_SIGN(JUNGLE_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    DARK_OAK_WALL_HANGING_SIGN(DARK_OAK_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    MANGROVE_WALL_HANGING_SIGN(MANGROVE_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    CRIMSON_WALL_HANGING_SIGN(CRIMSON_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    WARPED_WALL_HANGING_SIGN(WARPED_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    BAMBOO_WALL_HANGING_SIGN(BAMBOO_WALL_SIGN, true, VersionSupport.VersionType.v1_19_R2),
    PIGLIN_WALL_HEAD(CREEPER_WALL_HEAD, true, VersionSupport.VersionType.v1_19_R2);

    private static final HashMap<Materials, ItemStack> materialCache = new HashMap<>();
    private static final List<Materials> placeholderMaterials = new ArrayList<>();
    private MP prep;
    private boolean newPlaceholder = false;
    private Materials prepared = null;

    Materials(MP prepV1, MP prepV2) {
        String version = VersionSupport.getInstance().getVersion();
        byte versionCode = -1;
        switch (version) {
            case "v1_12_R1":
                versionCode = 1;
                break;
            case "v1_18_R2":
                versionCode = 2;
                break;
            case "v1_19_R1":
                versionCode = 3;
                break;
            case "v1_19_R2":
                versionCode = 4;
                break;
            case "v1_8_R3":
                versionCode = 0;
                break;
        }

        switch (versionCode) {
            case 0:
                this.prep = prepV1;
                break;
            case 1:
                this.prep = prepV2;
                break;
            case 2:
            case 3:
            case 4:
                this.prep = new MP(this.toString());
        }
    }

    Materials(MP prepV1, MP prepV2, boolean flag1, boolean flag2) {
        String version = VersionSupport.getInstance().getVersion();
        byte versionCode = -1;
        switch (version) {
            case "v1_12_R1":
                versionCode = 1;
                break;
            case "v1_18_R2":
                versionCode = 2;
                break;
            case "v1_19_R1":
                versionCode = 3;
                break;
            case "v1_19_R2":
                versionCode = 4;
                break;
            case "v1_8_R3":
                versionCode = 0;
                break;
        }

        switch (versionCode) {
            case 0:
            case 1:
                this.prep = prepV1;
                break;
            case 2:
            case 3:
            case 4:
                this.prep = prepV2;
        }
    }

    Materials(MP prepV1) {
        String version = VersionSupport.getInstance().getVersion();
        byte versionCode = -1;
        switch (version) {
            case "v1_12_R1":
                versionCode = 1;
                break;
            case "v1_18_R2":
                versionCode = 2;
                break;
            case "v1_19_R1":
                versionCode = 3;
                break;
            case "v1_19_R2":
                versionCode = 4;
                break;
            case "v1_8_R3":
                versionCode = 0;
                break;
        }

        switch (versionCode) {
            case 0:
            case 1:
                this.prep = prepV1;
                break;
            case 2:
            case 3:
            case 4:
                this.prep = new MP(this.toString());
        }
    }

    Materials(Materials preparedMaterial, boolean newPlaceholder) {
        this.newPlaceholder = newPlaceholder;
        String version = VersionSupport.getInstance().getVersion();
        byte versionCode = -1;
        switch (version) {
            case "v1_12_R1":
                versionCode = 1;
                break;
            case "v1_18_R2":
                versionCode = 2;
                break;
            case "v1_19_R1":
                versionCode = 3;
                break;
            case "v1_19_R2":
                versionCode = 4;
                break;
            case "v1_8_R3":
                versionCode = 0;
                break;
        }

        switch (versionCode) {
            case 0:
            case 1:
                this.prepared = preparedMaterial;
                break;
            case 2:
            case 3:
            case 4:
                this.prep = new MP(this.toString());
        }
    }

    Materials(Materials preparedMaterial, boolean newPlaceholder, VersionSupport.VersionType versionType) {
        this.newPlaceholder = newPlaceholder;
        String version = VersionSupport.getInstance().getVersion();
        byte versionCode = -1;
        if (version.equals("v1_12_R1")) {
            versionCode = 1;
        } else if (version.equals("v1_8_R3")) {
            versionCode = 0;
        }

        switch (versionCode) {
            case 0:
            case 1:
                this.prepared = preparedMaterial;
                break;
            default:
                if (VersionSupport.getInstance().getVersionType().getVersionID() < versionType.getVersionID()) {
                    this.prep = preparedMaterial.prep;
                } else {
                    this.prep = new MP(this.toString());
                }
        }
    }

    Materials(Materials preparedMaterial, boolean newPlaceholder, boolean flag) {
        this.newPlaceholder = newPlaceholder;
        String version = VersionSupport.getInstance().getVersion();
        byte versionCode = -1;
        switch (version) {
            case "v1_12_R1":
                versionCode = 1;
                break;
            case "v1_18_R2":
                versionCode = 2;
                break;
            case "v1_19_R1":
                versionCode = 3;
                break;
            case "v1_19_R2":
                versionCode = 4;
                break;
            case "v1_8_R3":
                versionCode = 0;
                break;
        }

        switch (versionCode) {
            case 0:
            case 1:
            case 2:
                this.prepared = preparedMaterial;
                break;
            case 3:
            case 4:
                this.prep = new MP(this.toString());
        }
    }

    Materials(Materials preparedMaterial, boolean newPlaceholder, String versionString) {
        this.newPlaceholder = newPlaceholder;
        String version = VersionSupport.getInstance().getVersion();
        byte versionCode = -1;
        switch (version) {
            case "v1_12_R1":
                versionCode = 1;
                break;
            case "v1_18_R2":
                versionCode = 2;
                break;
            case "v1_19_R1":
                versionCode = 3;
                break;
            case "v1_19_R2":
                versionCode = 4;
                break;
            case "v1_8_R3":
                versionCode = 0;
                break;
        }

        switch (versionCode) {
            case 0:
            case 1:
            case 2:
            case 3:
                this.prepared = preparedMaterial;
                break;
            case 4:
                this.prep = new MP(this.toString());
        }
    }

    Materials() {
        this.prep = new MP(this.toString());
    }

    public static void init() {
        Materials[] materialsArray = values();

        for (Materials material : materialsArray) {
            if (material.isPlaceholder() || material.isNewPlaceholder()) {
                placeholderMaterials.add(material);
            }

            materialCache.put(material, material.getItem().build());
        }
    }

    public static Materials getItemStackMaterial(ItemStack itemStack) {
        Materials[] materialsArray = values();

        for (Materials material : materialsArray) {
            if (!placeholderMaterials.contains(material)) {
                boolean matches;
                if (BambooUtils.isVersion(8, 12)) {
                    matches = materialCache.get(material).getType().getData() == itemStack.getType().getData();
                } else {
                    matches = true;
                }

                if (matches && materialCache.get(material).getType().equals(itemStack.getType())) {
                    return material;
                }
            }
        }

        return AIR;
    }

    public static Materials getBlockMaterial(Block block) {
        ItemBuilder itemBuilder = new ItemBuilder(block.getType());
        if (BambooUtils.isVersion(8, 12)) {
            itemBuilder.setData(block.getTypeId());
        }

        ItemStack itemStack = itemBuilder.build();
        Materials[] materialsArray = values();

        for (Materials material : materialsArray) {
            if (!placeholderMaterials.contains(material)) {
                boolean matches;
                if (BambooUtils.isVersion(8, 12)) {
                    matches = materialCache.get(material).getType().getData() == itemStack.getType().getData();
                } else {
                    matches = true;
                }

                if (matches && materialCache.get(material).getType().equals(itemStack.getType())) {
                    return material;
                }
            }
        }

        return AIR;
    }

    public static Materials getBlockMaterial(World world, int x, int y, int z) {
        Block block = world.getBlockAt(x, y, z);
        ItemBuilder itemBuilder = new ItemBuilder(block.getType());
        if (BambooUtils.isVersion(8, 12)) {
            itemBuilder.setData(block.getTypeId());
        }

        ItemStack itemStack = itemBuilder.build();
        Materials[] materialsArray = values();

        for (Materials material : materialsArray) {
            if (!placeholderMaterials.contains(material)) {
                boolean matches;
                if (BambooUtils.isVersion(8, 12)) {
                    matches = materialCache.get(material).getData() == itemStack.getData();
                } else {
                    matches = true;
                }

                if (matches && materialCache.get(material).getType().equals(itemStack.getType())) {
                    return material;
                }
            }
        }

        return AIR;
    }

    public ItemBuilder getItem() {
        ItemBuilder itemBuilder = null;
        if (this.prepared != null) {
            return this.prepared.getItem();
        } else {
            switch (this.prep.getPrepType()) {
                case MAT:
                    itemBuilder = new ItemBuilder(this.prep.getMaterial());
                    break;
                case ID:
                    itemBuilder = new ItemBuilder(this.prep.getId());
                    break;
                case ID_DATA:
                    itemBuilder = new ItemBuilder(this.prep.getId(), this.prep.getData());
            }

            return itemBuilder;
        }
    }

    public boolean isPlaceholder() {
        return this.prepared != null || this.prep.isPlaceholder();
    }

    public boolean isNewPlaceholder() {
        return this.newPlaceholder;
    }

    public boolean hasData() {
        return !BambooUtils.isVersion(17, 18, 19) && this.prep.getPrepType().equals(MP.PrepType.ID_DATA);
    }

    public int getData() {
        return this.prep.getData();
    }
}
