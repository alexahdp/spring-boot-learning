package com.alexahdp.spring.mapper;

public interface Mapper<F, T> {
    T  map(F object);

    default T map(F object, T target) {
        return target;
    }
}
