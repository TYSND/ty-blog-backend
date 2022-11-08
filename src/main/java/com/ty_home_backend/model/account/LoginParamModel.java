package com.ty_home_backend.model.account;


import com.ty_home_backend.model.CRUDModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginParamModel {
    private String account;
    private String password;
}
