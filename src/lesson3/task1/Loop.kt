@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import kotlin.math.PI
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int
{
    if (n == 0) return 1

    var cpy = n
    var number = 0

    while (cpy > 0)
    {
        number++
        cpy /= 10
    }

    return number
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int
{
    if (n == 0) return 0
    if (n == 1 || n == 2) return 1

    return fib(n - 1) + fib(n - 2)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int
{
    var a : Int = m
    var b : Int = n
    val nod : Int

    while (true)
    {
        a %= b
        if (a == 0)
        {
            nod = b
            break
        }
        b %= a
        if (b == 0)
        {
            nod = a
            break
        }
    }

    return m / nod * n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int
{
    for (i in 2..n / 2)
        if (n % i == 0) return i

    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int
{
    for (i in n / 2 downTo 2)
        if (n % i == 0) return i

    return 1
}


/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean
{
    if (m % n == 0 || n % m == 0) return false

    for (i in 2..m / 2)
        if (m % i == 0 && n % i == 0) return false

    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean
{
    var tmp : Long = 1

    while (tmp * tmp < m)
        tmp++

    return if (tmp * tmp <= n) true else false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int
{
    if (x == 1) return 0

    var numberSteps = 0
    var result = x

    while (true)
    {
        if (result % 2 == 0)
        {
            result /= 2
            numberSteps++
        }
        else
        {
            result = 3 * result + 1
            numberSteps++
        }

        if (result == 1) return numberSteps
    }
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double
{
    val X = x % (2 * PI)
    var numerator = X
    var denominator = 1.0
    var result = 0.0
    var sign = 1.0
    var tmp = 2.0

    while (numerator / denominator >= eps)
    {
        result += sign * numerator / denominator

        sign *= -1.0
        numerator *= X * X
        denominator *= tmp * (tmp + 1.0)
        tmp += 2.0
    }

    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double
{
    val X = x % (2 * PI)
    var numerator = X * X
    var denominator = 2.0
    var result = 1.0
    var sign = -1.0
    var tmp = 3.0

    while (numerator / denominator >= eps)
    {
        result += sign * numerator / denominator

        sign *= -1.0
        numerator *= X * X
        denominator *= tmp * (tmp + 1.0)
        tmp += 2.0
    }

    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int
{
    var cpy = n / 10
    var result = n % 10

    while (cpy > 0)
    {
        result = result * 10 + cpy % 10
        cpy /= 10
    }

    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean
{
    var cpy = n
    var numberDigits = 0
    var firstDigit : Int
    var endDigit : Int

    while (cpy > 0)
    {
        numberDigits++
        cpy /= 10
    }

    cpy = n
    while (numberDigits > 1)
    {
        endDigit = cpy % 10
        firstDigit = cpy / java.lang.Math.pow(10.0, (numberDigits - 1).toDouble()).toInt()

        if (endDigit != firstDigit) return false

        cpy %= java.lang.Math.pow(10.0, (numberDigits - 1).toDouble()).toInt()
        cpy /= 10
        numberDigits -= 2
    }

    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean
{
    if (n in 0..9) return false

    val endNumber = n % 10
    var cpy = n / 10

    while (cpy > 0)
    {
        if (cpy % 10 != endNumber) return true
        cpy /= 10
    }

    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int
{
    var count = 0

    var number = 0
    var squareNumber : Int

    var countDigitsInNumber : Int

    val numberOfResultDigit : Int

    while (true)
    {
        number++
        squareNumber = number * number

        var cpy = squareNumber
        countDigitsInNumber = 0
        while (cpy > 0)
        {
            countDigitsInNumber++
            cpy /= 10
        }

        count += countDigitsInNumber
        if (count >= n)
        {
            numberOfResultDigit = countDigitsInNumber - (count - n)

            while (numberOfResultDigit != countDigitsInNumber)
            {
                squareNumber /= 10
                countDigitsInNumber--
            }

            return squareNumber % 10
        }
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int
{
    var count = 0

    var first = 0
    var second = 1
    var number = 1

    var countDigitsInNumber : Int

    val numberOfResultDigit : Int

    while (true)
    {
        var cpy = number
        countDigitsInNumber = 0
        while (cpy > 0)
        {
            countDigitsInNumber++
            cpy /= 10
        }

        count += countDigitsInNumber
        if (count >= n)
        {
            numberOfResultDigit = countDigitsInNumber - (count - n)

            while (numberOfResultDigit != countDigitsInNumber)
            {
                number /= 10
                countDigitsInNumber--
            }

            return number % 10
        }
        else
        {
            number = first + second
            first = second
            second = number
        }
    }
}

