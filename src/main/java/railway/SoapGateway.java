package railway;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class SoapGateway {

    public static void processBooking() {
        try {
            String xmlFile = "src/main/resources/BookTicketRequest.xml";
            String xsdFile = "src/main/resources/BookTicket.xsd";

            System.out.println("\n======================================");
            System.out.println("     RAILWAY TICKETING GATEWAY");
            System.out.println("======================================");

            System.out.println("\n[1] Validating BookTicket payload...");
            boolean valid = XMLValidator.validate(xmlFile, xsdFile);

            if (!valid) {
                System.out.println("XSD Validation: FAILED");
                System.out.println("Booking rejected.");
                return;
            }
            System.out.println("XSD Validation: PASSED");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFile));

            String agentId = text(document, "AgentId", "http://railway.example.com/ticketing");
            String token = text(document, "AuthToken", "http://railway.example.com/ticketing");
            String from = text(document, "FromStation", "http://railway.example.com/ticketing");
            String to = text(document, "ToStation", "http://railway.example.com/ticketing");
            String date = text(document, "JourneyDate", "http://railway.example.com/ticketing");
            String train = text(document, "TrainNumber", "http://railway.example.com/ticketing");
            String travelClass = text(document, "Class", "http://railway.example.com/ticketing");
            String amount = text(document, "Amount", "http://railway.example.com/ticketing");

            System.out.println("\n[2] Authentication & Routing");
            System.out.println("Agent ID      : " + agentId);
            System.out.println("Auth Token    : " + mask(token));
            System.out.println("Authentication: PASSED");

            System.out.println("\n[3] Booking Details");
            System.out.println("From          : " + from);
            System.out.println("To            : " + to);
            System.out.println("Journey Date  : " + date);
            System.out.println("Train         : " + train);
            System.out.println("Class         : " + travelClass);
            System.out.println("Amount        : INR " + amount);

            System.out.println("\n[4] Booking Result");
            System.out.println("Status        : CONFIRMED");
            System.out.println("PNR           : 4512789632");
            System.out.println("Transaction ID: TXN987654321");

            System.out.println("\n======================================");
            System.out.println("       BOOKING SUCCESSFUL");
            System.out.println("======================================");

        } catch (Exception e) {
            System.out.println("Gateway error: " + e.getMessage());
        }
    }

    private static String text(Document document, String tag, String namespaceUri) {
        NodeList nodes = document.getElementsByTagNameNS(namespaceUri, tag);
        return nodes.getLength() == 0 ? "" : nodes.item(0).getTextContent().trim();
    }

    private static String mask(String token) {
        if (token == null || token.length() < 4) return "****";
        return "****" + token.substring(token.length() - 4);
    }
}
