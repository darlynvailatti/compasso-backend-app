package com.compasso.backend.app.util;

import java.util.Collection;
import java.util.Objects;

public class ExpectThat {
	
	
	public static final <T> boolean isNotNull(T o) {
		return !Objects.isNull(o);
	}
	
	public static final <T> boolean isNull(T o) {
		return Objects.isNull(o);
	}
	
	public static final <T> boolean isNotNullAndNotEmpty(Collection<T> collection) {
		if(isNotNull(collection))
			return !collection.isEmpty();
		return false;
	}

	public static final <T> boolean isNullAndEmpty(Collection<T> collection){
		return !isNotNullAndNotEmpty(collection);
	}
	
	public static final boolean isNotNullAndNotEmpty(String string) {
		if(isNotNull(string))
			return !string.isEmpty();
		return false;
	}

	public static final boolean isNullAndEmpty(String string){
		return !isNotNullAndNotEmpty(string);
	}

}
