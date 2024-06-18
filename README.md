# BookshopPJATK
Project for 4th semester advanced Java studies at PJATK
- [X] Stwórz trzy moduły: 
  - [X] Moduł „api-gen” generujący API z specyfikacji OpenApi przy pomocy pluginu OpenApiTools. Następne moduły powinny 
  podpiąć api-gen poprzez pom.xml (skorzystanie z Swagger Editor -5 pkt)
  - [X] Moduł „bookshop”, 
    - [X] który umożliwia wyświetlenie,
    - [X] filtrowanie 
    - [ ] kupienie książki przez zalogowanego użytkownika. 
    Uprawnienia do dodania/edycji/usunięcia książki powinien mieć administrator. (brak uprawnień -5 pkt) 
    - [X] Książka powinna zawierać: 
      - [X] Autora, 
        - [X] Autor powinien być osobną klasą, encją bazodanową.
      - [X] Gatunek, 
      - [X] Nazwę (chyba brakuje w oryginalnych wymaganiach)
      - [X] Cenę, 
      - [X] Ilość stron, 
      - [X] licznik odwiedzin, 
      - [X] oraz informację czy jest dostępna (ile sztuk).
      - [X] Książka, po jej wyszukaniu powinna inkrementować ilość odwiedzin. 
    - [X] Zaimplementuj metodę przy użyciu biblioteki feign, aby komunikować się z trzecim modułem.
    (oraz endpoint dla administratora /order-report który ją wywoła)  
  - [X] Moduł „book-order” 
    - [X] który przyjmuje listę obiektów {id książki, nazwa, ilość odwiedzin}. 
    - [X] Każde 10 odwiedzin danej książki to jedna książka którą należy zamówić do magazynu.
    - [X] W bazie danych trzymaj jedynie encję {id książki, ilość do zamówienia}. 
    - [X] Dodaj endpoint „/print” który stworzy plik .pdf z zamówieniem. (nie zwracanie pliku, a jedynie informacji -5 pkt) 
- [X] Zadbaj o to by w każdym module była walidacja oraz obsługa błędów wraz z aspektowym ich przechwytywaniem.
(Brak  -10 pkt) 
- [X] Przetestuj aplikacje z użyciem testów jednostkowych.
(Brak -5pkt) 

 