package com.fly.common.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public abstract  class BaseAction extends ActionSupport  implements ServletRequestAware,ServletResponseAware,SessionAware,ApplicationAware,ModelDriven<Object> {
	protected final String CUSTOM = "custom";
    private String target;
    protected final Log logger = LogFactory.getLog(getClass()); 
    protected HttpServletRequest request;	
	protected HttpServletResponse response;
	
	protected Map<String, Object> session;
	
	protected Map<String, Object> application;
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    } 
    protected String render(String _target){
        setTarget(_target);
        return CUSTOM;
    }


	/* （非 Javadoc）
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	/* （非 Javadoc）
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setSession(Map session) {
		this.session = session;
		
	}

	/* （非 Javadoc）
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	/* （非 Javadoc）
	 * @see org.apache.struts2.interceptor.ApplicationAware#setApplication(java.util.Map)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setApplication(Map application) {
		// TODO 自动生成方法存根
		this.application = application;
		
	}
	
	//实现ModelDriven模式，此处要在实现的action中被覆盖，也可以在这里不实现，直接在action中实现。注意return在action中为对象属性，不能返回null
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
