package de.tr7zw.trutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TRUtils {

	public static <E> TRCollection<E> wrap(Collection<E> collection){
		return new TRCollectionWrapper<>(collection);
	}
	
	public static <E> TRList<E> wrap(List<E> list){
		return new TRListWrapper<>(list);
	}
	
	public static <E> TRSet<E> wrap(Set<E> set){
		return new TRSetWrapper<>(set);
	}
	
	public static <K, V> TRMap<K, V> wrap(Map<K, V> baseMap){
		return new TRMapWrapper<>(baseMap);
	}
	
	public static <E> TRSet<E> createHashSet(){
		return wrap(new HashSet<>());
	}
	
	public static <K, V> TRMap<K, V> createHashMap(){
		return wrap(new HashMap<K, V>());
	}
	
	public static <E> TRList<E> createArrayList(){
		return wrap(new ArrayList<E>());
	}
	
	@SafeVarargs
	public static <E> TRList<E> createList(E... entries){
		TRList<E> list = createArrayList();
		for(int i = 0; i < entries.length; i++)
			list.add(entries[i]);
		return list;
	}
	
	@SafeVarargs
	public static <E> TRSet<E> createSet(E... entries){
		TRSet<E> set = createHashSet();
		for(int i = 0; i < entries.length; i++)
			set.add(entries[i]);
		return set;
	}
	
}
