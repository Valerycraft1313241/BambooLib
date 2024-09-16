package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.List;

public interface ItemBook {
   boolean hasTitle();

   String getTitle();

   ItemBuilder setTitle(String title);

   boolean hasAuthor();

   String getAuthor();

   ItemBuilder setAuthor(String author);

   boolean hasPages();

   String getPage(int page);

   ItemBuilder setPage(int page, String s);

   List<String> getPages();

   ItemBuilder setPages(List<String> pages);

   ItemBuilder addPages(String... pages);

   int getPageCount();
}
