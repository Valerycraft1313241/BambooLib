package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import org.bukkit.entity.EntityType;

public interface ItemSpawnEgg {
   EntityType getEntityType();

   ItemBuilder setEntityType(EntityType var1);
}
