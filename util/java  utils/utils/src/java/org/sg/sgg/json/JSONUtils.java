package org.sg.sgg.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

public class JSONUtils {

	/**
	 * ��List�������л�ΪJSON�ı�
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> String toJSONString(List<T> list) {
		JSONArray js = JSONArray.fromObject(list);
		return js.toString();
	}

	/**
	 * ���������л�ΪJSON�ı�
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object object) {
		JSONObject jo = JSONObject.fromObject(object);
		return jo.toString();
	}

	/***
	 * ��JSON�����������л�ΪJSON�ı�
	 * 
	 * @param ja
	 * @return
	 */
	public static String toJSONString(JSONArray ja) {
		return ja.toString();
	}

	/**
	 * ��JSON�������л�ΪJSON�ı�
	 * 
	 * @param jo
	 * @return
	 */
	public static String toJSONString(JSONObject jo) {
		return jo.toString();
	}

	/**
	 * ������ת��ΪList����
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toArrayList(Object object) {
		List arrList = new ArrayList();
		JSONArray ja = JSONArray.fromObject(object);
		Iterator<?> iterator = ja.iterator();
		while (iterator.hasNext()) {
			JSONObject jo = (JSONObject) iterator.next();
			Iterator<?> keys = jo.keys();
			while (keys.hasNext()) {
				Object key = keys.next();
				Object value = jo.get(key);
				arrList.add(value);
			}
		}
		return arrList;
	}

	/**
	 * ������ת��ΪCollection����
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Collection toCollection(Object object) {
		JSONArray ja = JSONArray.fromObject(object);
		return JSONArray.toCollection(ja);
	}

	/**
	 * ������ת��ΪJSON��������
	 * 
	 * @param object
	 * @return
	 */
	public static JSONArray toJSONArray(Object object) {
		return JSONArray.fromObject(object);
	}

	/***
	 * ������ת��ΪJSON����
	 * 
	 * @param object
	 * @return
	 */
	public static JSONObject toJSONObject(Object object) {
		return JSONObject.fromObject(object);
	}

	/**
	 * ��һ��json����ת����list����
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> json2List(String jsonStr) {
		JSONArray ja = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = ja.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(json2Map(json2));
		}
		return list;
	}

	/**
	 * ��һ��json����ת����list����
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> json2List(JSONArray ja) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = ja.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(json2Map(json2));
		}
		return list;
	}

	/**
	 * ��һ��json����ת����Map����
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jo = JSONObject.fromObject(jsonStr);
		for (Object key : jo.keySet()) {
			Object value = jo.get(key);
			if (value instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> iterator = ((JSONArray) value).iterator();
				while (iterator.hasNext()) {
					JSONObject jo2 = iterator.next();
					list.add(json2Map(jo2.toString()));
				}
				map.put(key.toString(), value);
			} else {
				map.put(key.toString(), value);
			}
		}
		return map;
	}

	/**
	 * ��һ��json����ת����Map����
	 * 
	 * @param jo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(JSONObject jo) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object key : jo.keySet()) {
			Object value = jo.get(key);
			if (value instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> iterator = ((JSONArray) value).iterator();
				while (iterator.hasNext()) {
					JSONObject jo2 = iterator.next();
					list.add(json2Map(jo2.toString()));
				}
				map.put(key.toString(), value);
			} else {
				map.put(key.toString(), value);
			}
		}
		return map;
	}
	
	public static String map2Json(Map<Object, Object> map){
		 if (null == map) {
			return null;
		}
		JSONObject jo = JSONObject.fromObject(map);
		return jo.toString();
	}

	/**
	 * //ͨ��HTTP url��ȡJSON����
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> getListByUrl(String url) {
		StringBuilder builder = null;
		try {
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			builder = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json2List(builder.toString());
	}

	/**
	 * //ͨ��HTTP url��ȡJSON����
	 * 
	 * @return
	 */
	public static Map<String, Object> getMapByUrl(String url) {
		StringBuilder builder = null;
		try {
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			String line = "";
			builder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json2Map(builder.toString());
	}

	/**
	 * ������ת��ΪHashMap
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static HashMap toHashMap(Object object) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject jo = JSONUtils.toJSONObject(object);
		Iterator iterator = jo.keys();
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			String key = "";
			if (obj instanceof String) {
				key = (String) obj;
			}
			Object value = jo.get(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * ������ת��ΪList<Map<String,Object>> ���ط�ʵ������(Map<String,Object>)��List
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map<String, Object>> toList(Object object) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray ja = JSONArray.fromObject(object);
		for (Object obj : ja) {
			if (obj instanceof JSONObject) {
				JSONObject jo = (JSONObject) obj;
				Map<String, Object> map = new HashMap<String, Object>();
				Iterator iterator = jo.keys();
				while (iterator.hasNext()) {
					Object o = iterator.next();
					String key = "";
					if (o instanceof String) {
						key = (String) o;
					}
					Object value = jo.get(key);
					map.put(key, value);
				}
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * ��JSON��������ת��Ϊ�������͵�List
	 * 
	 * @param <T>
	 * @param ja
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> List<T> toList(JSONArray ja, Class<T> clazz) {
		return JSONArray.toList(ja, clazz);
	}

	/**
	 * ������ת��Ϊ�������͵�List
	 * 
	 * @param <T>
	 * @param object
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> List<T> toList(Object object, Class<T> clazz) {
		JSONArray ja = JSONArray.fromObject(object);
		return JSONArray.toList(ja, clazz);
	}

	/**
	 * ��JSON����ת��Ϊ�������͵Ķ���
	 * 
	 * @param <T>
	 * @param jo
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public static <T> T toBean(JSONObject jo, Class<T> clazz) {
		return (T) jo.toBean(jo, clazz);
	}

	/**
	 * ��������ת��Ϊ�������͵Ķ���
	 * 
	 * @param <T>
	 * @param object
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public static <T> T toBean(Object object, Class<T> clazz) {
		JSONObject jo = JSONObject.fromObject(object);
		return (T) jo.toBean(jo, clazz);
	}

	/***
	 * ��JSON�ı������л�Ϊ���ӹ�ϵ��ʵ��
	 * 
	 * @param <T>
	 *            ����T ������ʵ������
	 * @param <D>
	 *            ����D �����ʵ������
	 * @param jsonString
	 *            JSON�ı�
	 * @param mainClass
	 *            ��ʵ������
	 * @param detailName
	 *            ��ʵ��������ʵ�����е���������
	 * @param detailClass
	 *            ��ʵ������
	 * @return
	 */
	@SuppressWarnings("unused")
	public static <T, D> T toBean(String jsonString, Class<T> mainClass,
			String detailName, Class<D> detailClass) {
		JSONObject jo = JSONObject.fromObject(jsonString);
		JSONArray ja = (JSONArray) jo.get(detailName);

		T mainEntity = JSONUtils.toBean(jo, mainClass);
		List<D> detailList = JSONUtils.toList(ja, detailClass);

		try {
			BeanUtils.setProperty(mainEntity, detailName, detailClass);

		} catch (Exception e) {
			throw new RuntimeException("���ӹ�ϵJSON�����л�ʵ��ʧ�ܣ�");
		}
		return mainEntity;
	}

	/**
	 * ��JSON�ı������л�Ϊ���ӹ�ϵ��ʵ��
	 * 
	 * @param <T>>����T ������ʵ������
	 * @param <D1>����D1 �����ʵ������
	 * @param <D2>����D2 �����ʵ������
	 * @param jsonStringJSON�ı�
	 * @param maiinClass
	 *            ��ʵ������
	 * @param detailName1
	 *            ��ʵ��������ʵ�����е�����
	 * @param detailClass1
	 *            ��ʵ������
	 * @param detailName2
	 *            ��ʵ��������ʵ�����е�����
	 * @param detailClass2
	 *            ��ʵ������
	 * @return
	 */
	@SuppressWarnings("unused")
	public static <T, D1, D2> T toBean(String jsonString, Class<T> maiinClass,
			String detailName1, Class<D1> detailClass1, String detailName2,
			Class<D2> detailClass2) {
		JSONObject jo = JSONObject.fromObject(jsonString);
		JSONArray ja1 = (JSONArray) jo.get(detailName1);
		JSONArray ja2 = (JSONArray) jo.get(detailName2);

		T mainEntity = JSONUtils.toBean(jo, maiinClass);
		List<D1> detailList1 = JSONUtils.toList(ja1, detailClass1);
		List<D2> detailList2 = JSONUtils.toList(ja2, detailClass2);

		try {
			BeanUtils.setProperty(mainEntity, detailName1, detailClass1);
			BeanUtils.setProperty(mainEntity, detailName2, detailClass2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("���ӹ�ϵJSON�����л�ʵ��ʧ�ܣ�");
		}
		return mainEntity;
	}

	/***
	 * ��JSON�ı������л�Ϊ���ӹ�ϵ��ʵ��
	 * 
	 * @param <T>����T ������ʵ������
	 * @param <D1>����D1 �����ʵ������
	 * @param <D2>����D2 �����ʵ������
	 * @param jsonString
	 *            JSON�ı�
	 * @param mainClass
	 *            ��ʵ������
	 * @param detailName1
	 *            ��ʵ��������ʵ�����е�����
	 * @param detailClass1
	 *            ��ʵ������
	 * @param detailName2
	 *            ��ʵ��������ʵ�����е�����
	 * @param detailClass2
	 *            ��ʵ������
	 * @param detailName3
	 *            ��ʵ��������ʵ�����е�����
	 * @param detailClass3
	 *            ��ʵ������
	 * @return
	 */
	public static <T, D1, D2, D3> T toBean(String jsonString,
			Class<T> mainClass, String detailName1, Class<D1> detailClass1,
			String detailName2, Class<D2> detailClass2, String detailName3,
			Class<D3> detailClass3) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
		JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);
		JSONArray jsonArray3 = (JSONArray) jsonObject.get(detailName3);

		T mainEntity = JSONUtils.toBean(jsonObject, mainClass);
		List<D1> detailList1 = JSONUtils.toList(jsonArray1, detailClass1);
		List<D2> detailList2 = JSONUtils.toList(jsonArray2, detailClass2);
		List<D3> detailList3 = JSONUtils.toList(jsonArray3, detailClass3);

		try {
			BeanUtils.setProperty(mainEntity, detailName1, detailList1);
			BeanUtils.setProperty(mainEntity, detailName2, detailList2);
			BeanUtils.setProperty(mainEntity, detailName3, detailList3);
		} catch (Exception ex) {
			throw new RuntimeException("���ӹ�ϵJSON�����л�ʵ��ʧ�ܣ�");
		}

		return mainEntity;
	}

	/***
	 * ��JSON�ı������л�Ϊ���ӹ�ϵ��ʵ��
	 * 
	 * @param <T>
	 *            ��ʵ������
	 * @param jsonString
	 *            JSON�ı�
	 * @param mainClass
	 *            ��ʵ������
	 * @param detailClass
	 *            ����˶����ʵ������ʵ�����������ƺ�����
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T toBean(String jsonString, Class<T> mainClass,
			HashMap<String, Class> detailClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T mainEntity = JSONUtils.toBean(jsonObject, mainClass);
		for (Object key : detailClass.keySet()) {
			try {
				Class value = (Class) detailClass.get(key);
				BeanUtils.setProperty(mainEntity, key.toString(), value);
			} catch (Exception ex) {
				throw new RuntimeException("���ӹ�ϵJSON�����л�ʵ��ʧ�ܣ�");
			}
		}
		return mainEntity;
	}
}
