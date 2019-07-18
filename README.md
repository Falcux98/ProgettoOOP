# ProgettoOOP
-Di Cecco Diego, Matricola 1084270;

#CHE COSA FA?

Seguendo le specifiche assegnate dai professori e partendo dal seguente indirizzo fornito tramite e-mail:
-http://data.europa.eu/euodp/data/api/3/action/package_show?id=GIGFgVkEyuzYNvbktE7tAQ
Il progetto effettua il download del dataset che contiene i dati e i metadati in formato CSV,dopo aver effettuato una decodifica del JSON che contiene l'URL
utile per scaricare il file.
Successivamente, effettua il parsing e la serializzazioe dei dati e dei metadati.

>Durante il parsing, si è reso necessario usare dei delimitatori, virgola e virgolette. Perchè il csv, per dividere i valori utilizzava ",".

In fine l'applicazione Spring si occupa di implementare le risposte alle richiese dell'utente restituendo i metadati, i dati e le statistiche con eventuali 
filtri applicati.

#COME USARE

Una volta scaricato il progetto da GitHub può essere aperto con il proprio IDE Java (quello utilizzato per sviluppare il progetto
è stato Eclipse), va eseguito come Spring Boot Application.
Oppure aprendo dalla cartella che contiene il file mvn, il prompt dei comandi, e digitare:
C:\Users\ProgettoOPP>mvn package //per il maven build

C:\Users\ProgettoOPP>cd target //per spostarsi nella directory dove è presente l'applicazione jar
C:\Users\ProgettoOPP>java -jar ProgettoOOP-0.0.1-SNAPSHOT.jar //per eseguire l'applicazione.

In entrambi i casi l'utente dovrà aprire il proprio Browser Web o un programma come Postman, che permette di testare le varie funzionalità API REST. 
Le richieste verranno scritte come URL con la seguente sintassi:

> http://localhost:6969/"tipologiaRichiesta"

All'interno della query string, la stringa *tipologiaRichiesta* indica il tipo di informazione che si vuole richiedere scegliendo tra queste tre possibilità:

 - **metadata** : Restituisce i metadati in formato JSON relativi agli attributi;
 - **data** : Restituisce i dati in formato JSON che sono contenuti all'interno del data-set;
 - **statistiche** : Restistuisce le statistiche in formato JSON relative a un determinato attributo specificato;

Per ricevere i dati o le statistiche relative ad un attributo bisognerà richiederlo con la seguente sintassi:

> http://localhost:6969/"tipologiaRichiesta"?attribute="nomeAttributo"

Dove nomeAttributo è appunto il nome dell'attributo richiesto.
Facciamo un piccolo esempio:

http://localhost:6969/statistiche?attribute=value


E' possibile applicare dei particolari filtri che restituiranno i dati filtrati secondo le eventuali richieste. I tipi di filtri (3 di tipo logico e 3 di tipo condizionale) sono elencati di seguito:

 - **$eq** : questo filtro permette di selezionare tutti gli oggetti con valore dell' attributo specificato uguale a quello richiesto;
 - **$not** : questo filtro permette di selezionare tutti gli oggetti con valore dell'attributo specificato diverso da quello richiesto;
 - **$or** : questo filtro permette di selezionare tutti gli oggetti con un valore dell'attributo specificato uguale ad almeno uno dei due valori richiesti;
 - **$gt** : questo filtro permette di selezionare tutti gli oggetti con valore dell'attributo numerico specificato strettamente maggiore di quello richiesto;
 - **$lt** : questo filtro permette di selezionare tutti gli oggetti con valore dell'attributo numerico specificato strettamente minore di quello richiesto;
 - **$bt** : questo filtro permette di selezionare tutti gli oggetti con un valore dell'attributo numerico specificato compreso tra i due valori richiesti.

Quindi l'utente, a seconda del filtro richiesto, dovrà specificare uno o due valori dell'attributo di cui si vuole avere informazioni. 

> Nota bene: se si utilizzano filtri per attributi di tipo numerico, non si possono selezionare attributi di tipo String e nel caso del filtro *$bt* il primo valore inserito dovrà essere minore del secondo.




@Falcux98
