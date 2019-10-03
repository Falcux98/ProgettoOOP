---


---

<h1 id="introduzione">Introduzione</h1>  
<p>Questo progetto è stato svolto per l'Università Politecnica delle Marche, presso la facoltà di ingegneria Informatica e dell'Automazione, tramite il corso di Programmazione ad Oggetti.
</p><p>Si basa sul linguaggio di programmazione Java e sfrutta il framework Spring e i vantaggi di un linguaggio OOP (Object-Oriented Programming.</p>  
<h1 id="utilizzo-del-software">Utilizzo</h1>
<h2 id="come-eseguire-l'applicazione">Come eseguire l'applicazione</h2>
<p>Per compilare il progetto bisogna recarsi nella cartella dove è presente il file "mvn" e aprire il terminale e digitare:
</p><p></p><pre><code>mvn -am package</code></pre>
<p></p><p>Bisogna spostarsi nella directory /target
</p><p></p><pre><code>cd target</code></pre>
<p></p><p></p><pre><code>java -jar ProgettoOOP-0.0.1-SNAPSHOT.jar</code></pre>
<p></p><p></p><p>
L'applicazione apre una connessione con il web-server in locale 
sulla porta tcp 8080, dedicata allo scambio di informazioni
tramite protocollo HTTP.
</p><h2 id="come-utilizzare">Come Utilizzare</h2>  
<p></p><p>Per utilizzare l'applicazione, si può usare Postman, ambiente
attraverso il quale è possibile richiedere dati, metadati, 
statistiche sui dati. O in alternativa il proprio Browser
</p><p>All’avvio, l’applicazione, scarica in automatico il dataset
ed effettua il parsing del file CSV contenuto nell’<a href="http://data.europa.eu/euodp/data/api/3/action/package_show?id=GIGFgVkEyuzYNvbktE7tAQ">URL</a> .
</p><h2 id="dati">Dati</h2>  
<p></p><p>Per ottenere i dati contenuti sul CSV, bisogna scrivere,
su Postman o sul browser il seguente comando: 
</p><pre><code>http://localhost:8080/data </code><p></p></pre>
https://picasaweb.google.com/117152600055400889215/6743612008475214209#6743612014066909362 
<p></p><h3 id="metadati">Metadati</h3>  
<p>Per i metadati bisogna scrivere:</p>  
<pre><code>http://localhost:8080/metadata </code><p></p></pre>
 https://picasaweb.google.com/117152600055400889215/6743612576054227681#6743612573724979970  
<h3 id="statistiche">Statistiche</h3>  
<p>Per ottenere le statistiche di ogni attributo relativo al dataset, bisogna scrivere:</p>  
<pre><code>http://localhost:8080/statistiche </code><p></p></pre>
https://picasaweb.google.com/117152600055400889215/6743613075511129265#6743613075438916322
<p>oppure se volgiamo le statistiche di uno specifico attributo , bisogna specificare nella rotta il campo <em>field</em> nel seguente modo:</p>  
<pre><code>http://localhost:8080/stats?field=value </code><p>
https://picasaweb.google.com/117152600055400889215/6743613451786695969#6743613450774963362
</p><p></p></pre>
Come possiamo notare dall'immagine di esempio, l'applicazione non tiene conto del carattere, quindi non è Case Sensitive
<p><em><strong>Gli attributi presenti nel dataset sono:</strong></em><br>  
</p><p><strong>time_period<br><br>  
ref_area <br><br>  
indicator<br><br>  
breakdown<br><br>  
unit_measure<br><br>  
value<br></strong></p>  
<h1 id="strutturazione-del-codice">Strutturazione del codice</h1>  
<h2 id="packaging">Packaging</h2>  
<p>Le classi sono inserite in quattro package principali basati sulla logica MVC (Model View Controller):</p>  
<p>-<em>Progetto.Controller</em> contiene la classe, <em>"Controller",</em> che gestisce le richieste tramite, GET.</p>  
<p>-<em>Progetto.GestioneDati</em> In questo package vi è la classe principale,  <em>"EuropeanInformationSociety",</em>su cui si basa l’intero programma.
E la classe che gestisce i metadati, <em>"Metadata",</em></p>  
<p>-<em>Progetto.Service</em> al suo interno contiene la classe,  <em>"EuropeanService",</em> che mette in collegamento tutte le classi presenti, per gestire al meglio i metadati, i dati e le statistiche</p>
<p>-<em>Progetto.Tool</em> contiene la classe che permette di fare il download e il parsing del file CSV,  <em>"DownloadParsing",</em> e la classe relativa alle statistiche,  <em>"Statistiche".</em></p>  
<p><strong>Falcux98<strong></strong></strong></p>  
<blockquote>  
<p>Written with <a href="https://stackedit.io/">StackEdit</a>.</p>  
</blockquote>

