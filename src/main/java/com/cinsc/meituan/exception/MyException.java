package com.cinsc.meituan.exception;

import com.cinsc.meituan.enums.ResultEnum;
import lombok.Data;

@Data
public class MyException extends RuntimeException{
    private Integer code;

    public MyException (ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public MyException(Integer code,String msg){
        super(msg);
        this.code = code;
    }

}
