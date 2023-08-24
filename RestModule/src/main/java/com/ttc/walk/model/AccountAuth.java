package com.ttc.walk.model;

import com.dslplatform.json.JsonAttribute;
import com.ttc.walk.num.AccountStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode()
@Data
public class AccountAuth {
    private String username;
    private String phone;
    @JsonAttribute(name = "user_id")
    private String userId;
    private AccountStatus status;
}
