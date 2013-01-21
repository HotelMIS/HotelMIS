package com.fly.hotelmis.login.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fly.common.action.BaseAction;
import com.fly.hotelmis.login.service.LoginService;
import com.fly.hotelmis.login.vo.UsersVo;

@Controller("LoginAction")
@Scope("prototype")
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	@Resource
	private LoginService loginService;

	// @Action(value = "/login", results =
	// { @Result(name = "success", location = "desktop.jsp",type="redirect")
	// @Result(name="input",location="login.jsp")})}
	
	public String login() throws Exception {
		// UserDetails ud = (UserDetails) SecurityContextHolder.getContext()
		// .getAuthentication().getPrincipal();
		// // 利用 UserDetails 对象 对数据库进行任何与 user相关的操作
		// Collection collection = ud.getAuthorities();
		// Iterator it = collection.iterator();
		// while (it.hasNext()) {
		// GrantedAuthority ga = (GrantedAuthority) it.next();
		// System.out.println("ga.getAuthority()================="
		// + ga.getAuthority());
		// }


		boolean checkUser = loginService.checkUser(username, password);
		if (checkUser == true) {
			UsersVo usersVo = new UsersVo();
			usersVo.setPassword(password);
			usersVo.setUsername(username);
			request.getSession().setAttribute("LONGIN_USER", usersVo);
		} else {
			request.setAttribute("msg", "账户或密码错误");
			return INPUT;
		}
		return "success";
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
