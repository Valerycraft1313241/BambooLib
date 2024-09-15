package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.List;

public interface ItemBook {
   boolean hasTitle();

   String getTitle();

   ItemBuilder setTitle(String var1);

   boolean hasAuthor();

   String getAuthor();

   ItemBuilder setAuthor(String var1);

   boolean hasPages();

   String getPage(int var1);

   ItemBuilder setPage(int var1, String var2);

   List<String> getPages();

   ItemBuilder setPages(List<String> var1);

   ItemBuilder addPages(String... var1);

   int getPageCount();
}
