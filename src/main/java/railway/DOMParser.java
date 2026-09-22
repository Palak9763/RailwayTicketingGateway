package railway;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMParser {
    public static void parseResponse() {
        try {
            File file = new File("src/main/resources/BookTicketResponse.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList status = document.getElementsByTagName("rt:Status");
            NodeList pnr = document.getElementsByTagName("rt:PNR");
            NodeList transaction = document.getElementsByTagName("rt:TransactionId");

            System.out.println("\nDOM PARSER");
            System.out.println("Status       : " + status.item(0).getTextContent());
            System.out.println("PNR          : " + pnr.item(0).getTextContent());
            System.out.println("Transaction  : " + transaction.item(0).getTextContent());

        } catch (Exception e) {
            System.out.println("DOM error: " + e.getMessage());
        }
    }
}
