# Projekt-HowTo: Spring Boot Library Management System


### VS Code öffnen
1. Navigation: `c:\Users\ahmed\OneDrive\Desktop\WS_2025-2026\TENG II\Abgabe\teng2-library-javabackend`

### Terminal 
1. Ins Projektverzeichnis wechseln:
   ```powershell
   cd "c:\Users\ahmed\OneDrive\Desktop\WS_2025-2026\TENG II\Abgabe\teng2-library-javabackend"
   ```

### Spring Boot starten
```powershell
.\mvnw.cmd spring-boot:run
```

**✅ Erfolgreich gestartet, wenn Sie diese Zeile sehen:**
```
Started DemoApplication in X.XXX seconds (process running; press CTRL+C to stop)
```

---

## 🌐 API-Endpunkte testen

### Basis-URLs (im Browser öffnen)
| Endpunkt | URL | Beschreibung |
|----------|-----|--------------|
| Alle Bücher | `http://localhost:8080/api/books` | Zeigt alle verfügbaren Bücher |
| Alle Kunden | `http://localhost:8080/api/clients` | Zeigt alle registrierten Kunden |
| Alle DVDs | `http://localhost:8080/api/dvds` | Zeigt alle verfügbaren DVDs |
| Alle Magazine | `http://localhost:8080/api/magazines` | Zeigt alle verfügbaren Magazine |

### Such-Funktionen
| Funktion | URL | Beispiel |
|----------|-----|----------|
| Bücher suchen | `http://localhost:8080/api/books/search?query=SUCHTERM` | `?query=Java` |
| DVDs suchen | `http://localhost:8080/api/dvds/search?query=SUCHTERM` | `?query=Matrix` |
| Magazine suchen | `http://localhost:8080/api/magazines/search?query=SUCHTERM` | `?query=National` |
| Buch per ISBN | `http://localhost:8080/api/books/isbn/ISBN` | `/isbn/978-3608939811` |

---

## 📝 Erweiterte Tests mit VS Code

### HTTP-Dateien nutzen
1. Öffnen Sie `src/main/resources/api-tests.http`
2. Klicken Sie auf **"Send Request"** über jeder HTTP-Anfrage
3. Ergebnisse werden direkt in VS Code angezeigt

### Ausleihe-System testen

#### 1. UUIDs sammeln
```http
GET http://localhost:8080/api/clients
GET http://localhost:8080/api/books
```

#### 2. Medium ausleihen
```http
POST http://localhost:8080/api/clients/{CLIENT-UUID}/borrow/{BOOK-UUID}
```

#### 3. Medium zurückgeben
```http
POST http://localhost:8080/api/clients/{CLIENT-UUID}/return/{BOOK-UUID}
```

---

## 🔧 Problembehebung

### Problem: "Port 8080 already in use"

**1. Prüfen, was auf Port 8080 läuft:**
```powershell
netstat -ano | findstr :8080
```

**2. Prozess beenden:**
```powershell
taskkill /F /PID [PROZESS-ID]
```

**3. Erneut starten:**
```powershell
.\mvnw.cmd spring-boot:run
```

### Problem: Maven-Fehler

**1. Projekt neu kompilieren:**
```powershell
.\mvnw.cmd clean compile
```

**2. Erneut starten:**
```powershell
.\mvnw.cmd spring-boot:run
```

### Problem: Java-Version prüfen
```powershell
java -version
```
**Benötigt:** Java 17 oder höher

---

## 📊 Vordefinierte Testdaten

### Bücher (Beispiele)
- **Der Herr der Ringe** (J.R.R. Tolkien) - ISBN: 978-3608939811
- **Harry Potter** (J.K. Rowling) - ISBN: 978-3551551672
- **Clean Code** (Robert C. Martin) - ISBN: 978-0132350884

### DVDs (Beispiele)
- Matrix, Inception, Interstellar, The Dark Knight

### Magazine (Beispiele)
- National Geographic, TIME, Scientific American, Der Spiegel

### Kunden (Beispiele)
- Anna Meier, Thomas Huber, Lena Schmidt, Max Mustermann

---

## ⚡ Schnelle Überprüfung

### Checkliste nach dem Start:
- [ ] Terminal zeigt "Started DemoApplication"
- [ ] `http://localhost:8080/api/books` zeigt JSON-Daten
- [ ] Keine roten Fehlermeldungen im Terminal
- [ ] Alle GET-Endpunkte liefern Daten zurück

---

## 🛑 Anwendung beenden

**Im Terminal:**
```
Ctrl + C
```

**Oder PowerShell-Befehl:**
```powershell
taskkill /F /PID [PROZESS-ID]
```

---

## 📂 Projektstruktur (Überblick)

```
teng2-library-javabackend/
├── src/main/java/
│   ├── com/example/demo/DemoApplication.java    # Hauptklasse
│   ├── controller/LibraryController.java        # REST-Controller
│   ├── service/LibraryService.java             # Business Logic
│   ├── repository/InMemoryDatabase.java        # Datenbank
│   ├── model/                                  # Datenmodelle
│   └── exception/                              # Exception-Handling
├── src/main/resources/
│   ├── api-tests.http                          # HTTP-Tests
│   └── application.properties                  # Konfiguration
├── pom.xml                                     # Maven-Konfiguration
└── API_DOCUMENTATION.md                        # API-Dokumentation
```

---

## 🎯 Erfolgreiche Testsequenz

1. ✅ **Anwendung starten** → `.\mvnw.cmd spring-boot:run`
2. ✅ **Bücher abrufen** → `http://localhost:8080/api/books`
3. ✅ **Kunden abrufen** → `http://localhost:8080/api/clients`
4. ✅ **Suche testen** → `http://localhost:8080/api/books/search?query=Java`
5. ✅ **UUIDs kopieren** → Für Ausleihe-Tests
6. ✅ **Ausleihe testen** → Mit HTTP-Datei oder Postman

**Bei erfolgreicher Durchführung ist Ihr System voll funktionsfähig! 🎉**