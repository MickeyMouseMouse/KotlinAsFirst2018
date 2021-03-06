@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String>
{
    val result = mutableMapOf<String, String>()

    for ((name, phone) in mapA)
        result[name] = phone

    for ((name, phone) in mapB)
        if ((name) in result)
        {
            if (result[name] != phone)
                result[name] = result[name] + ", $phone"
        }
        else
            result[name] = phone

    return result
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>>
{
    val result = mutableMapOf<Int, List<String>>()

    for ((name, grade) in grades)
        if (grade in result)
            result[grade] = result[grade]!!.plus(name)
        else
            result[grade] = listOf(name)

    for ((grade) in result)
        result[grade] = result[grade]!!.sortedDescending()

    return result
}


/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean
{
    for ((first, second) in a)
        if (first !in b || b[first] != second) return false

    return true
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double>
{
    val result = mutableMapOf<String, Double>()
    val number = mutableMapOf<String, Int>()

    for (element in stockPrices)
    {
        if (element.first in number)
            number[element.first] = number[element.first] !!+ 1
        else
            number[element.first] = 1
    }

    for (element in stockPrices)
    {
        if (element.first in result)
            result[element.first] = result[element.first] !!+
                    element.second / number[element.first]!!.toInt()
        else
            result[element.first] = element.second / number[element.first]!!.toInt()
    }

    return result        
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String?
{
    var cheapeastCost = Double.MAX_VALUE
    var result : String? = null

    for ((name, pair) in stuff)
        if (pair.first == kind && pair.second <= cheapeastCost)
        {
            if (cheapeastCost != pair.second)
            {
                cheapeastCost = pair.second
                result = name
            }
            else
            {
                if (result == null)
                    result = name
            }
        }

    return result
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun findAllHandshakes(friends: Map<String, Set<String>>,
                      result: MutableMap<String, MutableSet<String>>,
                      withOutFriends: MutableMap<String, MutableSet<String>>,
                      nameI: String,
                      nameFriend: String)
{
        if (friends.contains(nameFriend))
        {
            for (i in 0 until friends[nameFriend]!!.size)
                if (friends[nameFriend]!!.elementAt(i) != nameI &&
                    friends[nameFriend]!!.elementAt(i) !in result[nameI]!!)
                {
                    result[nameI]!!.add(friends[nameFriend]!!.elementAt(i))

                    findAllHandshakes(friends, result, withOutFriends, nameI,
                                      friends[nameFriend]!!.elementAt(i))
                }
        }
        else
            withOutFriends[nameFriend] = mutableSetOf()
}

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>>
{
    val result = mutableMapOf<String, MutableSet<String>>()
    val withOutFriends = mutableMapOf<String, MutableSet<String>>()

    for ((nameI) in friends)
    {
        result[nameI] = mutableSetOf()
        result[nameI]?.addAll(friends[nameI]!!)
    }

    for ((nameI) in friends)
        for (i in 0 until friends[nameI]!!.size)
            findAllHandshakes(friends, result, withOutFriends, nameI,
                              friends[nameI]!!.elementAt(i))

    return result + withOutFriends
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>)
{
    for ((firstStringB, secondStringB) in b)
        if (a[firstStringB] == secondStringB) a.remove(firstStringB)
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String>
{
    val result = ArrayList<String>()

    for (nameA in a)
        if (nameA in b && nameA !in result) result.add(nameA)

    return result
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean
{
    for (i in 0 until word.length)
        if (word[i].toLowerCase() !in chars && word[i].toUpperCase() !in chars)
            return false

    return true
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int>
{
    val result = mutableMapOf<String, Int>()

    if (list.size == 1) return result

    val list = list.sorted()
    val newList = ArrayList<String>()
    var i = 0
    while (i < list.size)
    {
        if ((i == 0 && list[i] == list[i + 1]) ||
           (i == list.size - 1 && list[i] == list[i - 1])) newList.add(list[i])

        if (i != 0 && i != list.size - 1)
            if (list[i - 1] == list[i] || list[i] == list[i + 1])
                newList.add(list[i])
        i++
    }

    for (i in 0 until newList.size)
        if (newList[i] in result)
            result[newList[i]] = result[newList[i]] !!+ 1
        else
            result[newList[i]] = 1

    return result
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>) =
        words.size != words.map {it.toList().sorted()}.toSet().size

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int>
{
    val size = list.size
    for (i in 0 until size - 1)
    {
        val tmpNumber = number - list[i]
        if (tmpNumber < 0) continue

        val tmpList = list.toMutableList()
        tmpList[i] = - 1
        if (tmpNumber in tmpList)
        {
            val index = tmpList.indexOf(tmpNumber)

            return Pair(i, index)
        }
    }
    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> = TODO()