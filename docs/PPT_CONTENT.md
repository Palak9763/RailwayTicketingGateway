# PPT Content - 10 Members

## Slide 1 - Title & Case Study
Member 1
- SOAP Message & Schema Design for a Railway Ticketing Gateway
- B2B travel-agent ticket booking
- Objectives: security, contract, validation, scalability

## Slide 2 - Why SOAP?
Member 2
- Standard XML-based enterprise messaging
- Header/Body separation
- Contract-driven integration
- XSD validation
- WSDL can define the service contract

## Slide 3 - SOAP Envelope
Member 3
Show:
Envelope -> Header + Body
Header -> AuthHeader + RoutingHeader
Body -> BookTicketRequest

## Slide 4 - SOAP Header
Member 4
Show AgentId, AuthToken, Timestamp, RequestId, Service, Version.
Explain authentication/routing metadata.

## Slide 5 - BookTicket Body
Member 5
Journey -> From, To, Date, Train, Class
PassengerList -> Passenger -> Name, Age, Gender
Payment -> Amount, Currency, PaymentReference

## Slide 6 - XSD
Member 6
Show BookTicket.xsd and explain complexType/simpleType/enumeration/pattern.

## Slide 7 - Validation Demo
Member 7
Show terminal screenshot:
XSD Validation: PASSED
Then change Class 3A to ABC and show XSD Validation: FAILED.

## Slide 8 - DOM
Member 8
- Full XML loaded into memory
- Tree structure
- Random access
- Easy for small/complex documents

## Slide 9 - SAX
Member 9
- Event-based
- Sequential streaming
- Lower memory usage
- Better suited to high-volume XML processing
- Mention StAX as another streaming option

## Slide 10 - Architecture & Conclusion
Member 10
Travel Agent -> SOAP Gateway -> Authentication -> XSD Validation -> Booking Service -> Railway System -> SOAP Response -> Java Client

Conclusion:
SOAP standardizes B2B communication, XSD enforces the contract, and SAX/StAX can reduce memory pressure for high-volume XML processing.
