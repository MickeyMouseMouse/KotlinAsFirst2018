@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String
{
    return when (((age % 100 >= 10) && (age % 100 <= 20)) || age % 10 == 0 || age % 10 > 4)
    {
        true -> age.toString() + " лет"
        else -> when (age % 10 == 1)
                {
                    true -> age.toString() + " год"
                    else -> age.toString() + " года"
                }
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double
{
    val s = (v1 * t1 + v2 * t2 + v3 * t3) / 2
    val s1 = v1 * t1

    if (s1 < s)
    {
        val s2 = v2 * t2

        if (s1 + s2 < s)
        {
            return t1 + t2 + (s - s1 - s2) / v3
        }
        else
        {
            return t1 + (s - s1) / v2
        }
    }
    else
    {
        return s / v1
    }

}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int
{
    var rook1Beats = false
    var rook2Beats = false

    if (rookX1 == kingX || rookY1 == kingY) rook1Beats = true
    if (rookX2 == kingX || rookY2 == kingY) rook2Beats = true

    if (rook1Beats && rook2Beats) return 3
    if (rook1Beats) return 1
    return if (rook2Beats) 2 else 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int
{
    var rookBeats = false
    var bishopBeats = false

    if (rookX == kingX || rookY == kingY) rookBeats = true
    if (abs(bishopX - kingX) == abs(bishopY - kingY)) bishopBeats = true

    if (rookBeats && bishopBeats) return 3
    if (rookBeats) return 1
    return if (bishopBeats) 2 else 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int
{
    if (a + b <= c || a + c <= b || b + c <= a) return -1

    //Law of cosines
    val cosAngle1 = (a * a - b * b - c * c) / (-2 * b * c)
    val cosAngle2 = (b * b - a * a - c * c) / (-2 * a * c)
    val cosAngle3 = (c * c - b * b - a * a) / (-2 * b * a)

    if (cosAngle1 < 0 || cosAngle2 < 0 || cosAngle3 < 0) return 2
    return if (cosAngle1 > 0 && cosAngle2 > 0 && cosAngle3 > 0) 0 else 1

}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int
{
    if (c in a..b && b <= d) return b - c
    if (d in a..b && c <= a) return d - a
    if (c in a..b && d in a..b) return d - c
    return if (a in c..d && b in c..d) b - a else -1
}
