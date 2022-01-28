## Opis stworzonej bazy danych

W ramach projektu **PAP + BD1** stworzona została baza danych służąca do obsługi portalu do sprzedaży oraz wymiany przedmiotów.  

W bazie danych znajduje się 12 powiązanych ze sobą tabel, trzymających dane użytkowników, wystawionych ofert, odbytych transakcji, zamieszczonych na portalu komentarzy, jak i ogólnego działania zaimplementowanego rozwiązania.

Każda z tabel posiada unikalny klucz główny *ID* oraz inne atrybuty odpowiadające cechom poszczególnym omawianym podmiotom.  
Relacje i zależności pomiędzy poszczególnymi tabelami, jak i szczegółowe informacje na temat kolumn w tabelach zobaczyć można na modelu relacyjnym zamieszczonym w pliku *relational_model.png*.  

Oprócz tabel, w bazie danych dostępne są funkcje, procedury z użyciem kursorów oraz triggery, czyli wszystkie wymagane funkcjonalności projektowe.
Obiekty te dostępne są do wglądu w plikach *functions.sql*, *procedure.sql* oraz *triggers.sql*. Zaimplementowane obiekty umożliwiają wygodniejsze korzystanie z bazy danych, a także udostępniają narzędzia do łatwiejszego korzystania z jej zasobów.

Ostatnimi dołączonymi plikami są *project_schema.sql* oraz *project_instert.sql*. Pierwszy z nich umożliwia stworzenie szkieletu naszej bazy danych, za to drugi służy uzupełnieniu tabel odpowiednimi wartościami. 

Wszystkie dostarczone pliki umożliwiają łatwe odtworzenie i przetestowanie zaimplementowanego rozwiązania oraz ukazują spójność całego projektu, zarówno w kontekście wymagań z przedmiotu **Bazy Danych 1**, jak i  tych z przedmiotu **Programowanie Aplikacyjne**.

#### Członkowie zespołu

Aleksandra Jamróz, 310708  
Aleksandra Majewska, 310832  
Gabriela Topczewska, 310961  
Radosław Kostrzewski, 310757  

Politechnika Warszawska,  
28.01.2022
