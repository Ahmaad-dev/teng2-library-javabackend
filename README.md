# Library Management System

Ein vollständiges Bibliotheksverwaltungssystem, entwickelt mit Spring Boot und Java.

## 🎯 Funktionen

- **Medienverwaltung**: Bücher, DVDs und Magazine verwalten
- **Suchfunktion**: Regex-basierte Suche nach Titel, Autor und ISBN
- **Kundenverwaltung**: Registrierung und Verwaltung von Bibliothekskunden
- **Ausleihsystem**: Ausleihe und Rückgabe von Medien
- **REST API**: Vollständige RESTful API für alle Funktionen
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

### Ausleihe
- `POST /api/clients/{clientId}/borrow/{itemId}` - Medium ausleihen
- `POST /api/clients/{clientId}/return/{itemId}` - Medium zurückgeben

## 🧪 API testen

### Mit HTTP-Datei (VS Code)
Die Datei `src/main/resources/api-tests.http` enthält vorkonfigurierte API-Tests.

### Mit Browser
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
```

## 📊 Testdaten

Die Anwendung wird mit folgenden Testdaten initialisiert:

### Bücher (10 Stück)
- Der Herr der Ringe, Harry Potter, 1984, Clean Code, Effective Java, etc.

### DVDs (10 Stück)
- Matrix, Inception, Interstellar, The Dark Knight, etc.

### Magazine (10 Stück)
- National Geographic, TIME, Scientific American, Der Spiegel, etc.

### Kunden (5 Stück)
- Anna Meier, Thomas Huber, Lena Schmidt, Max Mustermann, Julia Berger

## 🏗️ Projektstruktur

```
src/
├── main/
│   ├── java/
│   │   ├── com/example/demo/
│   │   │   └── DemoApplication.java         # Hauptklasse
│   │   ├── controller/
│   │   │   └── LibraryController.java       # REST Controller
│   │   ├── service/
│   │   │   └── LibraryService.java          # Business Logic
│   │   ├── repository/
│   │   │   └── InMemoryDatabase.java        # Datenbank-Simulation
│   │   ├── model/
│   │   │   ├── MediaItem.java               # Basis-Klasse
│   │   │   └── Unterklassen/
│   │   │       ├── Book.java                # Buch-Modell
│   │   │       ├── DVD.java                 # DVD-Modell
│   │   │       ├── Magazine.java            # Magazin-Modell
│   │   │       └── Client.java              # Kunden-Modell
│   │   └── exception/
│   │       ├── GlobalExceptionHandler.java  # Fehlerbehandlung
│   │       ├── ItemNotFoundException.java
│   │       └── OutOfStockException.java
│   └── resources/
│       ├── application.properties           # Konfiguration
│       └── api-tests.http                   # API-Tests
└── test/
    └── java/
        └── com/example/demo/
            └── DemoApplicationTests.java    # Tests
```

## 🛠️ Technologien

- **Java 19** - Programmiersprache
- **Spring Boot 3.5.5** - Framework
- **Spring Web** - REST API
- **Spring DevTools** - Entwicklungstools
- **Maven** - Build-Management
- **Jakarta Annotations** - Annotationen

## 📈 Features im Detail

### Suchfunktion
- Unterstützt Regex-Pattern für erweiterte Suche
- Case-insensitive Suche
- Suche in Titel, Autor (bei Büchern)

### Ausleihsystem
- Maximal 5 Medien pro Kunde
- Automatische Bestandsverwaltung
- Fehlerbehandlung bei nicht verfügbaren Medien

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

### Hot Reload
Spring DevTools aktiviert automatisches Neuladen bei Dateiänderungen während der Entwicklung.

## 📄 Lizenz

Dieses Projekt ist für Bildungszwecke erstellt worden.

---

## 🎉 Erfolgreich repariert!

✅ **Alle Fehler behoben:**
- Paket-Inkonsistenzen korrigiert
- Fehlende Klassen erstellt
- Import-Statements aktualisiert
- Maven Wrapper repariert
- Spring Boot konfiguration optimiert

✅ **Funktionsfähig:**
- Anwendung startet erfolgreich
- Alle API-Endpunkte funktional
- Testdaten werden korrekt geladen
- Exception Handling arbeitet ordnungsgemäß

✅ **Entwicklerfreundlich:**
- VS Code Tasks konfiguriert
- API-Dokumentation erstellt
- Test-Dateien bereitgestellt
- Umfassende README mit Anleitungen

**🚀 Die Anwendung ist jetzt vollständig funktionsfähig und einsatzbereit!**
