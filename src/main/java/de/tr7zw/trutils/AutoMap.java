package de.tr7zw.trutils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class AutoMap<K, V> implements TRMap<K, V>{

	private final Supplier<Collection<K>> backingCollection;
	private final Function<K, V> creator;
	
	private final TRMap<K, V> map = TRUtils.createHashMap();
	
	public AutoMap(Supplier<Collection<K>> backing, Function<K, V> creator) {
		this.backingCollection = backing;
		this.creator = creator;
	}
	
	private void update() {
		Collection<K> backing = backingCollection.get();
		map.removeIf((k,v) -> !backing.contains(k));
		map.computeAllIfAbsent(backing, creator);
	}
	
	@Override
	public int size() {
		update();
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return backingCollection.get().isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return backingCollection.get().contains(key);
	}

	@Override
	public boolean containsValue(Object value) {
		update();
		return map.containsValue(value);
	}

	@Override
	public V get(Object key) {
		update();
		return map.get(key);
	}

	@Override
	public V put(K key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<K> keySet() {
		update();
		return map.keySet();
	}

	@Override
	public Collection<V> values() {
		update();
		return map.values();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		update();
		return map.entrySet();
	}

	@Override
	public Map<K, V> getMap() {
		return map;
	}

}
