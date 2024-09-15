package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.List;
import org.bukkit.block.banner.Pattern;

public interface ItemBanner {
   ItemBuilder setPatterns(List<Pattern> var1);

   ItemBuilder setPattern(int var1, Pattern var2);

   ItemBuilder addPattern(Pattern var1);

   ItemBuilder removePattern(int var1);

   List<Pattern> getPatterns();

   Pattern getPattern(int var1);

   int getNumberOfPatterns();
}
