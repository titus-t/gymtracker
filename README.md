# GymTracker

GymTracker on Spring Bootilla toteutettu verkkosovellus omien kuntosaliharjoitusten seurantaan ja hallintaan. Sovellus on rakennettu kurssin harjoitustyönä ja se hyödyntää mm. käyttäjäntunnistusta ja REST-rajapintaa.

## Ominaisuudet

* **Vierailijat:** Kuka tahansa voi selata ja katsella järjestelmässä olevia mallitreenejä kategorioittain (Yläkroppa, Jalat, Cardio).
* **Kirjautuneet käyttäjät:** Voivat lisätä, muokata ja poistaa omia henkilökohtaisia treenejään. Omat treenit näkyvät vain käyttäjälle itselleen.
* **Ylläpitäjä (Admin):** Pystyy hallinnoimaan (lisäämään, muokkaamaan, poistamaan) kaikkien nähtävillä olevia mallitreenejä.
* **REST API:** Sovellus tarjoaa JSON-muotoisen REST-rajapinnan treenidatan noutamiseen.

## Teknologiat

* **Backend:** Java 17, Spring Boot (Web, Security, Data JPA, Validation)
* **Frontend:** HTML5, Thymeleaf, Bootstrap 5
* **Tietokanta:** MySQL & H2 (Kehityksessä)
* **Tietoturva:** Spring Security (Salasanojen BCrypt-salaus, roolipohjainen käyttöoikeuksien hallinta)

## Tietokanta ja tietoturva

Sovellus käyttää **MySQL**-relaatiotietokantaa (`titust`), jossa on kaksi toisiinsa kytkettyä taulua (`app_user` ja `workout` - @ManyToOne -suhde). 

## REST-rajapinta

Sovelluksen dataan pääsee käsiksi myös REST-rajapinnan kautta:

Sovelluksen linkki pilvessä on = https://softala.haaga-helia.fi:8091
