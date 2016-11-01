package org.sg.sgg.test1;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.sg.sgg.bean.User;
import org.sg.sgg.json.JSONUtils;
import org.sg.sgg.reflect.ReflectionUtils;

public class ReflectionUtilTest {

	@Test
	public void test1() throws Exception {
		User user = new User();
		user.setName("孙刚");
		user.setAge(20);
		Object object = ReflectionUtils.getFieldValue(user, "age");
		if (object != null) {
			System.out.println(object.toString());
		}
	}

	@Test
	public void test2() {
		User user = new User();
		ReflectionUtils.setFieldValue(user, "name", "孙刚");
		System.out.println(user.getName());
	}

	@Test
	public void test3() {
		JSONUtils jsonUtils = new JSONUtils();
		User user = new User();
		user.setName("孙刚");
		user.setAge(21);
		user.setId(1);
		user.setPhone("15893102398");
		user.setSex("男");
		Object object = null;
		try {
			object = ReflectionUtils.invokeMethod(jsonUtils,"toJSONString", new Class[] { JSONUtils.class },new Object[] {user});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (object != null) {
			System.out.println(object.toString());
		}
	}
	
	@Test
	public void test4(){
		User user = new User();
		user.setName("孙刚");
		user.setAge(21);
		user.setId(1);
		user.setPhone("15893102398");
		user.setSex("男");
		/*Class clazz = user.getClass();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			System.out.print(method.getName()+":");
			System.out.println("对应的参数类型：" +method.getParameterTypes());
		}*/
	}
	@Test
	public void test5(){
		User user = (User)ReflectionUtils.getObjByClassName("org.sg.sgg.bean.User");
		System.out.println(user == null ? "没有实例化." : user.getClass());
	}
	@Test
	public void test6(){
		//User user = new User();
		//user.setName("孙刚");
		//user.setAge(21);
		//user.setId(1);
		//user.setPhone("15893102398");
		//user.setSex("男");
		Object object = ReflectionUtils.getObjByClassNameAndParameter("org.sg.sgg.bean.User", new Object[]{1,"孙刚"});
		
	}
	@Test
	public void test7(){
		Object object = ReflectionUtils.getObjByClassName("org.sg.sgg.bean.User");
		if (object instanceof String) {
			String s = (String)object;
		}
	}
}
