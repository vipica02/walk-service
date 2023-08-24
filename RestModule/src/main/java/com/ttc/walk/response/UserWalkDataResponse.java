package com.ttc.walk.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserWalkDataResponse {
    private String userId;
    private String fullName;
    private Integer totalSteps;
    private Date startDate;
    private Date endDate;
}
