package com.ttc.walk.mapper;

import com.ttc.walk.model.entity.UserEntity;
import com.ttc.walk.request.CreateUserRequest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-25T00:30:00+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class Request2UserEntityMapperImpl implements Request2UserEntityMapper {

    @Override
    public List<UserEntity> mapList(List<CreateUserRequest> sources) {
        if ( sources == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( sources.size() );
        for ( CreateUserRequest createUserRequest : sources ) {
            list.add( map( createUserRequest ) );
        }

        return list;
    }

    @Override
    public UserEntity mapTo(CreateUserRequest source, UserEntity target) {
        if ( source == null ) {
            return null;
        }

        target.setFullName( source.getFullName() );
        target.setUsername( source.getUsername() );
        target.setPhone( source.getPhone() );
        target.setEmail( source.getEmail() );

        return target;
    }

    @Override
    public UserEntity map(CreateUserRequest source) {
        if ( source == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setFullName( source.getFullName() );
        userEntity.setUsername( source.getUsername() );
        userEntity.setPhone( source.getPhone() );
        userEntity.setEmail( source.getEmail() );

        return userEntity;
    }
}
