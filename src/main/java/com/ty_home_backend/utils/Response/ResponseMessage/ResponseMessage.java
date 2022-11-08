package com.ty_home_backend.utils.Response.ResponseMessage;

import com.ty_home_backend.utils.Response.DataObj;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseMessage {
    public String code;
    public DataObj data;
    public String msg;

}
