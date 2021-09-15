import java.util.*;

public class Driver {
    public static void main(String[] args){
        double result = 0;
        int right = 0;
        int operatorFlag = 0;

        System.out.println("Thank you for using THCalculator v1. Please enter: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace(" ", "");

        if (!validate(input))
            right = -1;
        else
            input = pemdas(input);

        while(right >= 0 && right < input.length()){
            //parse the operand
            int left = right;
            if(input.charAt(right) == '-')
                right++;
            while(right < input.length() && (Character.isDigit(input.charAt(right)) || input.charAt(right) == '.'))
                right++;

            double operand = Double.parseDouble(input.substring(left, right));

            //depending on the operatorFlag, calculate the new result
            //for the first number, the operatorFlag is set to add to inital value of 0
            if(operatorFlag == 0){
                result += operand;
            }else if(operatorFlag == 1){
                result -= operand;
            }else if(operatorFlag == 2)
                result *= operand;
            else if(operatorFlag == 3)
                result /= operand;

            //set the operatorFlag for the next loop
            if(right != input.length()){
                char sym = input.charAt(right);
                if(sym == '+')
                    operatorFlag = 0;
                else if(sym == '-')
                    operatorFlag = 1;
                else if(sym == '*')
                    operatorFlag = 2;
                else if(sym == '/')
                    operatorFlag = 3;
            }
            right++;
        }

        if(right != -1)
            System.out.println(result);
    }

    //input validation
    public static boolean validate(String input){
        List<Character> validSymbols = Arrays.asList('+', '*', '/');
        int index = 0;
        int symbolsInRow = 0;
        int minusInRow = 0;
        int decimalInRow = 0;

        while(index < input.length()){
            char ch = input.charAt(index);

            //case #1: trigger for invalid characters
            if(!Character.isDigit(ch) && !validSymbols.contains(ch) && ch != '-' && ch != '.') {
                System.out.println("Invalid Input");
                return false;
            }

            //case #2: trigger if first or last char is non-number
            if((index == 0 || index == input.length()-1) && validSymbols.contains(ch)){
                System.out.println("Invalid Syntax");
                return false;
            }

            if(validSymbols.contains(ch))
                symbolsInRow++;
            else
                symbolsInRow = 0;

            //case #3: trigger if there are two operators in a row (excluding '-')
            if(symbolsInRow == 2){
                System.out.println("Invalid Syntax");
                return false;
            }

            //case #4: trigger if there is an incorrect placement of '-'
            //there cannot be three '-' in a row
            //last char cannot be '-'
            //next char after '-' must be another '-', '.', or a number
            //first two char cannot be '-'
            if(ch == '-'){
                if(minusInRow == 2){
                    System.out.println("Invalid Syntax");
                    return false;
                }if(index == input.length()-1){
                    System.out.println("Invalid Syntax");
                    return false;
                }if(input.charAt(index+1) != '-' && !Character.isDigit(input.charAt(index+1)) && input.charAt(index+1) != '.'){
                    System.out.println("Invalid Syntax");
                    return false;
                }if(index == 1 && minusInRow == 1){
                    System.out.println("Invalid Syntax");
                    return false;
                }
                minusInRow++;
            }else
                minusInRow = 0;

            //case #5: trigger if there is an incorrect placement of '.'
            //there cannot be two decimal in a row
            //last char cannot be decimal
            //next char after '.' must be a digit
            if(ch == '.'){
                if(decimalInRow == 1){
                    System.out.println("Invalid Syntax");
                    return false;
                }if(index == input.length()-1){
                    System.out.println("Invalid Syntax");
                    return false;
                }if(!Character.isDigit(input.charAt(index+1))){
                    System.out.println("Invalid Syntax");
                    return false;
                }
                decimalInRow++;
            }else
                decimalInRow = 0;

            index++;
        }
        return true;
    }

    //evaluate the multiplication and division first
    public static String pemdas(String input){
        String output = "";
        int right = 0;

        while(right < input.length()){
            int left = right;
            if(input.charAt(right) == '-')
                right++;
            while(right < input.length() && (Character.isDigit(input.charAt(right)) || input.charAt(right) == '.'))
                right++;
            double operand = Double.parseDouble(input.substring(left, right));

            //add the last character in input string
            if(right == input.length()){
                output += operand;
                break;
            }

            //look at the operator on the right to two cases
            //case 1: (+) and (-) will be left alone
            //case 2: (*) and (/) will calculate the current operand and the next operand on the right
            if(input.charAt(right) == '+') {
                output += operand+ "+";
            }else if(input.charAt(right) == '-'){
                output += operand + "-";
            }else{
                char operator = input.charAt(right);
                right++;

                left = right;
                if(input.charAt(right) == '-')
                    right++;
                while(right < input.length() && (Character.isDigit(input.charAt(right)) || input.charAt(right) == '.'))
                    right++;
                double second_operand = Double.parseDouble(input.substring(left, right));

                if(operator == '*')
                    operand *= second_operand;
                else
                    operand /= second_operand;

                output += operand;

                if(right < input.length())
                    output += input.charAt(right);
            }
            right++;
        }
        return output;
    }
}
