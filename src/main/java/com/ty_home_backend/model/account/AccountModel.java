package com.ty_home_backend.model.account;

import com.ty_home_backend.model.CRUDModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountModel extends CRUDModel {
    private Integer userId;
    private String account;
    private String jwtSecret;
    private String pwdSalt;
    private String hashedPwd;


}
