# UML Diagramme - Library Management System

## Übersicht der erstellten UML-Diagramme

Diese Dokumentation enthält eine vollständige UML-Analyse des Library Management Systems mit folgenden Diagrammtypen:

### 📊 Verfügbare Diagramme

1. **[Klassendiagramm](UML_Klassendiagramm.md)**
   - Vollständige Klassenstruktur
   - Vererbungshierarchie (MediaItem → Book/DVD/Magazine)
   - Beziehungen zwischen allen Klassen
   - Layer-Architektur (Model, Service, Controller, Repository, Exception)

2. **[Anwendungsfalldiagramm](UML_Anwendungsfalldiagramm.md)**
   - Use Cases für Bibliotheksbenutzer und Administrator
   - Mediensuche, Ausleihe, Rückgabe, Statusabfragen
   - Detaillierte Beschreibungen aller Use Cases
   - Geschäftsregeln und Vorbedingungen

3. **[Sequenzdiagramm](UML_Sequenzdiagramm.md)**
   - Ausleihvorgang mit vollständiger Validierung
   - Suchvorgang mit Regex-Pattern-Matching
   - Rückgabevorgang mit Fehlerbehandlung
   - Interaktionen zwischen allen Systemkomponenten

4. **[Komponentendiagramm](UML_Komponentendiagramm.md)**
   - System-Architektur mit Layern
   - Komponentenabhängigkeiten
   - Package-Struktur
   - Spring Boot Integration

5. **[Zustandsdiagramm](UML_Zustandsdiagramm.md)**
   - MediaItem-Lebenszyklus (Verfügbar → Ausgeliehen)
   - Client-Ausleihstatus (0-5 Medien)
   - Ausleih- und Rückgabeprozesse
   - Zustandsübergänge mit Bedingungen

## 🎯 Projektanalysis-Ergebnisse

### Objektoriertierung & Vererbung (40%)
✅ **Vollständig implementiert**
- Abstrakte Basisklasse `MediaItem`
- Konkrete Ableitungen: `Book`, `DVD`, `Magazine`
- Polymorphie in Collections und Service-Methoden
- Gemeinsame Attribute (id, title, copiesAvailable) in Basisklasse

### Spring Boot & Dependency Injection (20%)
✅ **Korrekt umgesetzt**
- `@Component`, `@Service`, `@RestController` Annotationen
- Constructor-based Dependency Injection
- `@PostConstruct` für Dateninitialisierung
- Spring Boot Auto-Configuration

### Custom Exceptions (10%)
✅ **Professionell implementiert**
- `ItemNotFoundException` für nicht gefundene Entitäten
- `OutOfStockException` für Verfügbarkeitsprobleme
- Spezifische RuntimeException-Ableitungen

### @ControllerAdvice (10%)
✅ **Zentrale Fehlerbehandlung**
- `GlobalExceptionHandler` mit @ControllerAdvice
- HTTP-Status-Code-Mapping
- Benutzerfreundliche Fehlermeldungen

### UUID-basierte IDs (10%)
✅ **Automatische ID-Generierung**
- UUID.randomUUID() in Konstruktoren
- Eindeutige Identifikation aller Entitäten
- Typ-sichere ID-Verwendung

### Dateninitialisierung (5%)
✅ **Testdaten beim Start**
- @PostConstruct in InMemoryDatabase
- 10 Bücher, 10 DVDs, 10 Magazine, 5 Kunden
- Realistische Testszenarien

### Coding Style (5%)
✅ **Saubere Implementierung**
- Konsistente Namenskonventionen
- Korrekte Paket-Struktur
- Lesbare Code-Organisation

## 🔧 Technische Besonderheiten

### Regex-Suche
- Pattern & Matcher für flexible Suchfunktionen
- Case-Insensitive Suche
- Titel- und Autor-Matching bei Büchern

### Geschäftsregeln
- Maximum 5 Medien pro Client
- Verfügbarkeitsprüfung vor Ausleihe
- Atomare Ausleihe-/Rückgabe-Operationen

### REST API Design
- RESTful URL-Struktur
- HTTP-Verb-konforme Endpunkte
- JSON-basierte Datenübertragung
- Umfassende API-Dokumentation

### Erweiterte Features
- Ausleihstatus-Abfragen
- Verfügbarkeits-Monitoring
- Client-spezifische Medienübersicht
- Ausleiher-Tracking pro Medium

## 📋 Verwendung der Diagramme

### Für Entwickler
- **Klassendiagramm**: Verstehen der Code-Struktur
- **Sequenzdiagramm**: Nachvollziehen der Programmabläufe
- **Komponentendiagramm**: Architektur-Überblick

### Für Stakeholder
- **Anwendungsfalldiagramm**: Funktionalitäts-Übersicht
- **Zustandsdiagramm**: Geschäftsprozess-Verständnis

### Für Wartung/Erweiterung
- Alle Diagramme dienen als Dokumentation
- PlantUML-Format ermöglicht einfache Updates
- Nachvollziehbare System-Evolution

## 🛠️ PlantUML Integration

Alle Diagramme sind in PlantUML-Syntax erstellt und können mit folgenden Tools gerendert werden:

- **VS Code**: PlantUML Extension
- **IntelliJ IDEA**: PlantUML Integration Plugin  
- **Online**: http://www.plantuml.com/plantuml/
- **CLI**: plantuml.jar für Batch-Generierung

### Rendering-Befehle
```bash
# Einzelnes Diagramm rendern
java -jar plantuml.jar UML_Klassendiagramm.md

# Alle Diagramme rendern
java -jar plantuml.jar *.md
```