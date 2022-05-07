# Groovy Calculator

Console application script that can perform basic arithmetic operations from user input.

## Running

```shell
$ groovy src/calculator.groovy
```

## Usage

Enter desired action and numbers.

```
Action: multiply
First number: 2
Second number: 5
Result: 10
```

Application ignores all foreign character from number input. Exception `-` (minus), if it's present in input - number
will be considered negative.

```
Action: add
First number: 7jj
Second number: f3c5
Result: 42
```

In **free entry** mode input arithmetic expression, supported operations: `+ - * /`

```
Action: free entry
Expression: 2*3+3*3
Result: 15
```
