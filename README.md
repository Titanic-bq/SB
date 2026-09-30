# Spring Booti kasutajate CRUD-rakendus

Veebirakendus kasutajate lisamiseks, vaatamiseks, muutmiseks ja kustutamiseks. Projekt tutvustab Spring MVC-d, Spring Data JPA-d, Hibernate'i, Thymeleafi ja Bootstrapi.

## Tehnoloogiad

- Java 17 ja Spring Boot
- Spring MVC, Spring Data JPA ja Hibernate
- MySQL
- Thymeleaf ja Bootstrap 5
- JUnit 5, Spring Boot Test ja H2 testandmebaas

## Eeldused

- JDK 17 või uuem
- Maven 3.6.3 või uuem
- MySQL-server

Loo MySQL-is andmebaas:

```sql
CREATE DATABASE user_management;
```

Rakendus kasutab vaikimisi ühendust `jdbc:mysql://localhost:3306/user_management`, kasutajanime `root` ja tühja parooli. Muude ühendusandmete määramiseks sea keskkonnamuutujad `DB_URL`, `DB_USERNAME` ja `DB_PASSWORD`.

## Käivitamine

Projekti juurkaustas käivita:

```bash
mvn spring-boot:run
```

Seejärel ava [http://localhost:8080](http://localhost:8080).

## Testid

```bash
mvn test
```

Testid kasutavad H2 mälupõhist andmebaasi ega vaja MySQL-serverit.
