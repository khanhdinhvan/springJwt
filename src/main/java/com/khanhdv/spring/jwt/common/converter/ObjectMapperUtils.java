package com.khanhdv.spring.jwt.common.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapperUtils {

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <T, D> D map(final T entity, Class<D> dto) {
        return modelMapper.map(entity, dto);
    }

    public static <T, D> List<D> mapAll(final Collection<T> entityList, Class<D> dto) {
        return entityList.stream()
                .map(entity -> map(entity, dto))
                .collect(Collectors.toList());
    }

    public static <D, T> T map(final D dto, T entity) {
        modelMapper.map(dto, entity);
        return entity;
    }
}