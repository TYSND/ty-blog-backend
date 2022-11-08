package com.ty_home_backend.model.article;

import com.ty_home_backend.model.CRUDModel;
import com.ty_home_backend.utils.Response.Pager;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "文章查询请求参数")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleModelParams {
    @ApiModelProperty("文章标题")
    private String title;
    private Integer anthologyId;
    private Pager pager;
//    public ArticleModelParams(String title, Integer anthologyId, Pager pager) {
//        this.title = title;
//        this.anthologyId = anthologyId;
//        this.pager = pager;
//    }
    public ArticleModelParams(Pager pager) {
        this.pager = pager;
    }
    public ArticleModelParams(Integer pageNum, Integer pageSize) {
        this.pager = new Pager(pageNum, pageSize);
    }
//    public ArticleModelParams(String title, Integer anthologyId) {
//        this.title = title;
//        this.anthologyId = anthologyId;
//        this.pager = new Pager(1, 10);
//    }
}
