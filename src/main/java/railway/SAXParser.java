package railway;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXParser {
    public static void parseResponse() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

            javax.xml.parsers.SAXParser parser = factory.newSAXParser();
            File file = new File("src/main/resources/BookTicketResponse.xml");

            System.out.println("\nSAX PARSER");

            parser.parse(file, new DefaultHandler() {
                private String currentElement = "";

                @Override
                public void startElement(String uri, String localName,
                                          String qName, Attributes attributes) {
                    currentElement = qName;
                }

                @Override
                public void characters(char[] ch, int start, int length) {
                    String value = new String(ch, start, length).trim();
                    if (!value.isEmpty()
                            && (currentElement.equals("rt:Status")
                            || currentElement.equals("rt:PNR")
                            || currentElement.equals("rt:TransactionId"))) {
                        System.out.println(currentElement + " : " + value);
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    currentElement = "";
                }
            });

        } catch (Exception e) {
            System.out.println("SAX error: " + e.getMessage());
        }
    }
}
