package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;

public interface ItemMetaData {
   ItemBuilder add(String metadataName, String metadataValue);

   String get(String metadata);

   boolean has(String metadata);

   ItemBuilder remove(String metadata);
}
