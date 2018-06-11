package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    OrderDetail findByDetailId(long detailId);
    List<OrderDetail> findByOrderId(String orderId);
}
