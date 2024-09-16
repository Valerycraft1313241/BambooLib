package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.Map;
import org.bukkit.enchantments.Enchantment;

public interface ItemEnchantment {
   int getLevel(Enchantment enchantment);

   ItemBuilder add(Enchantment enchantment, int level);

   ItemBuilder addUnsafe(Enchantment enchantment, int level);

   ItemBuilder remove(Enchantment enchantment);

   Map<Enchantment, Integer> getList();

   boolean has(Enchantment enchantment);
}
