# Dokumentáció - Projektötlet

## Funkcionális követelmények 
* Vendégként a főoldal, regisztációs és bejelentkező oldal elérése -> Publikus tartalmak elérése
* Regisztráció a weboldalra -> Regisztráció 
* Bejelentkezés a weboldalra -> Belépés 
* Felhasználóként egyenleg feltöltése a fiókra -> Egyenleg feltöltése
* Felhasználóként böngészés, keresés a filmek között -> Böngészés, keresés 
* Felhasználóként film lapjára lépés, film adatainak megtekintése -> Film adatlapjának megtekintése 
* Felhasználóként film megvásárlása-> Vásárlás 
* Felhasználóként achivmentek feloldása -> Mérföldkövek
* Felhasználóként prémium jogosultság vásárlása -> Prémiummá válás
* Prémium felhasználóként összes film birtoklása és prémium oldal elérése -> Prémium funkciók
* Külön táblázatokba nyilvántartani a felhasználókat, filmeket, felhasználók és filmek közötti kapcsolatokat,főszereplőket, filmek és főszereplők közötti kapcsolatokat, mérföldköveket, felhasználók és mérföldkövek közötti kapcsolatokat, szerepköröket, felhasználók és szerepkörök közötti kapcsolatokat

## Nem funkcionális követelmények 
* Felhasználóbarát, könnyen átlátható, grafikus felület 
* Gyors működés -Biztonságos működés: jelszavak, banki adatok tárolása

## Szakterületi fogalomjegyzék: 
* Videólejátszó oldal: olyan weboldal, amin előre beágyazott videók játszhatóak le (pl. YouTube -ról)

## Szerepkörök 
* vendég: a főoldalhoz, bejelentkező és regisztráló felülethez fér csak hozzá 
* felhasználó: rendelkezik felhasználói fiókkal, el tudja érni az összes felhasználói funkciót 
* prémium: rendelkezik olyan felhasználói fiókkal, amihez prémium jogosultság van rendelve, így rendelkezik az összes prémium funkcióval

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
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/uml.png)

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
* `PUT /users/{id}` Egy felhasználó módosítása (id alapján)
* `PUT /users/upgrade/{id}` Egy felhasználó prémiummá fejlesztése (id alapján)
* `DELETE /users/{id}` Egy felhasználó törlése (id alapján)
* `DELETE /users/deleteByName/{name}` Egy felhasználó törlése (név alapján)

## Egy funkció szekvencia diagramja
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/seq.PNG)


## Szerepkörök

| Szereplő |                            |
|----------|----------------------------|
| Vendég | Megtekintheti a főoldalt, bejelentkezhet, illetve regisztrálhat. |
| Felhasználó | Szerkesztheti a profilját, egyenleget tölthet fel, megtekintheti az összes film adatlapját, vásárolhat filmeket, mérföldköveket oldhat fel, prémium jogosultságot vásárolhat. |
| Prémium | Birtokolja az összes filmet, megtekintheti a prémium oldalt. |

## Használati eset diagram
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/usecase.png)

# Dokumentáció - Front-end
## Fejlesztői környezet bemutatása
* Angular CLI: 9.1.3
* Node: 12.16.1
* OS: Windows 10 (x64)
* Visual Studio Code
* Google Chrome

## Használt technológiák
Használt modulok / függőségek:
* http
* forms
* browser animations
* materials: form-field, input, snack-bar, dialog, checkbox, table, paginator, sort, button, icon
* youtube-player

## Alkalmazott könyvtárstruktúra bemutatása:
* `src/app/` A front-end forrása
* `balance-topup` Egyenleg feltöltő komponens
* `helpers` HttpInterceptor megvalósítás
* `home` Főoldal komponens
* `login` Bejelentkező komponens
* `models` Modelek / osztályok
	* `achivement` Mérföldkő
	* `base-class` Alaposztály
	* `movie-member` Főszereplő
	* `movie` Film
	* `user` Felhasználó
	* `warning-options` warning-dialog -hoz használt címkék
* `movie-page` Film adatlap komponens
* `movies` Összes (vagy megvásárolt) filmek komponens
* `premium-board` Prémium oldal komponens
* `profile` Profil komponens
* `Register` Regisztrációs komponens
* `services` Szolgáltatások: 
	* `auth-service` bejelentkezés / regisztrációs szolgáltatás
	* `movie-service` Filmek szolgáltatásai
	* `token-storage-service` Session storage kezelés
	* `user-service` Felhasználó szolgáltatásai
* `warning-dialog` Felugró popup / dialog komponens

## Felületi tervek
Főoldal:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/home.PNG)

Regisztrációs oldal:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/register.PNG)

Bejelentkező oldal:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/login.PNG)

Összes film oldal:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/movies.PNG)

Egy olyan film adatlapja, ami még nincsen megvásárolva a felhasználó által:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/movie page.PNG)

Egy olyan film adatlapja, amit már megvásárolt a felhasználó:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/owned movie page.PNG)

Megvásárol filmek oldal:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/owned movies.PNG)

Profil oldal:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/profile.PNG)

Prémium oldal:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/premium page.PNG)

Egyenleg feltöltő popup dialog:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/tervek/topup.PNG)

# Felhasználói dokumentáció
## Feladat rövid ismertetése:
Az alkalmazás célja egy olyan webes filmnéző oldal, melyre lehet regisztrálni és belentkezni. Bejelentkezés után a felhasználó böngészhet a filmek között, és kedvére vásárolhat filmeket. Egy film megvásárlása előtt csak a film általános információit láthatja, vásárlás után pedig már a film előzetesét is. A vásárlásokkal mérföldköveket oldhat fel a felhasználó. Lehetőség van a fiókra egyenleget feltölteni, ebből lehet filmeket, illetve prémium jogosultságot vásárolni.

## Publikus tartalmak / menüpontok és funkcióik
A publikus tartalmak megtekintéséhez nincs szükség felhasználói fiókra. Publikus tartalmak a következők:

* Főoldal: az oldal köszönti a látogatót
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/home.PNG)

* Regisztrációs oldal: lehetőséget ad a felhasználónak regisztrációra.
	* Követelmények:
		* Felhasználónév legalább 3 karakter, és még nem foglalt
		* Email valós email cím, és még nem hoztak vele létre felhasználót eddig
		* Jelszó legalább 6 karakter hosszú
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/register.PNG)

* Bejelentkező oldal: lehetőséget ad a felasználónak a bejelentkezésre
	*Követelmények:
		* Felhasználónév és jelszó létező, már regisztrált felhasználóhoz tartozzon
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/login.PNG)

## Felhasználói tartalmak / menüpontok és funkcióik
Sikeres bejelentkezés után az alábbi oldalak és funkciók elérhetők:
* Összes film oldal: listázza az összes, adatbázisban szereplő filmet és adataikat. A találatok szürhetők egy keresési mezővel.
	* A filmek mellet lévő Megtekintés gomb menyomásával a kiválasztott film adatlapjára navigál
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/movies.PNG)

* Kiválasztott film adatlapja:
	* Amennyiben a felhasználó még nem vásárolta meg a filmet, a film összes adatát megtekintheti, és megvásárolhatja
	![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/movie page.PNG)
	* Amennyiben a felhasználó már megvásárolta a filmet, a film összes adatát megtekintheti, és a filmhez tartozó előzetest is
	![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/owned movie page.PNG)

* Megvásárolt filmek oldal: listázza az összes, a felhasználó által megvásárolt filmeket. A filmeket melletti Megtekintés gomb menyomásával a kiválasztott film adatlapjára navigál. Megjegyzés: amennyiben a felhasználó egy filmmet sem vásárolt még, akkor ez az oldal nem elérhető számára, és a menüsávban sem látható.
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/owned movies.PNG)

* Profil: a felhasználó megtekintheti a felhasználónevét, email címét, az általa megvásárolt filmeket, az elért mérföldköveket, a felhasználó jogosultságát, és amennyiben a felhasználó alap, Felhasználó jogosultsággal rendelkezik (alapból regisztráció után minden felhasználó), akkor lehetősége van prémium jogosultság megvásárlására, ami után elérhetővé válik az összes film és a prémium oldal is.
Alap felhasználó jogosultság esetén a profil:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/profile.PNG)

Prémium felhasználó jogosultság esetén a profil:
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/profile2.PNG)

* Egyenleg feltöltés: a felhasználónak lehetősége van megadni bankkártya számot, CVC -t, lejárati dátumot, majd a feltölteni kívánt összeget. A feltöltés gomb megnyomásával jóváíródik a felhasználó számlájára az összeg. Feltöltés előtt lehetősége van az Adatok mentése funkciót bepipálni, amivel a következő egyenleg feltöltéskor a bankkártya száma, CVC -je és lejárati dátuma automatikusan kitöltődik a mostani feltöltéskor megadott adatokkal.
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/topup.PNG)

## Prémium felhasználói tartalmak / menüpontok és funkcióik
* Prémium oldal: ezt az oldalt csak a prémium jogosultsággal rendelkező felhasználók érhetik el a menüpontokon keresztül. Tartalommal nem rendelkezik, csak annyival, hogy a Prémium funkciók hamarosan éreznek!
![ScreenShot](https://github.com/Stalidald/Cflix/blob/negyedik_merfoldko/pictures/feluletek/premium board.PNG)
