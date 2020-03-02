package de.tr7zw.trutils;

import java.util.Set;

/**
 * Extended Set interface with many new utility methods for shorter and
 * better code.
 * 
 * @author tr7zw
 *
 * @param <E>
 */
public interface TRSet<E> extends TRCollection<E>, Set<E>{

	/**
	 * Get the underlying Set
	 * 
	 * @return
	 */
	public Set<E> getSet();

	
}
