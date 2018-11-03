@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1
import lesson2.task2.daysInMonth
import kotlin.math.exp

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
val monthes = listOf("января", "февраля", "марта", "апреля", "мая",
        "июня", "июля", "августа", "сентября", "октября",
        "ноября", "декабря")

fun numberOfMonth(day: Int, year: Int, nameOfMonth: String): Int
{
    var month = monthes.indexOf(nameOfMonth)

    if (month == -1)
        return 0
    else
        month++

    if (day !in 1..daysInMonth(month, year)) return 0

    return month
}

fun dateStrToDigit(str: String): String
{
    val input = str.split(" ")

    if (input.size != 3) return ""

    val day = input[0].toIntOrNull()
    val year = input[2].toIntOrNull()

    if (day == null || year == null) return ""

    val month = numberOfMonth(day, year, input[1])

    if (month == 0) return ""

    return String.format("%02d.%02d.%d", day, month, year)
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
fun nameOfMonth(day: Int, year: Int, numberOfMonth: Int): String
{
    if (numberOfMonth !in 1..12) return ""

    val month = monthes[numberOfMonth - 1]

    if (day !in 1..daysInMonth(numberOfMonth, year)) return ""

    return month
}

fun dateDigitToStr(digital: String): String
{
    val input = digital.split(".")

    if (input.size != 3) return ""

    val day = input[0].toIntOrNull()
    val year = input[2].toIntOrNull()

    if (day == null || year == null) return ""

    val month = nameOfMonth(day, year, input[1].toInt())

    if (month == "") return ""

    return String.format("%d %s %d", day, month, year)
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
    val digits = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    val symbols = setOf(' ', '+', '-', '(', ')')

    val result = StringBuilder("")

    val phoneLenght = phone.length
    for (i in 0 until phoneLenght)
    {
        if (phone[i] !in symbols && phone[i] !in digits) return ""
        if (phone[i] == '+' && result.length != 0) return ""
        if (phone[i] == '(')
        {
            if (i != phoneLenght - 1)
            {
                if (phone[i + 1] == ')') return ""
            }
            else
                return ""

            var j = i + 1
            while (j < phoneLenght)
            {
                if (phone[j] == ')') break
                j++
            }

            if (j == phoneLenght) return ""
        }

        if (phone[i] != ' ' && phone[i] != '-' &&
            phone[i] != '(' && phone[i] != ')') result.append(phone[i])
    }

    if (result.length == 1 && result[0] == '+') return ""

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
    if (jumps.length == 0) return -1

    val tmpStr = StringBuilder("")
    var index = 0
    while (index < jumps.length)
    {
        tmpStr.append(jumps[index])

        if (jumps[index] == ' ')
            while(jumps[index] == ' ')
                index++
        else
            index++
    }

    val input = tmpStr.split(" ")

    var max : Int = -1
    for (i in 0 until input.size)
        if (input[i][0] !in setOf('%', '-'))
        {
            val tmpNumber = input[i].toIntOrNull()
            if (tmpNumber != null)
            {
                if (tmpNumber > max) max = tmpNumber
            }
            else
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
fun deleteWasteSpace(str : String) : StringBuilder
{
    val result = StringBuilder("")
    if (str == "" || str == " ") return result

    var index = 0
    if (str[0] == ' ') index++
    while (index < str.length)
    {
        if (str[index] != ' ')
        {
            result.append(str[index])
            index++
        }
        else
        {
            result.append(' ')
            while (str[index] == ' ')
            {
                index++

                if (index == str.length) break
            }
        }
    }

    return result
}

fun bestHighJump(jumps: String): Int
{
    val permissibleSymbols = setOf('+', '%', '-')

    val splitting = deleteWasteSpace(jumps).toString().split(" ")
    val size = splitting.size
    if (size % 2 != 0) return -1

    var result = -1
    for (i in 0 until size step 2)
    {
        val high = splitting[i].toIntOrNull()

        if (high == null)
            return -1
        else
        {
            for (j in 0 until splitting[i + 1].length)
                if (splitting[i + 1][j] !in permissibleSymbols) return -1

            if ('+' in splitting[i + 1] && result < high)
                result = high
        }
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
    if (expression.length == 0) throw IllegalArgumentException("")

    val str = deleteWasteSpace(expression)
    val input = str.split(" ")
    if (input.size % 2 == 0) throw IllegalArgumentException("")

    for (i in 0 until input.size)
        if (i % 2 == 0)
        {
            for (j in 0 until input[i].length)
                if (input[i][j].toInt() - '0'.toInt() !in 0..9)
                    throw IllegalArgumentException("")
        }
        else
            if (input[i] != "+" && input[i] != "-")
                throw IllegalArgumentException("")

    var result = 0
    var sign = true // true = +; false = -

    for (i in 0 until input.size step 2)
    {
        if (sign)
            result += input[i].toInt()
        else
            result -= input[i].toInt()

        if (i != input.size - 1)
            sign = (input[i + 1] == "+")
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

    var result = 0
    for (i in 0 until input.size - 1)
        if (input[i].toLowerCase() != input[i + 1].toLowerCase())
            result += input[i].length + 1
        else
            return result

    return -1
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
    if (description == "") return ""

    val description = deleteWasteSpace(description)
    var index = 0
    while (index < description.length)
    {
        if (description[index] == ';') description.deleteCharAt(index + 1)
        index++
    }

    val input = description.split(";")

    var max = -1.0
    var result = ""
    for (i in 0 until input.size)
    {
        val tmp = input[i].split(" ")
        if (tmp.size != 2) return ""

        val cost = tmp[1].toDoubleOrNull()
        if (cost == null) return ""

        if (cost > max)
        {
            max = cost
            result = tmp[0]
        }
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

    if (lengthRoman == 0) return -1
    for (i in 0 until lengthRoman)
        if (roman[i] !in setOf('I', 'V', 'X', 'L', 'C', 'D', 'M')) return -1

    for (i in 0 until lengthRoman - 4)
    {
        if (roman[i] == 'M') continue

        if (roman[i] == roman[i + 1] &&
            roman[i] == roman[i + 2] &&
            roman[i] == roman[i + 3]) return -1
    }

    var result = 0
    var i = 0
    while (i < lengthRoman)
    {
        when (roman[i])
        {
            'I' -> if (i + 1 != lengthRoman)
                    when (roman[i + 1])
                    {
                        'L', 'C', 'D', 'M' -> return -1

                        'V' ->
                        {
                            result += arabicNumbersOther[0]
                            i++
                        }

                        'X' ->
                        {
                            result += arabicNumbersOther[1]
                            i++
                        }

                        else -> result += arabicNumbers[0]
                    }
                   else
                    result += arabicNumbers[0]

            'V' -> result += arabicNumbers[1]

            'X' -> if (i + 1 != lengthRoman)
                    when (roman[i + 1])
                    {
                        'D', 'M' -> return -1

                        'L' ->
                        {
                            result += arabicNumbersOther[2]
                            i++
                        }

                        'C' ->
                        {
                            result += arabicNumbersOther[3]
                            i++
                        }

                        else -> result += arabicNumbers[2]
                    }
                   else
                    result += arabicNumbers[2]

            'L' -> result += arabicNumbers[3]

            'C' -> if (i + 1 != lengthRoman)
                    when(roman[i + 1])
                    {
                        'D' ->
                        {
                            result += arabicNumbersOther[4]
                            i++
                        }

                        'M' ->
                        {
                            result += arabicNumbersOther[5]
                            i++
                        }

                        else -> result += arabicNumbers[4]
                    }
                   else
                    result += arabicNumbers[4]

            'D' -> result += arabicNumbers[5]

            'M' -> result += arabicNumbers[6]
        }

        i++
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
