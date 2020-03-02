package de.tr7zw.trutils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SingletonMap<K, V> implements TRMap<K, V>{

	private final Map<K, V> kvMap;
	private final Map<V, K> vkMap;
	
	public SingletonMap() {
		kvMap = new HashMap<>();
		vkMap = new HashMap<>();
	}
	
	private SingletonMap(SingletonMap<V, K> src) {
		kvMap = src.vkMap;
		vkMap = src.kvMap;
	}
	
	public SingletonMap<V, K> getInverseMap(){
		return new SingletonMap<>(this);
	}
	
	@Override
	public int size() {
		return kvMap.size();
	}

	@Override
	public boolean isEmpty() {
		return kvMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return kvMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return vkMap.containsKey(value);
	}

	@Override
	public V get(Object key) {
		return kvMap.get(key);
	}
	
	public K getKey(Object value) {
		return vkMap.get(value);
	}

	@Override
	public V put(K key, V value) {
		if(vkMap.containsKey(value)) {
			throw new NullPointerException("The value '" + value + "' is in use!");
		}
		if(kvMap.containsKey(key)) {
			throw new NullPointerException("The key '" + key + "' is in use!");
		}
		vkMap.put(value, key);
		return kvMap.put(key, value);
	}

	@Override
	public V remove(Object key) {
		V val = kvMap.remove(key);
		if(val != null)
			vkMap.remove(val);
		return val;
	}
	
	public K removeValue(Object value) {
		K key = vkMap.remove(value);
		if(key != null)
			kvMap.remove(key);
		return key;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for(Entry<? extends K, ? extends V> ent : m.entrySet()) {
			put(ent.getKey(), ent.getValue());
		}
	}

	@Override
	public void clear() {
		kvMap.clear();
		vkMap.clear();
	}

	@Override
	public Set<K> keySet() {
		return kvMap.keySet();
	}

	@Override
	public Collection<V> values() {
		return kvMap.values();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return kvMap.entrySet();
	}

	@Override
	public Map<K, V> getMap() {
		return kvMap;
	}

}
