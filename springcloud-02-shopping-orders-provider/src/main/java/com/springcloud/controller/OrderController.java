package com.springcloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;
import com.springcloud.vo.ResultValue;

/**
 * 订单模块的控制层
 * 
 * @author window
 *
 */
@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private OrdersService ordersService;

	@RequestMapping(value = "/selectOrders")
	public ResultValue selectOrders(Orders orders, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			PageInfo<Orders> pageInfo = this.ordersService.selectOrders(orders, pageNumber);
			List<Orders> list = pageInfo.getList();
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("ordersList", list);
				// 分页信息
				PageUtils pageUtils = new PageUtils(pageInfo.getPages() * PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);

				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
//		rv.setMessage("没有找到满足条件的商品信息");
		return rv;
	}
	
	
	/**
	 *  修改指定编号订单的订单状态
	 * @param orders
	 * @return
	 */
	@RequestMapping(value = "/updateOrdersSatus")
	public ResultValue updateOrdersSatus(Orders orders) {
		ResultValue rv = new ResultValue();
		try {
			Integer ordersStatus = this.ordersService.updateOrdersStatus(orders);
			//如果成功
			if(ordersStatus > 0) {
				rv.setCode(0);				
				rv.setMessage("订单状态修改成功!!!");
				return rv;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("修改订单状态失败！！！");
		return rv;
	}
	
	/**
	 *  查询指定日期范围内的商品销售额
	 * @param orders 查询条件
	 * @return
	 */
	@RequestMapping(value = "/selectGrup")
	public ResultValue selectGrup(Orders orders) {
		ResultValue rv = new ResultValue();
		try {
			List<Orders> list = this.ordersService.selectGroup(orders);		
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				//创建两个集合，用于保存柱状图x轴与y轴数据
				List<String> x = new ArrayList<>();
				List<Double> y = new ArrayList<>();
				//将查询结果中商品名称存入x集合，销量存入y集合
				for(Orders o : list) {
					x.add(o.getOrderMonth());
					y.add(o.getOrderPrice());
				}
				Map<String, Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				//将Map添加到RequestValue对象中
				rv.setDataMap(map);
				
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("没有找到满足条件的商品销售额！！");
		return rv;
	} 
}
