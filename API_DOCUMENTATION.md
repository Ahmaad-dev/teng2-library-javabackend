# Library Management System - API Dokumentation

## Übersicht
Diese Spring Boot Anwendung stellt eine REST-API für ein Bibliotheksverwaltungssystem bereit.
Das System bietet sowohl eine vollständige REST-API als auch eine interaktive Web-Oberfläche.

## Basis-URL
`http://localhost:8080`

## Web-Interface
- **Hauptseite**: `http://localhost:8080`
- **Interaktive Tests**: Alle API-Endpoints über Web-UI testbar
- **Demo-Modus**: Automatische Präsentationen verfügbar

## Verfügbare API-Endpunkte

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
- **Antwort**: JSON-Array mit allen Kunden

#### Neuen Kunden erstellen
- **POST** `/api/clients`
- **Beschreibung**: Erstellt einen neuen Kunden
- **Content-Type**: `application/json`
- **Body**:
```json
{
  "name": "Max Mustermann",
  "email": "max@example.com",
  "phone": "+49 123 456789"
}
```
- **Antwort**: Strukturierte Response mit Kunde-Details

#### Kunde nach ID suchen
- **GET** `/api/clients/{clientId}`
- **Beschreibung**: Findet einen spezifischen Kunden anhand der ID
- **Parameter**: `clientId` - UUID des Kunden
- **Antwort**: Kunden-Details mit ausgeliehenen Medien

#### Kunde aktualisieren
- **PUT** `/api/clients/{clientId}`
- **Beschreibung**: Aktualisiert Kundendaten
- **Parameter**: `clientId` - UUID des Kunden
- **Content-Type**: `application/json`
- **Body**: Felder die aktualisiert werden sollen (optional)
```json
{
  "name": "Neuer Name",
  "email": "neue@email.com",
  "phone": "+49 987 654321"
}
```

#### Kunde löschen
- **DELETE** `/api/clients/{clientId}`
- **Beschreibung**: Löscht einen Kunden (nur möglich wenn keine Medien ausgeliehen)
- **Parameter**: `clientId` - UUID des Kunden

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

### 6. Status-Abfragen und Verwaltung

#### Ausgeliehene Medien eines Kunden
- **GET** `/api/clients/{clientId}/borrowed-items`
- **Beschreibung**: Zeigt alle von einem Kunden ausgeliehenen Medien
- **Parameter**: `clientId` - UUID des Kunden
- **Antwort**: JSON-Array der ausgeliehenen Medien

#### Status eines Mediums prüfen
- **GET** `/api/items/{itemId}/status`
- **Beschreibung**: Prüft ob ein Medium verfügbar oder ausgeliehen ist
- **Parameter**: `itemId` - UUID des Mediums
- **Antwort**: Status-String ("verfügbar" oder "ausgeliehen")

#### Verfügbare Exemplare prüfen
- **GET** `/api/items/{itemId}/availability`
- **Beschreibung**: Zeigt die Anzahl verfügbarer Exemplare
- **Parameter**: `itemId` - UUID des Mediums
- **Antwort**: Anzahl verfügbarer Exemplare

#### Wer hat ein Medium ausgeliehen?
- **GET** `/api/items/{itemId}/borrower`
- **Beschreibung**: Zeigt alle Kunden die ein bestimmtes Medium ausgeliehen haben
- **Parameter**: `itemId` - UUID des Mediums
- **Antwort**: JSON-Array der Kunden

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

### Kunden (10 Stück)
- Anna Meier (anna.meier@email.de) - +49 30 12345678
- Thomas Huber (thomas.huber@gmail.com) - +49 89 87654321
- Lena Schmidt (lena.schmidt@web.de) - +49 40 11223344
- Max Mustermann (max.mustermann@example.com) - +49 69 55667788
- Julia Berger (julia.berger@yahoo.de) - +49 221 99877766
- Michael Richter (michael.richter@arcor.de) - +49 351 98765432
- Daniel Klein (daniel.klein@freenet.de) - +49 341 22446688
- Stefan Wagner (stefan.wagner@outlook.de) - +49 711 44332211
- Sarah Hoffmann (sarah.hoffmann@gmx.de) - +49 201 1357924
- Maria Kaufmann (maria.kaufmann@t-online.de) - +49 511 66778899

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

# Neuen Kunden erstellen
curl -X POST http://localhost:8080/api/clients \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","phone":"+49 123 456789"}'

# Kunde nach ID suchen
curl http://localhost:8080/api/clients/{clientId}

# Kunde aktualisieren
curl -X PUT http://localhost:8080/api/clients/{clientId} \
  -H "Content-Type: application/json" \
  -d '{"name":"Neuer Name","email":"neue@email.com"}'

# Kunde löschen
curl -X DELETE http://localhost:8080/api/clients/{clientId}

# Medium ausleihen
curl -X POST http://localhost:8080/api/clients/{clientId}/borrow/{itemId}

# Status eines Mediums prüfen
curl http://localhost:8080/api/items/{itemId}/status

# Ausgeliehene Medien eines Kunden
curl http://localhost:8080/api/clients/{clientId}/borrowed-items
```

## Response-Format

### Erfolgreiche Operationen
```json
{
  "success": true,
  "message": "Operation erfolgreich durchgeführt",
  "data": {
    "id": "uuid",
    "name": "Kunde Name",
    "email": "email@example.com",
    "phone": "+49 123 456789",
    "borrowedItems": []
  }
}
```

### Fehler-Responses
```json
{
  "success": false,
  "message": "Fehlerbeschreibung",
  "error": "Detaillierte Fehlerinformation"
}
```

## Web-Interface Funktionen

### 📊 Datenübersicht
- Anzeige aller Medien und Kunden
- Schnell-Tests für häufige Operationen
- Live-Datenvisualisierung

### 🔍 Erweiterte Suchfunktionen
- Text-basierte Suche in allen Medientypen
- Regex-Pattern Unterstützung
- ISBN-spezifische Suche

### 📖 Ausleihe-Management
- Interaktive Kunde- und Medienauswahl
- Echtzeit-Statusverfolgung
- Ausleihe- und Rückgabe-Workflows

### 👥 Kundenverwaltung
- Vollständige CRUD-Operationen über UI
- Formular-basierte Dateneingabe
- Tabellarische Kundendarstellung mit Kontaktdaten

### ⚙️ Admin-Funktionen
- Medium-Status-Abfragen
- Verfügbarkeits-Checks
- Regex-basierte Erweiterte Suche
- Vollständige API-Tests

### 🎯 Demo-Präsentationen
- Automatische Demo-Abläufe
- Schritt-für-Schritt geführte Tests
- Fortschrittsanzeige und Ergebnisvisualisierung

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
- **API-Endpunkte**: `http://localhost:8080/api/*`
- **Web-Interface**: `http://localhost:8080`

## Validierung und Business Rules

### Kundenverwaltung
- **Eindeutige Namen**: Kunden-Namen müssen eindeutig sein (case-insensitive)
- **Pflichtfelder**: Name ist erforderlich, E-Mail und Telefon optional
- **Lösch-Beschränkung**: Kunden können nur gelöscht werden wenn keine Medien ausgeliehen sind

### Ausleihsystem
- **Maximum**: Bis zu 5 Medien pro Kunde
- **Verfügbarkeit**: Nur verfügbare Exemplare können ausgeliehen werden
- **Rückgabe**: Nur tatsächlich ausgeliehene Medien können zurückgegeben werden

## Fehlerbehandlung

Die API behandelt folgende Fehler automatisch:
- **404 Not Found**: Wenn ein Medium oder Kunde nicht gefunden wird
- **400 Bad Request**: Bei ungültigen Anfragen, keine Exemplare verfügbar, oder Business Rule Verstößen
- **409 Conflict**: Bei Duplikaten (z.B. Kunde mit gleichem Namen bereits vorhanden)
- **422 Unprocessable Entity**: Bei Validierungsfehlern in Eingabedaten
- **500 Internal Server Error**: Bei unerwarteten Fehlern

Alle Fehlerantworten enthalten strukturierte Meldungen im JSON-Format mit `success`, `message` und ggf. `error` Feldern.
