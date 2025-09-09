# teng2-library-javabackend

This is a project for lession Teng 2 - Semester 5 in University of applied Sciences 

Requirements
Requirements: Library Management

DVDs
DVDs can be added to the library
DVDs can be removed from the library
A user can RegEx search for DVDs by title
The library keeps track of how many copies of a DVD it has
Books
Books can be added to the library
Books can be removed from the library
A user can search for a book based on its ISBN
A user can RegEx search for books by title or author
The library keeps track of how many copies of a book it has
Magazines
Magazines can be added to the library
Magazines can be removed from the library
A user can RegEx search for magazines by title
The library keeps track of how many copies of a magazine it has
Clients
Clients can register to the library. The library keeps track of all registered clients.
Clients can only unregister, when the have no more things borrowed
Clients can borrow a DVD, book or magazine
Clients can borrow a maximum of 5 things at a time
Clients can return a borrowed thing
Error handling
When a client borrows a thing which is not in stock anymore (e.g. all copies are already borrowed or the book does not exist in the library at all), a HTTP error is returned
Architecture
Object-orientation and inheritance must be used for things that can be borrowed from the library (40%)
When starting the application, a small set of clients and things to borrow are already created in the application (5%)
Java & Spring Boot mechanics must be used where appropriate
Dependency Injection for common classes (20%)
Throwing appropriate, possibly custom exceptions when an operation cannot be performed (10%)
@ControllerAdvice for proper HTTP error handling (10%)
Use proper coding style, e.g. when it comes to indentations and capitalization (5%)
Clients use an automatically generated ID for identification (10%)


Hints

For RegEx search, check Pattern & Matcher
To throw errors, check RuntimeException
For error handling, check @ControllerAdvice
To initialize some data on startup, check InitializingBean or @PostConstruct
For ID generation, look at GUID & UUID
It is probably a good idea to separate Entities (objects you work with in your application) from DTOs (objects used in HTTP calls)
How-To

Work in groups of 3-4 students
For questions, feel free to reach me in Teams or via mail at any point
Upload the code in Moodle until 07.11.
In our last unit in November…
Give us a walkthrough of your application and its core features
Prepare a set of HTTP-Calls to showcase all core features of the application
A PowerPoint or similar is not required, going through the code together is sufficient. A small diagram could be useful to explain the application architecture



## First Commit

## Commit from group member Ahmad
