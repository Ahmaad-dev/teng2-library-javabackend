# UML Komponentendiagramm - Library Management System

## Komponentendiagramm in PlantUML Notation

```plantuml
@startuml Library Management System - Component Diagram

!theme spacelab

package "Library Management System" {
    
    package "Presentation Layer" {
        component [REST API] as REST
        component [LibraryController] as Controller
        component [GlobalExceptionHandler] as ExceptionHandler
        
        REST --> Controller : HTTP Requests
        Controller --> ExceptionHandler : Exception Handling
    }
    
    package "Business Layer" {
        component [LibraryService] as Service
        component [Search Engine] as Search
        component [Borrow Management] as BorrowMgmt
        component [Status Manager] as StatusMgr
        
        Service --> Search : Regex Search
        Service --> BorrowMgmt : Borrow/Return
        Service --> StatusMgr : Status Queries
    }
    
    package "Data Layer" {
        component [InMemoryDatabase] as Database
        component [Data Initialization] as DataInit
        
        Database --> DataInit : @PostConstruct
    }
    
    package "Domain Model" {
        component [MediaItem] as MediaModel
        component [Client] as ClientModel
        component [Exceptions] as ExceptionModel
        
        MediaModel --> ClientModel : Aggregation
    }
    
    package "External Libraries" {
        component [Spring Boot] as SpringBoot
        component [Spring Web] as SpringWeb
        component [Java Regex] as Regex
        component [Java UUID] as UUID
    }
}

' Abhängigkeiten zwischen Layern
Controller --> Service : Business Logic
Service --> Database : Data Access
Controller --> Database : Direct Access (Collections)

' Domain Model Verwendung
Service --> MediaModel : Uses
Service --> ClientModel : Uses
Service --> ExceptionModel : Throws
Controller --> ExceptionModel : Handles

' Framework Abhängigkeiten
Controller --> SpringWeb : @RestController, @RequestMapping
Service --> SpringBoot : @Service
Database --> SpringBoot : @Component
ExceptionHandler --> SpringWeb : @ControllerAdvice
DataInit --> SpringBoot : @PostConstruct

' Utility Dependencies
Service --> Regex : Pattern Matching
ClientModel --> UUID : ID Generation
MediaModel --> UUID : ID Generation

' Interface Definitionen
interface "REST Endpoints" as IREST
interface "Business Operations" as IBusiness
interface "Data Access" as IData

Controller .up.|> IREST
Service .up.|> IBusiness
Database .up.|> IData

@enduml
```

## Systemarchitektur Diagramm

```plantuml
@startuml Library Management System - System Architecture

!theme spacelab

!define RECTANGLE_COMPONENT(name, desc) rectangle name as "name\n<size:10><i>desc</i></size>"

package "Client Tier" {
    RECTANGLE_COMPONENT(WebBrowser, "Web Browser")
    RECTANGLE_COMPONENT(HTTPClient, "HTTP Client Tools")
    RECTANGLE_COMPONENT(MobileApp, "Mobile Applications")
}

package "Application Tier" {
    
    package "Web Layer" {
        RECTANGLE_COMPONENT(RestController, "REST Controller\n@RestController")
        RECTANGLE_COMPONENT(ExceptionHandler, "Global Exception Handler\n@ControllerAdvice")
    }
    
    package "Service Layer" {
        RECTANGLE_COMPONENT(LibraryService, "Library Service\n@Service")
    }
    
    package "Repository Layer" {
        RECTANGLE_COMPONENT(InMemoryDB, "In-Memory Database\n@Component")
    }
}

package "Domain Layer" {
    RECTANGLE_COMPONENT(MediaItem, "Media Item\nAbstract Base Class")
    RECTANGLE_COMPONENT(Book, "Book Entity")
    RECTANGLE_COMPONENT(DVD, "DVD Entity")  
    RECTANGLE_COMPONENT(Magazine, "Magazine Entity")
    RECTANGLE_COMPONENT(Client, "Client Entity")
}

package "Infrastructure" {
    RECTANGLE_COMPONENT(SpringBoot, "Spring Boot Framework")
    RECTANGLE_COMPONENT(JavaRuntime, "Java 17 Runtime")
    RECTANGLE_COMPONENT(Maven, "Maven Build Tool")
}

' Client Connections
WebBrowser --> RestController : HTTP/JSON
HTTPClient --> RestController : HTTP/JSON
MobileApp --> RestController : HTTP/JSON

' Internal Connections
RestController --> LibraryService : Method Calls
RestController --> ExceptionHandler : Exception Flow
LibraryService --> InMemoryDB : Data Access

' Domain Usage
LibraryService --> MediaItem : Business Logic
LibraryService --> Client : Customer Management
InMemoryDB --> Book : Storage
InMemoryDB --> DVD : Storage
InMemoryDB --> Magazine : Storage
InMemoryDB --> Client : Storage

' Inheritance
MediaItem <|-- Book
MediaItem <|-- DVD
MediaItem <|-- Magazine

' Infrastructure Dependencies
RestController --> SpringBoot : Framework
LibraryService --> SpringBoot : Framework
InMemoryDB --> SpringBoot : Framework
SpringBoot --> JavaRuntime : Runtime
SpringBoot --> Maven : Build

@enduml
```

## Package Diagramm

```plantuml
@startuml Library Management System - Package Diagram

!theme spacelab

package "com.example.demo" {
    class DemoApplication
}

package "controller" {
    class LibraryController
}

package "service" {
    class LibraryService
}

package "repository" {
    class InMemoryDatabase
}

package "model" {
    abstract class MediaItem
    class Client
    
    package "Unterklassen" {
        class Book
        class DVD
        class Magazine
    }
}

package "exception" {
    class ItemNotFoundException
    class OutOfStockException
    class GlobalExceptionHandler
}

' Package Dependencies
controller ..> service : <<uses>>
controller ..> repository : <<uses>>
controller ..> model : <<uses>>
controller ..> exception : <<uses>>

service ..> repository : <<uses>>
service ..> model : <<uses>>
service ..> exception : <<uses>>

repository ..> model : <<uses>>

model.Unterklassen ..> model : <<extends>>

@enduml
```

## Erklärung der Komponentenarchitektur

### 1. Presentation Layer
- **REST API**: Eingangsschicht für HTTP-Requests
- **LibraryController**: Orchestriert Request-Handling
- **GlobalExceptionHandler**: Zentrale Fehlerbehandlung

### 2. Business Layer
- **LibraryService**: Kerngeschäftslogik
- **Search Engine**: Regex-basierte Suchfunktionalität
- **Borrow Management**: Ausleihe- und Rückgabelogik
- **Status Manager**: Verfügbarkeits- und Statusabfragen

### 3. Data Layer
- **InMemoryDatabase**: Zentrale Datenhaltung
- **Data Initialization**: Testdaten-Setup

### 4. Domain Model
- **MediaItem Hierarchie**: Objektorientierte Medienmodellierung
- **Client Model**: Kundenrepräsentation
- **Exception Model**: Domänenspezifische Exceptions

### 5. External Dependencies
- **Spring Boot/Web**: Framework-Komponenten
- **Java Utilities**: UUID, Regex Pattern Matching

## Architektur-Prinzipien

### Layered Architecture
- Klare Trennung von Präsentation, Geschäftslogik und Datenhaltung
- Dependency Injection für lose Kopplung

### Domain-Driven Design
- Zentrale Domänenmodelle mit Vererbungshierarchie
- Geschäftsregeln in Service-Layer gekapselt

### Spring Boot Integration
- Annotation-basierte Konfiguration
- Automatische Bean-Registrierung und -Injection

### Error Handling Strategy
- Custom Exceptions für Domänenfehler
- Globaler Exception Handler für HTTP-Response-Mapping