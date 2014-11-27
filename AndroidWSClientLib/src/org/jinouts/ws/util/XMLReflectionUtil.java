/**
 *
 */
package org.jinouts.ws.util;

import hu.javaforum.commons.ReflectionHelper;

import java.lang.reflect.Field;

import org.jinouts.ws.exception.FieldNotMatchedException;
import org.jinouts.ws.exception.MethodNotMatchedException;
import org.jinouts.xml.bind.annotation.XmlAnyElement;
import org.jinouts.xml.bind.annotation.XmlElement;
import org.jinouts.xml.bind.annotation.XmlType;

/**
 * @author asraf
 *         asraf344@gmail.com
 */
public class XMLReflectionUtil {
    public static boolean invokeSetter(Object instance, String fieldName, Object value) {
        boolean isSet = false;
        try {
            isSet = ReflectionHelper.invokeSetter(instance, fieldName, value);
        } catch (FieldNotMatchedException e) {
            // get the field name by xml element
            fieldName = getFieldNameByXMLElement(instance.getClass(), fieldName);

            try {
                if (fieldName != null) {
                    isSet = ReflectionHelper.invokeSetter(instance, fieldName, value);
                }

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                //e1.printStackTrace();
                System.out.println("Error occurred at XMLReflectionUtil.invokeSetter() " + e1.getMessage());
            }
        } catch (MethodNotMatchedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return isSet;
    }

    public static Object invokeGetter(Object instance, String fieldName) {
        Object obj = null;
        try {
            obj = ReflectionHelper.invokeGetter(instance, fieldName);
        } catch (FieldNotMatchedException e) {
            // try to get the field name by xml element
            fieldName = getFieldNameByXMLElement(instance.getClass(), fieldName);

            if (fieldName != null) {
                try {
                    obj = ReflectionHelper.invokeGetter(instance, fieldName);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error occurred at XMLReflectionUtil.invokeGetter() " + ex.toString());
        }

        return obj;
    }

    public static String getClassNameByXmlTypeElement(Object obj) {
        Class clazz = obj.getClass();
        if (clazz.isAnnotationPresent(XmlType.class)) {
            XmlType xmlType = (XmlType) clazz.getAnnotation(XmlType.class);
            return xmlType.name();
        }
        return null;
    }

    public static String getFieldNameByXMLElement(Class instanceClass, String xmlName) {
        Field[] fields = instanceClass.getDeclaredFields();
        for (Field field : fields) {
            XmlElement xmlElement = field.getAnnotation(XmlElement.class);
            if (xmlElement != null) {
                if (xmlName.equalsIgnoreCase(xmlElement.name())) {
                    return field.getName();

                }
            }
        }
        return null;
    }

    public static String getFieldNameForXML(Field field) {
        if (field.isAnnotationPresent(XmlElement.class)) {
            XmlElement xmlElement = field.getAnnotation(XmlElement.class);
            if (xmlElement != null) {
                return xmlElement.name();
            }
        }
        if (field.isAnnotationPresent(XmlAnyElement.class)) {
            XmlAnyElement xmlElement = field.getAnnotation(XmlAnyElement.class);
            if (xmlElement != null && xmlElement.lax()) {
                return "";
            }
        }
        return field.getName();
    }

    public static String getFieldNamespaceForXML(Field field, String defaultValue) {
        if (field.isAnnotationPresent(XmlElement.class)) {
            XmlElement xmlElement = field.getAnnotation(XmlElement.class);
            if (xmlElement != null && isValidNamespace(xmlElement.namespace())) {
                return xmlElement.namespace();
            }
        }
        if (field.isAnnotationPresent(XmlAnyElement.class)) {
            XmlAnyElement xmlElement = field.getAnnotation(XmlAnyElement.class);
            if (xmlElement != null && xmlElement.lax()) {
                return "";
            }
        }
        return defaultValue;
    }

    private static boolean isValidNamespace(String namespace) {
        return !"##default".equals(namespace);
    }
}
