package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;

import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber) {
		if(orders.getUser() != null) {
			// 在商品名称两端加上模糊查询%
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}
		// 设置分页的信息
		PageHelper.startPage(pageNumber + 1, PageUtils.PAGE_ROW_COUNT);
		List<Orders> list = this.ordersMapper.selectOrders(orders);
		return new PageInfo<Orders>(list);// 实体类可以写也可以不写PageInfo<>
	}

	@Transactional  //事务增删改需要事务
	@Override
	public Integer updateOrdersStatus(Orders orders) {
		return this.ordersMapper.updateOrdersSatus(orders);
	}

	/**
	 *  查询 ：根据年月查询前10 的销售额
	 */
	@Override
	public List<Orders> selectGroup(Orders orders) {
		return this.ordersMapper.selectGrup(orders);
	}
}
