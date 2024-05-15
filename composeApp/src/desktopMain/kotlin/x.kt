
fun x0(): Boolean {
    println("был x0")
    return run
}

fun x1(): Boolean {
    println("был x1")
    //A=0
    return A.value == 0U
}

fun x2(): Boolean {
    println("был x2")
    //B=0
    return B.value == 0U
}

fun x3(): Boolean {
    println("был x3")
    //A(15) == 0
    return (((A.value shr 15) % 2U) == 1U)
}

fun x4(): Boolean {
    println("был x4")
    //B(0)
    return (BM.value % 2U == 1U)
}

fun x5(): Boolean {
    println("был x5")
    //B(0) ⊕ B(1)
    val b0 = (BM.value % 2U == 1U) // B(0)
    val b1 = (((BM.value shr 1) % 2U) == 1U) // B(1)
    //Операция XOR возвращает true (или 1), если один из операндов равен true (или 1), но не оба.
    val result = b0 xor b1
    return result
}

fun x6(): Boolean {
    println("был x6")
    // B(1)
    return (((BM.value shr 1) % 2U) == 1U)
}

fun x7(): Boolean {
    println("был x7")
    // CR = 0
    return CR.value == 0.toByte()
}

fun x8(): Boolean {
    println("был x8")
    // C(31)
    return (((C.value shr 31) % 2UL) == 1UL)
}

fun x9(): Boolean {
    println("был x9")
    // C(14)
    return (((C.value shr 14) % 2UL) == 1UL)
}

fun x10(): Boolean {
    println("был x10")
    // C(31)
    return (((C.value shr 31) % 2UL) == 1UL)
}