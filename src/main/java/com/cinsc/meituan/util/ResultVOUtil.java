package com.cinsc.meituan.util;

import com.cinsc.meituan.ViewObjetc.ResultVO;
import com.cinsc.meituan.enums.ResultEnum;

import java.util.HashMap;
import java.util.Map;

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }


    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static Map pushSuccess(){
        Map map = new HashMap();
        map.put("data","OK");
        return map;
    }


    public static ResultVO error(Integer code,String message){

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);

        return resultVO;
    }
    public static ResultVO error(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }

}
