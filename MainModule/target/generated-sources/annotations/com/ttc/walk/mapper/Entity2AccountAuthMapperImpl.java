package com.ttc.walk.mapper;

import com.ttc.walk.model.AccountAuth;
import com.ttc.walk.model.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-25T00:30:00+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class Entity2AccountAuthMapperImpl implements Entity2AccountAuthMapper {

    @Override
    public List<AccountAuth> mapList(List<UserEntity> sources) {
        if ( sources == null ) {
            return null;
        }

        List<AccountAuth> list = new ArrayList<AccountAuth>( sources.size() );
        for ( UserEntity userEntity : sources ) {
            list.add( map( userEntity ) );
        }

        return list;
    }

    @Override
    public AccountAuth map(UserEntity source) {
        if ( source == null ) {
            return null;
        }

        AccountAuth accountAuth = new AccountAuth();

        accountAuth.setUserId( source.getId() );
        accountAuth.setUsername( source.getUsername() );
        accountAuth.setPhone( source.getPhone() );
        accountAuth.setStatus( source.getStatus() );

        return accountAuth;
    }

    @Override
    public AccountAuth mapTo(UserEntity source, AccountAuth target) {
        if ( source == null ) {
            return null;
        }

        target.setUserId( source.getId() );
        target.setUsername( source.getUsername() );
        target.setPhone( source.getPhone() );
        target.setStatus( source.getStatus() );

        return target;
    }
}
