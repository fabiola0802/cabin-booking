package com.ikub.converter;

public interface BaseConverter<T, S> {

	T toEntity(S dto);

	S toDto(T entity);

}
