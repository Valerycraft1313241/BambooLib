package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;

public interface ItemMetaData {
   ItemBuilder add(String metadataName, String var2);

   String get(String metadata);

   boolean has(String metadata);

   ItemBuilder remove(String metadata);
}
