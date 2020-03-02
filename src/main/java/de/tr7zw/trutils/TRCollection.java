package de.tr7zw.trutils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;

/**
 * Extended Collection interface with many new utility methods for shorter and
 * better code.
 * 
 * @author tr7zw
 *
 * @param <E>
 */
public interface TRCollection<E> extends Collection<E> {

	/**
	 * Gets the reference to the underlying Collection
	 * 
	 * @return The underlying Collection
	 */
	public Collection<E> getCollection();

	/**
	 * @return HashSet copy of this Collections content
	 */
	public default TRSet<E> toSet() {
		return TRUtils.wrap(new HashSet<>(this));
	}

	/**
	 * @return ArrayList copy of this Collections content
	 */
	public default TRList<E> toList() {
		return TRUtils.wrap(new ArrayList<>(this));
	}

	/**
	 * Compute values for a given range
	 * 
	 * @param from
	 * @param to
	 * @param func
	 */
	public default void computeRange(int from, int to, Function<Integer, E> func) {
		for (int i = from; i <= to; i++) {
			add(func.apply(i));
		}
	}

	/**
	 * Create an ArrayList with values that got mapped from this Collection
	 * 
	 * @param <T>
	 * @param function
	 * @return
	 */
	public default <T> TRList<T> map(Function<E, T> function) {
		TRList<T> list = TRUtils.createArrayList();
		for (E ent : this)
			list.add(function.apply(ent));
		return list;
	}

	/**
	 * Create a HashMap containing the collection values as keys with computed
	 * values
	 * 
	 * @param <T>
	 * @param function
	 * @return
	 */
	public default <T> TRMap<E, T> computeKeyMap(Function<E, T> function) {
		TRMap<E, T> map = TRUtils.createHashMap();
		for (E ent : this)
			map.put(ent, function.apply(ent));
		return map;
	}

	/**
	 * Create a HashMap containing the collection values as values with computed
	 * keys
	 * 
	 * @param <T>
	 * @param function
	 * @return
	 */
	public default <T> TRMap<T, E> computeValueMap(Function<E, T> function) {
		TRMap<T, E> map = TRUtils.createHashMap();
		for (E ent : this)
			map.put(function.apply(ent), ent);
		return map;
	}

	/**
	 * Tries to get the generic datatype and creates an Array using it. Try to pass
	 * the Array target class if possible.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public default E[] toTypeArray() {
		if (isEmpty())
			return (E[]) toArray();
		Class<? extends E> clazz = getContentClass();
		if (clazz != null) {
			return toTypeArray(clazz);
		}
		return (E[]) toArray();
	}

	/**
	 * toArray helper class that does the typesave cast
	 * 
	 * @param <T>
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public default <T> T[] toTypeArray(Class<T> target) {
		return (T[]) toArray((Object[]) Array.newInstance(target, 0));
	}

	/**
	 * @return Any object inside the collection or null
	 */
	public default E getAny() {
		return stream().findAny().get();
	}

	/**
	 * Tries to get the generic type of the Collection
	 * 
	 * @return The type's class or null
	 */
	@SuppressWarnings("unchecked")
	public default Class<? extends E> getContentClass() {
		E val = getAny();
		if (val != null) {
			return (Class<? extends E>) val.getClass();
		}
		return null;
	}

}
