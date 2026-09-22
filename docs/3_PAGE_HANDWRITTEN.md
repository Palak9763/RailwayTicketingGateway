# 3-PAGE HANDWRITTEN SOLUTION

## PAGE 1 - SOAP MESSAGE DESIGN

Case:
A railway ticketing gateway allows B2B travel agents to book tickets programmatically.

SOAP Envelope has:
1. Header - authentication and routing
2. Body - BookTicket request

Header:
- AgentId
- AuthToken
- Timestamp
- RequestId
- Service
- Version

Body:
- Journey
- PassengerList
- Payment

Draw:
SOAP Envelope
|-- Header
|   |-- AuthHeader
|   |-- RoutingHeader
|-- Body
    |-- BookTicketRequest
        |-- Journey
        |-- PassengerList
        |-- Payment

## PAGE 2 - XSD

XSD validates:
- Station code using pattern [A-Z]{2,5}
- JourneyDate using xs:date
- Age using xs:positiveInteger
- Amount using xs:decimal
- Class using enumeration
- Gender using enumeration
- 1 to 6 passengers

Draw:
BookTicketRequest
|-- Journey
|-- PassengerList
|-- Payment

Explain that WSDL can publish the complete SOAP service contract and reference the XSD.

## PAGE 3 - JAVA PARSING

DOM:
- Loads full XML tree into memory
- Easy random access
- Higher memory usage

SAX:
- Event-based sequential parsing
- Lower memory usage
- Good for high-volume XML processing

Draw:
XML Response -> DOM -> Tree
XML Response -> SAX -> Events

Conclusion:
For a high-volume booking gateway, SAX/StAX is preferable when streaming processing is sufficient. DOM is convenient for smaller responses or random access.
