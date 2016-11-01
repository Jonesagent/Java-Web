package org.sg.sgg.test1;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DumpMethods {
	public static void main(String[] args) throws Exception{
		//载入指定的类
		Class clazz = Class.forName("java.lang.String");
		//获取 此类�?��的方�?
		Method[] m = clazz.getDeclaredMethods();
		Field[] fields = clazz.getFields();
		Constructor[] constructors = clazz.getConstructors();
		for (int i = 0; i < m.length; i++) {
			System.out.println(m[i].toString());
		}
		for (int i = 0; i < fields.length; i++) {
			System.out.println("fields ======= "+fields[i]);
		}
		for (int i = 0; i < constructors.length; i++) {
			System.out.println("constructors === ");
		}
	}
}
