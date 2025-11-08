# UML Klassendiagramm - Library Management System

## Klassendiagramm in PlantUML Notation

```plantuml
@startuml Library Management System - Class Diagram

!theme spacelab

package "Model Layer" {
    abstract class MediaItem {
        # UUID id
        # String title
        # int copiesAvailable
        
        + MediaItem(title: String, copiesAvailable: int)
        + getId(): UUID
        + getTitle(): String
        + getCopiesAvailable(): int
        + setCopiesAvailable(copiesAvailable: int): void
        + toString(): String
    }
    
    class Book extends MediaItem {
        - String author
        - String isbn
        
        + Book(title: String, author: String, isbn: String, copiesAvailable: int)
        + getAuthor(): String
        + setAuthor(author: String): void
        + getIsbn(): String
        + setIsbn(isbn: String): void
        + toString(): String
    }
    
    class DVD extends MediaItem {
        - String director
        - int duration
        
        + DVD(title: String, director: String, duration: int, copiesAvailable: int)
        + getDirector(): String
        + setDirector(director: String): void
        + getDuration(): int
        + setDuration(duration: int): void
        + toString(): String
    }
    
    class Magazine extends MediaItem {
        + Magazine(title: String, copiesAvailable: int)
    }
    
    class Client {
        - UUID id
        - String name
        - List<MediaItem> borrowedItems
        
        + Client(name: String)
        + getId(): UUID
        + getName(): String
        + getBorrowedItems(): List<MediaItem>
        + canBorrowMore(): boolean
        + borrowItem(item: MediaItem): void
        + returnItem(item: MediaItem): void
        + toString(): String
    }
}

package "Repository Layer" {
    class InMemoryDatabase {
        + Map<UUID, Book> books
        + Map<UUID, DVD> dvds
        + Map<UUID, Magazine> magazines
        + Map<UUID, Client> clients
        
        + init(): void
        - addBook(book: Book): void
        - addDVD(dvd: DVD): void
        - addMagazine(magazine: Magazine): void
        - addClient(client: Client): void
    }
}

package "Service Layer" {
    class LibraryService {
        - InMemoryDatabase db
        
        + LibraryService(db: InMemoryDatabase)
        + searchBooksByRegex(regex: String): List<Book>
        + searchDVDsByRegex(regex: String): List<DVD>
        + searchMagazinesByRegex(regex: String): List<Magazine>
        + findBookByIsbn(isbn: String): Book
        + borrowItem(clientId: UUID, itemId: UUID): void
        + returnItem(clientId: UUID, itemId: UUID): void
        + getBorrowedItemsForClient(clientId: UUID): List<MediaItem>
        + getItemStatus(itemId: UUID): String
        + getItemAvailability(itemId: UUID): int
        + getClientsBorrowingItem(itemId: UUID): List<Client>
        + isItemBorrowed(itemId: UUID): boolean
        - findAnyMedia(id: UUID): MediaItem
        - countBorrowedCopies(itemId: UUID): int
    }
}

package "Controller Layer" {
    class LibraryController {
        - LibraryService service
        - InMemoryDatabase db
        
        + LibraryController(service: LibraryService, db: InMemoryDatabase)
        + searchBooks(query: String): List<Book>
        + searchDVDs(query: String): List<DVD>
        + searchMagazines(query: String): List<Magazine>
        + findBookByIsbn(isbn: String): Book
        + borrow(clientId: UUID, itemId: UUID): String
        + giveBack(clientId: UUID, itemId: UUID): String
        + getAllBooks(): Collection<Book>
        + getAllDVDs(): Collection<DVD>
        + getAllMagazines(): Collection<Magazine>
        + getAllClients(): Collection<Client>
        + getBorrowedItems(clientId: UUID): List<MediaItem>
        + getItemStatus(itemId: UUID): String
        + getItemAvailability(itemId: UUID): int
        + getItemBorrowers(itemId: UUID): List<Client>
    }
}

package "Exception Layer" {
    class ItemNotFoundException extends RuntimeException {
        + ItemNotFoundException(message: String)
    }
    
    class OutOfStockException extends RuntimeException {
        + OutOfStockException(message: String)
    }
    
    class GlobalExceptionHandler {
        + handleItemNotFound(ex: ItemNotFoundException): ResponseEntity<String>
        + handleOutOfStock(ex: OutOfStockException): ResponseEntity<String>
        + handleGeneral(ex: RuntimeException): ResponseEntity<String>
    }
}

' Beziehungen
Client ||--o{ MediaItem : "borrows 0..5"
MediaItem <|-- Book
MediaItem <|-- DVD
MediaItem <|-- Magazine

LibraryController --> LibraryService : uses
LibraryController --> InMemoryDatabase : uses
LibraryService --> InMemoryDatabase : uses
LibraryService ..> ItemNotFoundException : throws
LibraryService ..> OutOfStockException : throws

InMemoryDatabase o-- Book : stores
InMemoryDatabase o-- DVD : stores
InMemoryDatabase o-- Magazine : stores
InMemoryDatabase o-- Client : stores

GlobalExceptionHandler ..> ItemNotFoundException : handles
GlobalExceptionHandler ..> OutOfStockException : handles

@enduml
```

## Erklärung der Klassenstruktur

### Model Layer (Domain Objects)
- **MediaItem**: Abstrakte Basisklasse für alle ausleihbaren Medien
- **Book, DVD, Magazine**: Konkrete Implementierungen verschiedener Medientypen
- **Client**: Repräsentiert Bibliothekskunden mit Ausleihfunktionalität

### Repository Layer
- **InMemoryDatabase**: Zentrale Datenhaltung mit Hash-Maps für alle Entitäten

### Service Layer
- **LibraryService**: Business Logic für Suche, Ausleihe und Statusabfragen

### Controller Layer
- **LibraryController**: REST-Endpunkte für die API

### Exception Layer
- **Custom Exceptions**: Spezifische Fehlerbehandlung
- **GlobalExceptionHandler**: Zentrale HTTP-Fehlerbehandlung

### Wichtige Designprinzipien
1. **Vererbung**: MediaItem als abstrakte Basisklasse
2. **Dependency Injection**: Service und Repository werden injiziert
3. **Separation of Concerns**: Klare Trennung der Layer
4. **Exception Handling**: Custom Exceptions mit globalem Handler