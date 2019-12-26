package com.restfull.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class ResponseDTO<T> {

    private String code;
    private String msg;

    public ResponseDTO(String code, String msg, T date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }

    private T date;

}
