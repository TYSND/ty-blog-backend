package com.ty_home_backend.utils.Response.ResponseMessage;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseFailMessage extends ResponseMessage{
    public ResponseFailMessage(String message) {
        super("-1", null, message);
    }
}
