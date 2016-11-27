package com.dmc.controller;

import com.dmc.model.Error;
import com.dmc.model.Role;
import com.dmc.model.SessionInfo;
import com.dmc.service.RoleService;
import com.dmc.util.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 角色控制器
 * 
 * @author yangfan
 * 
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;


	/**
	 * 添加角色
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Error add(Role role, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
		Error j = new Error();
		roleService.add(role, sessionInfo);

		j.setMsg("添加成功！");
		return j;
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Error edit(Role role) {
		Error j = new Error();
		roleService.edit(role);

		j.setMsg("编辑成功！");
		return j;
	}

	/**
	 * 获得角色列表
	 * 
	 * @return
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Role> treeGrid(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
		return roleService.treeGrid(sessionInfo);
	}

	/**
	 * 角色(只能看到自己拥有的角色)
	 * 
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<Role> tree(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
		return roleService.tree(sessionInfo);
	}

	/**
	 * 角色
	 * 
	 * @return
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Role> allTree() {
		return roleService.allTree();
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Error delete(String id) {
		Error j = new Error();
		roleService.delete(id);
		j.setMsg("删除成功！");

		return j;
	}


	/**
	 * 授权
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public Error grant(Role role) {
		Error j = new Error();
		roleService.grant(role);
		j.setMsg("授权成功！");

		return j;
	}

}