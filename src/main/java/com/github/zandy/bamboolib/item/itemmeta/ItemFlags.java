package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import org.bukkit.inventory.ItemFlag;

public interface ItemFlags {
   ItemBuilder add(ItemFlag... itemFlags);

   ItemBuilder remove(ItemFlag... itemFlags);

   boolean has(ItemFlag itemFlags);
}
