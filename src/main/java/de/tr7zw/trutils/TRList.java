package de.tr7zw.trutils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Extended List interface with many new utility methods for shorter and better
 * code.
 * 
 * @author tr7zw
 *
 * @param <E>
 */
public interface TRList<E> extends TRCollection<E>, List<E> {

	/**
	 * subList wrapped into a TRList
	 * 
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	public default TRList<E> getSubList(int fromIndex, int toIndex) {
		return TRUtils.wrap(subList(fromIndex, toIndex));
	}

	/**
	 * Get a sublist of all Entries that follow the Predicate
	 * 
	 * @param pred
	 * @return
	 */
	public default TRList<E> subList(Predicate<E> pred) {
		TRList<E> list = TRUtils.createArrayList();
		for (E ent : this)
			if (pred.test(ent))
				list.add(ent);
		return list;
	}

	/**
	 * Combines booth list using the bifunction. Combines till one of booth lists
	 * has reached it's end.
	 * 
	 * @param <T>
	 * @param <U>
	 * @param list
	 * @param function
	 * @return
	 */
	public default <T, U> TRList<T> combine(TRList<U> list, BiFunction<E, U, T> function) {
		TRList<T> combined = TRUtils.createArrayList();
		for (int i = 0; i < size() && i < list.size(); i++) {
			combined.add(function.apply(get(i), list.get(i)));
		}
		return combined;
	}

	/**
	 * Get the underlying List
	 * 
	 * @return
	 */
	public List<E> getList();

}
