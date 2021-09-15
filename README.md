# CalculatorProject
by Daniel Khuu

This is a solution to a take home exercise that asks for a calculator program that takes math expression strings as input and prints the answer.

EX:
- calculate "1 + 2" gives 3
- calculate "4*5/2" gives 10
- calculate "-5+-8--11*2" gives 9
- calculate "-.32       /.5" gives -0.64
- calculate "(4-2)*3.5" gives 7
- calculate "2+-+-4" gives Syntax Error (or similar)
- calculate "19 + cinnamon" gives Invalid Input (or similar)

## How to Run Program

1. Import the project to your IDE. 
2. Find the "Driver.java" class in the src folder. 
3. Right-click on the "Driver.java" class and click on "Run". 
4. Once the program runs, there will be a message on the console that reads:

"Thank you for using THCalculator v1. Please enter:"

5. Enter your math expression onto the console and press Enter.

## Functionality Information

This calculator only run once. Once the caulculator prints the answer, the program needs to be re-run.

Here is a list of features that this calculator supports:
1. (+) and (-) for whole numbers
2. (+) and (-) for all integers
3. (*) and (/) for all integers
4. Input Validation
5. Decimals

The feature missing is parentheses. When I first read the exercise description, I knew that parentheses would be the toughest feature, so I saved that for last in case I had time. 

If I did had time, I would have used a stack to order FILO the expressions the levels of parentheses while looping through the input string. Each '(' would push an expression onto the stack while each ')' would pop the last expression off the stack and be computed until the stack is empty. Once the stack is empty, I would append the result to the end of the output string.

I would create a new method called 'calculateParentheses" with the input string as input and the output be a string with all the parentheses parts replaced with its computed value. This will be placed after the validation method and before the pemdas method.