/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.html * 
 *
 */
package org.jinouts.ws.util;

import com.eviware.soapui.support.StringUtils;
import hu.javaforum.commons.ReflectionHelper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author asraf <asraf344@gmail.com>
 * @author Petr Havlena <havlenapetr@gmail.com>
 */
public abstract class WSRequestXMLUtils {

    public static String getXMLForKeyValue(Object valueObj, String name, String tagInitial, int level) {
        // if value is null just return null
        if (valueObj == null) {
            return null;
        } else if (isPrimitiveType(valueObj)) {
            return XMLUtils.getXMLTagWithNameValue(name, valueObj.toString(), tagInitial);
        }

        StringBuilder sb = new StringBuilder();
        if (!appendXMLForJavaConvertableObject(sb, valueObj, name, tagInitial, level)) {
            String valueClassName = getValueClassName(valueObj, name);
            sb.append(XMLUtils.getXMLStartTag(valueClassName,  tagInitial));
            getXMLForBizObject(sb, valueObj, tagInitial, level);
            sb.append(XMLUtils.getXMLEndTag(valueClassName, tagInitial));
        }
        return sb.toString();
    }

    private static String getValueClassName(Object object, String defaultValue) {
        if (!StringUtils.isNullOrEmpty(defaultValue)) {
            return defaultValue;
        }
        String valueClassName = XMLReflectionUtil.getClassNameByXmlTypeElement(object);
        if (StringUtils.isNullOrEmpty(valueClassName)) {
            throw new RuntimeException("Unable to resolve class name for value: '" + object + "'");
        }
        return valueClassName;
    }

    private static List<Field> getFilteredFields(Object valueObj) {
        List<Field> result = new ArrayList<Field>();
        for (Field field : ReflectionHelper.iterateFields(valueObj.getClass())) {
            if ((field.getModifiers() & Modifier.STATIC) != 0) {
                continue;
            } else if (field.getName().startsWith("__")) {
                continue;
            }
            result.add(field);
        }
        return Collections.unmodifiableList(result);
    }

    private static void getXMLForBizObject(StringBuilder sb, Object valueObj, String tagInitial, int level) {
        for (Field field : getFilteredFields(valueObj)) {
            field.setAccessible(true);

            String fieldName = XMLReflectionUtil.getFieldNameForXML(field);
            String fieldNamespace = XMLReflectionUtil.getFieldNamespaceForXML(field, tagInitial);

            Object value;
            try {
                value = field.get(valueObj);
                if (value == null) {
                    continue;
                }
            } catch (Exception e) {
                throw new RuntimeException("Cannot get  value for the field '" + fieldName + "': " + e);
            }

            boolean valueProcessed = appendXMLForJavaConvertableObject(sb, value, fieldName, fieldNamespace, level + 1);
            // else its another object or some primitive
            if (!valueProcessed) {
                sb.append(getXMLForKeyValue(value, fieldName, fieldNamespace, level + 1));
            }
        }
    }

    private static boolean appendXMLForJavaConvertableObject(StringBuilder sb, Object value, String fieldName, String tagInitial, int level) {
        String valueClassName = getValueClassName(value, fieldName);

        // / if its array of object
        if (value instanceof Object[]) {
            getXMLForObjArray(sb, value, fieldName, tagInitial, level);
            return true;
        }

        // if its some primitive array
        else if (value.getClass().isArray()) {
            getXMLForArray(sb, value, valueClassName, fieldName, tagInitial, level);
            return true;
        }
        // if its some list
        else if (value instanceof List) {
            List listValue = (List) value;
            getXMLForList(sb, listValue, fieldName, tagInitial, level);
            return true;
        }

        // if its some date
        else if (value instanceof Date) {
            getXMLForDate(sb, (Date) value, fieldName, tagInitial, level);
            return true;
        }

        // if its some date value
        else if (value instanceof XMLGregorianCalendar) {
            sb.append(XMLUtils.getXMLTagWithNameValue(fieldName, value.toString(), tagInitial));
            return true;
        }

        return false;
    }

    private static void getXMLForDate(StringBuilder sb, Date date, String name, String tagInitial, int level) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH);
        sb.append(XMLUtils.getXMLStartTag(name, tagInitial));
        // formatter.format((Date)value, sb, 0);
        sb.append(formatter.format(date));
        sb.append(XMLUtils.getXMLEndTag(name, tagInitial));
    }
/*
    private static String getXMLForDate(List listValue, String name, String tagInitial, int level) {
        StringBuilder sb = new StringBuilder();
        for (int listCount = 0; listCount < listValue.size(); listCount++) {
            Object itemValue = listValue.get(listCount);
            sb.append(getXMLForKeyValue(itemValue, name, tagInitial, level + 1));
        }
        return sb.toString();
    }
*/
    private static void getXMLForList(StringBuilder sb, List listValue, String name, String tagInitial, int level) {
        for (int listCount = 0; listCount < listValue.size(); listCount++) {
            Object itemValue = listValue.get(listCount);
            sb.append(getXMLForKeyValue(itemValue, name, tagInitial, level));
        }
    }

    private static void getXMLForObjArray(StringBuilder sb, Object value, String name, String tagInitial, int level) {
        Object[] array = (Object[]) value;
        for (int arrayCount = 0; arrayCount < array.length; arrayCount++) {
            Object itemValue = array[arrayCount];
            sb.append(getXMLForKeyValue(itemValue, name, tagInitial, level + 1));
        }
    }

    private static void getXMLForArray(StringBuilder sb, Object value, String name, String fieldName, String tagInitial, int level) {
        int arrayLength = Array.getLength(value);
        Class componentClass = value.getClass().getComponentType();
        if (componentClass.equals(byte.class)) {
            sb.append(XMLUtils.getXMLStartTag(fieldName, tagInitial));
            sb.append("><!-- HEX VALUE: -->");

            byte[] byteArray = (byte[]) value;
            for (int arrayCount = 0; arrayCount < arrayLength; arrayCount++) {
                if (byteArray[arrayCount] >= 0 && byteArray[arrayCount] < 10) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(byteArray[arrayCount] < 0 ? byteArray[arrayCount] + 256
                        : byteArray[arrayCount]));
            }
            sb.append(XMLUtils.getXMLEndTag(fieldName, tagInitial));

        } else {
            for (int arrayCount = 0; arrayCount < arrayLength; arrayCount++) {
                Object itemValue = Array.get(value, arrayCount);
                sb.append(getXMLForKeyValue(itemValue, name, tagInitial, level + 1));
            }
        }
    }

    private static boolean isPrimitiveType(Object valueObj) {
        return valueObj.getClass().getPackage().getName().startsWith("java.lang");
    }

}
