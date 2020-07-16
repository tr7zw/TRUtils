package de.tr7zw.trutils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class AutoMap<K, V> implements TRMap<K, V>{

	private final Supplier<Set<K>> backingSet;
	private final Function<K, V> creator;
	
	private final TRMap<K, V> map = TRUtils.createHashMap();
	
	public AutoMap(Supplier<Set<K>> backing, Function<K, V> creator) {
		this.backingSet = backing;
		this.creator = creator;
	}
	
	private void update() {
		Collection<K> backing = backingSet.get();
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
		return backingSet.get().isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return backingSet.get().contains(key);
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
		update();
		if(map.containsKey(key)) {
			V tmp = map.get(key);
			backingSet.get().remove(key);
			return tmp;
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		backingSet.get().clear();
	}

	@Override
	public Set<K> keySet() {
		return backingSet.get();
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
