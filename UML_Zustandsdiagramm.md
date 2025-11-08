# UML Zustandsdiagramm - Library Management System

## Zustandsdiagramm für MediaItem Lebenszyklus

```plantuml
@startuml Library Management System - State Diagram: MediaItem Lifecycle

!theme spacelab

title MediaItem Lebenszyklus

[*] --> Initialisiert : Erstellung mit Anfangsbestand

state Initialisiert {
    Initialisiert : copiesAvailable = initialCopies
    Initialisiert : Alle Exemplare verfügbar
}

state Verfügbar {
    Verfügbar : copiesAvailable > 0
    Verfügbar : Kann ausgeliehen werden
}

state TeilweiseAusgeliehen {
    TeilweiseAusgeliehen : 0 < copiesAvailable < totalCopies
    TeilweiseAusgeliehen : Einige Exemplare ausgeliehen
}

state Ausverkauft {
    Ausverkauft : copiesAvailable = 0
    Ausverkauft : Alle Exemplare ausgeliehen
}

Initialisiert --> Verfügbar : Systemstart abgeschlossen

Verfügbar --> TeilweiseAusgeliehen : Erstes Exemplar ausgeliehen\n[copiesAvailable > 1]
Verfügbar --> Ausverkauft : Letztes Exemplar ausgeliehen\n[copiesAvailable = 1]

TeilweiseAusgeliehen --> TeilweiseAusgeliehen : Weiteres Exemplar ausgeliehen\n[copiesAvailable > 1]
TeilweiseAusgeliehen --> Ausverkauft : Letztes verfügbares Exemplar ausgeliehen\n[copiesAvailable = 1]
TeilweiseAusgeliehen --> Verfügbar : Exemplar zurückgegeben\n[alle Exemplare verfügbar]

Ausverkauft --> TeilweiseAusgeliehen : Erstes Exemplar zurückgegeben\n[weitere noch ausgeliehen]
Ausverkauft --> Verfügbar : Alle Exemplare zurückgegeben

note right of Verfügbar : Ausleihe möglich
note right of TeilweiseAusgeliehen : Begrenzte Ausleihe möglich
note right of Ausverkauft : Keine Ausleihe möglich\nOutOfStockException

@enduml
```

## Zustandsdiagramm für Client Ausleihstatus

```plantuml
@startuml Library Management System - State Diagram: Client Borrow Status

!theme spacelab

title Client Ausleihstatus

[*] --> NeuRegistriert : Client Erstellung

state NeuRegistriert {
    NeuRegistriert : borrowedItems.size() = 0
    NeuRegistriert : Kann bis zu 5 Medien ausleihen
}

state KannAusleihen {
    KannAusleihen : borrowedItems.size() < 5
    KannAusleihen : Weitere Ausleihen möglich
}

state AusleihgrenzeerReicht {
    AusleihgrenzeerReicht : borrowedItems.size() = 5
    AusleihgrenzeerReicht : Keine weiteren Ausleihen möglich
}

NeuRegistriert --> KannAusleihen : Erstes Medium ausgeliehen

KannAusleihen --> KannAusleihen : Medium ausgeliehen\n[borrowedItems.size() < 4]
KannAusleihen --> AusleihgrenzeerReicht : 5. Medium ausgeliehen
KannAusleihen --> NeuRegistriert : Alle Medien zurückgegeben

AusleihgrenzeerReicht --> KannAusleihen : Medium zurückgegeben

note right of NeuRegistriert : Volles Ausleihlimit verfügbar
note right of KannAusleihen : Partielle Ausleihen möglich
note right of AusleihgrenzeerReicht : Ausleihlimit erreicht\nKeine weitere Ausleihe möglich

@enduml
```

## Zustandsdiagramm für Ausleihvorgang

```plantuml
@startuml Library Management System - State Diagram: Borrow Process

!theme spacelab

title Ausleihvorgang Zustandsmaschine

[*] --> AusleihAnfrage : POST /api/clients/{id}/borrow/{itemId}

state AusleihAnfrage {
    AusleihAnfrage : Request empfangen
    AusleihAnfrage : Parameter validieren
}

state ClientValidierung {
    ClientValidierung : Client-ID prüfen
    ClientValidierung : Ausleihberechtigung prüfen
}

state MediumValidierung {
    MediumValidierung : Medium-ID prüfen
    MediumValidierung : Verfügbarkeit prüfen
}

state AusleihDurchführung {
    AusleihDurchführung : Medium dem Client zuordnen
    AusleihDurchführung : Verfügbarkeit reduzieren
}

state Erfolgreich {
    Erfolgreich : HTTP 200 Response
    Erfolgreich : "Medium erfolgreich ausgeliehen"
}

state ClientFehler {
    ClientFehler : HTTP 404/400 Response
    ClientFehler : Client nicht gefunden oder Limit erreicht
}

state MediumFehler {
    MediumFehler : HTTP 404/400 Response
    MediumFehler : Medium nicht gefunden oder nicht verfügbar
}

AusleihAnfrage --> ClientValidierung : Parameter OK

ClientValidierung --> MediumValidierung : Client OK
ClientValidierung --> ClientFehler : Client nicht gefunden\noder Limit erreicht

MediumValidierung --> AusleihDurchführung : Medium verfügbar
MediumValidierung --> MediumFehler : Medium nicht gefunden\noder ausverkauft

AusleihDurchführung --> Erfolgreich : Transaktion erfolgreich

Erfolgreich --> [*]
ClientFehler --> [*]
MediumFehler --> [*]

@enduml
```

## Zustandsdiagramm für Rückgabevorgang

```plantuml
@startuml Library Management System - State Diagram: Return Process

!theme spacelab

title Rückgabevorgang Zustandsmaschine

[*] --> RückgabeAnfrage : POST /api/clients/{id}/return/{itemId}

state RückgabeAnfrage {
    RückgabeAnfrage : Request empfangen
    RückgabeAnfrage : Parameter validieren
}

state ClientPrüfung {
    ClientPrüfung : Client-ID validieren
    ClientPrüfung : Client existiert?
}

state AusleihPrüfung {
    AusleihPrüfung : Medium in Client.borrowedItems?
    AusleihPrüfung : Ausleihe bestätigen
}

state RückgabeDurchführung {
    RückgabeDurchführung : Medium von Client entfernen
    RückgabeDurchführung : Verfügbarkeit erhöhen
}

state RückgabeErfolgreich {
    RückgabeErfolgreich : HTTP 200 Response
    RückgabeErfolgreich : "Medium erfolgreich zurückgegeben"
}

state RückgabeFehler {
    RückgabeFehler : HTTP 404/400 Response
    RückgabeFehler : Client oder Ausleihe nicht gefunden
}

RückgabeAnfrage --> ClientPrüfung : Parameter OK

ClientPrüfung --> AusleihPrüfung : Client gefunden
ClientPrüfung --> RückgabeFehler : Client nicht gefunden

AusleihPrüfung --> RückgabeDurchführung : Ausleihe bestätigt
AusleihPrüfung --> RückgabeFehler : Medium nicht vom Client ausgeliehen

RückgabeDurchführung --> RückgabeErfolgreich : Transaktion erfolgreich

RückgabeErfolgreich --> [*]
RückgabeFehler --> [*]

@enduml
```

## Erklärung der Zustandsdiagramme

### 1. MediaItem Lebenszyklus
- **Zustände**: Basieren auf der Anzahl verfügbarer Exemplare
- **Übergänge**: Ausgelöst durch Ausleihe- und Rückgabeaktionen
- **Geschäftsregeln**: Ausleihe nur bei verfügbaren Exemplaren möglich

### 2. Client Ausleihstatus
- **Zustände**: Definiert durch Anzahl ausgeliehener Medien
- **Geschäftsregel**: Maximum 5 Medien pro Client
- **Übergänge**: Basierend auf Ausleihe- und Rückgabeaktionen

### 3. Ausleihvorgang
- **Prozess-Zustände**: Validierung → Durchführung → Ergebnis
- **Fehlerbehandlung**: Separate Fehlerzustände für verschiedene Szenarien
- **Transaktionale Sicherheit**: Atomare Operation

### 4. Rückgabevorgang
- **Validierungsschritte**: Client- und Ausleihprüfung
- **Geschäftslogik**: Nur tatsächlich ausgeliehene Medien können zurückgegeben werden
- **Ergebnisbehandlung**: Erfolg oder spezifische Fehlermeldungen

## Zustandsübergänge und Ereignisse

### Ereignisse
- **borrowItem()**: Löst Ausleihe aus
- **returnItem()**: Löst Rückgabe aus  
- **HTTP Requests**: Externe Trigger
- **Validation Results**: Interne Zustandsübergänge

### Bedingungen (Guards)
- **copiesAvailable > 0**: Verfügbarkeitsprüfung
- **borrowedItems.size() < 5**: Ausleihlimit-Prüfung
- **Client exists**: Existenzvalidierung
- **Item is borrowed by client**: Ausleihe-Beziehung