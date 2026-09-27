package railway;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class XMLValidatorTest {

    @Test
    void validatesSoapEnvelopeRequestAgainstSchema() {
        boolean valid = XMLValidator.validate(
                "src/main/resources/BookTicketRequest.xml",
                "src/main/resources/BookTicket.xsd"
        );

        assertTrue(valid, "The SOAP request should validate against the XSD");
    }
}
