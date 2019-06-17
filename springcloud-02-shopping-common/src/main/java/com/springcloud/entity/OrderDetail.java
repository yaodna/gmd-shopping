package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * order_detail表对应的实体类，用于封装一行订单明细信息
 * 
 * @author window
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7405309828011124128L;

	/**
	 * //订单明细编号
	 */
	private Integer orderDetailId;

	/**
	 * //订单编号
	 */
	private Integer orderId;

	/**
	 * //商品编号
	 */
	private Integer goodsId;

	/**
	 * //商品名称
	 */
	private String goodsName;

	/**
	 * //成交价
	 */
	private Double transactionPrice;

	/**
	 * //成交数量
	 */
	private Integer transactionCount;

	/**
	 * orderDetailId orderId goodsId goodsName transactionPrice transactionCount
	 */

}