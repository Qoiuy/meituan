package com.cinsc.meituan.service;

import com.cinsc.meituan.DTO.MTDish;

import java.io.File;

public interface ImageService {
    public String upLoadImage(String token, String ePoiId, String imageName, File file);
    public Object updateDishImage(String token, MTDish dish, File file);
}
