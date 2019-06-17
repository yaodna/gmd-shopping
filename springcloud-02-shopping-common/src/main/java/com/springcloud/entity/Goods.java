package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * GOODS表对应的实体类，用于保存表中商品的信息
 * @author window yaodan
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
	/**
	 * 商品编号
	 */
    private Integer goodsId;

    /**
	 * 商品名称
	 */
    private String goodsName;

    /**
	 * 商品价格
	 */
    private Double goodsPrices;

    /**
	 * 商品折扣价
	 */
    private Double goodsDiscount;

    /**
	 * 商品状态
	 */
    private Integer goodsStatus;

    /**
	 * 商品数量
	 */
    private Double goodsCount;

    /**
	 * 商品是否新品
	 */
    private Integer goodsIsNew;

    /**
	 * 商品是否热卖
	 */
    private Integer goodsIsHot;

    /**
	 * 商品级别
	 */
    private Integer goodsLevel;

    /**
	 * 商品简介
	 */
    private String goodsBrief;

    /**
	 * 商品描述
	 */
    private String goodsDetails;

    /**
	 * 商品照片
	 */
    private String goodsPhoto;

    /**
	 * 类别2编号
	 */
    private Integer class2Id;
   
    /**
     * 查询条件：商品价格上限
     */
    private Double goodsPriceMin;
    
    /**
     * 查询条件：商品价格下限
     */
    private Double goodsPriceMax;
    
    /**
     *查询条件： 类别1编号
     */
    private Integer class1Id;
    
    /**
     * 商品销售，用于保存统计分组的结果
     */
    private Integer goodsSum;

}