import androidx.compose.runtime.MutableState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel: ViewModel() {
    private val _selectedMode = MutableStateFlow(ExecutionMods.AUTOMATIC)
    val selectedMode: StateFlow<ExecutionMods> = _selectedMode

    private val _sectionAList = MutableStateFlow(List(16) { 0 })
    val sectionAList: StateFlow<List<Int>> = _sectionAList

    private val _sectionBList = MutableStateFlow(List(16) { 0 })
    val sectionBList: StateFlow<List<Int>> = _sectionBList

    private val _sectionAResList = MutableStateFlow(List(32) { 0 })
    val sectionAResList: StateFlow<List<Int>> = _sectionAResList

    private val _sectionBResList = MutableStateFlow(List(16) { 0 })
    val sectionBResList: StateFlow<List<Int>> = _sectionBResList

    private val _sectionCResList = MutableStateFlow(List(32) { 0 })
    val sectionCResList: StateFlow<List<Int>> = _sectionCResList

    private val _registerCounterResData = MutableStateFlow(List(4) { 0 })
    val registerCounterResData: StateFlow<List<Int>> = _registerCounterResData

    private val _A = MutableStateFlow(0u)
    val A: StateFlow<UInt> = _A

    private val _AM = MutableStateFlow(0U)
    val AM: StateFlow<UInt> = _AM

    private val _B = MutableStateFlow(0U)
    val B: StateFlow<UInt> = _B

    private val _BM = MutableStateFlow(0U)
    val BM: StateFlow<UInt> = _BM

    private val _C = MutableStateFlow(0UL)
    val C: StateFlow<ULong> = _C

    private val _CR = MutableStateFlow(0.toByte())
    val CR: StateFlow<Byte> = _CR

    private val _aState = MutableStateFlow(1)
    val aState: StateFlow<Int> = _aState

    private val _resultA10 = MutableStateFlow(0f)
    val resultA10: StateFlow<Float> = _resultA10

    private val _resultB10 = MutableStateFlow(0f)
    val resultB10: StateFlow<Float> = _resultB10

    private val _run = MutableStateFlow(true)
    val run: StateFlow<Boolean> = _run

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
        return (BM.value % 2U == 1U)
    }

    private fun x6(): Boolean {
        //B(0) ⊕ B(1)
        val b0 = (BM.value % 2U == 1U) // B(0)
        val b1 = (((BM.value shr 1) % 2U) == 1U) // B(1)
        //Операция XOR возвращает true (или 1), если один из операндов равен true (или 1), но не оба.
        val result = b0 xor b1
        return result
    }

    private fun x7(): Boolean {
        // B(1)
        return (((BM.value shr 1) % 2U) == 1U)
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
        val result = ((C.value shr 31) % 2UL) == 1UL
        return (((C.value shr 31) % 2UL) == 1UL)
    }

    private fun y0() {
        _run.value = false
    }

    private fun updateListFromBinaryValue(value: ULong, list: StateFlow<List<Int>>) {
        val binaryString = value.toString(2)
        val newList = mutableListOf<Int>()

        var j = binaryString.length - 1
        while (j >= 0) {
            newList.add(binaryString[j].toString().toInt())
            j--
        }
        (list as MutableStateFlow).value = newList.reversed()
    }


    private fun updateListFromByteValue(value: Byte, list: StateFlow<List<Int>>) {
        val binaryString = value.toString(2).padStart(4, '0')
        val newList = mutableListOf<Int>()
        binaryString.forEachIndexed { index, char ->
            newList[index] = char.toString().toInt()
        }
        (list as MutableStateFlow).value = newList
    }

    private fun y1() {
        // Reset C to 0
        _C.value = 0UL
        updateListFromBinaryValue(C.value, sectionCResList)
    }

    private fun y2() {
        // Set CR to 15
        _CR.value = 15
        updateListFromByteValue(CR.value, registerCounterResData)
    }

    private fun y3() {
        // Set AM(31:30) to A(15) A(15)
        val bit15 = (A.value shr 15) % 2U
        val bit31 = bit15.shl(31)
        val bit30 = bit15.shl(30)
        _AM.value = AM.value or bit31 or bit30
        updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
    }

    private fun y4() {
        // AM(29:15): = 0
        _AM.value = AM.value and 0xC0007FFFU
        updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
    }

    private fun y5() {
        // AM(14:0) := A(14:0)
        val lower15BitsA = A.value and 0x7FFFU
        _AM.value = (AM.value and 0xFFFF8000U) or lower15BitsA
        updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
    }

    private fun y6() {
        // AM(29:15):=A̅M̅(29:15)
        val invertedUpper15BitsAM = AM.value.inv() and 0xFFFF8000U
        _AM.value = (AM.value and 0x7FFFU) or invertedUpper15BitsAM
        updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
    }

    private fun y7() {
        // C:=C+A̅M̅+1
        // Calculate inverted A̅M̅
        val invertedAM = AM.value.inv()
        // Add 1 to invertedAM
        val invertedAMPlusOne = (invertedAM + 1U)
        // Add invertedAMPlusOne to C
        _C.value = C.value + invertedAMPlusOne.toULong()
        // Update sectionCResList with the new value of C
        updateListFromBinaryValue(C.value, sectionCResList)
    }

    private fun y8() {
        // AM:=L1(AM.0)
        // Shift bits of AM one position to the left
        val shiftedAM = (AM.value shl 1)

        // Set the least significant bit of shiftedAM to 0
        val newAM = shiftedAM and 0xFFFFFFFEU
        // Update AM
        _AM.value = newAM
        // Update sectionAResList with the new value of AM
        updateListFromBinaryValue(AM.value.toULong(), sectionAResList)
    }

    private fun y9() {
        // Сдвигаем все биты на одну позицию вправо и добавляем слева 0
        _BM.value = BM.value shr 1
        updateListFromBinaryValue(BM.value.toULong(), sectionBResList)
    }

    private fun y10() {
        _CR.value = (CR.value - 1).toByte()
        updateListFromByteValue(CR.value, registerCounterResData)
    }

    private fun y11() {
        // C:=C+AM
        val result = C.value + AM.value.toULong()
        _C.value = result
        updateListFromBinaryValue(C.value, sectionCResList)
    }

    private fun y12() {
        // C(29:0)=C̅(29:0) + 1
        val complementedLower30Bits = C.value.inv() and 0x3FFFFFFFU.toULong()

        // Add one to the complemented value
        val result = complementedLower30Bits + 1U

        // Update C with the result
        _C.value = (C.value and 0xC0000000U.toULong()) or result

        // Update sectionCResList with the new value of C
        updateListFromBinaryValue(C.value, sectionCResList)
    }

    private fun y13() {
        // C(30:16) = C̅(29:15) + 1

        // Получаем значение C̅(29:15)
        val invertedLower15BitsC = C.value.inv() and 0xFFFF8000UL

        // Добавляем 1 к этому значению
        val incrementedValue = (invertedLower15BitsC + 1UL)

        // Устанавливаем C(30:16) равным этому новому значению
        _C.value = (C.value and 0x00007FFFUL) or (incrementedValue shl 15)

        // Обновляем список sectionCResList с новым значением C
        updateListFromBinaryValue(C.value, sectionCResList)
    }

    private fun y14() {
        // C(30:16) = C̅(30:16) + 1
        val complementedBits = C.value.inv() and 0x3FFF8000U.toULong()

        // Add one to the complemented value
        val result = complementedBits + 1U

        // Update bits 16 to 30 of C with the result
        _C.value = (C.value and 0xC0007FFFU.toULong()) or result

        // Update sectionCResList with the new value of C
        updateListFromBinaryValue(C.value, sectionCResList)
    }

    fun runMP() {
        when (_aState.value) {
            1 -> {
                if (x1()) {
                    if (x2()) {
                        y1()
                        _aState.value = 8
                    } else {
                        if (x3()) {
                            y1()
                            _aState.value = 8
                        } else {
                            y1()
                            y2()
                            y3()
                            y4()
                            y5()
                            _aState.value = 2
                        }
                    }
                } else {
                    _aState.value = 1
                }
            }

            2 -> {
                if (x4()) {
                    y6()
                    _aState.value = 3
                } else {
                    _aState.value = 3
                }
            }

            3 -> {
                if (x5()) {
                    y7()
                    y8()
                    _aState.value = 4
                } else {
                    y8()
                    _aState.value = 4
                }
            }

            4 -> {
                if (x6()) {
                    if (x7()) {
                        y7()
                        y8()
                        y9()
                        y10()
                        _aState.value = 5
                    } else {
                        y11()
                        y8()
                        y9()
                        y10()
                        _aState.value = 5
                    }
                } else {
                    y8()
                    y9()
                    y10()
                    _aState.value = 5
                }
            }

            5 -> {
                if (x8()) {
                    if (x9()) {
                        y12()
                        _aState.value = 6
                    } else {
                        _aState.value = 6
                    }
                } else {
                    _aState.value = 4
                }
            }

            6 -> {
                if (x10()) {
                    y13()
                    _aState.value = 7
                } else {
                    _aState.value = 7
                }
            }

            7 -> {
                if (x11()) {
                    y14()
                    _aState.value = 8
                } else {
                    _aState.value = 8
                }
            }

            8 -> {
                y0()
                _aState.value = 1
            }

            else -> {}
        }
    }

    fun clear(testValue: MutableState<Int>) {
        _sectionAList.value = List(16) { 0 }
        _sectionBList.value = List(16) { 0 }
        _sectionAResList.value = List(32) { 0 }
        _sectionBResList.value = List(16) { 0 }
        _sectionCResList.value = List(32) { 0 }
        _registerCounterResData.value = List(4) { 0 }

        _resultA10.value = 0f
        _resultB10.value = 0f
        _A.value = 0u
        _AM.value = 0U
        _B.value = 0u
        _BM.value = 0U
        _C.value = 0u
        _CR.value = 0
        _aState.value = 1
        _run.value = true
        testValue.value = 0
    }
}