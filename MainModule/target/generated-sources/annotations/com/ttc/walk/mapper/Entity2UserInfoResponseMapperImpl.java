package com.ttc.walk.mapper;

import com.ttc.walk.model.entity.UserEntity;
import com.ttc.walk.response.UserInfoResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-25T00:30:00+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class Entity2UserInfoResponseMapperImpl implements Entity2UserInfoResponseMapper {

    @Override
    public List<UserInfoResponse> mapList(List<UserEntity> sources) {
        if ( sources == null ) {
            return null;
        }

        List<UserInfoResponse> list = new ArrayList<UserInfoResponse>( sources.size() );
        for ( UserEntity userEntity : sources ) {
            list.add( map( userEntity ) );
        }

        return list;
    }

    @Override
    public UserInfoResponse map(UserEntity source) {
        if ( source == null ) {
            return null;
        }

        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setId( source.getId() );
        userInfoResponse.setUsername( source.getUsername() );
        userInfoResponse.setFullName( source.getFullName() );
        userInfoResponse.setPhone( source.getPhone() );
        userInfoResponse.setEmail( source.getEmail() );
        userInfoResponse.setCreatedDate( source.getCreatedDate() );
        userInfoResponse.setCreatedBy( source.getCreatedBy() );
        userInfoResponse.setLastModifiedDate( source.getLastModifiedDate() );
        userInfoResponse.setLastModifiedBy( source.getLastModifiedBy() );

        return userInfoResponse;
    }

    @Override
    public UserInfoResponse mapTo(UserEntity source, UserInfoResponse target) {
        if ( source == null ) {
            return null;
        }

        target.setId( source.getId() );
        target.setUsername( source.getUsername() );
        target.setFullName( source.getFullName() );
        target.setPhone( source.getPhone() );
        target.setEmail( source.getEmail() );
        target.setCreatedDate( source.getCreatedDate() );
        target.setCreatedBy( source.getCreatedBy() );
        target.setLastModifiedDate( source.getLastModifiedDate() );
        target.setLastModifiedBy( source.getLastModifiedBy() );

        return target;
    }
}
