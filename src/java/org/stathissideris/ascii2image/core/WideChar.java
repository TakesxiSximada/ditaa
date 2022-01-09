/**
 * jditaa code
 *
 * Original source code is https://tamura70.hatenadiary.org/entry/20100317/org
 *
 */
package org.stathissideris.ascii2image.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.lang.Character.UnicodeBlock;
import java.util.HashMap;
import java.util.Map;


public class WideChar {
    static Map<UnicodeBlock,Integer> widthMap;

    static {
        widthMap = new HashMap<UnicodeBlock,Integer>();
        widthMap.put(UnicodeBlock.ARROWS, 2);
        widthMap.put(UnicodeBlock.CJK_COMPATIBILITY, 2);
        widthMap.put(UnicodeBlock.CJK_COMPATIBILITY_FORMS, 2);
        widthMap.put(UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS, 2);
        widthMap.put(UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, 2);
        widthMap.put(UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, 2);
        widthMap.put(UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS, 2);
        widthMap.put(UnicodeBlock.HIRAGANA, 2);
        widthMap.put(UnicodeBlock.KATAKANA, 2);
    }

    public static int charWidth(char ch) {
        UnicodeBlock block = UnicodeBlock.of(ch);
        Integer w = widthMap.get(block);
        if (w != null) {
            return w;
        }
        return 1;
    }
    public String convert(String line) {
	StringBuilder s = new StringBuilder();
	for (int i = 0; i < line.length(); i++) {
	    char ch = line.charAt(i);
	    s.append(ch);
	    if (Character.isHighSurrogate(ch)) {
		i++;
		s.append(line.charAt(i));
	    } else {
                if (ch != 0) {
                    int w = charWidth(ch);
                    for (int k = 1; k < w; k++)
                        s.append(' ');
		}
	    }
	}
	return s.toString();
    }
}
