package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CLASS1表对应的实体类，用于保存表中一行一级的信息
 * @author window	yaodan
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class1 {
	/**
	 * 类别1编号
	 */
    private Integer class1Id;

    /**
	 * 类别1名字
	 */
    private String class1Name;

    /**
	 * 类别1序号
	 */
    private Integer class1Num;//序号

    /**
	 * 备注
	 */
    private String remark;
}