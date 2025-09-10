# 📚 Ausleihstatus prüfen - Vollständige Anleitung

## 🎯 Neue Features für Ausleihstatus

Jetzt kannst du ganz einfach prüfen, ob ein Medium ausgeliehen ist! Ich habe 4 neue API-Endpunkte hinzugefügt:

## 🔍 Verfügbare Endpunkte

### 1. **Ausgeliehene Medien eines Kunden anzeigen**
```
GET /api/clients/{clientId}/borrowed-items
```
**Was es macht:** Zeigt alle Medien an, die ein bestimmter Kunde ausgeliehen hat.

**Beispiel:**
```bash
curl http://localhost:8080/api/clients/abc123-def456/borrowed-items
```

---

### 2. **Status eines Mediums prüfen**
```
GET /api/items/{itemId}/status
```
**Was es macht:** Zeigt detaillierte Informationen über verfügbare und ausgeliehene Exemplare.

**Antwort-Beispiel:**
```
"Verfügbar: 2 von 3 Exemplaren (ausgeliehen: 1)"
```

**Beispiel:**
```bash
curl http://localhost:8080/api/items/book123-xyz789/status
```

---

### 3. **Verfügbare Exemplare prüfen**
```
GET /api/items/{itemId}/availability
```
**Was es macht:** Gibt nur die Anzahl verfügbarer Exemplare zurück (Zahl).

**Antwort-Beispiel:**
```
2
```

**Beispiel:**
```bash
curl http://localhost:8080/api/items/book123-xyz789/availability
```

---

### 4. **Wer hat ein Medium ausgeliehen?**
```
GET /api/items/{itemId}/borrower
```
**Was es macht:** Zeigt alle Kunden an, die dieses Medium ausgeliehen haben.

**Beispiel:**
```bash
curl http://localhost:8080/api/items/book123-xyz789/borrower
```

---

## 🛠️ Praktisches Beispiel: Schritt-für-Schritt

### **Schritt 1: UUIDs abrufen**

Zuerst holst du dir die echten UUIDs:

```bash
# Alle Kunden anzeigen
curl http://localhost:8080/api/clients

# Alle Bücher anzeigen  
curl http://localhost:8080/api/books
```

### **Schritt 2: Status vor der Ausleihe prüfen**

```bash
# Prüfe Status (ersetze durch echte UUID)
curl http://localhost:8080/api/items/DEINE-BUCH-UUID/status
```

**Erwartete Antwort:** `"Verfügbar: 3 von 3 Exemplaren (ausgeliehen: 0)"`

### **Schritt 3: Buch ausleihen**

```bash
# Leihe Buch aus (ersetze durch echte UUIDs)
curl -X POST http://localhost:8080/api/clients/DEINE-CLIENT-UUID/borrow/DEINE-BUCH-UUID
```

### **Schritt 4: Status nach der Ausleihe prüfen**

```bash
# Prüfe Status erneut
curl http://localhost:8080/api/items/DEINE-BUCH-UUID/status
```

**Erwartete Antwort:** `"Verfügbar: 2 von 3 Exemplaren (ausgeliehen: 1)"`

### **Schritt 5: Wer hat es ausgeliehen?**

```bash
# Zeige Ausleiher an
curl http://localhost:8080/api/items/DEINE-BUCH-UUID/borrower
```

**Erwartete Antwort:** Liste mit Kunden-Details

### **Schritt 6: Ausgeliehene Medien anzeigen**

```bash
# Zeige alle ausgeliehenen Medien des Kunden
curl http://localhost:8080/api/clients/DEINE-CLIENT-UUID/borrowed-items
```

---

## 🌐 Browser-Tests

Du kannst auch einfach den Browser verwenden:

1. **Alle Bücher:** `http://localhost:8080/api/books`
2. **Alle Kunden:** `http://localhost:8080/api/clients`
3. **Status prüfen:** `http://localhost:8080/api/items/BUCH-UUID/status`
4. **Verfügbarkeit:** `http://localhost:8080/api/items/BUCH-UUID/availability`

---

## 📄 HTTP-Datei verwenden

In VS Code kannst du die Datei `src/main/resources/api-tests.http` verwenden:

1. Öffne die Datei
2. Ersetze `{{clientId}}` und `{{itemId}}` durch echte UUIDs
3. Klicke auf "Send Request" über jeder HTTP-Anfrage

---

## 🎉 Zusammenfassung

**Du kannst jetzt:**

✅ **Prüfen ob ein Medium ausgeliehen ist** (`/status`)
✅ **Sehen wer ein Medium ausgeliehen hat** (`/borrower`) 
✅ **Alle Medien eines Kunden anzeigen** (`/borrowed-items`)
✅ **Verfügbare Exemplare zählen** (`/availability`)

**Alle Features funktionieren perfekt mit der bestehenden Ausleihe-Logik!** 🚀
