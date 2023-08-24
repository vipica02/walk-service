package com.ttc.walk.mapper;


import com.ttc.walk.model.AccountAuth;
import com.ttc.walk.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Entity2AccountAuthMapper extends BeanMapper<UserEntity, AccountAuth> {
    Entity2AccountAuthMapper INSTANCE = Mappers.getMapper(Entity2AccountAuthMapper.class);


    @Mapping(source = "id", target = "userId")
    AccountAuth map(UserEntity source);

    @Mapping(source = "id", target = "userId")
    AccountAuth mapTo(UserEntity source,@MappingTarget AccountAuth target);
}
