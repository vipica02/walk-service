package com.ttc.walk.model.entity;

import com.ttc.spring.entity.Auditable;
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
@Table(name = "walk_data")
public class WalkDataEntity extends Auditable<String> {
    @ManyToOne
    private UserEntity user;

    private Integer stepsCount;
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
