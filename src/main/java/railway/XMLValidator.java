package railway;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class XMLValidator {
    public static boolean validate(String xmlFile, String xsdFile) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(
                    XMLConstants.W3C_XML_SCHEMA_NS_URI);
            factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            Schema schema = factory.newSchema(new File(xsdFile));
            var validator = schema.newValidator();
            validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            documentFactory.setNamespaceAware(true);
            documentFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            documentFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            documentFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentFactory.setXIncludeAware(false);
            documentFactory.setExpandEntityReferences(false);

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFile));

            NodeList requestNodes = document.getElementsByTagNameNS(
                    "http://railway.example.com/ticketing", "BookTicketRequest");
            if (requestNodes.getLength() == 0) {
                throw new IllegalArgumentException("No rt:BookTicketRequest element found in the SOAP body.");
            }

            Element requestElement = (Element) requestNodes.item(0);
            validator.validate(new DOMSource(requestElement));
            return true;
        } catch (Exception e) {
            System.out.println("Validation Error: " + e.getMessage());
            return false;
        }
    }
}
