/*
    Calcolatore codice fiscale.
    *************************************************************************************************
    COGNOME

    Vengono prese le consonanti del cognome in ordine.
    Se le consonanti sono insufficienti, si prelevano anche le vocali, sempre in ordine.
    Se sono sufficienti, si prendono la prima, la seconda e la terza consonante.
    Le vocali vengono riportate dopo le consonanti (es. Rosi -> RSO).
    Se un cognome ha meno di tre lettere, si completa aggiungendo una X (es. Fo -> FOX).

    **********************************************************************************************
    NOME

    Vengono prese le consonanti del nome in ordine.
    Se le consonanti sono insufficienti, si prelevano anche le vocali, sempre in ordine.
    Se il nome ha 3 consonanti, si prendono la prima, la seconda e la terza consonante (es. Tiziana -> TZN).
    Se il nome ha più di 3 consonanti, si prendono la prima, la seconda e la quarta consonante.
    Le vocali vengono riportate dopo le consonanti (es. Luca -> LCU).
    Se un nome ha meno di tre lettere, si completa aggiungendo una X (es. Fo -> FOX).

    **********************************************************************************************
    ANNO E MESE DI NASCITA
    Anno: si prendono le ultime 2 cifre dell'anno di nascita.
    Mese: a ogni mese si associa una lettera secondo la tabella sottostante.

    Gennaio -> A
    Febbraio -> B
    Marzo -> C
    Aprile -> D
    Maggio -> E
    Giugno -> H
    Luglio -> L
    Agosto -> M
    Settembre -> P
    Ottobre -> R
    Novembre -> S
    Dicembre -> T

    ************************************************************************************************
    GIORNO DI NASCITA E SESSO
    Se il soggetto è maschio, si prende il giorno di nascita, se invece è femmina lo si aumenta di 40.

    ************************************************************************************************
    COMUNE (O STATO) DI NASCITA
    Si prende il codice catastale (forte assunzione che il soggetto sia nato in Italia).

    ************************************************************************************************
    LETTERA DI CONTROLLO

    - Si mettono da una parte i caratteri alfanumerici che si trovano in posizione dispari e da un'altra quelli che si
      trovano in posizione pari.
    - I caratteri vengono convertiti in valori numerici secondo apposite tabelle.
    - Il valore ottenuto dai caratteri pari e quello dai caratteri dispari si sommano tra di loro e il risultato va
      diviso per 26; il resto determina la lettera di controllo secondo apposita tabella.
*/

fun main() {
    // Richiedo in input nome.
    print("Nome: ")
    val nome = readLine().toString()

    // Richiedo in input cognome.
    print("Cognome: ")
    val cognome = readLine().toString()

    // Richiedo in input data di nascita.
    print("Data di nascita (formato gg/mm/aaaa): ")
    val dataNascita = readLine().toString()

    // Richiedo in input sesso.
    print("Sesso: ")
    val sessoTmp = readLine().toString()

    // Richiedo in input luogo di nascita.
    print("Luogo di nascita: ")
    val luogoNascita = readLine().toString()

    // Istanzio la persona.
    val persona = Persona(
        nome = nome,
        cognome = cognome,
        dataNascita = dataNascita,
        isFemmina = sessoTmp == "F",
        luogoNascita = luogoNascita
    )

    // Stampo a video il codice fiscale.
    println("Codice fiscale: ${persona.getCodiceFiscale()}")
}