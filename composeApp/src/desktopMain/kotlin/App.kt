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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import courseworkmatmem.composeapp.generated.resources.UAOA
import courseworkmatmem.composeapp.generated.resources.pngschema
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.pow


var run = mutableStateOf(true)
val A = mutableStateOf(0u)
val AM = mutableStateOf<UInt>(0U)
val B = mutableStateOf<UInt>(0U)
val BM = mutableStateOf<UInt>(0U)
val C = mutableStateOf<ULong>(0UL)
val CR = mutableStateOf<Byte>(0)

val sectionAList = mutableStateListOf<Int>().apply { repeat(16) { add(0) } }
val sectionBList = mutableStateListOf<Int>().apply { repeat(16) { add(0) } }
val sectionAResList = mutableStateListOf<Int>().apply { repeat(32) { add(0) } }
var sectionBResList = mutableStateListOf<Int>().apply { repeat(16) { add(0) } }
val sectionCResList = mutableStateListOf<Int>().apply { repeat(32) { add(0) } }
val registerCounterResData = mutableStateListOf<Int>().apply { repeat(4) { add(0) } }

val aState = mutableStateOf(0)
val resultA10 = mutableStateOf((0f))
val resultB10 = mutableStateOf((0f))
val resultC10 = mutableStateOf((0f))

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val selectedMode = remember { mutableStateOf(ExecutionMods.AUTOMATIC) }
        val selectedProgram = remember { mutableStateOf(ProgramType.PROGRAM1) }

        val marker = CommandMarker(
            aState
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.width(600.dp)
            ) {
                val stateVertical = rememberScrollState(0)
                if (selectedProgram.value == ProgramType.PROGRAM1) {
                    Image(
                        painterResource(Res.drawable.pngschema),
                        null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                            .verticalScroll(stateVertical)
                    )
                } else {
                    Column(
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Image(
                            painterResource(Res.drawable.UAOA),
                            null
                        )
                        OAUAScreen()
                    }
                }
                if (selectedProgram.value == ProgramType.PROGRAM1) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
                        CheckBoxStateA(aState)
                    }
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
                        ResultSection(resultC10)
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
                        LevelModeling(selectedProgram)
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
                            if (selectedMode.value == ExecutionMods.AUTOMATIC) {
                                while (run.value) {
                                    if (testValue.value == 0) {
                                        A.value = getValueDouble(sectionAList)
                                        B.value = getValueDouble(sectionBList)
                                        BM.value = B.value
                                        a_out[0] = true
                                        X[0] = true
                                        testValue.value = 1
                                    }
                                    if (selectedProgram.value == ProgramType.PROGRAM1) {
                                        marker.runMP()
                                    } else {
                                        if (run.value) {
                                            if (!startUAOA()) {
                                                yStop()
                                            }
                                        }
                                    }
                                    val sign = (((C.value shr 31) % 2UL) == 1UL && ((C.value shr 30) % 2UL) == 0UL)
                                    resultC10.value = calculateFinalResult(sectionCResList, sign)
                                }
                            } else {
                                if (testValue.value == 0) {
                                    A.value = getValueDouble(sectionAList)
                                    B.value = getValueDouble(sectionBList)
                                    BM.value = B.value
                                    a_out[0] = true
                                    X[0] = true
                                    testValue.value = 1
                                }
                                if (selectedProgram.value == ProgramType.PROGRAM1) {
                                   marker.runMP()
                                } else {
                                    if (run.value) {
                                        if (!startUAOA()) {
                                            yStop()
                                        }
                                    }
                                }
                                val sign = (((C.value shr 31) % 2UL) == 1UL && ((C.value shr 30) % 2UL) == 0UL)
                                resultC10.value = calculateFinalResult(sectionCResList, sign)
                            }
                        }, onClearClick = {
                            clearAll(testValue)
                        })
                    }
                }
            }
        }
    }
}

fun clearAll(testValue: MutableState<Int>) {
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
    resultC10.value = 0f
    A.value = 0u
    AM.value = 0U
    B.value = 0u
    BM.value = 0U
    C.value = 0u
    CR.value = 0
    aState.value = 0
    run.value = true
    testValue.value = 0

    Q.clear()
    repeat(4) { Q.add(false) }

    D_out.clear()
    repeat(4) { D_out.add(false) }

    a_out.clear()
    repeat(9) { a_out.add(false) }

    T.clear()
    repeat(24) { T.add(false) }

    Y.clear()
    repeat(14) { Y.add(false) }

    X.clear()
    repeat(11) { X.add(false) }

    X_PLY.clear()
    repeat(2) { X_PLY.add(false) }
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

fun calculateFinalResult(tableData: List<Int>, sign: Boolean): Float {
    val binaryString = tableData.drop(2).joinToString("")
    var result = Integer.parseInt(binaryString, 2).toFloat()
    result /= 2.0.pow(tableData.size - 2).toFloat()
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
    private val a: MutableState<Int>
) {
    fun runMP() {
        when (a.value) {
            0 -> {
                if (x0()) {
                    if (x1()) {
                        y0()
                        a.value = 7
                    } else {
                        if (x2()) {
                            y0()
                            a.value = 7
                        } else {
                            y0()
                            y1()
                            y2()
                            y3()
                            y4()
                            a.value = 1
                        }
                    }
                } else {
                    a.value = 0
                }
            }

            1 -> {
                if (x3()) {
                    y5()
                    a.value = 2
                } else {
                    a.value = 2
                }
            }

            2 -> {
                if (x4()) {
                    y6()
                    y7()
                    a.value = 3
                } else {
                    y7()
                    a.value = 3
                }
            }

            3 -> {
                if (x5()) {
                    if (x6()) {
                        y6()
                        y7()
                        y8()
                        y9()
                        a.value = 4
                    } else {
                        y10()
                        y7()
                        y8()
                        y9()
                        a.value = 4
                    }
                } else {
                    y7()
                    y8()
                    y9()
                    a.value = 4
                }
            }

            4 -> {
                if (x7()) {
                    if (x8()) {
                        y11()
                        a.value = 5
                    } else {
                        a.value = 5
                    }
                } else {
                    a.value = 3
                }
            }

            5 -> {
                if (x9()) {
                    y12()
                    a.value = 6
                } else {
                    a.value = 6
                }
            }

            6 -> {
                if (x10()) {
                    y13()
                    a.value = 7
                } else {
                    a.value = 7
                }
            }

            7 -> {
                yStop()
                a.value = 0
            }

            else -> {}
        }
    }
}

@Composable
fun LevelModeling(
    selectedProgram: MutableState<ProgramType>
) {
    Column {
        Text("Уровень моделирования ОУ")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedProgram.value == ProgramType.PROGRAM1,
                onClick = { selectedProgram.value = ProgramType.PROGRAM1 }
            )
            Text("Микропрограмма")
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedProgram.value == ProgramType.PROGRAM2,
                onClick = { selectedProgram.value = ProgramType.PROGRAM2 }
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
) {
    Column {
        Text("Управление вычислением")
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (selectedMode.value == ExecutionMods.AUTOMATIC) {
                ElevatedButton(
                    onClick = onTackClick,
                    shape = RoundedCornerShape(4.dp),
                    enabled = run.value
                ) {
                    Text("Пуск")
                }
            }
            if (selectedMode.value == ExecutionMods.STEP_BY_STEP) {
                ElevatedButton(
                    onClick = onTackClick,
                    shape = RoundedCornerShape(4.dp),
                    enabled = run.value
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
fun ResultSection(resultC10: MutableState<Float>) {
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
            value = "%.10f".format(resultC10.value),
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
            Text("A0")
            Checkbox(checked = a.value == 0, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("A1")
            Checkbox(checked = a.value == 1, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("A2")
            Checkbox(checked = a.value == 2, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("A3")
            Checkbox(checked = a.value == 3, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("A4")
            Checkbox(checked = a.value == 4, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("A5")
            Checkbox(checked = a.value == 5, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("A6")
            Checkbox(checked = a.value == 6, onCheckedChange = {})
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("A7")
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
                                resultA10.value =
                                    calculateResult(sectionAList, sign = sectionAList[0] == 1)
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
                                resultB10.value =
                                    calculateResult(sectionBList, sign = sectionBList[0] == 1)
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