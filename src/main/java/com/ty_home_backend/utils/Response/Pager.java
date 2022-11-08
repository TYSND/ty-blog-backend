package com.ty_home_backend.utils.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

@ApiModel("分页参数")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pager {
    private int pageNum;
    private int pageSize;
    @JsonIgnore
    public int getOffset(){
        return (pageNum-1)*pageSize;
    }
    @JsonIgnore
    public int getLimit(){
        return pageSize;
    }
}
