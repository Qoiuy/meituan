package com.cinsc.meituan.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface DeliverService {
    public Object sendDelivering(String appAuthToken,String orderId,String courierName,String courierPhone) throws IOException, URISyntaxException;
    public Object sendDelivered(String appAuthToken,String orderId) throws IOException, URISyntaxException;
}
