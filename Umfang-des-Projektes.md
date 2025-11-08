# Umfang des Projektes - Library Management System

## 📂 **GitHub Repository**
- **🏠 Lokale Applikation (Main Branch)**: [https://github.com/Ahmaad-dev/teng2-library-javabackend](https://github.com/Ahmaad-dev/teng2-library-javabackend)
- **☁️ AWS Deployment Branch**: [https://github.com/Ahmaad-dev/teng2-library-javabackend/tree/aws-deployment](https://github.com/Ahmaad-dev/teng2-library-javabackend/tree/aws-deployment)

## 🌐 **AWS CLOUD DEPLOYMENT - LIVE DEMO**
**🚀 Produktive Anwendung**: [http://library-management-system.eu-north-1.elasticbeanstalk.com](http://library-management-system.eu-north-1.elasticbeanstalk.com)

*✅ 24/7 verfügbar in der AWS Cloud mit Elastic Beanstalk*

---

## 👥 **Projektmitglieder**
- Ahmad Alsayad, se231310@ustp-students.at
- Lukas Kirmann, se231312@ustp-students.at
- Markus Fink, se231301@ustp-students.at
- Pavle Kapetanovic, se231332@ustp-students.at

*Studiengang: Smart Engineering, Jahrgang 2023  
Fach: Technical English II*

## ☁️ **Cloud Infrastructure**
**Hosting**: AWS Elastic Beanstalk (EU North - Stockholm)
**S3 Bucket**
**Runtime**: Java 17 + Spring Boot 3.5.5  
**Verfügbarkeit**: 99.9% Uptime mit automatischem Monitoring

## �🎯 **Hauptfunktionen**

### 📚 **Medienverwaltung**
- **Bücher, DVDs & Magazine** verwalten
- **Suchfunktionen** (Text & Regex-basiert)
- **ISBN-Suche** für Bücher
- **Bestandsübersicht** mit Verfügbarkeitsanzeige

### 👥 **Benutzerverwaltung**
- **Kunden anlegen** (Name, E-Mail, Telefon)
- **Kunden bearbeiten** und löschen
- **Kundendaten verwalten** mit Validierung
- **Eindeutige Namen-Prüfung**

### 📖 **Ausleihsystem**
- **Medien ausleihen** an Kunden
- **Medien zurückgeben**
- **Ausleihstatus verfolgen**
- **Maximale Ausleihen** pro Kunde (5 Medien)

### 🔍 **Status & Abfragen**
- **Medium-Status prüfen** (verfügbar/ausgeliehen)
- **Wer hat was ausgeliehen?**
- **Ausgeliehene Medien pro Kunde**
- **Verfügbare Exemplare anzeigen**

### 🌐 **Benutzeroberflächen**
- **REST-API** für alle Funktionen
- **Web-Frontend** mit interaktiver Oberfläche
- **Automatische Demo-Präsentationen**
- **API-Test-Interface**

## 🛠️ **Technische Umsetzung**
- **Spring Boot 3.5.5** Backend
- **Java 17+** Programmierung
- **In-Memory Database** mit Testdaten
- **RESTful API** Architektur
- **Responsive Web-UI**

## 📊 **Vordefinierte Testdaten**
- **10 Bücher** (Java, Clean Code, Harry Potter, etc.)
- **10 DVDs** (Matrix, Inception, Interstellar, etc.)
- **10 Magazine** (National Geographic, TIME, etc.)
- **10 Kunden** mit vollständigen Kontaktdaten

## ☁️ **Cloud Deployment Details**

### � **AWS Infrastructure**
- **Platform**: AWS Elastic Beanstalk
- **Region**: EU North 1 (Stockholm)
- **Runtime**: Java 17 (Amazon Corretto)
- **Storage**: Amazon S3 Bucket für Artifact Management

### �📊 **Production Features**
- ✅ **24/7 Verfügbarkeit** - Automatische Skalierung
- ✅ **Health Monitoring** - AWS CloudWatch Integration
- ✅ **Error Tracking** - Umfassendes Exception Handling
- ✅ **CORS Support** - Cross-Origin Resource Sharing aktiviert
- ✅ **S3 Integration** - Zentrale Artifact-Verwaltung
- ✅ **Load Balancing** - AWS Application Load Balancer

### 🔗 **Live URLs**
- **📱 Main App**: [http://library-management-system.eu-north-1.elasticbeanstalk.com](http://library-management-system.eu-north-1.elasticbeanstalk.com)
- **❤️ Health Check**: [/health](http://library-management-system.eu-north-1.elasticbeanstalk.com/health)
- **📚 Books API**: [/api/books](http://library-management-system.eu-north-1.elasticbeanstalk.com/api/books)
- **👥 Clients API**: [/api/clients](http://library-management-system.eu-north-1.elasticbeanstalk.com/api/clients)

## �📋 **Weitere Informationen**

- **📖 Detaillierte Anleitung**: [README.md](README.md)
- **🔗 API-Dokumentation**: [API_DOCUMENTATION.md](API_DOCUMENTATION.md)
- **☁️ AWS Deployment**: [AWS-DEPLOYMENT/](AWS-DEPLOYMENT/)
- **💻 GitHub Repository**: https://github.com/Ahmaad-dev/teng2-library-javabackend

---
*🌐 Vollständiges Bibliotheksverwaltungssystem mit modernem Web-Interface, umfassender API und produktivem AWS Cloud Deployment*