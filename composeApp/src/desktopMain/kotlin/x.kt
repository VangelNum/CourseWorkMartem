
fun x0(): Boolean {
    return run.value
}

fun x1(): Boolean {
    //A=0
    return A.value == 0U
}

fun x2(): Boolean {
    //B=0
    return B.value == 0U
}

fun x3(): Boolean {
    //A(15) == 0
    return (((A.value shr 15) % 2U) == 1U)
}

fun x4(): Boolean {
    //B(0)
    return (BM.value % 2U == 1U)
}

fun x5(): Boolean {
    //B(0) ⊕ B(1)
    val b0 = (BM.value % 2U == 1U) // B(0)
    val b1 = (((BM.value shr 1) % 2U) == 1U) // B(1)
    //Операция XOR возвращает true (или 1), если один из операндов равен true (или 1), но не оба.
    val result = b0 xor b1
    return result
}

fun x6(): Boolean {
    // B(1)
    return (((BM.value shr 1) % 2U) == 1U)
}

fun x7(): Boolean {
    // CR = 0
    return CR.value == 0.toByte()
}

fun x8(): Boolean {
    // C(31)
    return (((C.value shr 31) % 2UL) == 1UL)
}

fun x9(): Boolean {
    // C(14)
    return (((C.value shr 14) % 2UL) == 1UL)
}

fun x10(): Boolean {
    // C(31)
    return (((C.value shr 31) % 2UL) == 1UL)
}