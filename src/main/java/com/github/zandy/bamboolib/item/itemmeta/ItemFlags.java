package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import org.bukkit.inventory.ItemFlag;

public interface ItemFlags {
   ItemBuilder add(ItemFlag... var1);

   ItemBuilder remove(ItemFlag... var1);

   boolean has(ItemFlag var1);
}
