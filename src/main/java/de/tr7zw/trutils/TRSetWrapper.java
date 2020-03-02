package de.tr7zw.trutils;

import java.util.Set;

public class TRSetWrapper<E> extends TRCollectionWrapper<E> implements TRSet<E>{

	private final Set<E> set;
	
	protected TRSetWrapper(Set<E> set) {
		super(set);
		this.set = set;
	}

	@Override
	public Set<E> getSet() {
		return set;
	}

}
