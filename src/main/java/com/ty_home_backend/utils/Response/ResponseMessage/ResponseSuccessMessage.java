package com.ty_home_backend.utils.Response.ResponseMessage;

import com.ty_home_backend.utils.Response.DataObj;

public class ResponseSuccessMessage extends ResponseMessage {
    public ResponseSuccessMessage(DataObj dataObj) {
        super("200", dataObj,null);
    }
    public ResponseSuccessMessage() {
        super("200",null,null );
    }
}
