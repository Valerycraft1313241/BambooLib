package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.UUID;

public interface ItemSkull {
   UUID getOwner();

   boolean hasOwner();

   ItemBuilder setOwner(String var1);

   ItemBuilder setSkin(String var1);
}