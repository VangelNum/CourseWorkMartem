import androidx.compose.foundation.Image
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import courseworkmatmem.composeapp.generated.resources.Res
import courseworkmatmem.composeapp.generated.resources.endchema
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.pow

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val selectedMode = remember { mutableStateOf(ExecutionMods.AUTOMATIC) }

        val sectionAList = remember { mutableStateListOf<Int>().apply { repeat(16) { add(0) } } }
        val sectionBList = remember { mutableStateListOf<Int>().apply { repeat(16) { add(0) } } }
        val sectionAResList = remember { mutableStateListOf<Int>().apply { repeat(32) { add(0) } } }
        val sectionBResList = remember { mutableStateListOf<Int>().apply { repeat(16) { add(0) } } }
        val sectionCResList = remember { mutableStateListOf<Int>().apply { repeat(32) { add(0) } } }
        val registerCounterResData = remember { mutableStateListOf<Int>().apply { repeat(4) { add(0) } } }

        val A = remember { mutableStateOf(0u) }
        val AM = remember { mutableStateOf<UInt>(0U) }
        val B = remember { mutableStateOf<UInt>(0U) }
        val BM = remember { mutableStateOf<ULong>(0UL) }
        val C = remember { mutableStateOf<ULong>(0UL) }
        val CR = remember { mutableStateOf<Byte>(0) }

        val aState = remember { mutableStateOf(1) }

        val resultA10 = remember {
            mutableStateOf((0f))
        }

        val resultB10 = remember {
            mutableStateOf((0f))
        }

        val run = remember {
            mutableStateOf(true)
        }

        val marker = CommandMarker(
            A,
            AM,
            B,
            BM,
            C,
            CR,
            sectionAList,
            sectionBList,
            sectionAResList,
            sectionBResList,
            sectionCResList,
            registerCounterResData,
            aState,
            resultA10,
            resultB10,
            run
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.width(600.dp)
            ) {
                val stateVertical = rememberScrollState(0)
                Image(
                    painterResource(Res.drawable.endchema),
                    null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .verticalScroll(stateVertical)
                )
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
                    CheckBoxStateA(aState)
                }
                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(stateVertical)
                )
            }
            val sizeOfSection = 20.dp
            Column {
                Spacer(Modifier.height(6.dp))
                Box(
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp).border(
                        0.5.dp,
                        Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    ).padding(10.dp)
                ) {
                    Row {
                        Column {
                            Text("Исходные данные")
                            SectionA(sizeOfSection, sectionAList, resultA10)
                            SectionB(sizeOfSection, sectionBList, resultB10)
                        }
                        CounterRegister(sizeOfSection, registerCounterResData)
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp).border(
                        0.5.dp,
                        Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    ).padding(10.dp)
                ) {
                    Column {
                        Text("Ход вычисления")
                        SectionAM(sizeOfSection, sectionAResList)
                        SectionBM(sizeOfSection, sectionBResList)
                        SectionC(sizeOfSection, sectionCResList)
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp).border(
                        0.5.dp,
                        Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    ).padding(10.dp)
                ) {
                    Column {
                        Text("Результат")
                        ResultSection()
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Box(
                        modifier = Modifier.padding(start = 10.dp).border(
                            0.5.dp,
                            Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        ).padding(10.dp)
                    ) {
                        ExecutionMode(selectedMode)
                    }
                    Box(
                        modifier = Modifier.padding(start = 10.dp).border(
                            0.5.dp,
                            Color.Black,
                            shape = RoundedCornerShape(10.dp)
                        ).padding(10.dp)
                    ) {
                        LevelModeling()
                    }
                    var testValue = remember {
                        mutableStateOf(0)
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp)
                            .border(
                                0.5.dp,
                                Color.Black,
                                shape = RoundedCornerShape(10.dp)
                            ).padding(10.dp)
                    ) {
                        CalculationControl(selectedMode, onTackClick = {
                            if (testValue.value == 0) {
                                A.value = getValueDouble(sectionAList)
                                B.value = getValueDouble(sectionBList)
                                testValue.value = 1
                            }
                            marker.runMP()
                            run.value = true
                        }, onClearClick = {
                            marker.clear(testValue)
                        }, buttonCanBeShown = run.value)
                    }
                }
            }
        }
    }
}

fun calculateResult(tableData: List<Int>, sign: Boolean): Float {
    val binaryString = tableData.drop(1).joinToString("")
    var result = Integer.parseInt(binaryString, 2).toFloat()
    result /= 2.0.pow(tableData.size - 1).toFloat()
    return if (sign) {
        if (result == 0F) {
            result
        } else -result
    } else {
        result
    }
}

fun getValueDouble(
    anyList: SnapshotStateList<Int>
): UInt {
    val binaryString = anyList.joinToString("") { it.toString() }
    val binaryIntValue = binaryString.toInt(radix = 2)
    return binaryIntValue.toUInt()
}

class CommandMarker(
    private val A: MutableState<UInt>,
    private val AM: MutableState<UInt>,
    private val B: MutableState<UInt>,
    private val BM: MutableState<ULong>,
    private val C: MutableState<ULong>,
    private val CR: MutableState<Byte>,
    private val sectionAList: SnapshotStateList<Int>,
    private val sectionBList: SnapshotStateList<Int>,
    private val sectionAResList: SnapshotStateList<Int>,
    private val sectionBResList: SnapshotStateList<Int>,
    private val sectionCResList: SnapshotStateList<Int>,
    private val registerCounterResData: SnapshotStateList<Int>,
    private val a: MutableState<Int>,
    private val resultA10: MutableState<Float>,
    private val resultB10: MutableState<Float>,
    private val run: MutableState<Boolean>
) {

    private fun setDgvs(value: UInt, list: SnapshotStateList<Int>) {
        var valX = value
        for (col in list.size - 1 downTo 0) {
            list[col] = (valX % 2UL).toInt()
            valX = valX shr 1
        }

//        var binaryValue = value.toString(2)
//        binaryValue = binaryValue.padStart(list.size, '0') // Дополнение нулями до нужной длины
//        for (index in binaryValue.indices) {
//            list[index] = binaryValue[index].toString().toInt()
//        }



//        val binaryString = value.toString(2)
//
//        // Если длина бинарной строки больше, чем размер списка, обрежьте ее до нужной длины
//        val truncatedBinaryString = if (binaryString.length > list.size) {
//            binaryString.substring(binaryString.length - list.size)
//        } else {
//            binaryString
//        }
//
//        // Запишите значения в список
//        truncatedBinaryString.forEachIndexed { index, char ->
//            list[index] = char.toString().toInt()
//        }
    }

    private fun x1(): Boolean {
        return run.value
    }

    private fun x2(): Boolean {
        //A=0
        return A.value == 0U
    }

    private fun x3(): Boolean {
        //B=0
        return B.value == 0U
    }

    private fun x4(): Boolean {
        //A(15) == 0
        return (((A.value shr 15) % 2U) == 1U)
    }

    private fun x5(): Boolean {
        //B(0)
        return (B.value % 2U == 1U)
    }

    private fun x6(): Boolean {
        //B(0) ⊕ B(1)
        val b0 = (B.value % 2U == 1U) // B(0)
        val b1 = (((B.value shr 1) % 2U) == 1U) // B(1)
        return b0 xor b1
    }

    private fun x7(): Boolean {
        // B(1)
        return (((B.value shr 1) % 2U) == 1U)
    }

    private fun x8(): Boolean {
        // CR = 0
        return CR.value == 0.toByte()
    }

    private fun x9(): Boolean {
        // C(31)
        return (((C.value shr 31) % 2UL) == 1UL)
    }

    private fun x10(): Boolean {
        // C(14)
        return (((C.value shr 14) % 2UL) == 1UL)
    }

    private fun x11(): Boolean {
        // C(31)
        return (((C.value shr 31) % 2UL) == 1UL)
    }

    private fun y1() {
        //C:=0
        C.value = 0UL
        setDgvs(C.value.toUInt(), sectionCResList)
    }

    private fun y2() {
        // СЧ:=15
        CR.value = 15
        setDgvs(CR.value.toUInt(), registerCounterResData)
    }

    private fun y3() {
        //AM(31:30):=A(15) A(15)
        val bit15 = (A.value shr 15) % 2U
        val bit31 = bit15.shl(31)
        val bit30 = bit15.shl(27)
        AM.value = (AM.value and 0x3FFFFFFFU) or bit31 or bit30
        println(AM.value)
        setDgvs(AM.value, sectionAResList)
    }

    private fun y4() {
        // AM(29:15) := 0
        val mask: UInt = ((1u shl 15) - 1u) and ((1u shl 30) - 1u)
        // Применяем инвертированную маску к AM
        AM.value = AM.value and mask.inv()
    }

    private fun y5() {
        // AM(14:0) := A(14:0)
//        val mask = 0x00007FFFU
//        AM.value = (AM.value and (mask.inv())) or (A.value and mask)
//        setDgvs(AM.value, sectionAResList)
    }

    private fun y6() {
        // AM(29:15):=A̅M̅(29:15)
    }

    private fun y7() {
        // C:=C+A̅M̅+1
    }

    private fun y8() {
        // A̅M̅:=L1(AM.0)
    }
    private fun y9() {
        // B:=R1(0.B)
        BM.value = (B.value shr 1 and 0x1FFFFU).toULong()
        setDgvs(BM.value.toUInt(), sectionBResList)
    }
    private fun y10() {
        // CЧ:=CЧ-1
        CR.value--
        setDgvs(CR.value.toUInt(), registerCounterResData)
    }
    private fun y11() {
        // C:=C+AM
    }
    private fun y12() {
        // C(29:0)=C̅(29:0)+1
    }
    private fun y13() {
        // C(30:16)=С(29:15)+1
    }
    private fun y14() {
        // C(30:16)=C̅(30:16)+1
    }

    private fun y0() {
        run.value = false
    }

    fun runMP() {
        when (a.value) {
            1 -> {
                if (x1()) {
                    if (x2()) {
                        y1()
                        a.value = 8
                    } else {
                        if (x3()) {
                            y1()
                            a.value = 8
                        } else {
                            y1()
                            y2()
                            y3()
                            y4()
                            y5()
                            a.value = 2
                        }
                    }
                } else {
                    a.value = 1
                }
            }

            2 -> {
                if (x4()) {
                    y6()
                    a.value = 3
                } else {
                    a.value = 3
                }
            }

            3 -> {
                if (x5()) {
                    y7()
                    y8()
                    a.value = 4
                } else {
                    y8()
                    a.value = 4
                }
            }

            4 -> {
                if (x6()) {
                    if (x7()) {
                        y7()
                        y8()
                        y9()
                        y10()
                        a.value = 5
                    } else {
                        y11()
                        y8()
                        y9()
                        y10()
                        a.value = 5
                    }
                } else {
                    y8()
                    y9()
                    y10()
                    a.value = 5
                }
            }

            5 -> {
                if (x8()) {
                    if (x9()) {
                        y12()
                        a.value = 6
                    } else {
                        a.value = 6
                    }
                } else {
                    a.value = 4
                }
            }

            6 -> {
                if (x10()) {
                    y13()
                    a.value = 8
                } else {
                    a.value = 8
                }
            }

            7 -> {
                if (x11()) {
                    y14()
                    a.value = 8
                } else {
                    a.value = 8
                }
            }

            8 -> {
                y0()
                a.value = 1
            }

            else -> {}
        }
    }

    fun clear(testValue: MutableState<Int>) {
        for (i in sectionAList.indices) {
            sectionAList[i] = 0
        }
        for (i in sectionBList.indices) {
            sectionBList[i] = 0
        }
        for (i in sectionAResList.indices) {
            sectionAResList[i] = 0
        }
        for (i in sectionBResList.indices) {
            sectionBResList[i] = 0
        }
        for (i in sectionCResList.indices) {
            sectionCResList[i] = 0
        }
        for (i in registerCounterResData.indices) {
            registerCounterResData[i] = 0
        }
        resultA10.value = 0f
        resultB10.value = 0f
        A.value = 0u
        AM.value = 0U
        B.value = 0u
        BM.value = 0U
        C.value = 0u
        CR.value = 0
        a.value = 1
        run.value = true
        testValue.value = 0
    }
}

@Composable
fun LevelModeling() {
    var selectedMode by remember { mutableStateOf(ProgramType.PROGRAM1) }

    Column {
        Text("Уровень моделирования ОУ")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedMode == ProgramType.PROGRAM1,
                onClick = { selectedMode = ProgramType.PROGRAM1 }
            )
            Text("Микропрограмма")
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedMode == ProgramType.PROGRAM2,
                onClick = { selectedMode = ProgramType.PROGRAM2 }
            )
            Text("Взаимодействие УА и ОА")
        }
    }
}

@Composable
fun ExecutionMode(selectedMode: MutableState<ExecutionMods>) {
    Column {
        Text("Режим выполнения")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedMode.value == ExecutionMods.AUTOMATIC,
                onClick = { selectedMode.value = ExecutionMods.AUTOMATIC }
            )
            Text("Автоматический")
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedMode.value == ExecutionMods.STEP_BY_STEP,
                onClick = { selectedMode.value = ExecutionMods.STEP_BY_STEP }
            )
            Text("Пошаговый")
        }
    }
}

@Composable
fun CalculationControl(
    selectedMode: MutableState<ExecutionMods>,
    onTackClick: () -> Unit,
    onClearClick: () -> Unit,
    buttonCanBeShown: Boolean
) {
    Column {
        Text("Управление вычислением")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (selectedMode.value == ExecutionMods.AUTOMATIC) {
                ElevatedButton(onClick = {}, shape = RoundedCornerShape(4.dp)) {
                    Text("Пуск")
                }
            }
            if (selectedMode.value == ExecutionMods.STEP_BY_STEP) {
                ElevatedButton(
                    onClick = onTackClick,
                    shape = RoundedCornerShape(4.dp),
                    enabled = buttonCanBeShown
                ) {
                    Text("Такт")
                }
            }
            ElevatedButton(onClick = onClearClick, shape = RoundedCornerShape(4.dp)) {
                Text("Сброс")
            }
        }
    }
}

@Composable
fun CounterRegister(
    sizeOfSection: Dp,
    registerCounterResData: SnapshotStateList<Int>
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("СЧ", fontSize = 20.sp, modifier = Modifier.padding(end = 10.dp))
        Column() {
            Text("Регистр счётчика")
            Row {
                registerCounterResData.indices.reversed().forEach {
                    TableCell(text = it, size = sizeOfSection)
                }
            }
            Row {
                registerCounterResData.forEach { item ->
                    TableCell(text = item, size = sizeOfSection)
                }
            }
        }
    }
}

@Composable
fun ResultSection() {
    val result by remember {
        mutableStateOf("")
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Произведение")
        Spacer(modifier = Modifier.width(10.dp))
        Text("C")
        Text("10", fontSize = 10.sp)
        Text(" = ")
        BasicTextField(
            value = result,
            onValueChange = {},
            modifier = Modifier.width(200.dp)
                .border(0.5.dp, Color.Black, shape = RoundedCornerShape(12.dp)).padding(6.dp)
        )
    }
}

@Composable
fun CheckBoxStateA(a: MutableState<Int>) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("a1")
            Checkbox(checked = a.value == 1, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("a2")
            Checkbox(checked = a.value == 2, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("a3")
            Checkbox(checked = a.value == 3, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("a4")
            Checkbox(checked = a.value == 4, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("a5")
            Checkbox(checked = a.value == 5, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("a6")
            Checkbox(checked = a.value == 6, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("a7")
            Checkbox(checked = a.value == 7, onCheckedChange = {})
        }
    }
}

@Composable
fun TableCell(
    text: Int,
    size: Dp,
    onClick: () -> Unit = {}
) {
    Text(
        text = text.toString(),
        modifier = Modifier
            .border(0.5.dp, Color.Black)
            .clickable(onClick = onClick)
            .size(size),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                spanStyle = PlatformSpanStyle(),
                paragraphStyle = PlatformParagraphStyle()
            )
        )
    )
}

@Composable
fun SectionA(
    sizeOfSection: Dp = 25.dp,
    sectionAList: SnapshotStateList<Int>,
    resultA10: MutableState<Float>
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text("A", fontSize = 20.sp, modifier = Modifier.padding(end = 10.dp))
            Column {
                Text("Множимое", fontSize = 12.sp)
                Row {
                    sectionAList.indices.reversed().forEach { item ->
                        TableCell(text = item, size = sizeOfSection)
                    }
                }
                Row {
                    sectionAList.forEachIndexed { index, item ->
                        TableCell(
                            text = item,
                            size = sizeOfSection,
                            onClick = {
                                sectionAList[index] = if (item == 0) 1 else 0
                                resultA10.value = calculateResult(sectionAList, sign = sectionAList[0] == 1)
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("A")
            Text("10", fontSize = 10.sp)
            Text(" = ")
            BasicTextField(
                value = "%.5f".format(resultA10.value),
                onValueChange = {},
                modifier = Modifier.width(200.dp)
                    .border(0.5.dp, Color.Black, shape = RoundedCornerShape(12.dp)).padding(6.dp)
            )
        }
    }
}

@Composable
fun SectionB(
    sizeOfSection: Dp = 25.dp,
    sectionBList: SnapshotStateList<Int>,
    resultB10: MutableState<Float>
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Text("B", fontSize = 20.sp, modifier = Modifier.padding(end = 10.dp))
            Column() {
                Text("Множитель", fontSize = 12.sp)
                Row() {
                    sectionBList.indices.reversed().forEach { item ->
                        TableCell(text = item, size = sizeOfSection)
                    }
                }
                Row {
                    sectionBList.forEachIndexed { index, item ->
                        TableCell(
                            text = item,
                            size = sizeOfSection,
                            onClick = {
                                sectionBList[index] = if (item == 0) 1 else 0
                                resultB10.value = calculateResult(sectionBList, sign = sectionBList[0] == 1)
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("B")
            Text("10", fontSize = 10.sp)
            Text(" = ")
            BasicTextField(
                value = "%.5f".format(resultB10.value),
                onValueChange = {},
                modifier = Modifier.width(200.dp)
                    .border(0.5.dp, Color.Black, shape = RoundedCornerShape(12.dp)).padding(6.dp)
            )
        }
    }
}


@Composable
fun SectionAM(
    sizeOfSection: Dp = 25.dp,
    sectionAResList: SnapshotStateList<Int>
) {
    Column {
        Row {
            Text("A", fontSize = 20.sp, modifier = Modifier.padding(top = 40.dp, end = 10.dp))
            Column {
                Text("Регистр множимого", fontSize = 12.sp)
                Row {
                    sectionAResList.indices.reversed().forEach { item ->
                        TableCell(text = item, size = sizeOfSection)
                    }
                }
                Row {
                    sectionAResList.forEach {
                        TableCell(text = it, size = sizeOfSection)
                    }
                }
            }
        }
    }
}

@Composable
fun SectionBM(
    sizeOfSection: Dp = 25.dp,
    sectionBResList: SnapshotStateList<Int>
) {
    Column {
        Row {
            Text("B", fontSize = 20.sp, modifier = Modifier.padding(top = 40.dp, end = 10.dp))
            Column {
                Text("Регистр множителя", fontSize = 12.sp)
                Row() {
                    sectionBResList.indices.reversed().forEach { item ->
                        TableCell(text = item, size = sizeOfSection)
                    }
                }
                Row {
                    sectionBResList.forEach {
                        TableCell(text = it, size = sizeOfSection)
                    }
                }
            }
        }
    }
}

@Composable
fun SectionC(
    sizeOfSection: Dp = 25.dp,
    sectionCResList: SnapshotStateList<Int>
) {
    Column {
        Row {
            Text("C", fontSize = 20.sp, modifier = Modifier.padding(top = 40.dp, end = 10.dp))
            Column {
                Text("Регистр произведения", fontSize = 12.sp)
                Row {
                    sectionCResList.indices.reversed().forEach { item ->
                        TableCell(text = item, size = sizeOfSection)
                    }
                }
                Row {
                    sectionCResList.forEach {
                        TableCell(text = it, size = sizeOfSection)
                    }
                }
            }
        }
    }
}