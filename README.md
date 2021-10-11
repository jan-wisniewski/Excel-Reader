# O aplikacji

Aplikacja pozwala na wczytywanie plików w formacie .xlsx oraz .xls. Następnie parsuje i waliduje je, a na końcu wyświetla w tabeli. Program na wstępie weryfikuje strukturę pliku Excel, który musi posiadać odpowiednie nagłówki kolumn tj.

> Nr polisy | Typ polisy | Suma ubezpieczenia | Imię ubezpieczonego | Nazwisko ubezpieczonego | Przedmiot ubezpieczonego


# Instalacja

W celu utworzenia wykonywalnego pliku .jar, należy w głównym katalogu aplikacji, będąc na gałęzi master, wywołać polecenie:

```bash
mvn clean install
```
Po stworzeniu pliku excel.jar w katalogu /target, możemy uruchomić go za pomocą polecenia:

```bash
java -jar excel.jar
```

W celu podejrzenia działającej aplikacji należy w przeglądarce udać się pod adres:
> http://localhost:8081/

Program wymaga uruchomionej instancji bazy danych MySQL na porcie 3306. Dane dostępowe do bazy danych zostały zapisane w pliku konfiguracyjnym:

> application.properties

# Właściwości aplikacji
* Zabezpieczenia przed wczytaniem pliku o złej strukturze
* Walidatory wczytywanych rekordów, które poinformują o niepoprawnie wypełnionych danych
* Filtrowanie po wczytanych danych
* Dynamiczne wczytywanie oraz usuwanie danych z tabeli

# Technologie

* Spring Boot
* Angular 8+
* Java 17
* Hibernate 
* Baza danych MySQL

# Film pokazowy

https://youtu.be/Qfx9i_e_i6s

# Screenshots

## 1. Ekran startowy
![1](https://i.ibb.co/71R9jjK/app2.png)

## 2. Komunikat o błędzie, jeżeli struktura pliku Excel jest niepoprawna
![2](https://i.ibb.co/tZ9HGnc/app3.png)

## 3. Aplikacja po załadowaniu i zwalidowaniu danych. Przy każdym wierszu otrzymujemy komunikat o rodzaju błędu
![3](https://i.ibb.co/rmP11G8/app1.png)

## 4. Filtrowanie danych
![4](https://i.ibb.co/LNbb8F8/app4.png)

# Autor

Jan Wiśniewski
