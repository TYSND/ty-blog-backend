package com.ty_home_backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {
    /**
     * java对象转json字符串
     */
    public static final ObjectWriter writer=new ObjectMapper().writer().withDefaultPrettyPrinter();
    /**
     * json字符串转java对象
     */
    public static final ObjectMapper mapper=new ObjectMapper();
}
