# Dokumentáció - Projektötlet

## Funkcionális követelmények 
* Regisztráció a weboldalra -> Regisztráció 
* Bejelentkezés a weboldalra -> Belépés 
* Felhasználóként egyenleg feltöltése a fiókra -> Egyenleg feltöltése 
* Felhasználóként más felhasználó csatolása a fiókhoz -> Családi kör létrehozása 
* Felhasználóként a családi körben lévő más felhasználók korhatárának beállítása, egyenleg feltöltése az ő számukra -> Családi kör tevékenységek 
* Felhasználóként böngészés, keresés a filmek, kategóriák között -> Böngészés, keresés 
* Felhasználóként film előzetesének lapjára lépés, film adatainak megtekintése -> Film adatlapjának megtekintése 
* Felhasználóként film előzetesének megvásárlása egyszeri megtekintésre, vagy végleges feloldása -> Vásárlás 
* Felhasználóként a megnézett film előzetesének értékelése, kommentelése -> Véleményezés 
* Felhasználóként achivmentek feloldása, jutalmak, kedvezmények szerzése -> Jutalmazó rendszer 
* Felhasználóként jelezni a kívánságlistában, hogy milyen filmeket szeretné, ha feltennének az oldalra -> Kívánságlista 
* Adminként bármely film előzetesének megtekintése korlátlanul -> Korlátlan film előzetes nézés 
* Adminként bármely film előzetesének értékelésinek (kommenteinek) moderálása -> Moderálás 
* Külön táblázatokba nyilvántartani a felhasználókat, kapcsolataikat, felhasználókhoz tartozó információkat, filmeket

## Nem funkcionális követelmények 
* Felhasználóbarát, könnyen átlátható, grafikus felület 
* Gyors működés -Biztonságos működés: jelszavak, banki adatok tárolása

## Szakterületi fogalomjegyzék: 
* Videólejátszó oldal: olyan weboldal, amin előre beágyazott videók játszhatóak le (pl. YouTube -ról)

## Szerepkörök 
* vendég: a bejelentkező/regisztráló felülethez fér csak hozzá 
* felhasználó: rendelkezik felhasználói fiókkal, el tudja érni az összes felhasználói funkciót 
* szülő: olyan felhasználó, aki benne van egy családi körben, mint szülő, ezáltal tudja korlátozni a családi körben lévő gyerek felhasználókat, illetve egyenleget tölteni nekik 
* gyerek: olyan felhasználó, aki benne van egy családi körben, mint gyerek, ezáltal a szülei tudják korlátozni a fiókját, illetve tudnak neki egyenleget tölteni 
* admin: rendelkezik olyan felhasználói fiókkal, amihez admin jogosultság van rendelve, így rendelkezik az összes admin funkcióval

# Dokumentáció - Backend megvalósítása

## Alkalmazott technológiák
Java SE 8, Java Spring keretrendszer. A back-end alkalmazás egy Rest API, amellyel http kérésekkel lehet kommunikálni. Az alkalmazás a localhost:8080 címen érhető el.

### Használt függőségek
Az alábbi Spring Boot függőségeket használjuk: Jpa, Web, Spring security, JWT, Devtools, Lombok, Junit jupiter, H2

### Build
Az alkalmazás buildhez Mavent használunk ezért a fordításához a Maven telepítése szükséges [Maven link](https://maven.apache.org/download.cgi).
A szükséges függőségeket a Maven tölti le a felhasználó számára. Windows operációs rendszer esetén a gyökérkönyvtárban található build.ps1 [script](build.ps1), 
Linux és MAC operációs rendszer esetén pedig a build.sh [script](build.sh).
A futtatható alkalmazás a target mappában található (CataflixBackEnd-0.0.1-SNAPSHOT.jar).

### Adatbázis
H2 adatbázis kezelőt használ az alkalmazás.
Az adatbázis használatához szükséges konfigurációkat az [application.properties](src/main/resources/application.properties) tartalmazza.

### Egyéb beállítások
A JSON Web Token (JWT) generálásához használt titkos kulcsot és lejárati időt szintén az [application.properties](src/main/resources/application.properties) tartalmazza.

### Könyvtárstruktúra
* `CataflixBackEndApplication` A program indulópontja, main metódus
* `controllers` Végpontok (kontrollerek)
	* `AchivementController` Achivementek végpontjai
	* `AuthController` Authentikáció végpontjai
	* `BaseController` Alap kontroller osztály származtatáshoz
	* `MovieController` Filmek végpontjai
	* `MovieMemberController` Stábtagok végpontjai
	* `UserController` Felhasználók végpontjai
* `entities` Adatbázis entitások (táblák)
	* `AchivementEntity` Achivementek táblája
	* `BaseEntity` Alap osztály táblája származtatáshoz
	* `MovieEntity` Filmek táblája
	* `MovieMemberEntity` Stábtagok táblája
	* `UserEntity` Felhasználók táblája
* `models` Segéd modellek
	* `ERole` Jogosultságok enumja
	* `Role` Jogkörök táblája
* `payload` Kommunikációs segédosztályok
	* `request` Kérelmek
		* `LoginRequest` Bejelentkező kérelem osztály
		* `SignupRequest` Regisztrációs kérelem osztály
	* `response` Válaszok
		* `JwtResponse` Hitelesítő válasz osztály
		* `MessageResponse` Üzenethordó válasz osztály
* `repositories` Adatbázissal való kommunikáció, adatok mentése, tárolása
	* `AchivementRepository` Achivementek tárolása, elérése
	* `MovieMembersRepository` Stábtagok tárolása, elérése
	* `MovieRepository` Filmek tárolása, elérése
	* `RoleRepository` Jogkörök tárolása, elérése
	* `UserRepository` Felhasználók tárolása, elérése
* `security` Biztonságért felelős osztályok
	* `jwt` JSON Web Tokent használó osztályok
		* `AuthEntryPointJwt` Nem hitelesített kérelmek kezelése
		* `AuthTokenFilter` Kérelmek szűrése
		* `JwtUtils` JSON Web Tokenek kezelése
	* `services` Biztonságért felelős szolgáltatások
		* `WebSecurityConfig` Biztonsági beállítások konfigurációja
* `services` Kérés és válasz közötti adat feldolgozása, adatbázissal való kommunikáció
	* `exceptions` Egyedi kivételek
		* `EmailNotFoundException` Emailcím sikertelen megkeresése
		* `EntityCannotBeChangedException` Entitás sikertelen megváltoztatása
		* `EntityInactiveException` Entitás inaktív
		* `NameNotFoundException` Név sikertelen megkeresése
	* `validator` Hitelesítő servicek
		* `AbstractDataValidator` Hitelesítő
	* `AchivementService` Achivementek lekérdezése, mentése
	* `BaseService` Alap service osztály származtatáshoz
	* `MovieMemberService` Stábtagok lekérdezése, mentése
	* `MovieService` Filmek lekérdezése, mentése
	* `UserService` Felhasználók lekérdezése, mentése

## Adatbázis-terv
### Táblák kapcsolati UML diagramja
![ScreenShot](https://github.com/Stalidald/Cflix/tree/develop/CataflixBackEnd/uml.png "UML diagram")

## Végpont tervek és leírások
* `POST /api/auth/signin` Bejelentkezés
* `POST /api/auth/signup` Regisztráció
* `GET /achivement` Összes achivement listázása
* `GET /achivement/{id}` Egy achivement lapja (id alapján)
* `GET /achivement/name/{name}` Egy achivement lapja (név alapján)
* `DELETE /achivement/{id}` Egy achivement törlése (id alapján)
* `DELETE /achivement/{name}` Egy achivement törlése (név alapján)
* `GET /movies` Összes film listázása
* `GET /movies/{id}` Egy film lapja
* `GET /movies/rating/{rate}` Megadott értékelésű filmek listázása
* `GET /movies/ratingIsHighter/{rate}` Megadott érték fölötti értékelésű filmek listázása
* `GET /movies/ratingIsLower/{rate}` Megadott érték alatti értékelésű filmek listázása
* `POST /movies` Film hozzáadása
* `DELETE /movies/{id}` Egy film törlése (id alapján)
* `DELETE /movies/deleteByTitle/{title}` Egy film törlése (cím alapján)
* `GET /moviemember` Összes stábtag listázása
* `GET /moviemember/{id}` Egy stábtag lapja (id alapján)
* `GET /moviemember/name/{name}` Egy stábtag lapja (név alapján)
* `DELETE /moviemember/{id}` Egy stábtag törlése (id alapján)
* `DELETE /moviemember/deleteByName/{name}` Egy stábtag törlése (név alapján)
* `GET /users` Összes felhasználó listázása
* `GET /users/{id}` Egy felhasználó lapja (id alapján)
* `GET /users/email/{email}` Egy felhasználó lapja (email alapján)
* `GET /users/name/{name}` Egy felhasználó lapja (név alapján)
* `DELETE /users/{id}` Egy felhasználó törlése (id alapján)
* `DELETE /users/deleteByName/{name}` Egy felhasználó törlése (név alapján)

## Egy funkció szekvencia diagramja
![ScreenShot](https://github.com/Stalidald/Cflix/tree/develop/CataflixBackEnd/seq.png "Szekvencia diagram")