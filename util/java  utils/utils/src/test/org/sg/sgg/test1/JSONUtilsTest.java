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
	 * ���Խ�����ת��ΪList����
	 */
	@Test
	public void test1() {
		user = new User();
		user.setId(1);
		user.setName("���");
		user.setAge(21);
		user.setSex("��");
		user.setPhone("15893102398");
		user.setAddress("����פ���");
		List<User> users = JSONUtils.toArrayList(user);
		System.out.println("����................................");
	}

	@Test
	public void test2() {
		user = new User();
		user.setId(1);
		user.setName("���");
		user.setAge(21);
		user.setSex("��");
		user.setPhone("15893102398");
		user.setAddress("����פ���");
		Collection c = JSONUtils.toCollection(user);
		System.out.println("����................................");
	}

	@Test
	public void test3() {
		user = new User();
		user.setId(1);
		user.setName("���");
		user.setAge(21);
		user.setSex("��");
		user.setPhone("15893102398");
		user.setAddress("����פ���");
		JSONArray c = JSONUtils.toJSONArray(user);
		System.out.println("����................................");
	}

	@Test
	public void test4() {
		user = new User();
		user.setId(1);
		user.setName("���");
		user.setAge(21);
		user.setSex("��");
		user.setPhone("15893102398");
		user.setAddress("����פ���");
		JSONObject c = JSONUtils.toJSONObject(user);
		System.out.println("����................................");
	}

	@Test
	public void test5() {
		user = new User();
		user.setId(1);
		user.setName("���");
		user.setAge(21);
		user.setSex("��");
		user.setPhone("15893102398");
		user.setAddress("����פ���");
		HashMap map = JSONUtils.toHashMap(user);
		System.out.println("����................................");
	}

	@Test
	public void test6() {
		user = new User();
		user.setId(1);
		user.setName("���");
		user.setAge(21);
		user.setSex("��");
		user.setPhone("15893102398");
		user.setAddress("����פ���");
		List<Map<String, Object>> list = JSONUtils.toList(user);
		System.out.println("����................................");
	}

	@Test
	public void test7() {
		user = new User();
		user.setId(1);
		user.setName("���");
		user.setAge(21);
		user.setSex("��");
		user.setPhone("15893102398");
		user.setAddress("����פ���");
		JSONArray ja = JSONUtils.toJSONArray(user);
		List<User> list = (List<User>) JSONUtils.toList(ja, user.getClass());
		System.out.println("����................................");
	}

	@Test
	public void test8() {
		user = new User();
		user.setId(1);
		user.setName("���");
		user.setAge(21);
		user.setSex("��");
		user.setPhone("15893102398");
		user.setAddress("����פ���");
		List<User> list = (List<User>) JSONUtils.toList(user, user.getClass());
		System.out.println("����................................");
	}

	@Test
	public void test9() {
		user = new User();
		user.setId(1);
		user.setName("���");
		user.setAge(21);
		user.setSex("��");
		user.setPhone("15893102398");
		user.setAddress("����פ���");
		List<User> list = (List<User>) JSONUtils.toList(user, user.getClass());
		System.out.println("����................................");
	}
}
