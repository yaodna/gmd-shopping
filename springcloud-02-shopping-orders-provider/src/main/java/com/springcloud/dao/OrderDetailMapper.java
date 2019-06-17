package com.springcloud.dao;

import com.springcloud.entity.OrderDetail;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer orderDetailId);

    int insert(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer orderDetailId);

    List<OrderDetail> selectAll();

    int updateByPrimaryKey(OrderDetail record);
    
    
    /**
             *    查询指定订单编号的订单明细信息
     * @param orderId	订单编号
     * @return	成功返回java.util.List<OrderDetail>类型的实例，发展返回努力了
     */
    public abstract List<OrderDetail> selectByOrderId(Integer orderId);
}