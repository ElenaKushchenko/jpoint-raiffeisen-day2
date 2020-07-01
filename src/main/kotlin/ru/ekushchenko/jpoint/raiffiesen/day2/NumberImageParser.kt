package ru.ekushchenko.jpoint.raiffiesen.day2

object NumberImageParser {

    private const val digitSize = 3
    private val digits = setOf(
        DigitImage(0, arrayOf(" _ ", "| |", "|_|")),
        DigitImage(1, arrayOf("   ", "  |", "  |")),
        DigitImage(2, arrayOf(" _ ", " _|", "|_ ")),
        DigitImage(3, arrayOf(" _ ", " _|", " _|")),
        DigitImage(4, arrayOf("   ", "|_|", "  |")),
        DigitImage(5, arrayOf(" _ ", "|_ ", " _|")),
        DigitImage(6, arrayOf(" _ ", "|_ ", "|_|")),
        DigitImage(7, arrayOf(" _ ", "  |", "  |")),
        DigitImage(8, arrayOf(" _ ", "|_|", "|_|")),
        DigitImage(9, arrayOf(" _ ", "|_|", " _|"))
    )

    fun parse(data: String): Long {
        val lines = data.lines()
        require(lines.size == digitSize) { "Input data must contain exactly 3 lines." }

        val parsedOptions = mutableMapOf<Int, Set<DigitImage>>()
        lines.forEachIndexed { ix, line ->
            var part = ""
            var position = 0
            
            for (symbol in line) {
                part += symbol
                
                if (part.length > digitSize) {
                    if (symbol.isWhitespace().not()) {
                        throw IllegalArgumentException("Input data contains unexpected characters.")
                    }
                    part = ""
                    continue
                }
                if (part.length == digitSize) {
                    val currentOptions = parsedOptions.getOrElse(position) { digits }
                    val newOptions = clarifyOptions(part, ix, currentOptions)
                    if (newOptions.isEmpty()) {
                        throw IllegalArgumentException("Cannot parse input data.")
                    }
                    parsedOptions[position] = newOptions
                    position++
                }
            }
        }

        return parsedOptions.toSortedMap().values
            .map { getFinalDigitValue(it) }
            .reduce { acc, it -> acc * 10 + it }
    }

    private fun clarifyOptions(part: String, partIx: Int, options: Set<DigitImage>) =
        options.filter { part == it.image[partIx] }.toSet()
    
    private fun getFinalDigitValue(options: Set<DigitImage>): Long {
        if (options.size > 1) {
            throw IllegalArgumentException("More than one option for a digit.")
        }
        return options.single().value.toLong()
    }

}