# 📚 Library Management – Requirements & Guidelines

## 🎬 DVDs
- DVDs können **hinzugefügt** werden  
- DVDs können **entfernt** werden  
- User können DVDs per **RegEx-Suche nach Titel** finden  
- Die Bibliothek verwaltet die **Anzahl der verfügbaren Exemplare**  

---

## 📖 Books
- Bücher können **hinzugefügt** werden  
- Bücher können **entfernt** werden  
- User können ein Buch per **ISBN-Suche** finden  
- User können Bücher per **RegEx-Suche nach Titel oder Autor** finden  
- Die Bibliothek verwaltet die **Anzahl der verfügbaren Exemplare**  

---

## 📰 Magazines
- Magazine können **hinzugefügt** werden  
- Magazine können **entfernt** werden  
- User können Magazine per **RegEx-Suche nach Titel** finden  
- Die Bibliothek verwaltet die **Anzahl der verfügbaren Exemplare**  

---

## 👤 Clients
- Clients können sich **registrieren**  
- Die Bibliothek verwaltet alle **registrierten Clients**  
- Clients können sich nur **abmelden**, wenn **keine Medien ausgeliehen** sind  
- Clients können DVDs, Bücher oder Magazine **ausleihen**  
- Clients dürfen **max. 5 Medien gleichzeitig** ausleihen  
- Clients können Medien **zurückgeben**  

---

## ⚠️ Error Handling
- Wenn ein Client ein Medium ausleihen möchte, das **nicht mehr verfügbar** ist (kein Exemplar mehr da oder Medium existiert nicht), wird ein **HTTP Error** zurückgegeben  

---

## 🏗️ Architecture & Implementation
- **Objektorientierung & Vererbung** für alle ausleihbaren Medien (**40%**)  
- Beim Start der Anwendung werden **einige Clients und Medien automatisch erstellt** (**5%**)  
- **Spring Boot & Dependency Injection** wo sinnvoll (**20%**)  
- **Custom Exceptions** bei fehlerhaften Operationen (**10%**)  
- **@ControllerAdvice** für zentrale HTTP-Fehlerbehandlung (**10%**)  
- Sauberer **Coding Style** (Einrückung, Namenskonventionen) (**5%**)  
- Clients haben eine **automatisch generierte ID** (z. B. UUID) (**10%**)  

---

## 💡 Hints
- **RegEx-Suche**: `Pattern` & `Matcher`  
- **Errors werfen**: `RuntimeException`  
- **Fehlerbehandlung**: `@ControllerAdvice`  
- **Initialdaten laden**: `InitializingBean` oder `@PostConstruct`  
- **ID-Generierung**: `UUID.randomUUID()`  
- Entities vs. DTOs trennen  

---

## 📌 How-To
- Teamgröße: **3–4 Studierende**  
- Fragen jederzeit via **Teams oder Mail**  
- Code-Upload: **bis 07.11. in Moodle**  
- Letzte Einheit im November:
  - Walkthrough der Anwendung & Core Features  
  - Vorbereitete HTTP-Calls zum Testen  
  - **Keine PowerPoint nötig** – Code & ggf. kleines Architekturdiagramm reicht  
