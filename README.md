
# SOAP Railway Ticketing Gateway - Mini Project
## What this project demonstrates
- SOAP-style BookTicket XML envelope
- Authentication and routing headers
- XSD validation of the booking payload
- Java DOM parsing
- Java SAX parsing
- Simulated booking confirmation and PNR generation

## Requirements
- JDK 17+
- Maven 3.8+ (optional; Java can also run the classes directly from an IDE)
- IntelliJ IDEA / Eclipse / VS Code / NetBeans

## Run in an IDE
1. Open this folder as a Maven project.
2. Make sure the project uses JDK 17 or later.
3. Run `railway.Main`.

## Run with Maven
From the project root:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=railway.Main
```
If your Maven installation does not have the exec plugin cached, run `railway.Main` from your IDE instead.

## Demo
The supplied request is valid and should show:
- XSD Validation: PASSED
- Authentication: PASSED
- Booking Status: CONFIRMED
- PNR: 4512789632

For the invalid-request demo, change:
```xml
<rt:Class>3A</rt:Class>
```
to:
```xml
<rt:Class>ABC</rt:Class>
```
Then run again. XSD validation should fail.

## Important academic note
The XSD in this prototype validates the `BookTicketRequest` payload. A real SOAP stack/WSDL handles the SOAP envelope and operation contract. This project is a classroom prototype and does not connect to IRCTC or perform real ticket booking/payment.
=======
# RailwayTicketingGateway
SOAP-based Railway Ticketing Gateway mini-project using Java, XML, XSD, DOM, and SAX parsing. Demonstrates ticket booking request validation, authentication, simulated booking response, and XML response parsing.
>>>>>>> 245e2cdb1940680a9207360ed9d1dee0fc25a4bf
