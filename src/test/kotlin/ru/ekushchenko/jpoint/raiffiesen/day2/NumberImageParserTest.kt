package ru.ekushchenko.jpoint.raiffiesen.day2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException

class NumberImageParserTest {

    @ParameterizedTest
    @MethodSource("successfulCases")
    fun shouldParse(input: String, expected: Long) {
        val file = this::class.java.getResource(input)
        val actual = NumberImageParser.parse(file.readText())
        Assertions.assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("faultyCases")
    fun shouldThrowException(input: String) {
        val file = this::class.java.getResource(input)
        Assertions.assertThrows(IllegalArgumentException::class.java) { 
            NumberImageParser.parse(file.readText()) 
        }
    }

    companion object {
        
        @JvmStatic
        fun successfulCases() = listOf(
            Arguments.of("/case1.txt", 123456789),
            Arguments.of("/case2.txt", 23056789),
            Arguments.of("/case3.txt", 823856989)
        )

        @JvmStatic
        fun faultyCases() = listOf(
            Arguments.of("/case4.txt"),
            Arguments.of("/case5.txt"),
            Arguments.of("/case6.txt")
        )
    }
}