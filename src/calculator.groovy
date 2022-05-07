println """=== Welcome to Groovy Calculator ===
Available actions:
\tadd (+)
\tsubtract (-)
\tmultiply (*)
\tdivide (/)
\tfree entry(?)
"""

Scanner scanner = new Scanner(System.in)
print "Action: "
try {
    Closure action = getAction scanner.nextLine()
    if (action) {
        print "First number: "
        int numberOne = parseInt scanner.nextLine()
        print "Second number: "
        int numberTwo = parseInt scanner.nextLine()
        println "Result: " + action(numberOne, numberTwo)
    } else {
        println "(example: 1+2*3)"
        print "Expression: "
        println "Result: " + calcFreeEntry(scanner.nextLine())
    }
} catch (ArithmeticException e) {
    throwError "ArithmeticException: " + e.message
} catch (NumberFormatException ignored) {
    throwError "Could not find number in entry"
} catch (Exception ignored) {
    throwError "Could not parse input"
}

def getAction(String input) {
    switch (input.toLowerCase()) {
        case "add", "+" -> { int a, int b -> a + b }
        case "subtract", "-" -> { int a, int b -> a - b }
        case "multiply", "*" -> { int a, int b -> a * b }
        case "divide", "/" -> { int a, int b -> a / b }
        case "free entry", "?" -> null
        default -> throwError "There is no such action"
    }
}

def calcFreeEntry(String input) {
    // The easy way -> Eval.me(input.replaceAll("[^-+*/\\d]", ""))
    String[] tokens = input.replaceAll("[^-+*/\\d]", "").split("(?<=\\d[-+*/])|(?=[-+*/])")
    def operatorsQueue = [["*", "/"], ["+", "-"]]
    def operation = null
    def calculations = []
    operatorsQueue.each { operators ->
        tokens.each { token ->
            if (operators.contains(token)) {
                operation = getAction(token)
            } else if (operation) {
                def lastIndex = calculations.size() - 1
                calculations[lastIndex] = operation(calculations[lastIndex] as int, token as int)
                operation = null
            } else {
                calculations.add(token)
            }
        }
        tokens = calculations
        calculations.clear()
    }
    return tokens[0]
}

int parseInt(String input) {
    (input.contains("-") ? "-" : "") + input.replaceAll("\\D", "") as int
}

void throwError(message) {
    println message
    System.exit 1
}
