package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.Map;
import org.bukkit.enchantments.Enchantment;

public interface ItemEnchantment {
   int getLevel(Enchantment var1);

   ItemBuilder add(Enchantment var1, int var2);

   ItemBuilder addUnsafe(Enchantment var1, int var2);

   ItemBuilder remove(Enchantment var1);

   Map<Enchantment, Integer> getList();

   boolean has(Enchantment var1);
}
