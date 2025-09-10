# Library Management System - API Dokumentation

## Übersicht
Diese Spring Boot Anwendung stellt eine REST-API für ein Bibliotheksverwaltungssystem bereit.

## Basis-URL
`http://localhost:8080`

## Verfügbare Endpunkte

### 1. Bücher verwalten

#### Alle Bücher anzeigen
- **GET** `/api/books`
- **Beschreibung**: Zeigt alle verfügbaren Bücher an
- **Antwort**: JSON-Array mit allen Büchern

#### Bücher suchen (Regex)
- **GET** `/api/books/search?query=<suchterm>`
- **Beschreibung**: Sucht Bücher nach Titel oder Autor (unterstützt Regex)
- **Parameter**: `query` - Suchterm oder Regex-Pattern
- **Beispiel**: `/api/books/search?query=Java`

#### Buch nach ISBN finden
- **GET** `/api/books/isbn/{isbn}`
- **Beschreibung**: Findet ein spezifisches Buch anhand der ISBN
- **Parameter**: `isbn` - Die ISBN des Buches
- **Beispiel**: `/api/books/isbn/978-3608939811`

### 2. DVDs verwalten

#### Alle DVDs anzeigen
- **GET** `/api/dvds`
- **Beschreibung**: Zeigt alle verfügbaren DVDs an

#### DVDs suchen (Regex)
- **GET** `/api/dvds/search?query=<suchterm>`
- **Beschreibung**: Sucht DVDs nach Titel (unterstützt Regex)
- **Parameter**: `query` - Suchterm oder Regex-Pattern

### 3. Magazine verwalten

#### Alle Magazine anzeigen
- **GET** `/api/magazines`
- **Beschreibung**: Zeigt alle verfügbaren Magazine an

#### Magazine suchen (Regex)
- **GET** `/api/magazines/search?query=<suchterm>`
- **Beschreibung**: Sucht Magazine nach Titel (unterstützt Regex)
- **Parameter**: `query` - Suchterm oder Regex-Pattern

### 4. Kunden verwalten

#### Alle Kunden anzeigen
- **GET** `/api/clients`
- **Beschreibung**: Zeigt alle registrierten Kunden an

### 5. Ausleihe und Rückgabe

#### Medium ausleihen
- **POST** `/api/clients/{clientId}/borrow/{itemId}`
- **Beschreibung**: Leiht ein Medium an einen Kunden aus
- **Parameter**: 
  - `clientId` - UUID des Kunden
  - `itemId` - UUID des Mediums
- **Antwort**: Bestätigungsnachricht

#### Medium zurückgeben
- **POST** `/api/clients/{clientId}/return/{itemId}`
- **Beschreibung**: Gibt ein ausgeliehenes Medium zurück
- **Parameter**: 
  - `clientId` - UUID des Kunden
  - `itemId` - UUID des Mediums
- **Antwort**: Bestätigungsnachricht

## Vordefinierte Testdaten

### Bücher
- Der Herr der Ringe (J.R.R. Tolkien) - ISBN: 978-3608939811
- Harry Potter (J.K. Rowling) - ISBN: 978-3551551672
- 1984 (George Orwell) - ISBN: 978-0451524935
- Clean Code (Robert C. Martin) - ISBN: 978-0132350884
- Effective Java (Joshua Bloch) - ISBN: 978-0134685991
- und weitere...

### DVDs
- Matrix, Inception, Interstellar, The Dark Knight, und weitere...

### Magazine
- National Geographic, TIME, Scientific American, Der Spiegel, und weitere...

### Kunden
- Anna Meier, Thomas Huber, Lena Schmidt, Max Mustermann, Julia Berger

## Beispiel-Anfragen

```bash
# Alle Bücher anzeigen
curl http://localhost:8080/api/books

# Nach Java-Büchern suchen
curl "http://localhost:8080/api/books/search?query=Java"

# Buch nach ISBN finden
curl http://localhost:8080/api/books/isbn/978-3608939811

# Alle Kunden anzeigen
curl http://localhost:8080/api/clients

# Medium ausleihen (Beispiel-IDs)
curl -X POST http://localhost:8080/api/clients/{clientId}/borrow/{itemId}
```

## Starten der Anwendung

### Voraussetzungen
- Java 17 oder höher
- Maven (wird über Maven Wrapper bereitgestellt)

### Starten
```bash
# Im Projektverzeichnis
./mvnw spring-boot:run

# Oder unter Windows
.\mvnw.cmd spring-boot:run
```

Die Anwendung startet auf Port 8080.

## Fehlerbehandlung

Die API behandelt folgende Fehler automatisch:
- **404 Not Found**: Wenn ein Medium oder Kunde nicht gefunden wird
- **400 Bad Request**: Bei ungültigen Anfragen oder wenn keine Exemplare verfügbar sind
- **500 Internal Server Error**: Bei unerwarteten Fehlern

Alle Fehlerantworten enthalten eine aussagekräftige Fehlermeldung im JSON-Format.
