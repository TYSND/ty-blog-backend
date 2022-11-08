package com.ty_home_backend.model.picture;

import com.ty_home_backend.model.CRUDModel;
import com.ty_home_backend.utils.Constants.Enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PictureModel extends CRUDModel {

    private Integer picId;
    private File path;
    private String md5;

    private Date createTime;
    private long size;
    private Enums.picType type;

    public PictureModel(File path, String md5, Date createTime, long size, Enums.picType type) {
        this.path = path;
        this.md5 = md5;
        this.createTime = createTime;
        this.size = size;
        this.type = type;
    }
}
