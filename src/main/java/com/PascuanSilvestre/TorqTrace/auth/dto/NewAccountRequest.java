package com.PascuanSilvestre.TorqTrace.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record NewAccountRequest(
        @NotEmpty @Length(min = 8) String username,
        @NotEmpty @Length(min = 8)String password,
        @Email String email
){
}
