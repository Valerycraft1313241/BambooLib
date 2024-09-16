package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public interface ItemPotion {
   boolean hasCustomEffects();

   List<PotionEffect> getCustomEffects();

   ItemBuilder removeCustomEffect(PotionEffectType potionEffectType);

   boolean hasColor();

   Color getColor();

   ItemBuilder setColor(Color color);

   ItemBuilder setSplash(PotionType potionType, boolean splash);

   ItemBuilder addCustomEffect(PotionEffect customEffect);

}
