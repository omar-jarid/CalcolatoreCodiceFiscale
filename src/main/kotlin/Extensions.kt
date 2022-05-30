fun String.rimuoviVocali(): String {
    val re = StringBuilder()
    this.forEach { char ->
        if (
            char != 'A' && char != 'a' &&
            char != 'E' && char != 'e' &&
            char != 'I' && char != 'i' &&
            char != 'O' && char != 'o' &&
            char != 'U' && char != 'u'
        ) re.append(char)
    }

    return re.toString()
}

fun String.rimuoviConsonanti(): String {
    val re = StringBuilder()
    this.forEach { char ->
        if (
            char == 'A' || char == 'a' ||
            char == 'E' || char == 'e' ||
            char == 'I' || char == 'i' ||
            char == 'O' || char == 'o' ||
            char == 'U' || char == 'u'
        ) re.append(char)
    }

    return re.toString()
}

fun List<Char>.convertiCaratteriPari(): Int {
    var somma = 0
    this.forEach { char ->
        somma += when (char) {
            'A', '0' -> 0
            'B', '1' -> 1
            'C', '2' -> 2
            'D', '3' -> 3
            'E', '4' -> 4
            'F', '5' -> 5
            'G', '6' -> 6
            'H', '7' -> 7
            'I', '8' -> 8
            'J', '9' -> 9
            'K' -> 10
            'L' -> 11
            'M' -> 12
            'N' -> 13
            'O' -> 14
            'P' -> 15
            'Q' -> 16
            'R' -> 17
            'S' -> 18
            'T' -> 19
            'U' -> 20
            'V' -> 21
            'W' -> 22
            'X' -> 23
            'Y' -> 24
            'Z' -> 25

            // Caso triviale in cui non cadremo mai.
            else -> 0
        }
    }

    return somma
}

fun List<Char>.convertiCaratteriDispari(): Int {
    var somma = 0
    this.forEach { char ->
        somma += when (char) {
            'A', '0' -> 1
            'B', '1' -> 0
            'C', '2' -> 5
            'D', '3' -> 7
            'E', '4' -> 9
            'F', '5' -> 13
            'G', '6' -> 15
            'H', '7' -> 17
            'I', '8' -> 19
            'J', '9' -> 21
            'K' -> 2
            'L' -> 4
            'M' -> 18
            'N' -> 20
            'O' -> 11
            'P' -> 3
            'Q' -> 6
            'R' -> 8
            'S' -> 12
            'T' -> 14
            'U' -> 16
            'V' -> 10
            'W' -> 22
            'X' -> 25
            'Y' -> 24
            'Z' -> 23

            // Caso triviale in cui non cadremo mai.
            else -> 0
        }
    }

    return somma
}