package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.service.DeliverService;
import com.cinsc.meituan.util.MyUtil;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderDeliveredRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutOrderDeliveringRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Slf4j
public class DeliverServiceImpl implements DeliverService{

    @Override
    public Object sendDelivering(String appAuthToken, String orderId, String courierName, String courierPhone) throws IOException, URISyntaxException {
        CipCaterTakeoutOrderDeliveringRequest request = new CipCaterTakeoutOrderDeliveringRequest();
        request.setRequestSysParams(MyUtil.getParams(appAuthToken));
        request.setOrderId(Long.valueOf(orderId));
        request.setCourierName(courierName);
        request.setCourierPhone(courierPhone);
        log.info("商家上传配送自配送正在配送信息..");
        Object result = request.doRequest();
        return result;
    }

    @Override
    public Object sendDelivered(String appAuthToken, String orderId) throws IOException, URISyntaxException {
        CipCaterTakeoutOrderDeliveredRequest request = new CipCaterTakeoutOrderDeliveredRequest();
        request.setRequestSysParams(MyUtil.getParams(appAuthToken));
        request.setOrderId(Long.valueOf(orderId));
        log.info("商家上传配送状态已送达..");
        Object result = request.doRequest();
        return result;
    }
}
