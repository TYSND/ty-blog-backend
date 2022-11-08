package com.ty_home_backend.model.account;

import com.ty_home_backend.model.CRUDModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoModel extends CRUDModel {
    private Integer userId;
    private String account  ;
}
