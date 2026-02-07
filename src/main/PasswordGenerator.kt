import kotlin.random.Random

fun main() {
    val length = 12
    val password = generatePassword(length)
    println("Generated password: $password")
}

fun generatePassword(length: Int): String {
    val chars =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz" +
        "0123456789" +
        "!@#$%^&*()-_=+[]{}|;:,.<>?"

    return (1..length)
        .map { chars[Random.nextInt(chars.length)] }
        .joinToString("")
}
