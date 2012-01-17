package org.upsam.tecmov.yourphotos.spring.assembler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class PListAssembler extends AbstractAssembler<Object, String> {
	
	/**
	 * Cabecera
	 */
	private static final String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\"><plist version=\"1.0\">";
	/**
	 * Pie
	 */
	private static final String footer = "</plist>";

	/**
	 * 
	 */
	private SimpleDateFormat dateFormat;

	/**
	 * @return the dateFormat
	 */
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * @param dateFormat
	 *            the dateFormat to set
	 */
	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public PListAssembler() {
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	}


	@Override
	public String assemble(Object source) {
		return header + convertToPlist(source) + footer;
	}
	
	private String convertToPlist(Object obj) {
		StringBuffer sb = new StringBuffer();
		if (obj != null) {
			Class<?> clazz = obj.getClass();
			if (Collection.class.isAssignableFrom(clazz)) {
				sb.append(convertCollectionToPlist(obj));
	
			} else {
				sb.append(convertElementToPlist(obj));
			}
		}
		return sb.toString();
	}

	private String convertCollectionToPlist(Object obj) {
		StringBuffer sb = new StringBuffer();
		sb.append("<array>");
		Collection<?> collection = (Collection<?>) obj;
		for (Object object : collection) {
			sb.append(convertToPlist(object));
		}
		sb.append("</array>");
		return sb.toString();
	}

	private String convertElementToPlist(Object obj) {
		Class<?> clazz = obj.getClass();
		
		if (Collection.class.isAssignableFrom(clazz)) {
			return convertCollectionToPlist(obj);
		
		} else if (String.class.isAssignableFrom(clazz)) {
			return "<string><![CDATA[" + (String) obj + "]]></string>";
			
		} else if (Date.class.isAssignableFrom(clazz)) {
			return "<date>" + dateFormat.format((Date) obj) + "</date>";
			
		} else if (Integer.class.isAssignableFrom(clazz)) {
			return "<integer>" + (Integer) obj + "</integer>";
			
		} else if (Long.class.isAssignableFrom(clazz)) {
			return "<integer>" + (Long) obj + "</integer>";
			
		} else if (Float.class.isAssignableFrom(clazz)) {
			return "<real>" + (Float) obj + "</real>";
			
		} else if (Double.class.isAssignableFrom(clazz)) {
			return "<real>" + (Double) obj + "</real>";
			
		} else if (BigDecimal.class.isAssignableFrom(clazz)) {
			return "<real>" + (BigDecimal) obj + "</real>";
			
		} else if (Boolean.class.isAssignableFrom(clazz)) {
			if (((Boolean) obj).booleanValue()) {
				return "<true/>";
				
			} else {
				return "<false/>";
			}
			
		} else {
			return convertBeanToPlist(obj);
		}
	}

	private String convertBeanToPlist(Object obj) {
		StringBuffer sb = new StringBuffer();
		String methodName = null;
		Object getterResult = null;
		Class<?> clazz = obj.getClass();
		sb.append("<dict>");
		
		Method methods[] = clazz.getMethods();
		for (Method method : methods) {
			methodName = method.getName();
			if (! methodName.equals("getClass") && methodName.startsWith("get")) {
				try {
					getterResult = method.invoke(obj);
					
				} catch (IllegalArgumentException e) {
					//logger.warn("Invocación de método con argumentos inválidos", e);

				} catch (IllegalAccessException e) {
					//logger.warn("Invocación de método con acceso no permitido", e);

				} catch (InvocationTargetException e) {
					//logger.warn("Invocación de método incorrecta", e);
				}
				if (getterResult != null) {
					sb.append("<key>" + methodName.substring(3).toLowerCase()  + "</key>");
					sb.append(convertToPlist(getterResult));
				}
			}	
		}
		sb.append("</dict>");
		return sb.toString();
	}

}
