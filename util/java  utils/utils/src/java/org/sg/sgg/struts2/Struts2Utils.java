package org.sg.sgg.struts2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Struts2Utils {
	
	private static final Logger logger = Logger.getLogger(Struts2Utils.class);
	private static final String[] daoCfgs = new String[] { "config/applicationContext.xml", "config/applicationContext-dao.xml" };
	/**
	 * 返回HttpServletRequest对象
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request;
	}
	
	/**
	 * 返回HttpServletResponse对象
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		return response;
	}
	
	/**
	 * 返回HttpSession对象
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	/**
	 * 返回请求的参数值
	 * 
	 * @return
	 */
	public static String getParameter(String key) {
		String value = getRequest().getParameter(key);
		return value;
	}
	/**
	 * 往HttpServletRequest对象里添加值
	 * 
	 * @return
	 */
	public static void setRequest(String key, Object value) {
		getRequest().setAttribute(key, value);
	}
	
	/**
	 * 往HttpSession对象里添加值
	 * 
	 * @return
	 */
	public static void setSession(String key, Object value) {
		getRequest().getSession().setAttribute(key, value);
	}
	
	/**
	 * 返回spring配置中的bean
	 * 
	 * @param str
	 *            bean的id
	 * @return
	 */
	public static Object getBean(String str) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				daoCfgs);
		return context.getBean(str);
	}
}
