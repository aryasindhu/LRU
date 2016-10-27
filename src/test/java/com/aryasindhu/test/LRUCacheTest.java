package com.aryasindhu.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aryasindhu.cache.LRUCache;

public class LRUCacheTest {

	private static String TEST_DATA_FILE = null;

	@BeforeClass
	public static void setup() {
		TEST_DATA_FILE = "LRUTestData.txt";
	}

	@Test
	public void testLRUCache() throws IOException {

		LRUCache cacheImpl = new LRUCache();

		String inputString = null;
		String expectedResult = null;
		int cacheSize = 0;
		String actualResult = null;

		InputStream is = this.getClass().getClassLoader().getResourceAsStream(TEST_DATA_FILE);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String eachLine = null;
		while ((eachLine = reader.readLine()) != null) {
			
			if(eachLine.trim().equals("0")) {
				continue;
			}

			System.out.print("Processing [" + eachLine + "]");
			cacheSize = Integer.parseInt(eachLine.substring(0, eachLine.indexOf(' ')));
			inputString = eachLine.substring(1 + eachLine.indexOf(' '), eachLine.indexOf('|'));
			expectedResult = eachLine.substring(1 + eachLine.indexOf('|'));

			actualResult = cacheImpl.process(cacheSize, inputString);
			Assert.assertEquals(expectedResult, actualResult);
			System.out.println("\tPASSED");
		}

		reader.close();
	}

}
