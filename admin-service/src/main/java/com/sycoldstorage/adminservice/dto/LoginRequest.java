package com.sycoldstorage.adminservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {
    @NotNull(message = "ID를 입력하세요.")
    @Size(min= 3, message = "ID는 3자리 이상 입력하세요.")
    private String id;

    @NotNull(message = "Password를 입력하세요.")
    @Size(min= 4, message = "Password는 4자리 이상 입력하세요.")
    private String password;
}
