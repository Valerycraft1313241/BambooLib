package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.List;
import org.bukkit.FireworkEffect;

public interface ItemFirework {
   ItemBuilder addEffect(FireworkEffect var1);

   ItemBuilder addEffects(FireworkEffect... var1);

   List<FireworkEffect> getEffects();

   int getEffectsSize();

   ItemBuilder removeEffect(int var1);

   ItemBuilder clearEffects();

   boolean hasEffects();

   int getPower();

   ItemBuilder setPower(int var1);

   ItemBuilder setEffect(FireworkEffect var1);

   boolean hasEffect();

   FireworkEffect getEffect();
}
