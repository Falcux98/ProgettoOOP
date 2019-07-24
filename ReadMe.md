---


---

<h1 id="introduzione">Introduzione</h1>
<p>Il seguente progetto è stato svolto per il corso di Programmazione ad Oggetti offerto dall’Università Politecnica delle Marche.</p>
<p>L’applicazione, basata sul linguaggio di programmazione Java, sfrutta il framework Spring e i vantaggi di un linguaggio OOP (incapsulamento, ereditarietà e polimorfismo).</p>
<h1 id="utilizzo-del-software">Utilizzo</h1>
<h2 id="cosa-può-fare">Cosa può fare</h2>
<p>All’avvio, l’applicazione apre una connessione con il web-server in locale sula porta 8080. Viene scaricato in automatico il dataset ed effettua il parsing del file CSV contenuto nell’<a href="http://data.europa.eu/euodp/data/api/3/action/package_show?id=GIGFgVkEyuzYNvbktE7tAQ">URL</a> .</p>
<p>Per testare il software è possibile utilizzare Postman, ambiente attraverso il quale è possibile richiedere dati, metadati, statistiche sui dati e si possono applicare filtri a dati e statistiche. O in alternativa il proprio Browser</p>
<h3 id="dati">Dati</h3>
<p>Per richiedere i dati contenuti nel file CSV bisogna impostare il metodo “GET” e definire la rotta</p>
<pre><code>localhost:8080/data

</code></pre>
<h3 id="metadati">Metadati</h3>
<p>Per richiedere i metadati bisogna impostare il metodo <strong>GET</strong> e definire la rotta</p>
<pre><code>localhost:8080/metadata

</code></pre>
<h3 id="statistiche">Statistiche</h3>
<p>Per ottenere le statistiche di ogni attributo del dataset bisogna impostare il metodo <strong>GET</strong> e definire la rotta</p>
<pre><code>localhost:8080/statistiche

</code></pre>
<p>oppure ad esempio per il campo , bisogna specificare nella rotta il campo <em>field</em> nel seguente modo:</p>
<pre><code>localhost:8080/stats?field=value

</code></pre>
<p></p>
<h2 id="come-applicare-un-filtro">Come applicare un filtro</h2>
<p>Per applicare un filtro ai dati bisogna scegliere il metodo <strong>POST</strong> . Sul body impostare la stringa <em>raw</em> con il formato <em>JSON(application/json)</em>.</p>
<p><em><strong>Esempio(Age):</strong></em></p>
<pre><code>localhost:8080/data

</code></pre>
<p>e aggiungere nel body la stringa:</p>
<pre><code>{"fieldName": "time_period", "op":"&amp;gt", "rif":2013}

</code></pre>
<p>Se invece si vuole lavorare con le statistiche di tutti i dati filtrati, impostare il metodo <strong>POST</strong> e assegnare la rotta:</p>
<pre><code>localhost:8080/stats

</code></pre>
<p><em><strong>Esempio</strong></em></p>
<pre><code>{"fieldName":"ref_area", "op":"&amp;in", "rif":["AT","BE"]}

</code></pre>
<p>Vengono restituite le statistiche di tutti i campi che hanno al loro interno gli elementi AT, BE .</p>
<p>Se si vogliono ottenere le statistiche di un solo campo della lista dei dati filtrati, bisogna specificare nella rotta, attraverso la variabile field, il nome dell’attributo desiderato. Ad esempio:</p>
<pre><code>localhost:8080/stats?field=time_period

</code></pre>
<p>che restituisce le statistiche del solo campo Age preso dalla lista dei dati filtrati secondo quanto specificato nel body.</p>
<p><strong>fieldName</strong> rappresenta il nome del campo su cui applicare il filtro</p>
<p><strong>op</strong> indica l’operatore ovvero il tipo di filtro richiesto. Si possono applicare i seguenti operatori:</p>
<ul>
</ul><li>
</li><p>&amp;eq : uguaglianza di due valori</p>

<li>
</li><p>&amp;not : non uguaglianza tra due valori</p>

<li>
</li><p>&amp;gt : (greater than) elementi maggiori del valore passato</p>

<li>
</li><p>&amp;gte : elementi maggiori e uguali al valore passato</p>

<li>
</li><p>&amp;lt : (less than) elementi minori al valore passato</p>

<li>
</li><p>&amp;lte : elementi minori e uguali al valore passato</p>

<li>
</li><p>&amp;in : elemento contenuto nei dati</p>

<li>
</li><p>&amp;nin : elemento non contenuto nei dati</p>

<li>
</li><p>&amp;bt : (between) elemento compreso tra i valori passati al riferimento</p>


<p><strong>Rif</strong> simboleggia il valore di riferimento.</p>
<p><strong>Altri esempi:</strong><br>
</p><p>1- se il riferimento è una stringa</p>
<pre><code>{"fieldName":"REF_AREA", "op":"&amp;eq", "rif":"AT"}

</code></pre>
<p>2-se il riferimento è una lista di stringhe</p>
<pre><code>{"fieldName":"REF_AREA", "op":"&amp;in", "rif":["AT","BE","CY"]}

</code></pre>
<p>3-se il riferimento è una lista di numeri</p>
<pre><code>{"fieldName":"VALUE", "op":"&amp;bt", "rif":[0.8,0.7]}

</code></pre>
<p><em><strong>Di seguito viene elencata la lista degli attributi:</strong></em><br>
</p><p>time_period<br><br>
ref_area <br><br>
indicator<br><br>
breakdown<br><br>
unit_measure<br><br>
value<br></p>
<h1 id="struttura-del-codice">Struttura del codice</h1>
<h2 id="packaging">Packaging</h2>
<p>Le classi sono inserite in quattro package principali basati sulla logica MVC (Model View Controller):</p>
<p><em>Controller</em> racchiude tutti i metodi necessari per far fronte alle richieste GET e POST.</p>
<p><em>Model</em> contiene la classe principale su cui si basa l’intero programma.</p>
<p><em>Service</em> è utile nell’implementazione di metodi che gestiscono l’accesso a dati, metadati, statistiche e filtri.</p>
<p><em>Utilities</em> include la classe che permette di fare il download e il parsing del file CSV.</p>
<p><strong>Falcux98<strong></strong></strong></p>
<blockquote>
<p>Written with <a href="https://stackedit.io/">StackEdit</a>.</p>
</blockquote>

