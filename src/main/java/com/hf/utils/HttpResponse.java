package com.hf.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpResponse {
    private int code;
    private String msg;
    private Object data;
}
