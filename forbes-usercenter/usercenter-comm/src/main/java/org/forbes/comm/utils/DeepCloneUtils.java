package org.forbes.comm.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;

/***
 * DeepCloneUtils概要说明：深度复制
 * @author Huanghy
 */
@Slf4j
public class DeepCloneUtils<T> {
	
	
	/***
	 * deepClone方法慨述:
	 * @param object
	 * @return T
	 * @创建人 huanghy
	 * @创建时间 2019年12月10日 下午6:09:51
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deepClone(Object object) {
	        T cloneObject = null;
	        try {
	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	            objectOutputStream.writeObject(object);
	            objectOutputStream.close();
	            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	            cloneObject = (T)objectInputStream.readObject();
	            objectInputStream.close();
	        }  catch (ClassNotFoundException e) {
	            log.trace("类不存在",e);
	        } catch (IOException e) {
	        	log.trace("读写异常",e);
	        }
	        return cloneObject;
	    }
}
