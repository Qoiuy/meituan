package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    OrderMaster findByOrderId(String orderId);
    //订单信息不用删除
    List<OrderMaster> findByOrderStatus(Integer orderStatus);
    List<OrderMaster> findByPayStatus(Integer payStatus);
}
