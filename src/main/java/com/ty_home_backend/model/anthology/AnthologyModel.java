package com.ty_home_backend.model.anthology;

import com.ty_home_backend.model.CRUDModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AnthologyModel extends CRUDModel {
    private Integer id;
    private String name;
    private Boolean isHide;
    private Date createTime;
    private Date updateTime;
}
