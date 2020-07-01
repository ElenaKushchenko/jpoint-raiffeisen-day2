package ru.ekushchenko.jpoint.raiffiesen.day2

class DigitImage(
    val value: Int,
    val image: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DigitImage

        if (value != other.value) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value
        result = 31 * result + image.contentHashCode()
        return result
    }
}