
fun yStop() {
    run = false
}

fun y0() {
    // Reset C to 0
    C.value = 0UL
    updateListFromBinaryValue(C.value, sectionCResList)
}

fun y1() {
    // Set CR to 15
    CR.value = 15
    updateListFromByteValue(CR.value, registerCounterResData)
}

fun y2() {
    // Set AM(31:30) to A(15) A(15)
    val bit15 = (A.value shr 15) % 2U
    val bit31 = bit15.shl(31)
    val bit30 = bit15.shl(30)
    AM.value = AM.value or bit31 or bit30
    updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
}

fun y3() {
    // AM(29:15): = 0
    AM.value = AM.value and 0xC0007FFFU
    updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
}

fun y4() {
    // AM(14:0) := A(14:0)
    val lower15BitsA = A.value and 0x7FFFU
    AM.value = (AM.value and 0xFFFF8000U) or lower15BitsA
    updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
}

fun y5() {
    // AM(29:15):=A̅M̅(29:15)
    val invertedUpper15BitsAM = AM.value.inv() and 0xFFFF8000U
    AM.value = (AM.value and 0x7FFFU) or invertedUpper15BitsAM
    updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
}

fun y6() {
    // C:=C+A̅M̅+1
    // Calculate inverted A̅M̅
    val sum = C.value + (AM.value.inv().toULong() + 1UL)

    // Update C with the sum
    C.value = sum

    // Update the list sectionCResList
    updateListFromBinaryValue(C.value, sectionCResList)
}

fun y7() {
    // AM:=L1(AM.0)
    // Shift bits of AM one position to the left
    val shiftedAM = (AM.value shl 1)

    // Set the least significant bit of shiftedAM to 0
    val newAM = shiftedAM and 0xFFFFFFFEU
    // Update AM
    AM.value = newAM
    // Update sectionAResList with the new value of AM
    updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
}

fun y8() {
    // Сдвигаем все биты на одну позицию вправо и добавляем слева 0
    BM.value = BM.value shr 1
    updateListFromBinaryValue(BM.value.toULong(), sectionBResList)
}

fun y9() {
    CR.value = (CR.value - 1).toByte()
    updateListFromByteValue(CR.value, registerCounterResData)
}

fun y10() {
    // C:=C+AM
    val result = C.value + AM.value.toULong()
    C.value = result
    updateListFromBinaryValue(C.value, sectionCResList)
}

fun y11() {
    // C(29:0)=C̅(29:0) + 1
    val complementedLower30Bits = C.value.inv() and 0x3FFFFFFFU.toULong()

    // Add one to the complemented value
    val result = complementedLower30Bits + 1U

    // Update C with the result
    C.value = (C.value and 0xC0000000U.toULong()) or result

    // Update sectionCResList with the new value of C
    updateListFromBinaryValue(C.value, sectionCResList)
}

fun y12() {
    // Получаем комплемент верхних 15 бит C(29:15)
//        val complementedBits = C.value.inv() and 0x3FFF8000U.toULong()
//
//        // Добавляем к комплементу единицу
//        val result = complementedBits + 1U
//
//        // Обновляем биты с 16 по 30 переменной C результатом
//        C.value = (C.value and 0xC0007FFFU.toULong()) or result
//
//        // Обновляем sectionCResList новым значением C
//        updateListFromBinaryValue(C.value, sectionCResList)
}


fun y13() {
    // C(30:16) = C̅(30:16) + 1
    val complementedBits = C.value.inv() and 0x3FFF8000U.toULong()

    // Add one to the complemented value
    val result = complementedBits + 1U

    // Update bits 16 to 30 of C with the result
    C.value = (C.value and 0xC0007FFFU.toULong()) or result

    // Update sectionCResList with the new value of C
    updateListFromBinaryValue(C.value, sectionCResList)
}

private fun updateListFromByteValue(value: Byte, list: MutableList<Int>) {
    val binaryString = value.toString(2).padStart(4, '0')
    binaryString.forEachIndexed { index, char ->
        list[index] = char.toString().toInt()
    }
}

private fun updateListFromBinaryValue(value: ULong, list: MutableList<Int>) {
    val binaryString = value.toString(2)

    var i = list.size - 1
    var j = binaryString.length - 1

    while (i >= 0 && j >= 0) {
        list[i] = binaryString[j].toString().toInt()
        i--
        j--
    }
}