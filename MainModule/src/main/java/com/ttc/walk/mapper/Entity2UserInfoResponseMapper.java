package com.ttc.walk.mapper;


import com.ttc.walk.model.entity.UserEntity;
import com.ttc.walk.response.UserInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Entity2UserInfoResponseMapper extends BeanMapper<UserEntity, UserInfoResponse> {
    Entity2UserInfoResponseMapper INSTANCE = Mappers.getMapper(Entity2UserInfoResponseMapper.class);


    UserInfoResponse map(UserEntity source);

    UserInfoResponse mapTo(UserEntity source,@MappingTarget UserInfoResponse target);
}
