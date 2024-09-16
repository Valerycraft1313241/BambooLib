package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.List;
import org.bukkit.FireworkEffect;

public interface ItemFirework {
   ItemBuilder addEffect(FireworkEffect fireworkEffect);

   ItemBuilder addEffects(FireworkEffect... fireworkEffect);

   List<FireworkEffect> getEffects();

   int getEffectsSize();

   ItemBuilder removeEffect(int number);

   ItemBuilder clearEffects();

   boolean hasEffects();

   int getPower();

   ItemBuilder setPower(int power);

   ItemBuilder setEffect(FireworkEffect fireworkEffect);

   boolean hasEffect();

   FireworkEffect getEffect();
}
