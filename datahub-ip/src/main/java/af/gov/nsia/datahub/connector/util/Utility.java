/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.util;

import af.gov.nsia.datahub.connector.exception.DocumentTranformationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Slf4j
public class Utility {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static TypeFactory typeFactory = objectMapper.getTypeFactory();

    public static boolean isNullOrBlank(String key) {
        int strLen;
        if (key != null && (strLen = key.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(key.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String objectToJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonToString = mapper.writeValueAsString(object);
            return jsonToString;
        } catch (Exception var3) {
            log.error("exception", var3);
            return null;
        }
    }

    public static <T1> List<T1> convertModelList(List<? extends Object> sourceClass, Class<T1> destinationClass) {
        try {
            CollectionType collectionType = typeFactory.constructCollectionType(List.class, destinationClass);
            return objectMapper.convertValue(sourceClass, collectionType);
        } catch (Exception exp) {
            log.error("[convertModelList] Exception occurred while converting model list", exp);
            return null;
        }
    }

    public static <T> T convertModel(Object sourceClass, Class<T> destinationClass) {
        try {
//            JavaType javaType = typeFactory.constructType(destinationClass);
            return objectMapper.convertValue(sourceClass, destinationClass);
        } catch (Exception exp) {
            log.error("Exception", exp);
            return null;
        }
    }

    public static Long getLongValue(String str) {
        try {
            return new Long(str);
        } catch (Exception e) {
            log.error("Error Occurred while converting String to Long value - " + str, e);
        }
        return 0L;
    }

    public static boolean getBooleanValue(String str) {
        try {
            return Boolean.valueOf(str);
        } catch (Exception e) {
            log.error("Error occurred while converting String to boolean - " + str, e);
        }
        return false;
    }

    public static boolean isNonEmpty(Collection<?> collection) {
        return (collection != null && !collection.isEmpty());
    }

    /**
     * Transform XML document to String
     *
     * @param doc
     * @return
     */
    public static String xmlToString(Document doc) {
        String tree = "";
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            tree = writer.toString();
        } catch (TransformerException e) {
            new DocumentTranformationException("Transformation is failed!");
        }
        return tree;
    }

    /**
     * Convert String to XML document
     *
     * @param xmlString
     * @return
     */
    public static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Iterable<Long> toIterable(long[] numbers) {
        return LongStream.of(numbers).boxed().collect(Collectors.toList());
    }

}
