import java.util.*;

public class Driver {
    public static void main(String[] args){
        List<Character> operators = Arrays.asList('+', '-', '*', '/');
        System.out.println("Thank you for using THCalculator v1. Please enter: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.replace(" ", "");
        int result = 0;
        int right = 0;
        int operatorFlag = 0;

        while(right < input.length()){
            char ch = input.charAt(right);
            if(!Character.isDigit(ch) && !operators.contains(ch)){
                System.out.println("Invalid input");
                break;
            }

            int left = right;
            while(right != input.length() && Character.isDigit(input.charAt(right))){
                right++;
            }

            if(operatorFlag == 0){
                result += Integer.parseInt(input.substring(left, right));
            }else if(operatorFlag == 1){
                result -= Integer.parseInt(input.substring(left, right));
            }else if(operatorFlag == 2)
                result *= Integer.parseInt(input.substring(left, right));
            else if(operatorFlag == 3)
                result /= Integer.parseInt(input.substring(left, right));

            if(right != input.length() && operators.contains(input.charAt(right))){
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

        System.out.println(result);
    }
}
