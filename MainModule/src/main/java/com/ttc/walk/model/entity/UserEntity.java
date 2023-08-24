package com.ttc.walk.model.entity;

import com.ttc.spring.entity.Auditable;
import com.ttc.walk.num.AccountStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "user")
public class UserEntity extends Auditable<String> {
    @Column(name = "fullName", length = 100)
    private String fullName;
    @Column(name = "username", length = 100)
    private String username;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "email",  length = 50)
    private String email;
    @Column(length = 100, nullable = false)
    private String hashPassword;
    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    AccountStatus status;
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
