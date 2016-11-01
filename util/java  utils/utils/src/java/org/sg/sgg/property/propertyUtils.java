package org.sg.sgg.property;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;


public class propertyUtils {
	private final static Logger logger = Logger.getLogger(propertyUtils.class);
	
	private static Hashtable<String, Properties> register = new Hashtable<String, Properties>();// ��̬�����ʼ��[����������֮ǰ
	
	private static Properties config = null;// ��ϵͳ������
	
	static {
		config = new Properties();
		try {
			config = getPropertiesMap("/jdbc.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author  ��ȡproperties�����ļ�������
	 * @param filepath
	 *            �����ļ����·��
	 * @return ����Properites����
	 * */
	public static Properties getPropertiesMap(String filepath)
			throws IOException {
		String fpath = propertyUtils.class.getClassLoader().getResource(filepath)
				.getPath();
		
		// String fpath = FileUtil.class.getResource(filepath).getPath();
		// ȥ��·���ո�����
		fpath = java.net.URLDecoder.decode(fpath, "UTF-8");
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(fpath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Properties p = new Properties();
		p.load(in);
		return p;
	}
	/**
	 *  ��ȡ�����ļ� 
	 * @param fileName
	 * @return
	 */
	public static Properties getProperties(String fileName){
		InputStream in = null;
		try {
			config = register.get(fileName);
			if (config == null) {
				in = new FileInputStream(fileName);
				if (fileName.startsWith("/")) {
					in = propertyUtils.class.getResourceAsStream(fileName);
				}else {
					in = propertyUtils.class.getResourceAsStream("/"+fileName);
				}
				config = new Properties();
				config.load(in);
				register.put(fileName, config);
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}
	
	
	public static String getConfigParam(String key){
		return config.getProperty(key);
	}
}
