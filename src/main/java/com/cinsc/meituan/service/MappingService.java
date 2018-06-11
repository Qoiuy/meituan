package com.cinsc.meituan.service;



public interface MappingService {
    public Object dishesMapping(String token,String ePoiId,String dishMappings);//dishMappings需要array输出成字符串型
    public Object mappingAllDishes(String token,String ePoiId);
}
