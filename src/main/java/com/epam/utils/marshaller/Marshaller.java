package com.epam.utils.marshaller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

class Marshaller {

	private static Marshaller instance;

	private final Map<Class<?>, JAXBContext> contextCache;

	/**
	 * Class Logger
	 */

	private Marshaller() {

		contextCache = new HashMap<Class<?>, JAXBContext>();
	}

	/**
	 * Get instance for Marshaller
	 */
	static Marshaller getInstance() {
		if (null == instance) {
			instance = new Marshaller();
		}
		return instance;
	}

	/**
	 * Returns cached JAXBContext for specified class
	 * 
	 * @param clazz
	 *            - Class
	 * @return - JAXBContext for specified class
	 */
	private JAXBContext getJAXBContext(Class<?> clazz) {
		try {
			if (!contextCache.containsKey(clazz)) {
				JAXBContext context = JAXBContext.newInstance(clazz);
				contextCache.put(clazz, context);
				return context;

			}
			return contextCache.get(clazz);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns Marshaller from JAXBContext for specified class
	 * 
	 * @param clazz
	 *            - Class
	 * @return - Marshaller
	 */
	private javax.xml.bind.Marshaller getMarshaller(Class<?> clazz) {
		try {

			javax.xml.bind.Marshaller marshaller = getJAXBContext(clazz).createMarshaller();
			marshaller.setProperty("jaxb.encoding", "Unicode");
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
			return marshaller;

		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns Unmarshaller from JAXBContext for specified class
	 * 
	 * @param clazz
	 *            - Class
	 * @return - Unmarshaller
	 */
	private javax.xml.bind.Unmarshaller getUnmarshaller(Class<?> clazz) {
		try {

			javax.xml.bind.Unmarshaller unmarshaller = getJAXBContext(clazz).createUnmarshaller();

			return unmarshaller;

		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * unmarshall @param source to object by type @param resultClazz
	 * 
	 * @param source
	 * @param resultClazz
	 * @return serialized object
	 */
	@SuppressWarnings("unchecked")
	public <T> T unmarshall(Source source, Class<T> resultClazz) {
		try {

			return (T) getUnmarshaller(resultClazz).unmarshal(source);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * unmarshall @param string to object by type @param resultClazz
	 * 
	 * @param string
	 * @param resultClazz
	 * @return serialized object
	 */
	@SuppressWarnings("unchecked")
	public <T> T unmarshall(String string, Class<T> resultClazz) {
		try {

			return (T) getUnmarshaller(resultClazz).unmarshal(new ByteArrayInputStream(string.getBytes()));
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * unmarshall @param file to object by type @param resultClazz
	 * 
	 * @param file
	 * @param resultClazz
	 * @return serialized object
	 */
	@SuppressWarnings("unchecked")
	public <T> T unmarshall(File file, Class<T> resultClazz) {
		try {
			return (T) getUnmarshaller(resultClazz).unmarshal(file);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * unmarshall @param is to object by type @param resultClazz
	 * 
	 * @param is
	 * @param resultClazz
	 * @return serialized object
	 */
	@SuppressWarnings("unchecked")
	public <T> T unmarshall(InputStream is, Class<T> resultClazz) {
		try {
			return (T) getUnmarshaller(resultClazz).unmarshal(is);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Marshal xml-based object to result object
	 * 
	 * @param jaxbElement
	 * @param paramResult
	 */
	public void marshall(Object jaxbElement, Result paramResult) {
		try {
			getMarshaller(jaxbElement.getClass()).marshal(jaxbElement, paramResult);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Marshal xml-based object to result writer
	 * 
	 * @param jaxbElement
	 * @param writer
	 */
	public void marshall(Object jaxbElement, Writer writer) {
		try {
			getMarshaller(jaxbElement.getClass()).marshal(jaxbElement, writer);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

	}
}