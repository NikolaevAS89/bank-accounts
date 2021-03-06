package ru.timestop.xslt;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Created by nikolaev13-as on 31.10.2018.
 */
public class TransformXSLT {
    private static final DocumentBuilderFactory BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
    private static final TransformerFactory TRANSFORMER_FACTORY = TransformerFactory.newInstance();

    public static String transform(File xsl_file, File xml_file) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        return transform(new FileInputStream(xsl_file), new FileInputStream(xml_file));
    }

    public static String transform(InputStream xsl_stream, InputStream xml_stream) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilder builder = BUILDER_FACTORY.newDocumentBuilder();
        Document document = builder.parse(xml_stream);
        StreamSource styleSource = new StreamSource(xsl_stream);
        Transformer transformer = TRANSFORMER_FACTORY.newTransformer(styleSource);

        DOMSource source = new DOMSource(document);
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(source, sr);
        return sw.toString();
    }
}
