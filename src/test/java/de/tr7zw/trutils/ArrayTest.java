package de.tr7zw.trutils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayTest {

	@Test
	public void testListtoArray() {
		TRList<Integer> list = TRUtils.createList(1, 2, 3, 4, 5);
		Integer[] array = list.toTypeArray();
		assertEquals(array.length, 5);
		assertEquals(array[0], (Integer)1);
		assertEquals(array[1], (Integer)2);
		assertEquals(array[4], (Integer)5);
	}

	@Test
	public void testListtoArrayInt() {
		TRList<Integer> list = TRUtils.createList(1, 2, 3, 4, 5);
		Integer[] array = list.toTypeArray(Integer.class);
		assertEquals(array.length, 5);
		assertEquals(array[0], (Integer)1);
		assertEquals(array[1], (Integer)2);
		assertEquals(array[4], (Integer)5);
	}
	
}
