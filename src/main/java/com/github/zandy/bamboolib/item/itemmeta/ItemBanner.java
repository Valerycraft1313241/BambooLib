package com.github.zandy.bamboolib.item.itemmeta;

import com.github.zandy.bamboolib.item.ItemBuilder;
import java.util.List;
import org.bukkit.block.banner.Pattern;

public interface ItemBanner {
   ItemBuilder setPatterns(List<Pattern> patternList);

   ItemBuilder setPattern(int i, Pattern pattern);

   ItemBuilder addPattern(Pattern pattern);

   ItemBuilder removePattern(int i);

   List<Pattern> getPatterns();

   Pattern getPattern(int i);

   int getNumberOfPatterns();
}
