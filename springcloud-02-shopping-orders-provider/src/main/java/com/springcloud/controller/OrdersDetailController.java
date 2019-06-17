package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailService;
import com.springcloud.vo.ResultValue;

/**
 * 订单明细模块的控制层
 * 
 * @author window
 *
 */
@RestController
@RequestMapping("orderDetail")
public class OrdersDetailController {
	@Autowired
	private OrderDetailService orderDetailService;

	@RequestMapping(value = "/selectByOrderId")
	public ResultValue selectByOrderId(@RequestParam("orderId") Integer orderId,
			@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			//调用service相应的方法查询所有的一级类别的信息，并保存查询的价格
			PageInfo<OrderDetail> pageInfo = this.orderDetailService.selectByOrderId(orderId, pageNumber);
			// 获得查询页数的信息
			List<OrderDetail> list = pageInfo.getList();
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				// 把获得的页数和订单明细信息传回map
				Map<String, Object> map = new HashMap<>();
				// 把获得信息存入map
				map.put("orderDetailList", list);
				// 分页信息
				PageUtils pageUtils = new PageUtils(5,pageInfo.getPages() * 5);
				// 告诉她我现在是多少页
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);

				// 把map放入ResultValue中
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("没有找到满足条件的订单明细信息！！");
		return rv;
	}
}
