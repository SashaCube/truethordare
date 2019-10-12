package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.random.Randomizer
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.random.RhythmicalRandom
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RhythmicalRandomUnitTest {

    private fun getSomeRhythmicalRandomizer(): RhythmicalRandom {
        val randomizer = RhythmicalRandom()
        val basisValue = 9

        with(randomizer) {
            basis = basisValue
            getNext()
            getNext()
        }

        return randomizer
    }

    @Test
    fun randomizer_set_get_basis() {
        val randomizer: Randomizer = RhythmicalRandom()
        val basisNewValue = 9

        randomizer.basis = basisNewValue

        assertEquals(basisNewValue, randomizer.basis)
    }

    @Test
    fun randomizer_set_basis_to_1_getNext_1() {
        val randomizer: Randomizer = RhythmicalRandom()
        val basisNewValue = 1

        randomizer.basis = basisNewValue

        assertEquals(basisNewValue, randomizer.getNext())
    }

    @Test
    fun randomizer_set_basis_to_less_then_1_getNext_1() {
        val randomizer: Randomizer = RhythmicalRandom()
        val basisNewValue = -1

        randomizer.basis = basisNewValue

        assertEquals(1, randomizer.getNext())
    }

    @Test
    fun randomizer_set_basis_to_less_then_1_get_basis_1() {
        val randomizer: Randomizer = RhythmicalRandom()
        val basisNewValue = -1

        randomizer.basis = basisNewValue

        assertEquals(1, randomizer.basis)
    }

    @Test
    fun randomizer_getNext_two_times() {
        val randomizer: Randomizer = RhythmicalRandom()
        val basisNewValue = 2

        randomizer.basis = basisNewValue
        val first = randomizer.getNext()
        val second = randomizer.getNext()

        assertEquals(false, first == second)
    }

    @Test
    fun randomizer_restart() {
        val randomizer = getSomeRhythmicalRandomizer()
        val mutableList = mutableListOf<Int>()
        var distinct: Boolean

        with(randomizer) {
            restart()
            for (i in 1..basis) {
                mutableList.add(getNext())
            }
        }
        distinct = (mutableList.size.toLong() == mutableList.stream().distinct().count())

        assertEquals(true, distinct)
    }

    @Test
    fun randomizer_getNext_after_create() {
        val randomizer = RhythmicalRandom()
        val mutableList = mutableListOf<Int>()

        with(randomizer) {
            basis = 7
            for (i in 1..basis) {
                mutableList.add(getNext())
            }
        }
        val distinct = (mutableList.size.toLong() == mutableList.stream().distinct().count())

        assertEquals(true, distinct)
    }

    @Test
    fun randomizer_restart_more_than_one_time() {
        val randomizer = getSomeRhythmicalRandomizer()
        val mutableList = mutableListOf<Int>()
        var distinct: Boolean

        with(randomizer) {
            restart()
            for (i in 1..(basis * 4)) {
                mutableList.add(getNext())
            }
        }
        distinct = (mutableList.size.toLong().div(4) == mutableList.stream().distinct().count())

        assertEquals(true, distinct)
    }

    @Test
    fun randomizer_randomize_3_cycles_are_all_unique() {
        val randomizer = getSomeRhythmicalRandomizer()
        val mutableList1 = mutableListOf<Int>()
        val mutableList2 = mutableListOf<Int>()
        val mutableList3 = mutableListOf<Int>()
        var same = false

        with(randomizer) {
            restart()
            for (i in 1..basis) {
                mutableList1.add(getNext())
            }
            for (i in 1..basis) {
                mutableList2.add(getNext())
            }
            for (i in 1..basis) {
                mutableList3.add(getNext())
            }

            for (i in 0 until basis) {
                if ((mutableList1[i] == mutableList2[i]) && (mutableList3[i] == mutableList1[i])) {
                    same = true
                }
            }
        }

        assertEquals(false, same)
    }
}
