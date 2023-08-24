package com.ttc.walk.mapper;

import com.ttc.walk.model.entity.UserEntity;
import com.ttc.walk.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Request2UserEntityMapper extends BeanMapper<CreateUserRequest, UserEntity>{
    Request2UserEntityMapper INSTANCE = Mappers.getMapper(Request2UserEntityMapper.class);

    @Override
    UserEntity map(CreateUserRequest source);
}
