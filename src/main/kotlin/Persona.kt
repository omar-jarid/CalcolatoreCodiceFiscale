import java.io.File

data class Persona(
    val nome: String = "",
    val cognome: String = "",
    val dataNascita: String = "",
    val isFemmina: Boolean = false,
    val luogoNascita: String = ""
) {
    private val codiceFiscaleSb = StringBuilder()

    private fun calcolaPezzoCognome() {
        /*
            COGNOME

            Vengono prese le consonanti del cognome in ordine.
            Se le consonanti sono insufficienti, si prelevano anche le vocali, sempre in ordine.
            Se sono sufficienti, si prendono la prima, la seconda e la terza consonante.
            Le vocali vengono riportate dopo le consonanti (es. Rosi -> RSO).
            Se un cognome ha meno di tre lettere, si completa aggiungendo una X (es. Fo -> FOX).
        */

        val cognomeSenzaVocali = cognome.rimuoviVocali()
        val cognomeTmp = if (cognome.length == 3) {
            cognome
        } else if (cognome.length < 3) {
            val cognomeSb = StringBuilder()
            cognomeSb.append(cognome)
            while (cognomeSb.length < 3) cognomeSb.append('X')
            cognomeSb.toString()
        } else {
            if (cognomeSenzaVocali.length < 3) {
                val cognomeSenzaConsonanti = cognome.rimuoviConsonanti()
                "$cognomeSenzaVocali$cognomeSenzaConsonanti".substring(0, 2)
            } else if (cognomeSenzaVocali.length == 3){
                cognomeSenzaVocali
            } else { cognomeSenzaVocali.substring(0, 3) }
        }

        codiceFiscaleSb.append(cognomeTmp.uppercase())
    }

    private fun calcolaPezzoNome() {
        /*
             NOME

            Vengono prese le consonanti del nome in ordine.
            Se le consonanti sono insufficienti, si prelevano anche le vocali, sempre in ordine.
            Se il nome ha TRE consonanti, si prendono la prima, la seconda e la terza consonante (es. Tiziana -> TZN).
            Se il nome ha più di TRE consonanti, si prendono la prima, la seconda e la quarta consonante.
            Le vocali vengono riportate dopo le consonanti (es. Luca -> LCU).
            Se un nome ha meno di tre lettere, si completa aggiungendo una X (es. Fo -> FOX).
        */
        val nomeSenzaVocali = nome.rimuoviVocali()
        val nomeTmp = if (nome.length == 3) {
            nome
        } else if (nome.length < 3) {
            val nomeSb = StringBuilder()
            nomeSb.append(nome)
            while (nomeSb.length < 3) nomeSb.append('X')
            nomeSb.toString()
        } else {
            if (nomeSenzaVocali.length < 3) {
                val nomeSenzaConsonanti = nome.rimuoviConsonanti()
                "$nomeSenzaVocali$nomeSenzaConsonanti".substring(0, 3)
            } else if (nomeSenzaVocali.length == 3) {
                nomeSenzaVocali
            } else {
                // OCCHIO! DIVERSO DAL COGNOME.
                "${nomeSenzaVocali[0]}${nomeSenzaVocali[2]}${nomeSenzaVocali[3]}"
            }
        }

        codiceFiscaleSb.append(nomeTmp.uppercase())
    }

    private fun calcolaPezzoDataNascitaESesso() {
        // Spezzo la data di nascita.
        val giornoMeseAnno = dataNascita.split('/')
        val day = giornoMeseAnno.first()
        val month = giornoMeseAnno[1]
        val year = giornoMeseAnno.last()

        /* ANNO E MESE DI NASCITA
        Anno: si prendono le ultime DUE cifre dell'anno di nascita.
        Mese: a ogni mese si associa una lettera. */

        val annoTmp = year.takeLast(2)
        codiceFiscaleSb.append(annoTmp)
        val meseTmp = when (month.toInt()) {
            1 -> 'A'
            2 -> 'B'
            3 -> 'C'
            4 -> 'D'
            5 -> 'E'
            6 -> 'H'
            7 -> 'L'
            8 -> 'M'
            9 -> 'P'
            10 -> 'R'
            11 -> 'S'
            12 -> 'T'

            // Caso triviale che non capiterà mai
            else -> 'W'
        }

        codiceFiscaleSb.append(meseTmp)

        /*
            GIORNO DI NASCITA E SESSO
            Se il soggetto è maschio, si prende il giorno di nascita, se invece è femmina lo si aumenta di 40.
        */
        val giornoTmp = if (isFemmina) (day.toInt() + 40).toString() else day
        codiceFiscaleSb.append(giornoTmp)
    }

    private fun calcolaPezzoLuogoNascita() {
        /*
            COMUNE (O STATO) DI NASCITA
            Si prende il codice catastale (forte assunzione che il soggetto sia nato in Italia).
        */
        val listaComuni = mutableListOf<String>()
        File("comuni.txt").useLines { lines -> lines.forEach { listaComuni.add(it) } }
        val comuneTmp = listaComuni.first { it.contains(luogoNascita.uppercase()) }.takeLast(4)
        codiceFiscaleSb.append(comuneTmp)
    }

    private fun calcolaLetteraControllo() {
        /*
            LETTERA DI CONTROLLO
            Algoritmo molto particolare.
        */

        // Prendo il codice fiscale come calcolato finora per avere una lista di char.
        val codiceFiscaleTmp = codiceFiscaleSb.toString().toList()

        // Ottengo lista caratteri pari e lista caratteri dispari.
        val caratteriPari = codiceFiscaleTmp.filterIndexed {index, _ -> (index + 1) % 2 == 0}
        val caratteriDispari = codiceFiscaleTmp.filterIndexed {index, _ -> (index + 1) % 2 != 0}

        // Converto caratteri pari e caratteri dispari, entrambe conversioni coi numeri già sommati.
        val primoPezzo = caratteriPari.convertiCaratteriPari()
        val secondoPezzo = caratteriDispari.convertiCaratteriDispari()

        // Divido la somma dei due numeri ottenuti per 26, prendo il resto e mi ricavo la lettera di controllo.
        val letteraControllo = when ((primoPezzo + secondoPezzo) % 26) {
            0 -> 'A'
            1 -> 'B'
            2 -> 'C'
            3 ->'D'
            4 -> 'E'
            5 -> 'F'
            6 -> 'G'
            7 -> 'H'
            8 -> 'I'
            9 -> 'J'
            10 -> 'K'
            11 -> 'L'
            12 -> 'M'
            13 -> 'N'
            14 -> 'O'
            15 -> 'P'
            16 -> 'Q'
            17 -> 'R'
            18 -> 'S'
            19 -> 'T'
            20 -> 'U'
            21 -> 'V'
            22 -> 'W'
            23 -> 'X'
            24 -> 'Y'
            25 -> 'Z'

            // Caso triviale in cui non cadremo mai.
            else -> '<'
        }

        codiceFiscaleSb.append(letteraControllo)
    }

    fun getCodiceFiscale(): String {
        calcolaPezzoCognome()
        calcolaPezzoNome()
        calcolaPezzoDataNascitaESesso()
        calcolaPezzoLuogoNascita()
        calcolaLetteraControllo()
        return codiceFiscaleSb.toString()
    }
}
