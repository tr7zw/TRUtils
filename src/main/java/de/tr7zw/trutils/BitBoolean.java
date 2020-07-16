package de.tr7zw.trutils;

import java.util.Arrays;

// Fork of https://gist.github.com/zapstar/9594616
public class BitBoolean {

	// Holds boolean data as bits
	// Each byte can hold 8 boolean values
	// So index = booleanDataIndex + booleanMappingIndex
	// booleanDataIndex = index/8
	// boleanMappingIndex, see booleanMapping variable
	private byte[] booleanData;

	private int booleanDataLength;

	// Maps 0-7 to fetch boolean value, to bit mask and set/get booleans
	private static final int[] booleanBitMask = new int[] { 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01 };

	public BitBoolean(int size) {
		// Sanity check
		if (size <= 0 || size > Integer.MAX_VALUE)
			throw new IllegalArgumentException("Valid range of N: N > 0 or N <=" + Integer.MAX_VALUE);

		// Amount of boolean data to be held
		booleanDataLength = size - 1;

		// Allocate memory to hold boolean values
		booleanData = new byte[booleanDataLength / 8 + 1];
		/* NOTE: All boolean values will be initially false */

	}

	private void checkIndexBounds(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException("Index cannot be less than 0." + "Index Value: " + index);
		if (index > booleanDataLength)
			throw new IndexOutOfBoundsException(
					"Index cannot be greater than " + booleanDataLength + "." + "Index Value: " + index);
	}

	// Gets the boolean value
	public boolean get(int index) {
		checkIndexBounds(index);
		return (booleanData[index / 8] & booleanBitMask[index & 0x07]) > 0;
	}

	// Sets index's boolean value
	public void set(int index, boolean value) {
		checkIndexBounds(index);
		if (value) {
			booleanData[index / 8] |= booleanBitMask[index & 0x07];
		} else {
			booleanData[index / 8] &= ~booleanBitMask[index & 0x07];
		}
	}

	private String printByteValue(byte b) {
		return (Integer.toBinaryString(0x100 + (int) (b & 0xFF)).substring(1));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (byte b : booleanData) {
			builder.append(printByteValue(b));
		}
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(booleanData);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BitBoolean other = (BitBoolean) obj;
		if (!Arrays.equals(booleanData, other.booleanData))
			return false;
		return true;
	}

}