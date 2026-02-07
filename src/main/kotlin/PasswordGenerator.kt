import kotlin.random.Random

fun main(args: Array<String>) {
    val settings = parseArgs(args)
    val password = generatePassword(settings)
    println("Generated password: $password")
}

fun generatePassword(settings: Settings): String {
    var chars = ""

    if (settings.includeUppercase) chars += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    if (settings.includeLowercase) chars += "abcdefghijklmnopqrstuvwxyz"
    if (settings.includeNumbers) chars += "0123456789"
    if (settings.includeSymbols) chars += "!@#$%^&*()-_=+[]{}|;:,.<>?"

    require(chars.isNotEmpty()) {
        "At least one character type must be enabled"
    }

    return (1..settings.length)
        .map { chars[Random.nextInt(chars.length)] }
        .joinToString("")
}

fun parseArgs(args: Array<String>): Settings {
    var length = 12
    var upper = true
    var lower = true
    var numbers = true
    var symbols = true

    args.forEachIndexed { index, arg ->
        when (arg) {
            "--length" -> length = args.getOrNull(index + 1)?.toIntOrNull() ?: length
            "--no-upper" -> upper = false
            "--no-lower" -> lower = false
            "--no-numbers" -> numbers = false
            "--no-symbols" -> symbols = false
        }
    }

    return Settings(
        length = length,
        includeUppercase = upper,
        includeLowercase = lower,
        includeNumbers = numbers,
        includeSymbols = symbols
    )
}
