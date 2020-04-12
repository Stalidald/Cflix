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
