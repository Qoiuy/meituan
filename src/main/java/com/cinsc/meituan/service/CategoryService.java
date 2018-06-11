package com.cinsc.meituan.service;

import java.util.List;

public interface CategoryService {
    Object addCat(String token, Integer sequence, String catName);
    Object updateCat(String token,String oldCatName,String catName);
    List queryCatList(String token);
    Object deleteCat(String token,String catName);
}
