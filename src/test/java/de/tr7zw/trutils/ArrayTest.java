package de.tr7zw.trutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ArrayTest {

	@Test
	public void testListtoArray() {
		TRList<Integer> list = TRUtils.createList(1, 2, 3, 4, 5);
		Integer[] array = list.toTypeArray();
		assertEquals(array.length, 5);
		assertEquals(array[0], (Integer) 1);
		assertEquals(array[1], (Integer) 2);
		assertEquals(array[4], (Integer) 5);
	}

	@Test
	public void testListtoArrayInt() {
		TRList<Integer> list = TRUtils.createList(1, 2, 3, 4, 5);
		Integer[] array = list.toTypeArray(Integer.class);
		assertEquals(array.length, 5);
		assertEquals(array[0], (Integer) 1);
		assertEquals(array[1], (Integer) 2);
		assertEquals(array[4], (Integer) 5);
	}

	@Test
	public void testNoContent() {
		TRList<Integer> list = TRUtils.createArrayList();
		assertNull(list.getContentClass());
		Integer[] ar = list.toTypeArray();
		assertNull(ar);
		ar = list.toTypeArray(Integer.class);
		assertEquals(ar.length, 0);
	}

	@Test
	public void testRange() {
		TRList<Integer> list = TRUtils.createArrayList();
		list.computeRange(1, 100, i -> i * i);
		assertEquals(100, list.size());
		assertEquals(list.get(99), (Integer) 10000);
	}

	@Test
	public void testToSet() {
		TRList<Integer> list = TRUtils.createList(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
		TRSet<Integer> set = list.toSet();
		assertEquals(5, set.size());
	}

	@Test
	public void testSublist() {
		TRList<Integer> list = TRUtils.createList(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
		TRList<Integer> sublist = list.getSubList(0, 5);
		assertEquals(5, sublist.size());
		assertEquals((Integer) 3, sublist.get(4));
		TRList<Integer> even = list.subList(i -> i % 2 == 0);
		assertEquals(4, even.size());
	}

	@Test
	public void testListCombining() {
		TRList<Integer> list = TRUtils.createList(1, 2, 3, 4, 5);
		TRList<String> list2 = TRUtils.createList("This", "is", "a", "test");
		TRList<String> out = list.combine(list2, (i,s) -> s+i);
		assertEquals(4, out.size());
		out = list2.combine(list, (s,i) -> s+i);
		assertEquals(4, out.size());
	}

}
