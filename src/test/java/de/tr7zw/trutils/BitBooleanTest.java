package de.tr7zw.trutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Random;

import org.junit.Test;

public class BitBooleanTest {

	@Test
	public void testStorage() {
		BitBoolean bitbool = new BitBoolean(55);
		Random rng = new Random(0);
		for(int i = 0; i < 55; i++)
			bitbool.set(i, rng.nextBoolean());
		rng = new Random(0);
		for(int i = 0; i < 55; i++)
			assertEquals(bitbool.get(i), rng.nextBoolean());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testUpperLimit() {
		BitBoolean bitbool = new BitBoolean(55);
		bitbool.get(56);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testLowerLimit() {
		BitBoolean bitbool = new BitBoolean(55);
		bitbool.get(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreation() {
		BitBoolean bitbool = new BitBoolean(-1);
	}
	
	@Test()
	public void testAllFalse() {
		BitBoolean bitbool = new BitBoolean(36);
		for(int i = 0; i < 36; i++)
			assertFalse(bitbool.get(i));
	}
	
}
