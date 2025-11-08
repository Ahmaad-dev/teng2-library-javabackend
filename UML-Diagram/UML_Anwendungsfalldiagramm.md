# UML Anwendungsfalldiagramm - Library Management System

## Use Case Diagramm in PlantUML Notation

```plantuml
@startuml Library Management System - Use Case Diagram

!theme spacelab

left to right direction

actor "Bibliotheksbenutzer" as User
actor "System Administrator" as Admin

rectangle "Library Management System" {
    
    package "Mediensuche" {
        usecase "Bücher suchen" as SearchBooks
        usecase "DVDs suchen" as SearchDVDs  
        usecase "Magazine suchen" as SearchMagazines
        usecase "Buch nach ISBN finden" as FindBookByISBN
    }
    
    package "Medienverwaltung" {
        usecase "Alle Bücher anzeigen" as ShowAllBooks
        usecase "Alle DVDs anzeigen" as ShowAllDVDs
        usecase "Alle Magazine anzeigen" as ShowAllMagazines
    }
    
    package "Kundenverwaltung" {
        usecase "Alle Kunden anzeigen" as ShowAllClients
        usecase "Kundeninformationen abrufen" as GetClientInfo
    }
    
    package "Ausleihverwaltung" {
        usecase "Medium ausleihen" as BorrowItem
        usecase "Medium zurückgeben" as ReturnItem
        usecase "Ausgeliehene Medien anzeigen" as ShowBorrowedItems
        usecase "Ausleihstatus prüfen" as CheckBorrowStatus
    }
    
    package "Statusabfragen" {
        usecase "Verfügbarkeit prüfen" as CheckAvailability
        usecase "Mediumstatus anzeigen" as ShowItemStatus
        usecase "Ausleiher anzeigen" as ShowBorrowers
    }
    
    package "Systemfunktionen" {
        usecase "Testdaten initialisieren" as InitTestData
        usecase "Fehlerbehandlung" as HandleErrors
    }
}

' Benutzer-Use Cases
User --> SearchBooks
User --> SearchDVDs
User --> SearchMagazines
User --> FindBookByISBN
User --> ShowAllBooks
User --> ShowAllDVDs
User --> ShowAllMagazines
User --> BorrowItem
User --> ReturnItem
User --> ShowBorrowedItems
User --> CheckBorrowStatus
User --> CheckAvailability
User --> ShowItemStatus
User --> ShowBorrowers

' Administrator-Use Cases
Admin --> ShowAllClients
Admin --> GetClientInfo
Admin --> InitTestData

' System-Use Cases (automatisch)
BorrowItem .> HandleErrors : <<include>>
ReturnItem .> HandleErrors : <<include>>
SearchBooks .> HandleErrors : <<include>>
FindBookByISBN .> HandleErrors : <<include>>

' Erweiterte Beziehungen
BorrowItem ..> CheckAvailability : <<extend>>
ReturnItem ..> ShowItemStatus : <<extend>>

@enduml
```

## Detaillierte Use Case Beschreibungen

### 1. Mediensuche
- **Bücher suchen**: Regex-basierte Suche nach Titel oder Autor
- **DVDs suchen**: Regex-basierte Suche nach Titel
- **Magazine suchen**: Regex-basierte Suche nach Titel
- **Buch nach ISBN finden**: Exakte Suche über ISBN-Nummer

### 2. Medienverwaltung
- **Alle Bücher/DVDs/Magazine anzeigen**: Vollständige Auflistung aller verfügbaren Medien

### 3. Kundenverwaltung
- **Alle Kunden anzeigen**: Übersicht aller registrierten Kunden
- **Kundeninformationen abrufen**: Details zu einzelnen Kunden

### 4. Ausleihverwaltung
- **Medium ausleihen**: 
  - Vorbedingung: Kunde existiert, Medium verfügbar, max. 5 Medien pro Kunde
  - Nachbedingung: Medium dem Kunden zugeordnet, Verfügbarkeit reduziert
- **Medium zurückgeben**:
  - Vorbedingung: Medium wurde vom Kunden ausgeliehen
  - Nachbedingung: Medium vom Kunden entfernt, Verfügbarkeit erhöht
- **Ausgeliehene Medien anzeigen**: Liste aller von einem Kunden ausgeliehenen Medien

### 5. Statusabfragen
- **Verfügbarkeit prüfen**: Anzahl verfügbarer Exemplare
- **Mediumstatus anzeigen**: Detaillierte Verfügbarkeitsinformation
- **Ausleiher anzeigen**: Welche Kunden haben ein bestimmtes Medium ausgeliehen

### 6. Systemfunktionen
- **Testdaten initialisieren**: Automatisches Laden von Beispieldaten beim Start
- **Fehlerbehandlung**: Globale Exception-Behandlung

## Akteure

### Bibliotheksbenutzer
- Primärer Akteur für alle Medien- und Ausleihfunktionen
- Kann Medien suchen, ausleihen, zurückgeben und Status abfragen

### System Administrator
- Verwaltung von Kundendaten
- System-Initialisierung und -Wartung

## Geschäftsregeln
1. **Ausleihlimit**: Max. 5 Medien pro Kunde gleichzeitig
2. **Verfügbarkeitsprüfung**: Nur verfügbare Medien können ausgeliehen werden
3. **Eindeutige Identifikation**: Alle Entitäten haben UUID als Primärschlüssel
4. **Regex-Unterstützung**: Flexible Suchfunktionen mit Pattern Matching