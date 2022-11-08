package com.ty_home_backend.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty_home_backend.model.CRUDModel;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel(value = "文章实体类结构")
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleModel extends CRUDModel {
    private Integer id;
    private String title;
    private String content;
//    @JsonIgnore
    private String description;
//    @JsonIgnore
    private Date createTime;
//    @JsonIgnore
    private Date updateTime;
    private Boolean isDelete;
    private Boolean isPublish;

    private Integer anthologyId;
//    @JsonIgnore
    private String anthologyName;
}
