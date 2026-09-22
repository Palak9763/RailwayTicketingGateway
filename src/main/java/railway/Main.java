package railway;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println(" SOAP RAILWAY TICKETING GATEWAY");
        System.out.println("==========================================");

        SoapGateway.processBooking();
        DOMParser.parseResponse();
        SAXParser.parseResponse();

        System.out.println("\n==========================================");
        System.out.println("          DEMO COMPLETED");
        System.out.println("==========================================");
    }
}
