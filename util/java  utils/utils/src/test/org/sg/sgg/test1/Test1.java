package org.sg.sgg.test1;

import org.junit.Test;
import org.sg.sgg.HardWare.HardWareUtils;
import org.sg.sgg.base64.Base64Utils;

public class Test1 {
	@Test
	public void test1(){
		String str = "123456";
		byte[] b = Base64Utils.decode(str);
		System.err.println(b.length);
	}
	@Test
	public void test2(){
		System.out.println("获取CPU序列号 :"+HardWareUtils.getCPUSerial());
		System.out.println(" 获取MAC地址:"+HardWareUtils.getMac());
	}
	
}
