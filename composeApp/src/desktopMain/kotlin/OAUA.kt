import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OAUAScreen() {
    var xPLY by remember { mutableStateOf(Array(2) { false }) }
    var q by remember { mutableStateOf(Array(4) { false }) }
    var aOut by remember { mutableStateOf(Array(9) { false }) }
    var t by remember { mutableStateOf(Array(18) { false }) }
    var y by remember { mutableStateOf(Array(17) { false }) }
    var dOut by remember { mutableStateOf(Array(4) { false }) }
    var x by remember { mutableStateOf(Array(7) { false }) }

    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = xPLY[0], onCheckedChange = {})
                    Text("X2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = xPLY[1], onCheckedChange = {})
                    Text("X3")
                }
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = q[0], onCheckedChange = {})
                    Text("Q0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = q[1], onCheckedChange = {})
                    Text("Q1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = q[2], onCheckedChange = {})
                    Text("Q2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = q[3], onCheckedChange = {})
                    Text("Q3")
                }
            }

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = aOut[0], onCheckedChange = {})
                    Text("a0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = aOut[1], onCheckedChange = {})
                    Text("a1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = aOut[2], onCheckedChange = {})
                    Text("a2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = aOut[3], onCheckedChange = {})
                    Text("a3")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = aOut[4], onCheckedChange = {})
                    Text("a4")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = aOut[5], onCheckedChange = {})
                    Text("a5")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = aOut[6], onCheckedChange = {})
                    Text("a6")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = aOut[7], onCheckedChange = {})
                    Text("a7")
                }
            }

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[0], onCheckedChange = {})
                    Text("t0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[1], onCheckedChange = {})
                    Text("t1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[2], onCheckedChange = {})
                    Text("t2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[3], onCheckedChange = {})
                    Text("t3")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[4], onCheckedChange = {})
                    Text("t4")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[5], onCheckedChange = {})
                    Text("t5")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[6], onCheckedChange = {})
                    Text("t6")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[7], onCheckedChange = {})
                    Text("t7")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[8], onCheckedChange = {})
                    Text("t8")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[9], onCheckedChange = {})
                    Text("t9")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[10], onCheckedChange = {})
                    Text("t10")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[11], onCheckedChange = {})
                    Text("t11")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[12], onCheckedChange = {})
                    Text("t12")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[13], onCheckedChange = {})
                    Text("t13")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[14], onCheckedChange = {})
                    Text("t14")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[15], onCheckedChange = {})
                    Text("t15")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[16], onCheckedChange = {})
                    Text("t16")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = t[17], onCheckedChange = {})
                    Text("t17")
                }
            }

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[0], onCheckedChange = {})
                    Text("Y0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[1], onCheckedChange = {})
                    Text("Y1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[2], onCheckedChange = {})
                    Text("Y2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[3], onCheckedChange = {})
                    Text("Y3")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[4], onCheckedChange = {})
                    Text("Y4")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[5], onCheckedChange = {})
                    Text("Y5")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[6], onCheckedChange = {})
                    Text("Y6")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[7], onCheckedChange = {})
                    Text("Y7")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[8], onCheckedChange = {})
                    Text("Y8")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[9], onCheckedChange = {})
                    Text("Y9")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[10], onCheckedChange = {})
                    Text("Y10")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[11], onCheckedChange = {})
                    Text("Y11")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[12], onCheckedChange = {})
                    Text("Y12")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[13], onCheckedChange = {})
                    Text("Y13")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[14], onCheckedChange = {})
                    Text("Y14")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[15], onCheckedChange = {})
                    Text("Y15")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = y[16], onCheckedChange = {})
                    Text("Y16")
                }
            }

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = dOut[0], onCheckedChange = {})
                    Text("D0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = dOut[1], onCheckedChange = {})
                    Text("D1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = dOut[2], onCheckedChange = {})
                    Text("D2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = dOut[3], onCheckedChange = {})
                    Text("D3")
                }
            }

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = x[0], onCheckedChange = {})
                    Text("X0")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = x[1], onCheckedChange = {})
                    Text("X1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = x[2], onCheckedChange = {})
                    Text("X2")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = x[3], onCheckedChange = {})
                    Text("X3")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = x[4], onCheckedChange = {})
                    Text("X4")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = x[5], onCheckedChange = {})
                    Text("X5")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = x[6], onCheckedChange = {})
                    Text("X6")
                }
            }
        }

    }


    val Q = mutableListOf<Boolean>()
    val D_out = mutableListOf<Boolean>()
    var a_out = mutableListOf<Boolean>()
    val T = mutableListOf<Boolean>()
    val Y = mutableListOf<Boolean>()
    val X = mutableListOf<Boolean>()
    val X_PLY = mutableListOf<Boolean>()


    fun PLY() {
        X_PLY[0] = X[2]
        X_PLY[1] = X[3]
        println("x_ply $X_PLY")
    }

    fun PS() {
//        Q = D_out.toMutableList()
        println("Q $Q")
    }

    fun DCH() {
        var a = 0
        if (Q[0]) a += 1
        if (Q[1]) a += 2
        if (Q[2]) a += 4
        if (Q[3]) a += 8
        a_out = MutableList(9) { false }
        a_out[a] = true
    }

    fun isEnd(): Boolean {
        var a_q = 0
        if (Q[0]) a_q += 1
        if (Q[1]) a_q += 2
        if (Q[2]) a_q += 4
        if (Q[3]) a_q += 8

        var a_d = 0
        if (D_out[0]) a_d += 1
        if (D_out[1]) a_d += 2
        if (D_out[2]) a_d += 4
        if (D_out[3]) a_d += 8

        return !(a_d == 0 && a_q != 0)
    }

    fun t0(): Boolean = aOut[0] && !x[0]
    fun t1(): Boolean = aOut[0] && x[0]
    fun t2(): Boolean = aOut[1] && x[1]
    fun t3(): Boolean = aOut[1] && !x[1] && xPLY[0]
    fun t4(): Boolean = aOut[1] && !x[1] && !xPLY[0]
    fun t5(): Boolean = aOut[2] && !xPLY[1]
    fun t6(): Boolean = aOut[2] && xPLY[1]
    fun t7(): Boolean = aOut[3]
    fun t8(): Boolean = aOut[4]
    fun t9(): Boolean = aOut[5] && xPLY[1]
    fun t10(): Boolean = aOut[5] && !xPLY[1]
    fun t11(): Boolean = aOut[6]
    fun t12(): Boolean = aOut[7] && !x[4]
    fun t13(): Boolean = aOut[7] && x[4] && x[5]
    fun t14(): Boolean = aOut[7] && x[4] && !x[5] && x[6]
    fun t15(): Boolean = aOut[7] && x[4] && !x[5] && !x[6]
    fun t16(): Boolean = aOut[8] && x[6]
    fun t17(): Boolean = aOut[8] && !x[6]

    fun OU_T() {
        val T = BooleanArray(18) { false }
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
    }

    fun CSY() {
        val Y = BooleanArray(17) { false }

        if (T[1]) {
            Y[0] = true
            Y[1] = true
            Y[2] = true
            Y[3] = true
        }

        if (T[2] || T[5]) {
            Y[16] = true
        }

        if (T[3] || T[7]) {
            Y[8] = true
        }

        if (T[4] || T[8] || T[12]) {
            Y[4] = true
        }

        if (T[6]) {
            Y[5] = true
        }

        if (T[7] || T[11]) {
            Y[6] = true
            Y[7] = true
        }

        if (T[7]) {
            Y[9] = true
        }

        if (T[10]) {
            Y[10] = true
        }

        if (T[9]) {
            Y[11] = true
            Y[12] = true
        }

        if (T[11]) {
            Y[13] = true
        }

        if (T[13]) {
            Y[14] = true
        }

        if (T[14] || T[16]) {
            Y[15] = true
        }

        if (T[2] || T[5]) {
            Y[16] = true
        }

        println(Y.asList())
    }

    fun OA() {
//        if (Y[0]) y0()
//        if (Y[1]) y1()
//        if (Y[2]) y2()
//        if (Y[3]) y3()
//        if (Y[4]) y4()
//        if (Y[5]) y5()
//        if (Y[6]) y6()
//        if (Y[7]) y7()
//        if (Y[8]) y8()
//        if (Y[9]) y9()
//        if (Y[10]) y10()
//        if (Y[11]) y11()
//        if (Y[12]) y12()
//        if (Y[13]) y13()
//        if (Y[14]) y14()
//        if (Y[15]) y15()
//        if (Y[16]) y16()
//
//        X[0] = x0()
//        X[1] = x1()
//        X[2] = x2()
//        X[3] = x3()
//        X[4] = x4()
//        X[5] = x5()
//        X[6] = x6()
    }

    fun CSD() {
        val D_out = BooleanArray(4)

        D_out[0] = T[0] || T[1] || T[5] || T[6] || T[8] || T[11]
        D_out[1] = T[2] || T[3] || T[4] || T[9] || T[10] || T[11]
        D_out[2] = T[7] || T[8] || T[9] || T[10] || T[11]
        D_out[3] = T[12] || T[13] || T[14]

        println(D_out.asList())
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

}