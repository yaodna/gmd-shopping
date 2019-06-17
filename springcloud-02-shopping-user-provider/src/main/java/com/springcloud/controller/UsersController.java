package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.service.UsersService;
import com.springcloud.vo.ResultValue;

/**
 * 控制层：接收视图层的数据，并调用模型层相应的方法，将数据返回到视图层中
 * 
 * @author window
 *
 */
@RestController
public class UsersController {
	@Autowired
	private UsersService usersService;

	/**
	 * 用户登录 用户信息
	 * 
	 * @param userId       用户编号
	 * @param userPassword 用户密码
	 * @param jdictionId   用户权限
	 * @return 成功返回0，失败返回1
	 */
	@RequestMapping(value = "/login")
	public ResultValue login(@RequestParam("userId") Integer userId, @RequestParam("userPassword") String userPassword,
			@RequestParam("jdictionId") Integer jdictionId) {
		ResultValue rv = new ResultValue();
		try {
			Users login = this.usersService.login(userId, userPassword, jdictionId);
			if (login != null) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("loginUser", login);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("登录信息不正确，请重新输入！！！！");
		return rv;
	}

	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Users insert = this.usersService.insert(users);
			if (insert != null) {
				rv.setCode(0);
				rv.setMessage("用户录入成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户录入失败!!!!!");
		return rv;
	}

	@RequestMapping(value = "select")
	public ResultValue select(Users users, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			// 获得分页的数据
			List<Users> list = page.getContent();
			// 判断是否查询到了数据
			if (list != null && list.size() > 0) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				// 将分页的数据添加到Map中
				map.put("userList", list);
				PageUtils pageUtils = new PageUtils((int) page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				//将分页信息添加到map中
				map.put("pageUtils", pageUtils);
				// 将Map添加到RequestValue对象中
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	
	/**
	 * 修改users表中指定编号的用户状态
	 * @param userId	用户编号
	 * @param userStatus	用户状态
	 * @return 成功返回0 ，失败返回1
	 */
	@RequestMapping(value = "/updateStatus")
	public ResultValue updateStatus(@RequestParam("userId") Integer userId,@RequestParam("userStatus") Integer userStatus) {
		ResultValue rv = new ResultValue();
		try {
			Integer status = this.usersService.updateStatus(userId, userStatus);
			if(status > 0) {
				rv.setCode(0);
				rv.setMessage("用户状态修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户状态修改失败！！！！");
		return rv;	
	}
	
	@RequestMapping(value = "/select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId){
		ResultValue rv = new ResultValue();
		try {
			//调用service中的方法，并获得查询的结果
			Users selectById = this.usersService.selectById(userId);
			//如果成功
			if(selectById != null) {
				//设置结果状态为0
				rv.setCode(0);				
				//创建Map集合保存查询结果
				Map<String, Object> map = new HashMap<>();
				//将查询结果保存到Map集合中
				map.put("user", selectById);				
				//将Map集合添加到ResultValue对象中
				rv.setDataMap(map);				
				//返回ResultValue对象
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("获得用户信息失败！！");
		return rv;
	}
	
	@RequestMapping(value = "/update")
	public ResultValue update(Users users) {
		ResultValue rv = new ResultValue();
		try {
			//调用service中的方法，并获得查询的结果
			Integer update = this.usersService.update(users);
			//如果修改成功
			if(update != null) {
				//设置结果的状态为0
				rv.setCode(0);												
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息修改失败！！！！");
		return rv;	
	}
	
	@RequestMapping(value = "/updateMessage")
	public ResultValue updateMessage(Users users) {
		ResultValue rv = new ResultValue();
		try {
			//调用service中的方法，并获得查询的结果
			Integer message = this.usersService.updateMessage(users);
			
			//如果修改成功
			if(message > 0) {
				//设置结果的状态为0
				rv.setCode(0);
				//修改成功，设置提示信息
				rv.setMessage("用户信息修改成功！！");
				//返回ResultValue对象
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//修改失败，设置结果的状态为0
		rv.setCode(1);
		//修改失败，设置提示信息
		rv.setMessage("用户信息修改失败！！！");
		//返回ResultValue对象
		return rv;
	}
}
