import java.util.*;

public class Driver {
    public static void main(String[] args){
        int result = 0;
        int right = 0;
        int operatorFlag = 0;
        List<Character> operators = Arrays.asList('+', '-', '*', '/');

        System.out.println("Thank you for using THCalculator v1. Please enter: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace(" ", "");
        if (!validate(input))
            right = -1;
        else
            input = pemdas(input);

        while(right >= 0 && right < input.length()){
            char ch = input.charAt(right);

            int left = right;
            if(ch == '-')
                right++;
            while(right < input.length() && Character.isDigit(input.charAt(right))){
                right++;
            }

            int operand = Integer.parseInt(input.substring(left, right));

            if(operatorFlag == 0){
                result += operand;
            }else if(operatorFlag == 1){
                result -= operand;
            }else if(operatorFlag == 2)
                result *= operand;
            else if(operatorFlag == 3)
                result /= operand;

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

        while(index < input.length()){
            char ch = input.charAt(index);

            //case #1: trigger for invalid characters
            if(!Character.isDigit(ch) && !validSymbols.contains(ch) && ch != '-') {
                System.out.println("Invalid Input");
                return false;
            }

            //case #2: trigger if first or last char is non-number or '-
            if((index == 0 || index == input.length()-1) && validSymbols.contains(ch)){
                System.out.println("Invalid Syntax2");
                return false;
            }

            if(validSymbols.contains(ch))
                symbolsInRow++;
            else
                symbolsInRow = 0;

            //case #3: trigger if there are two operators in a row
            if(symbolsInRow == 2){
                System.out.println("Invalid Syntax3");
                return false;
            }

            //case #4: trigger if there is an incorrect placement of '-'
            if(ch == '-'){
                //last char cannot be negative
                //next char after '-' must be another '-' or a digit
                //first two char cannot be '-'
                if(index == input.length()-1){
                    System.out.println("Invalid Syntax4a");
                    return false;
                }if(input.charAt(index+1) != '-' && !Character.isDigit(input.charAt(index+1))){
                    System.out.println("Invalid Syntax4b");
                    return false;
                }
                if(index == 1 && minusInRow == 1){
                    System.out.println("Invalid Syntax4c");
                    return false;
                }
                minusInRow++;
            }else
                minusInRow = 0;

            //case #5: trigger if there are three '-' in a row
            if(minusInRow == 3){
                System.out.println("Invalid Syntax5");
                return false;
            }

            index++;
        }
        return true;
    }

    //evaluate the multiplication and division first
    public static String pemdas(String input){
        String output = "";
        int right = 0;
        List<Character> validSymbols = Arrays.asList('+', '*', '/');

        while(right < input.length()){
            char ch = input.charAt(right);

            int left = right;
            if(ch == '-')
                right++;
            while(right < input.length() && Character.isDigit(input.charAt(right))){
                right++;
            }
            int operand = Integer.parseInt(input.substring(left, right));

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
                if(ch == '-')
                    right++;
                while(right < input.length() && Character.isDigit(input.charAt(right))){
                    right++;
                }
                int second_operand = Integer.parseInt(input.substring(left, right));
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
