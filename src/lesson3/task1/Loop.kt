@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1
import java.lang.Math.*


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
    var cpy = abs(n)
    var number = 0

    do
    {
        number++
        cpy /= 10
    }
    while (cpy > 0)

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
fun gcd (m : Int, n : Int) : Int
{
    var a = m
    var b = n
    val result : Int

    while (true)
    {
        a %= b
        if (a == 0)
        {
            result = b
            break
        }
        b %= a
        if (b == 0)
        {
            result = a
            break
        }
    }

    return result
}

fun lcm(m: Int, n: Int): Int
{
    return m / gcd(m, n) * n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int
{
    for (i in 2..(sqrt(n.toDouble())).toInt())
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
fun isCoPrime(m: Int, n: Int) = gcd(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean
{
    for (i in ceil(sqrt(m.toDouble())).toInt()..floor(sqrt(n.toDouble())).toInt())
        if (i * i in m..n) return true

    return false
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
fun count(x: Double, eps: Double, result: Double, numerator: Double,
          denominator: Double, sign: Double, tmp: Double): Double
{

    var result = result
    var numerator = numerator
    var denominator = denominator
    var sign = sign
    var tmp = tmp

    while (abs(numerator / denominator) >= eps)
    {
        result += sign * numerator / denominator

        sign *= -1.0
        numerator *= x * x
        denominator *= tmp * (tmp + 1.0)
        tmp += 2.0
    }

    return result
}

fun sin(x: Double, eps: Double) =
count(x % (2 * PI), eps, 0.0,x % (2 * PI), 1.0, 1.0, 2.0)

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double) =
count(x % (2 * PI), eps, 1.0,(x % (2 * PI)) * (x % (2 * PI)), 2.0, -1.0, 3.0)

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
        firstDigit = cpy / pow(10.0, (numberDigits - 1).toDouble()).toInt()

        if (endDigit != firstDigit) return false

        cpy %= pow(10.0, (numberDigits - 1).toDouble()).toInt()
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
fun countDigits(value: Int): Int
{
    var x = value
    var result = 0

    do
    {
        result++
        x /= 10
    }
    while (x > 0)

    return result
}

fun countResult(countDigitsInNumber : Int, count : Int, n  : Int, squareNumber : Int) : Int
{
    val numberOfResultDigit : Int
    var squareNumber = squareNumber
    var countDigitsInNumber = countDigitsInNumber

    numberOfResultDigit = countDigitsInNumber - (count - n)

    while (numberOfResultDigit != countDigitsInNumber)
    {
        squareNumber /= 10
        countDigitsInNumber--
    }

    return squareNumber % 10
}

fun squareSequenceDigit(n: Int): Int
{
    var count = 0

    var number = 0
    var squareNumber : Int

    var countDigitsInNumber : Int

    while (true)
    {
        number++
        squareNumber = number * number

        countDigitsInNumber = countDigits(squareNumber)

        count += countDigitsInNumber
        if (count >= n)
            return countResult(countDigitsInNumber, count, n, squareNumber)
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

    while (true)
    {
        countDigitsInNumber = countDigits(number)

        count += countDigitsInNumber
        if (count >= n)
            return countResult(countDigitsInNumber, count, n, number)

        number = first + second
        first = second
        second = number
    }
}

