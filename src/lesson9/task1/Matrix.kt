@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson9.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E) =
    if (height > 0 && width > 0)
        MatrixImpl(height, width, e)
    else
        throw IllegalArgumentException()

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E>
{
    val matrix = MutableList(height * width){e}

    override fun get(row: Int, column: Int) =
        matrix[row * width + column]

    override fun get(cell: Cell) =
        get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E)
    {
        matrix[row * width + column] = value
    }

    override fun set(cell: Cell, value: E)
    {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?): Boolean
    {
        if (other !is Matrix<*>) return false

        for (i in 0 until height)
            for (j in 0 until width)
                if (get(i, j) != other[i, j]) return false

        return true
    }

    override fun toString(): String
    {
        val result = StringBuilder("")

        for (i in 0 until height)
        {
            for (j in 0 until width)
                result.append(get(i, j).toString() + " ")

            result.append("* ")
        }

        return result.take(result.length - 3).toString()
    }
}

