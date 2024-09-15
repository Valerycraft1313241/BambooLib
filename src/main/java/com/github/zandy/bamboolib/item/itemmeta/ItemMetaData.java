package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;

public interface ItemMetaData {
   ItemBuilder add(String var1, String var2);

   String get(String var1);

   boolean has(String var1);

   ItemBuilder remove(String var1);
}
