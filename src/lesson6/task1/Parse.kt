@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1
import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        }
        else {
            println("Прошло секунд с начала суток: $seconds")
        }
    }
    else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String
{
    val input = str.split(" ")

    if (input.size != 3) return ""

    try
    {
        val month = when (input[1])
                        {
                            "января" -> 1
                            "февраля" -> 2
                            "марта" -> 3
                            "апреля" -> 4
                            "мая" -> 5
                            "июня" -> 6
                            "июля" -> 7
                            "августа" -> 8
                            "сентября" -> 9
                            "октября" -> 10
                            "ноября" -> 11
                            "декабря" -> 12
                            else -> return ""
                        }
        val day = input[0].toInt()
        val year = input[2].toInt()
        when (month)
        {
            1 -> if (day !in 1..31) return ""
            2 -> if (day !in 1..daysInMonth(2, year)) return ""
            3 -> if (day !in 1..31) return ""
            4 -> if (day !in 1..30) return ""
            5 -> if (day !in 1..31) return ""
            6 -> if (day !in 1..30) return ""
            7 -> if (day !in 1..31) return ""
            8 -> if (day !in 1..31) return ""
            9 -> if (day !in 1..30) return ""
            10 -> if (day !in 1..31) return ""
            11 -> if (day !in 1..30) return ""
            else -> if (day !in 1..31) return ""
        }

        return String.format("%02d.%02d.%d", day, month, year)
    }
    catch (e: Exception)
    {
        return ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String
{
    val input = digital.split(".")

    if (input.size != 3) return ""

    try
    {
        val month = when (input[1].toInt())
        {
            1 -> "января"
            2 -> "февраля"
            3 -> "марта"
            4 -> "апреля"
            5 -> "мая"
            6 -> "июня"
            7 -> "июля"
            8 -> "августа"
            9 -> "сентября"
            10 -> "октября"
            11 -> "ноября"
            12 -> "декабря"
            else -> return ""
        }

        val day = input[0].toInt()
        val year = input[2].toInt()
        when (input[1].toInt())
        {
            1 -> if (day !in 1..31) return ""
            2 -> if (day !in 1..daysInMonth(2, year)) return ""
            3 -> if (day !in 1..31) return ""
            4 -> if (day !in 1..30) return ""
            5 -> if (day !in 1..31) return ""
            6 -> if (day !in 1..30) return ""
            7 -> if (day !in 1..31) return ""
            8 -> if (day !in 1..31) return ""
            9 -> if (day !in 1..30) return ""
            10 -> if (day !in 1..31) return ""
            11 -> if (day !in 1..30) return ""
            else -> if (day !in 1..31) return ""
        }

        return String.format("%d %s %d", day, month, year)
    }
    catch (e: Exception)
    {
        return ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String
{
    val permissibleSymbols =
            setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                  ' ', '+', '-', '(', ')')
    val result = StringBuilder("")

    for (symbol in phone)
    {
        if (symbol !in permissibleSymbols) return ""

        if (symbol != ' ' && symbol != '-' &&
            symbol != '(' && symbol != ')') result.append(symbol)
    }

    return result.toString()
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int
{
    val tmp = StringBuilder("")
    var index = 0
    while (index < jumps.length)
    {
        tmp.append(jumps[index])

        if (jumps[index] == ' ')
            while(jumps[index] == ' ')
                index++
        else
            index++
    }


    val input = tmp.split(" ")

    var max = -1
    try
    {
        for (i in 0 until input.size)
            if (input[i][0] !in setOf('%', '-'))
                if (input[i].toInt() > max) max = input[i].toInt()
    }
    catch (e: Exception)
    {
        return -1
    }

    return max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int
{
    val permissibleSymbols = setOf('0', '1', '2', '3', '4', '5', '6', '7',
                                   '8', '9', ' ', '+', '%', '-')

    val jumps = ' ' + jumps
    val lengthJumps = jumps.length
    for (i in 0 until lengthJumps)
        if (jumps[i] !in permissibleSymbols) return -1

    var startNumber = 0
    var endNumber = 0
    var result = -1
    for (i in 1 until lengthJumps)
        if (jumps[i] != '+')
        {
            if ((jumps[i].toInt() - '0'.toInt() in 0..9) &&
                (jumps[i - 1] == ' ') &&
                (jumps[i + 1] == ' '))
                {
                    startNumber = i
                    endNumber = i
                    continue
                }

            if ((jumps[i].toInt() - '0'.toInt() in 0..9) &&
                (jumps[i - 1].toInt() - '0'.toInt() in 0..9) &&
                (jumps[i + 1] == ' '))
            {
                endNumber = i
                continue
            }

            if ((jumps[i].toInt() - '0'.toInt() in 0..9) &&
                (jumps[i + 1].toInt() - '0'.toInt() in 0..9) &&
                (jumps[i - 1] == ' ' && i - 1 >= 0))
            {
                startNumber = i
                continue
            }
        }
        else
        {
            var number = 0
            for (j in startNumber..endNumber)
            {
                number *= 10
                number += jumps[j].toInt() - '0'.toInt()
            }
            if (number > result) result = number
        }

    return result
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * При нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int
{
    if (expression == "") throw IllegalArgumentException("")

    val numbers = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    val signs = setOf('+', '-')

    val input = expression.split(" ")

    for (i in 0 until input.size)
        if (i % 2 == 0)
        {
            for (j in 0 until input[i].length)
                if (input[i][j] !in numbers) throw IllegalArgumentException("")
        }
        else
        {
            for (j in 0 until input[i].length)
                if (input[i][j] !in signs) throw IllegalArgumentException("")
        }

    var result = 0
    var sign = 1 // 1 = +; 0 = -
    for (i in 0 until input.size step 2)
    {
        if (sign == 1)
            result += input[i].toInt()
        else
            result -= input[i].toInt()

        if (i != input.size - 1)
            if (input[i + 1] == "+")
                sign = 1
            else
                sign = 0
    }

    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int
{
    val input = str.split(" ")

    var tmp = -1
    for (i in 0 until input.size - 1)
        if (input[i].toLowerCase() == input[i + 1].toLowerCase())
        {
            tmp = i
            break
        }

    if (tmp == -1) return -1

    var result = -1
    for (i in 0 until tmp)
        result += input[i].length + 1

    return result + 1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String
{
    // создание строки без ;
    val tmp = StringBuilder("")
    for (i in 0 until description.length)
        if (description[i] != ';') tmp.append(description[i])

    val input = tmp.split(" ")


    var max = -1.0
    var result = ""
    for (i in 1 until input.size step 2)
        if (input[i].toDouble() > max)
        {
            max = input[i].toDouble()
            result = input[i - 1]
        }


    return result
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int
{
    val arabicNumbers = listOf(1, 5, 10, 50, 100, 500, 1000)
    val arabicNumbersOther = listOf(4, 9, 40, 90, 400, 900)
    val lengthRoman = roman.length

    if (roman.length == 0) return -1
    for (i in 0 until lengthRoman)
        if (roman[i] !in setOf('I', 'V', 'X', 'L', 'C', 'D', 'M')) return -1

    var result = 0
    var tmp = 0 // индекс элемента строки
    while (tmp < lengthRoman)
    {
        when (roman[tmp])
        {
            'I' -> if (tmp + 1 != lengthRoman)
                    when (roman[tmp + 1])
                    {
                        'V' ->
                        {
                            result += arabicNumbersOther[0]
                            tmp++
                        }

                        'X' ->
                        {
                            result += arabicNumbersOther[1]
                            tmp++
                        }

                        else -> result += arabicNumbers[0]
                    }
                   else
                    result += arabicNumbers[0]

            'V' -> result += arabicNumbers[1]

            'X' -> if (tmp + 1 != lengthRoman)
                    when (roman[tmp + 1])
                    {
                        'L' ->
                        {
                            result += arabicNumbersOther[2]
                            tmp++
                        }

                        'C' ->
                        {
                            result += arabicNumbersOther[3]
                            tmp++
                        }

                        else -> result += arabicNumbers[2]
                    }
                   else
                    result += arabicNumbers[2]

            'L' -> result += arabicNumbers[3]

            'C' -> if (tmp + 1 != lengthRoman)
                    when(roman[tmp + 1])
                    {
                        'D' ->
                        {
                            result += arabicNumbersOther[4]
                            tmp++
                        }

                        'M' ->
                        {
                            result += arabicNumbersOther[5]
                            tmp++
                        }

                        else -> result += arabicNumbers[4]
                    }
                   else
                    result += arabicNumbers[4]

            'D' -> result += arabicNumbers[5]

            'M' -> result += arabicNumbers[6]
        }

        tmp++
    }

    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
