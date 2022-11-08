package com.ty_home_backend.utils.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataObj {
    private Object data;
    private Integer totalNumber;

    public DataObj(Object data) {
        this.data = data;
    }

    public DataObj(String data) {
        this.data = data;
    }

}
