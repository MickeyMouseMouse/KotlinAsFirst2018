@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double
{
    var result = 0.0

    for (element in v)
    {
        result += element * element
    }

    return sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double
{
    if (list.isEmpty()) return 0.0

    var sum = 0.0
    var number = 0

    for (element in list)
    {
        sum += element
        number++
    }

    return sum / number

}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double>
{
    if (list.isEmpty()) return list

    val arithmeticMean = mean(list)

    for (i in 0 until list.size)
        list[i] -= arithmeticMean

    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double
{
    if (a.isEmpty() || b.isEmpty()) return 0.0

    var result = 0.0

    for (i in 0 until a.size)
        result += a[i] * b[i]

    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double
{
    if (p.isEmpty()) return 0.0

    var result = p[0]
    var multiplier = x

    for (i in 1 until p.size)
    {
        result += p[i] * multiplier
        multiplier *= x
    }

    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double>
{
    if (list.isEmpty()) return list

    var sum = list[0]

    for (i in 1 until list.size)
    {
        list[i] += sum
        sum = list[i]
    }

    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int>
{
    val simpleMultipliers = mutableListOf<Int>()
    var i = 2
    var n = n

    if (lesson3.task1.isPrime(n)) return listOf(n)

    while (i <= n.toDouble())
        if (n % i == 0)
        {
            simpleMultipliers.add(i)
            n /= i
        }
        else
        {
            i++
            while (!lesson3.task1.isPrime(i))
                i++
        }

    return simpleMultipliers
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int) = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int>
{
    val list = mutableListOf<Int>()
    var n = n

    do
    {
        list.add(n % base)
        n /= base
    }
    while (n != 0)

    return list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun addNewSymbol(resultStr : StringBuilder, x : Int)
{
    val resultStr = resultStr
    val number : Char

    if (x >= 10)
    {
        number = ('a'.toInt() + x - 10).toChar()
        resultStr.insert(0, number)
    }
    else
        resultStr.insert(0, x)
}

fun convertToString(n: Int, base: Int): String
{
    val resultStr = StringBuilder("")
    var n = n


    while (n >= base)
    {
        addNewSymbol(resultStr, n % base)

        n /= base
    }

    addNewSymbol(resultStr, n)

    return resultStr.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int
{
    var result = 0.0

    for (i in 0 until digits.size)
        result += (digits[i] * pow(base.toDouble(), (digits.size - i - 1).toDouble()))

    return result.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int
{
    var result = 0.0

    for (i in 0 until str.length)
    {
        result += if (str[i].toInt() in '0'.toInt()..'9'.toInt())
         (str[i].toInt() - '0'.toInt()) * pow(base.toDouble(), (str.length - i - 1).toDouble())
        else
         (str[i].toInt() - 'a'.toInt() + 10) * pow(base.toDouble(), (str.length - i - 1).toDouble())
    }

    return result.toInt()
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun newDigit(type: String, x: Int, result: StringBuilder)
{
    val romanDigits = mapOf("hundreds" to "CDM",
                            "decades" to "XLC",
                            "units" to "IVX")
    val result = result

    when (x)
    {
        1, 2, 3 -> repeat(x) {result.append(romanDigits[type]!![0])}

        4 -> {
                result.append(romanDigits[type]!![0])
                result.append(romanDigits[type]!![1])
             }

        5 -> result.append(romanDigits[type]!![1])

        6, 7, 8 ->
        {
            result.append(romanDigits[type]!![1])
            repeat(x - 5) {result.append(romanDigits[type]!![0])}
        }

        9 -> {
                result.append(romanDigits[type]!![0])
                result.append(romanDigits[type]!![2])
             }
    }
}

fun roman(n: Int): String
{
    val result = StringBuilder("")

    for (i in 1..n / 1000)
        result.append('M')

    newDigit("hundreds", (n % 1000) / 100, result)
    newDigit("decades",(n % 100) / 10, result)
    newDigit("units",n % 10, result)

    return result.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = TODO()