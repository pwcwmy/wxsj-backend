package com.ruoyi.oss.model.vo;

import lombok.Data;
import lombok.NonNull;

@Data
public class Login {
    @NonNull
    private Integer username;
    @NonNull
    private String password;
}
