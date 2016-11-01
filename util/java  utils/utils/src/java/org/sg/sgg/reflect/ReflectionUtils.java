package org.sg.sgg.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * 反射的Utils函数集合.
 * 
 * 提供访问私有变量,获取泛型类型Class,提取集合中元素的属性等Utils函数.
 * 
 * @author
 */
public class ReflectionUtils {
	private static final Logger logger = Logger
			.getLogger(ReflectionUtils.class);

	static {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		ConvertUtils.register(dc, Date.class);
	}

	/**
	 * 按照类名反射出它的一个对象
	 * 
	 * @param className
	 *            - 包名+类名
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObjByClassName(final String className) {
		Object object = null;
		if (null != className) {
			try {
				Class clazz = Class.forName(className);
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return object;
	}

	/**
	 * 按照类名,参数值反射出它的一个对象
	 * 
	 * @param className
	 *            - 包名+类名
	 * @param parameters
	 *            - 构造函数的参数值
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getObjByClassNameAndParameter(final String className,
			final Object[] parameters) {
		Object object = null;
		if (null != className) {
			try {
				Class clazz = Class.forName(className);
				// 获取公有的构造函数,指定参数
				Constructor constructor = clazz
						.getConstructor(getParameterClass(parameters));
				object = constructor.newInstance(parameters);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return object;
	}

	/**
	 * 获取参数列表的class对象
	 * 
	 * @param parameters
	 *            - 参数值数组
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class[] getParameterClass(Object[] parameters) {
		Class[] methodParameters = null;
		if (null != parameters && parameters.length > 0) {
			methodParameters = new Class[parameters.length];
			for (int i = 0; i < methodParameters.length; i++) {
				methodParameters[i] = parameters[i].getClass();
			}
		}
		return methodParameters;
	}

	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 * 
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(final Object object,
			final String fieldName) {

		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}" + e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(final Object object,
			final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		}
		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}" + e.getMessage());
		}
	}

	/**
	 * 直接调用对象方法,无视private/protected修饰符.
	 * 
	 * @return
	 */
	public static Object invokeMethod(final Object object,
			final String methodName, final Class<?>[] parameterTypes,
			final Object[] parameters) throws InvocationTargetException {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method ["
					+ methodName + "] on target [" + object + "]");
		}
		method.setAccessible(true);
		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}" + e.getMessage());
		}
		return null;
	}

	/**
	 * 用类名反射调用它的某个方法(一般针对工具类或者service)(无参数)
	 * 
	 * @param className
	 *            - 类名
	 * @param methodName
	 *            - 方法名
	 * @return
	 */
	public static Object invokeMethod(final String className,
			final String methodName) {
		return invokeMethodWithObjHasParame(className,
				getObjByClassName(className), methodName, new Object[0]);
	}

	/**
	 * 用类名反射调用它的某个方法(一般针对工具类或者service)(有参数)
	 * 
	 * @param className
	 *            - 类名
	 * @param methodName
	 *            - 方法名
	 * @param parameter
	 *            - 参数数组
	 * @return
	 */
	public static Object invokeMethodHasParame(final String className,
			final String methodName, final Object[] parameters) {
		return invokeMethodWithObjHasParame(className,
				getObjByClassName(className), methodName, parameters);
	}

	/**
	 * 用对象反射调用它的某个方法(没有参数的方法)
	 * 
	 * @param className
	 *            - 类名
	 * @param object
	 *            - 对象
	 * @param methodName
	 *            - 方法名
	 * @return
	 */
	public static Object invokeMethodWithObj(final String className,
			final Object object, final String methodName) {
		return invokeMethodWithObjHasParame(className, object, methodName,
				new Object[0]);
	}

	/**
	 * 用对象反射调用它的某个方法(有参数的方法)
	 * 
	 * @param className
	 *            - 类名
	 * @param object
	 *            - 对象
	 * @param methodName
	 *            - 方法名
	 * @param parameters
	 *            - 参数数组
	 * @return
	 */
	public static Object invokeMethodWithObjHasParame(final String className,
			final Object object, final String methodName,
			final Object[] parameters) {
		return invokeMethodWithObjHasSpecialParame(className, object,
				methodName, parameters, getParameterClass(parameters));
	}
	
	/**
	 * 反射获取一个类的方法信息 包括参数,方法名,返回类型 www.2cto.com
	 * @param className - 类名
	 * @return
	 */
	public static List<String> getMethodMsg(final String className){
		List<String> list = new ArrayList<String>();
		if (null != className) {
			try {
				@SuppressWarnings("rawtypes")
				Class clazz = Class.forName(className);
				//获取所有方法
				Method[] methods = clazz.getDeclaredMethods();
				for (int i = 0; i < methods.length; i++) {
					String meth = methods[i].toString();
					 // 截取出所有的参数,参数以,形式分割
					meth = meth.substring(meth.indexOf("(")+1,meth.indexOf(")"));
					// ret由3部分构成：参数;方法名;返回类型
					String ret = meth + ";" + methods[i].getName()+";" + methods[i].getReturnType();
					list.add(ret);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @param object
	 * @param fieldName
	 * @return
	 */
	protected static Field getDeclaredField(final Object object,
			final String fieldName) {
		Assert.notNull(object, "object不能为空.");
		Assert.notNull(fieldName, "fieldName不能为空.");
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				logger.error(fieldName + " 属性不存在 ：" + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 循环向上转型,获取对象的DeclaredMethod.
	 * 
	 * @param object
	 * @param methodName
	 * @return
	 */
	public static Method getDeclaredMethod(Object object, String methodName,
			Class<?>[] parameterTypes) {
		Assert.notNull(object, "object不能为空.");

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 环向上转型,获取对象的DeclaredField.
	 * 
	 * @param field
	 */
	public static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 通过反射,获得Class定义中声明的父类的泛型参数的类型.
	 * 
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends
	 * HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            - the class to introspect
	 * @param index
	 *            - the Index of the generic ddeclaration,start from 0.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(final Class clazz,
			final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			logger.warn("Index :" + index + ", Size of" + clazz.getSimpleName()
					+ "'s Parameterized Type: " + params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName()
					+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 调用Setter方法.使用value的Class来查找Setter方法.
	 * 
	 * @param object
	 * @param propertyName
	 * @param value
	 */
	public static void invokeSetterMethod(final Object object,
			final String propertyName, final Object value) {
		invokeSetterMethod(object, propertyName, value, null);
	}

	/**
	 * 调用Setter方法.
	 * 
	 * @param object
	 * @param propertyName
	 * @param value
	 * @param propertyType
	 *            - 用于查找Setter方法,为空时使用value的Class替代.
	 */
	@SuppressWarnings({ "unused", "deprecation" })
	public static void invokeSetterMethod(final Object object,
			final String propertyName, final Object value, Class<?> propertyType) {
		Class<?> clazz = propertyType != null ? propertyType : value.getClass();
		String setterMethodName = "set" + StringUtils.capitalise(propertyName);
		try {
			invokeMethod(object, setterMethodName,
					new Class<?>[] { propertyType }, new Object[] { value });
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 转换字符串类型到clazz的property类型的值.
	 * 
	 * @param value
	 *            - 待转换的字符串
	 * @param clazz
	 *            - 提供类型信息的Class
	 * @param propertyName
	 *            - 提供类型信息的Class的属性.
	 * @return
	 */
	public static Object convertValue(Object value, Class<?> clazz,
			String propertyName) {
		Class<?> toType = BeanUtils.getPropertyDescriptor(clazz, propertyName)
				.getPropertyType();
		DateConverter converter = new DateConverter();
		converter.setUseLocaleFormat(true);
		converter.setPatterns(new String[] { "yyyy-MM-dd",
				"yyyy-MM-dd HH:mm:ss" });
		ConvertUtils.register(converter, Date.class);
		return ConvertUtils.convert(value, toType);
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数),组合成List.
	 * 
	 * @param collection
	 *            - 来源集合.
	 * @param propertyName
	 *            - 要提取的属性名.
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List convertElementPropertyToList(
			final Collection collection, final String propertyName) {
		List list = new ArrayList();
		for (Object object : list) {
			try {
				list.add(PropertyUtils.getProperty(object, propertyName));
			} catch (Exception e) {
				convertToUncheckedException(e);
			}
		}
		return list;
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数),组合成由分割符分隔的字符串.
	 * 
	 * @param collection
	 *            - 来源集合.
	 * @param propertyName
	 *            - 要提取的属性名.
	 * @param separator
	 *            - 分隔符.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String convertElementPropertyToString(
			final Collection collection, final String propertyName,
			final String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 转换字符串到相应类型.
	 * 
	 * @param <T>
	 * @param value
	 *            - 待转换的字符串
	 * @param toType
	 *            - 转换目标类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertStringToObject(final String value,
			final Class<T> toType) {
		return (T) ConvertUtils.convert(value, toType);
	}

	/**
	 * 调用Getter方法.
	 */
	@SuppressWarnings("deprecation")
	public static Object invokeGetterMethod(final Object object,
			final String propertyName) {
		String getterMethodName = "get" + StringUtils.capitalise(propertyName);
		Object obj = null;
		try {
			obj = invokeMethod(object, getterMethodName, new Class<?>[] {},
					new Object[] {});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 用对象反射调用它的某个方法(指定参数类型的方法)
	 * 
	 * @param className
	 *            - 类名
	 * @param object
	 *            - 对象
	 * @param methodName
	 *            - 方法名
	 * @param parameters
	 *            - 参数数组
	 * @param methodParameters
	 *            - 参数类型数组
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object invokeMethodWithObjHasSpecialParame(
			final String className, final Object object,
			final String methodName, final Object[] parameters,
			final Class[] methodParameters) {

		Object obj = null;
		try {
			if (className != null) {
				Class clazz = Class.forName(className);
				Method method = clazz.getMethod(className.trim(),
						methodParameters);
				obj = method.invoke(object, parameters);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 将反射时的checked exception转换为unchecked exception。
	 */
	public static IllegalArgumentException convertToUncheckedException(
			Exception e) throws IllegalArgumentException {
		if (e instanceof IllegalAccessException
				|| e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException)
			throw new IllegalArgumentException("Refelction Exception.", e);
		else
			throw new IllegalArgumentException(e);
	}

}
