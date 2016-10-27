package com.aryasindhu.cache;

import org.apache.commons.lang.ArrayUtils;

public class LRUCache {

	public String process(int maxCacheSize, String string) {

		if (maxCacheSize > 1 && string.charAt(0) != '!') {
			char[] cache = new char[maxCacheSize];
			String output = "";
			int nextPos = 0;
			int pos = -1;
			for (char c : string.toCharArray()) {
				if (c == '!') {
					output = output.concat(String.copyValueOf(cache).trim()).concat(",");
				} else {
					pos = ArrayUtils.indexOf(cache, c);
					// if not in cache : need to add to cache
					if (pos == -1) {
						// if cache full : remove first element
						if (nextPos == maxCacheSize) {
							removeFromArray(cache, 0);
							nextPos--;
						}
					} else { // if found in cache : remove the element from its current position
						removeFromArray(cache, pos);
						nextPos--;
					}
					// add the element to the last of the cache
					cache[nextPos++] = c;
				}
			}
			return output.substring(0, output.length() - (output.contains(",") ? 1 : 0));
		}
		return null;
	}

	private void removeFromArray(char[] cache, int index) {
		char[] tempCache = ArrayUtils.remove(cache, index);
		int nextIndex = 0;
		while (nextIndex < tempCache.length) {
			cache[nextIndex] = tempCache[nextIndex];
			nextIndex++;
		}
		cache[nextIndex] = 0;
	}

}
