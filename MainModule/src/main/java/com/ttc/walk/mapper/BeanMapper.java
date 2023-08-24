package com.ttc.walk.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;


public interface BeanMapper<S, T> {
  T map(S source);

  List<T> mapList(List<S> sources);

  T mapTo(S source, @MappingTarget T target);
}
