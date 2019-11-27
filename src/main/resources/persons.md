Filtrare si sortare
---

Exemplu URL: 

http://localhost:8080/persons?sort=age,DESC&sort=name,ASC&size=3&page=0&age=lt=1900&father_age=ge=27

Pare complicat, dar hai sa il spargem in bucati:

_http://localhost:8080/persons_  => URL de baza pentru endpoint de persons

__?__ => de aici incep request parameters
__&__ => folosit pentru a separa request parameters

_sort_=__age,DESC__  => prima data sortam descrescator, dupa varsta

_sort_=__mother_name,ASC__  => dupa, sortam crescator, dupa nume

_size_=__3__ => raspunsul vine intr-o pagina, size e dimensiunea paginii, aka cate entitati sunt intr-o pagina
page=0 => indexul paginii, incepe de la 0

_age_=__lt=1900__ => age less than 1900 aka varsta mai mica de 1900

_father_age_=__ge=27__ => father.age >= 27 aka varsta de la father mai mare de 27

Mai jos sunt toti "operatorii":

|Operator | Key|
|:-------:|:--:|
|==|        eq|
|!=|ne|
|=<| le|
|<| lt|
|=>| ge|
|>| gt|
|like| lk|
