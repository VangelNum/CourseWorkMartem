fun yStop() {
    run.value = false
}

fun y0() {
    // C:=0
    C.value = 0UL
    updateListFromBinaryValue(C.value, sectionCResList)
}

fun y1() {
    // СЧ:=15
    CR.value = 15
    updateListFromByteValue(CR.value, registerCounterResData)
}

fun y2() {
    // AM(31:30):=A(15) A(15)
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
    val sum = C.value + (AM.value.inv().toULong() + 1UL)
    C.value = sum
    updateListFromBinaryValue(C.value, sectionCResList)
}

fun y7() {
    // AM:=L1(AM.0)
    val shiftedAM = (AM.value shl 1)
    val newAM = shiftedAM and 0xFFFFFFFEU
    AM.value = newAM
    updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
}

fun y8() {
    // BM:=R1(0.BM)
    BM.value = BM.value shr 1
    sectionBResList.clear()
    val binaryString = BM.value.toString(2).padStart(16, '0')
    binaryString.forEach {
        sectionBResList.add(it.toString().toInt())
    }
}

fun y9() {
    // CЧ:=CЧ-1
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
    val result = complementedLower30Bits + 1U
    C.value = (C.value and 0xC0000000U.toULong()) or result
    updateListFromBinaryValue(C.value, sectionCResList)
}

fun y12() {
    val complementedBits = C.value.inv() and 0x3FFF8000U.toULong()
    val result = complementedBits + 1U
    C.value = (C.value and 0xC0007FFFU.toULong()) or result
    updateListFromBinaryValue(C.value, sectionCResList)
}


fun y13() {
    // C(30:16) = C̅(30:16) + 1
    val complementedBits = C.value.inv() and 0x3FFF8000U.toULong()
    val result = complementedBits + 1U
    C.value = (C.value and 0xC0007FFFU.toULong()) or result
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