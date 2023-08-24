package com.ttc.walk.request;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class UpdateStepsDailyRequest {
    private String stepsCount;
    private Date lastModified;
}
