package de.tr7zw.trutils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Extended Map interface with many new utility methods for shorter and better
 * code.
 * 
 * @author tr7zw
 *
 * @param <K>
 * @param <V>
 */
public interface TRMap<K, V> extends Map<K, V> {

	/**
	 * Get the underlying map
	 * 
	 * @return Reference to the map
	 */
	public Map<K, V> getMap();

	/**
	 * Run the Consumer if the key is found
	 * 
	 * @param key
	 * @param function
	 */
	public default void using(K key, Consumer<V> function) {
		V val = get(key);
		if (val != null) {
			function.accept(val);
		}
	}

	/**
	 * Run the Consumer for each key, if the key is found
	 * 
	 * @param key
	 * @param function
	 */
	public default void using(Collection<K> keys, Consumer<V> function) {
		for (K key : keys) {
			V val = get(key);
			if (val != null) {
				function.accept(val);
			}
		}
	}

	/**
	 * Remove all entries that match the Predicate
	 * 
	 * @param predicate
	 * @return
	 */
	public default int removeIf(BiPredicate<K, V> predicate) {
		Set<K> remove = new HashSet<>();
		for (Entry<K, V> entry : entrySet()) {
			if (predicate.test(entry.getKey(), entry.getValue()))
				remove.add(entry.getKey());
		}
		removeAll(remove);
		return remove.size();
	}

	/**
	 * Remove all keys in the Collection
	 * 
	 * @param keys
	 */
	public default void removeAll(Collection<K> keys) {
		for (K key : keys)
			remove(key);
	}

	/**
	 * Get a Set of all keys with this value
	 * 
	 * @param val
	 * @return
	 */
	public default TRSet<K> getKeys(V val) {
		TRSet<K> keys = TRUtils.createHashSet();
		for (Entry<K, V> entry : entrySet()) {
			if ((val != null && val.equals(entry.getValue())) || val == entry.getValue()) {
				keys.add(entry.getKey());
			}
		}
		return keys;
	}

	/**
	 * TRSet wrapped keySet
	 * 
	 * @return
	 */
	public default TRSet<K> getKeySet() {
		return TRUtils.wrap(keySet());
	}

	/**
	 * TRCollection wrapped values
	 * 
	 * @return
	 */
	public default TRCollection<V> getValues() {
		return TRUtils.wrap(values());
	}

	/**
	 * TRSet wrapped entrySet
	 * 
	 * @return
	 */
	public default TRSet<Entry<K, V>> getEntrySet() {
		return TRUtils.wrap(entrySet());
	}

	/**
	 * Put all keys and compute the value
	 * 
	 * @param keys
	 * @param func
	 */
	public default void putAllKeys(Collection<K> keys, Function<K, V> func) {
		for (K key : keys) {
			put(key, func.apply(key));
		}
	}

	/**
	 * Put all values and compute the keys
	 * 
	 * @param values
	 * @param func
	 */
	public default void putAllValues(Collection<V> values, Function<V, K> func) {
		for (V value : values) {
			put(func.apply(value), value);
		}
	}
	
	/**
	 * Put all absent keys and compute the value
	 * 
	 * @param keys
	 * @param func
	 */
	public default void computeAllIfAbsent(Collection<K> keys, Function<K, V> func) {
		for (K key : keys) {
			computeIfAbsent(key, func);
		}
	}

}
