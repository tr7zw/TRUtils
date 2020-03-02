package de.tr7zw.trutils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MapTest {

	@Test
	public void testListMappings() {
		TRMap<Integer, String> map = TRUtils.createList(10, 20, 30, 40).map(i -> i * i)
				.computeKeyMap(i -> Integer.toHexString(i));
		assertEquals(map.size(), 4);
		assertEquals(map.get(400), "190");
		assertEquals(map.get(1600), "640");
		assertEquals(map.get(100), "64");
		assertEquals(map.get(900), "384");
	}
}
