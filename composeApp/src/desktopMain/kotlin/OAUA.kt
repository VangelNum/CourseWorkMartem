import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

var Q = mutableStateListOf<Boolean>().apply { repeat(3) { add(false) } }
val D_out = mutableStateListOf<Boolean>().apply { repeat(3) { add(false) } }
val a_out = mutableStateListOf<Boolean>().apply { repeat(9) { add(false) } }
val T = mutableStateListOf<Boolean>().apply { repeat(24) { add(false) } }
val Y = mutableStateListOf<Boolean>().apply { repeat(14) { add(false) } }
val X = mutableStateListOf<Boolean>().apply { repeat(11) { add(false) } }
val X_PLY = mutableStateListOf<Boolean>().apply { repeat(3) { add(false) } }

fun PLY() {
    println("PLY START")
    X_PLY[0] = X[4]
    X_PLY[1] = X[5]
    X_PLY[2] = X[6]
    X_PLY.forEach {
        print("$it ")
    }
}

fun PS() {
    println("PS START")
    Q.clear()
    Q.addAll(D_out)
    Q.forEach {
        print("$it ")
    }
    println()
}

fun DCH() {
    println("DCH START")
    var a = 0
    if (Q[0]) a += 1
    if (Q[1]) a += 2
    if (Q[2]) a += 4
    a_out.fill(false)
    a_out[a] = true

    println("State after DCH: $a")
    println()
}

fun OU_T() {
    println("OUT_T START")
    T.fill(false)
    if (t0()) T[0] = true
    if (t1()) T[1] = true
    if (t2()) T[2] = true
    if (t3()) T[3] = true
    if (t4()) T[4] = true
    if (t5()) T[5] = true
    if (t6()) T[6] = true
    if (t7()) T[7] = true
    if (t8()) T[8] = true
    if (t9()) T[9] = true
    if (t10()) T[10] = true
    if (t11()) T[11] = true
    if (t12()) T[12] = true
    if (t13()) T[13] = true
    if (t14()) T[14] = true
    if (t15()) T[15] = true
    if (t16()) T[16] = true
    if (t17()) T[17] = true
    if (t18()) T[18] = true
    if (t19()) T[19] = true
    if (t20()) T[20] = true
    if (t21()) T[21] = true
    if (t22()) T[22] = true
    if (t23()) T[23] = true
    println("T AFTER REF")
    T.forEach {
        print("$it ")
    }
    println()
}

fun CSY() {
    println("CSY START")
    Y.fill(false)

    if (T[1] || T[2]) {
        Y[0] = true
    }

    if (T[3]) {
        Y[0] = true
        Y[1] = true
        Y[2] = true
        Y[3] = true
        Y[4] = true
    }

    if (T[4]) {
        Y[5] = true
    }

    if (T[5]) {
        Y[6] = true
        Y[7] = true
    }

    if (T[6]) {
        Y[7] = true
    }

    if (T[7]) {
        Y[6] = true
        Y[7] = true
    }

    if (T[8]) {
        Y[7] = true
    }

    if (T[9]) {
        Y[7] = true
        Y[8] = true
        Y[9] = true
    }

    if (T[10]) {
        Y[6] = true
        Y[7] = true
        Y[8] = true
        Y[9] = true
    }

    if (T[11]) {
        Y[10] = true
        Y[7] = true
        Y[8] = true
        Y[9] = true
    }

    if (T[12]) {
        Y[7] = true
        Y[8] = true
        Y[9] = true
    }

    if (T[13]) {
        Y[6] = true
        Y[7] = true
        Y[8] = true
        Y[9] = true
    }

    if (T[14]) {
        Y[10] = true
        Y[7] = true
        Y[8] = true
        Y[9] = true
    }

    if (T[15]) {
        Y[11] = true
    }

    if (T[16]) {
        Y[12] = true
    }

    if (T[17]) {
        Y[13] = true
    }

    if (T[18]) {
        endMP()
    }

    if (T[19]) {
        Y[12] = true
    }

    if (T[20]) {
        Y[13] = true
    }

    if (T[21]) {
        endMP()
    }

    if (T[22]) {
        Y[13] = true
    }

    if (T[23]) {
        endMP()
    }

    println("IN CSY Y")
    Y.forEach {
        print("$it ")
    }
    println()
}

fun OA() {
        if (Y[0]) y0()
        if (Y[1]) y1()
        if (Y[2]) y2()
        if (Y[3]) y3()
        if (Y[4]) y4()
        if (Y[5]) y5()
        if (Y[6]) y6()
        if (Y[10]) y10()
        if (Y[7]) y7()
        if (Y[8]) y8()
        if (Y[9]) y9()
        if (Y[11]) y11()
        if (Y[12]) y12()
        if (Y[13]) y13()
        X[0] = x0()
        X[1] = x1()
        X[2] = x2()
        X[3] = x3()
        X[4] = x4()
        X[5] = x5()
        X[6] = x6()
        X[7] = x7()
        X[8] = x8()
        X[9] = x9()
        X[10] = x10()
}

fun t0(): Boolean = a_out[0] && !X[0]
fun t1(): Boolean = a_out[0] && X[0] && X[1]
fun t2(): Boolean = a_out[0] && X[0] && !X[1] && X[2]
fun t3(): Boolean = a_out[0] && X[0] && !X[1] && !X[2]

fun t4(): Boolean = a_out[1] && X[3]
fun t5(): Boolean = a_out[1] && !X[3] && X_PLY[0]
fun t6(): Boolean = a_out[1] && !X[3] && !X_PLY[0]

fun t7(): Boolean = a_out[2] && X_PLY[0]
fun t8(): Boolean = a_out[2] && !X_PLY[0]

fun t9(): Boolean = a_out[3] && !X_PLY[1]
fun t10(): Boolean = a_out[3] && X_PLY[1] && X_PLY[2]
fun t11(): Boolean = a_out[3] && X_PLY[1] && !X_PLY[2]

fun t12(): Boolean = a_out[4] && !X[7] && !X[5]
fun t13(): Boolean = a_out[4] && !X[7] && X[5] && X[6]
fun t14(): Boolean = a_out[4] && !X[7] && X[5] && !X[6]

fun t15(): Boolean = a_out[4] && X[7] && X[8]
fun t16(): Boolean = a_out[4] && X[7] && !X[8] && X[9]
fun t17(): Boolean = a_out[4] && X[7] && !X[8] && !X[9] && X[10]
fun t18(): Boolean = a_out[4] && X[7] && !X[8] && !X[9] && !X[10]

fun t19(): Boolean = a_out[5] && X[9]
fun t20(): Boolean = a_out[5] && !X[9] && X[10]
fun t21(): Boolean = a_out[5] && !X[9] && !X[10]

fun t22(): Boolean = a_out[6] && X[10]
fun t23(): Boolean = a_out[6] && !X[10]

fun CSD() {
    D_out.fill(false)
    D_out[0] =  T[1] || T[3] || T[5] || T[6] || T[8] || T[11]|| T[10]
    D_out[1] =  T[4] || T[10] || T[11] || T[6]
    D_out[2] = T[7] || T[8] || T[9] || T[12] || T[13] || T[14]
    println("CSD START")
    D_out.forEach {
        print("$it ")
    }
}

fun isEnd(): Boolean {
    var a_q = 0
    if (Q[0]) a_q += 1
    if (Q[1]) a_q += 2
    if (Q[2]) a_q += 4

    var a_d = 0
    if (D_out[0]) a_d += 1
    if (D_out[1]) a_d += 2
    if (D_out[2]) a_d += 4

    return !(a_d == 0 && a_q != 0)
}

fun startUAOA(): Boolean {
    PLY()

    PS()
    DCH()

    OU_T()
    CSY()
    OA()

    OU_T()
    CSD()
    return isEnd()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OAUACheckbox() {
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X_PLY[0], onCheckedChange = {})
                    Text("X4")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X_PLY[1], onCheckedChange = {})
                    Text("X5")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X_PLY[2], onCheckedChange = {})
                    Text("X6")
                }
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Q[0], onCheckedChange = {})
                    Text("Q0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Q[1], onCheckedChange = {})
                    Text("Q1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Q[2], onCheckedChange = {})
                    Text("Q2")
                }
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Checkbox(checked = Q[3], onCheckedChange = {})
//                    Text("Q3")
//                }
            }

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = a_out[0], onCheckedChange = {})
                    Text("a0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = a_out[1], onCheckedChange = {})
                    Text("a1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = a_out[2], onCheckedChange = {})
                    Text("a2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = a_out[3], onCheckedChange = {})
                    Text("a3")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = a_out[4], onCheckedChange = {})
                    Text("a4")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = a_out[5], onCheckedChange = {})
                    Text("a5")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = a_out[6], onCheckedChange = {})
                    Text("a6")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = a_out[7], onCheckedChange = {})
                    Text("a7")
                }
            }

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[0], onCheckedChange = {})
                    Text("t0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[1], onCheckedChange = {})
                    Text("t1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[2], onCheckedChange = {})
                    Text("t2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[3], onCheckedChange = {})
                    Text("t3")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[4], onCheckedChange = {})
                    Text("t4")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[5], onCheckedChange = {})
                    Text("t5")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[6], onCheckedChange = {})
                    Text("t6")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[7], onCheckedChange = {})
                    Text("t7")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[8], onCheckedChange = {})
                    Text("t8")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[9], onCheckedChange = {})
                    Text("t9")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[10], onCheckedChange = {})
                    Text("t10")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[11], onCheckedChange = {})
                    Text("t11")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[12], onCheckedChange = {})
                    Text("t12")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[13], onCheckedChange = {})
                    Text("t13")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[14], onCheckedChange = {})
                    Text("t14")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[15], onCheckedChange = {})
                    Text("t15")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[16], onCheckedChange = {})
                    Text("t16")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[17], onCheckedChange = {})
                    Text("t17")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[18], onCheckedChange = {})
                    Text("t18")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[19], onCheckedChange = {})
                    Text("t19")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[20], onCheckedChange = {})
                    Text("t20")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[21], onCheckedChange = {})
                    Text("t21")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[22], onCheckedChange = {})
                    Text("t22")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = T[23], onCheckedChange = {})
                    Text("t23")
                }
            }

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[0], onCheckedChange = {})
                    Text("Y0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[1], onCheckedChange = {})
                    Text("Y1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[2], onCheckedChange = {})
                    Text("Y2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[3], onCheckedChange = {})
                    Text("Y3")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[4], onCheckedChange = {})
                    Text("Y4")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[5], onCheckedChange = {})
                    Text("Y5")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[6], onCheckedChange = {})
                    Text("Y6")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[7], onCheckedChange = {})
                    Text("Y7")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[8], onCheckedChange = {})
                    Text("Y8")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[9], onCheckedChange = {})
                    Text("Y9")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[10], onCheckedChange = {})
                    Text("Y10")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[11], onCheckedChange = {})
                    Text("Y11")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[12], onCheckedChange = {})
                    Text("Y12")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = Y[13], onCheckedChange = {})
                    Text("Y13")
                }
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = D_out[0], onCheckedChange = {})
                    Text("D0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = D_out[1], onCheckedChange = {})
                    Text("D1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = D_out[2], onCheckedChange = {})
                    Text("D2")
                }
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Checkbox(checked = D_out[3], onCheckedChange = {})
//                    Text("D3")
//                }
            }

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[0], onCheckedChange = {})
                    Text("X0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[1], onCheckedChange = {})
                    Text("X1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[2], onCheckedChange = {})
                    Text("X2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[3], onCheckedChange = {})
                    Text("X3")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[4], onCheckedChange = {})
                    Text("X4")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[5], onCheckedChange = {})
                    Text("X5")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[6], onCheckedChange = {})
                    Text("X6")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[7], onCheckedChange = {})
                    Text("X7")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[8], onCheckedChange = {})
                    Text("X8")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[9], onCheckedChange = {})
                    Text("X9")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = X[10], onCheckedChange = {})
                    Text("X10")
                }
            }
        }
    }
}