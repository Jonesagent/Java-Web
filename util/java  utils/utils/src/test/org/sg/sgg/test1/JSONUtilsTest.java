package org.sg.sgg.test1;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.sg.sgg.bean.User;
import org.sg.sgg.json.JSONUtils;

public class JSONUtilsTest {
	User user = null;

	/**
	 * 测试将对象转换为List对象
	 */
	@Test
	public void test1() {
		user = new User();
		user.setId(1);
		user.setName("孙刚");
		user.setAge(21);
		user.setSex("男");
		user.setPhone("15893102398");
		user.setAddress("河南驻马店");
		List<User> users = JSONUtils.toArrayList(user);
		System.out.println("结束................................");
	}

	@Test
	public void test2() {
		user = new User();
		user.setId(1);
		user.setName("孙刚");
		user.setAge(21);
		user.setSex("男");
		user.setPhone("15893102398");
		user.setAddress("河南驻马店");
		Collection c = JSONUtils.toCollection(user);
		System.out.println("结束................................");
	}

	@Test
	public void test3() {
		user = new User();
		user.setId(1);
		user.setName("孙刚");
		user.setAge(21);
		user.setSex("男");
		user.setPhone("15893102398");
		user.setAddress("河南驻马店");
		JSONArray c = JSONUtils.toJSONArray(user);
		System.out.println("结束................................");
	}

	@Test
	public void test4() {
		user = new User();
		user.setId(1);
		user.setName("孙刚");
		user.setAge(21);
		user.setSex("男");
		user.setPhone("15893102398");
		user.setAddress("河南驻马店");
		JSONObject c = JSONUtils.toJSONObject(user);
		System.out.println("结束................................");
	}

	@Test
	public void test5() {
		user = new User();
		user.setId(1);
		user.setName("孙刚");
		user.setAge(21);
		user.setSex("男");
		user.setPhone("15893102398");
		user.setAddress("河南驻马店");
		HashMap map = JSONUtils.toHashMap(user);
		System.out.println("结束................................");
	}

	@Test
	public void test6() {
		user = new User();
		user.setId(1);
		user.setName("孙刚");
		user.setAge(21);
		user.setSex("男");
		user.setPhone("15893102398");
		user.setAddress("河南驻马店");
		List<Map<String, Object>> list = JSONUtils.toList(user);
		System.out.println("结束................................");
	}

	@Test
	public void test7() {
		user = new User();
		user.setId(1);
		user.setName("孙刚");
		user.setAge(21);
		user.setSex("男");
		user.setPhone("15893102398");
		user.setAddress("河南驻马店");
		JSONArray ja = JSONUtils.toJSONArray(user);
		List<User> list = (List<User>) JSONUtils.toList(ja, user.getClass());
		System.out.println("结束................................");
	}

	@Test
	public void test8() {
		user = new User();
		user.setId(1);
		user.setName("孙刚");
		user.setAge(21);
		user.setSex("男");
		user.setPhone("15893102398");
		user.setAddress("河南驻马店");
		List<User> list = (List<User>) JSONUtils.toList(user, user.getClass());
		System.out.println("结束................................");
	}

	@Test
	public void test9() {
		user = new User();
		user.setId(1);
		user.setName("孙刚");
		user.setAge(21);
		user.setSex("男");
		user.setPhone("15893102398");
		user.setAddress("河南驻马店");
		List<User> list = (List<User>) JSONUtils.toList(user, user.getClass());
		System.out.println("结束................................");
	}
}
