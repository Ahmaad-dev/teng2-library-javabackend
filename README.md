# Library Management System

Ein vollständiges Bibliotheksverwaltungssystem, entwickelt mit Spring Boot und Java.

## 🎯 Funktionen

- **Medienverwaltung**: Bücher, DVDs und Magazine verwalten
- **Suchfunktion**: Regex-basierte Suche nach Titel, Autor und ISBN
- **Kundenverwaltung**: Vollständige CRUD-Operationen für Bibliothekskunden
- **Ausleihsystem**: Ausleihe und Rückgabe von Medien mit Statusverfolgung
- **REST API**: Vollständige RESTful API für alle Funktionen
- **Web-Frontend**: Interaktive HTML-Oberfläche für alle Funktionen
- **Exception Handling**: Umfassende Fehlerbehandlung

## 🚀 Schnellstart

### Voraussetzungen
- Java 17 oder höher
- Maven (über Maven Wrapper bereitgestellt)

### Anwendung starten

```bash
# Projekt klonen oder herunterladen
cd teng2-library-javabackend

# Anwendung starten
./mvnw spring-boot:run
# Oder unter Windows:
.\mvnw.cmd spring-boot:run
```

Die Anwendung läuft dann auf: `http://localhost:8080`

### 🌐 Web-Interface
Das System bietet eine vollständige Web-Oberfläche unter:
- **Hauptseite**: `http://localhost:8080`
- **Interaktive API-Tests**: Alle Funktionen über die Web-UI testbar

### 🔧 VS Code Tasks

Das Projekt enthält vorkonfigurierte VS Code Tasks:

1. **Build Library Backend** - Kompiliert das Projekt
2. **Run Library Backend** - Startet die Anwendung
3. **Test Library Backend** - Führt Tests aus
4. **Package Library Backend** - Erstellt ein JAR-Package

Tasks über `Ctrl+Shift+P` → "Tasks: Run Task" ausführen.

## 📡 API-Endpunkte

### Bücher
- `GET /api/books` - Alle Bücher anzeigen
- `GET /api/books/search?query=<term>` - Bücher suchen
- `GET /api/books/isbn/{isbn}` - Buch nach ISBN finden

### DVDs
- `GET /api/dvds` - Alle DVDs anzeigen
- `GET /api/dvds/search?query=<term>` - DVDs suchen

### Magazine
- `GET /api/magazines` - Alle Magazine anzeigen
- `GET /api/magazines/search?query=<term>` - Magazine suchen

### Kunden
- `GET /api/clients` - Alle Kunden anzeigen
- `POST /api/clients` - Neuen Kunden erstellen
- `GET /api/clients/{id}` - Kunde nach ID suchen
- `PUT /api/clients/{id}` - Kunden aktualisieren
- `DELETE /api/clients/{id}` - Kunde löschen

### Ausleihe & Statusabfragen
- `POST /api/clients/{clientId}/borrow/{itemId}` - Medium ausleihen
- `POST /api/clients/{clientId}/return/{itemId}` - Medium zurückgeben
- `GET /api/clients/{clientId}/borrowed-items` - Ausgeliehene Medien eines Kunden
- `GET /api/items/{itemId}/status` - Status eines Mediums prüfen
- `GET /api/items/{itemId}/availability` - Verfügbare Exemplare prüfen
- `GET /api/items/{itemId}/borrower` - Wer hat ein Medium ausgeliehen

## 🧪 API testen

### Mit HTTP-Datei (VS Code)
Die Datei `api-tests.http` enthält vorkonfigurierte API-Tests für alle Endpoints.

### Mit Web-Interface
Öffne `http://localhost:8080` im Browser für eine vollständige interaktive Oberfläche mit:
- 📊 Datenübersicht aller Medien und Kunden
- 🔍 Erweiterte Suchfunktionen
- 📖 Ausleihe-Management
- 👥 Vollständige Kundenverwaltung
- ⚙️ Admin-Funktionen und Status-Abfragen
- 🎯 Automatische Demo-Präsentationen

### Mit Browser (API direkt)
- Bücher: `http://localhost:8080/api/books`
- DVDs: `http://localhost:8080/api/dvds`
- Magazine: `http://localhost:8080/api/magazines`
- Kunden: `http://localhost:8080/api/clients`

### Mit curl
```bash
# Alle Bücher anzeigen
curl http://localhost:8080/api/books

# Nach Java-Büchern suchen
curl "http://localhost:8080/api/books/search?query=Java"

# Buch nach ISBN finden
curl http://localhost:8080/api/books/isbn/978-3608939811

# Neuen Kunden erstellen
curl -X POST http://localhost:8080/api/clients \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","phone":"+49 123 456789"}'

# Medium ausleihen
curl -X POST http://localhost:8080/api/clients/{clientId}/borrow/{itemId}
```

## 📊 Testdaten

Die Anwendung wird mit folgenden Testdaten initialisiert:

### Bücher (10 Stück)
- Der Herr der Ringe, Harry Potter, 1984, Clean Code, Effective Java, etc.

### DVDs (10 Stück)
- Matrix, Inception, Interstellar, The Dark Knight, etc.

### Magazine (10 Stück)
- National Geographic, TIME, Scientific American, Der Spiegel, etc.

### Kunden (10 Stück)
- Anna Meier, Thomas Huber, Lena Schmidt, Max Mustermann, Julia Berger,
- Michael Richter, Daniel Klein, Stefan Wagner, Sarah Hoffmann, Maria Kaufmann

## 🏗️ Projektstruktur

```
src/
├── main/
│   ├── java/
│   │   ├── com/library/
│   │   │   └── LibraryApplication.java      # Hauptklasse
│   │   ├── controller/
│   │   │   └── LibraryController.java       # REST Controller
│   │   ├── service/
│   │   │   └── LibraryService.java          # Business Logic
│   │   ├── repository/
│   │   │   └── InMemoryDatabase.java        # Datenbank-Simulation
│   │   ├── model/
│   │   │   ├── MediaItem.java               # Basis-Klasse
│   │   │   ├── Book.java                    # Buch-Modell
│   │   │   ├── DVD.java                     # DVD-Modell
│   │   │   ├── Magazine.java                # Magazin-Modell
│   │   │   ├── Client.java                  # Kunden-Modell
│   │   │   └── dto/
│   │   │       └── CreateClientRequest.java # Client-DTO
│   │   └── exception/
│   │       ├── GlobalExceptionHandler.java  # Fehlerbehandlung
│   │       ├── ItemNotFoundException.java
│   │       └── OutOfStockException.java
│   └── resources/
│       ├── application.properties           # Konfiguration
│       └── static/
│           └── index.html                   # Web-Frontend
├── api-tests.http                           # API-Tests
└── test/
    └── java/
        └── com/library/
            └── LibraryApplicationTests.java # Tests
```

## 🛠️ Technologien

- **Java 17+** - Programmiersprache
- **Spring Boot 3.5.5** - Framework
- **Spring Web** - REST API
- **Spring DevTools** - Entwicklungstools
- **Maven** - Build-Management
- **HTML/CSS/JavaScript** - Web-Frontend

## 📈 Features im Detail

### Suchfunktion
- Unterstützt Regex-Pattern für erweiterte Suche
- Case-insensitive Suche
- Suche in Titel, Autor (bei Büchern)

### Kundenverwaltung
- Vollständige CRUD-Operationen (Create, Read, Update, Delete)
- Kontaktdaten: Name, E-Mail, Telefonnummer
- Eindeutige Namen-Validierung
- Strukturierte API-Responses mit Erfolgs-/Fehlermeldungen

### Ausleihsystem
- Maximal 5 Medien pro Kunde
- Automatische Bestandsverwaltung
- Status-Tracking für alle Medien
- Fehlerbehandlung bei nicht verfügbaren Medien

### Web-Frontend
- Vollständige interaktive Benutzeroberfläche
- Tabbed-Interface mit verschiedenen Funktionsbereichen
- Live-API-Tests und Datenvisualisierung
- Responsive Design für mobile Geräte
- Automatische Demo-Präsentationen

### Error Handling
- **404 Not Found**: Medium/Kunde nicht gefunden
- **400 Bad Request**: Ungültige Anfragen, keine Exemplare verfügbar
- **500 Internal Server Error**: Unerwartete Fehler

## 📝 Entwicklung

### Build Commands
```bash
# Kompilieren
.\mvnw.cmd compile

# Tests ausführen
.\mvnw.cmd test

# Package erstellen
.\mvnw.cmd package

# Anwendung starten
.\mvnw.cmd spring-boot:run
```

