import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение с двумя арабскими или римскими числами:  ");
        String expression = scanner.nextLine().toUpperCase();
        System.out.println(action(expression));
    }

    public static String action(String expression) throws Exception {
        int num1;
        int num2;
        String operand;
        String result;
        boolean isRoman;

        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнда и один оператор(+,-,* или /).");
        operand = detectOperation(expression);
        if (Rim.isRoman(operands[0]) && Rim.isRoman(operands[1])) {
            num1 = Rim.convertToArab(operands[0]);
            num2 = Rim.convertToArab(operands[1]);
            isRoman = true;
        }
        else if(!Rim.isRoman(operands[0]) && !Rim.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Оба числа должны быть в одной системе исчесления и больше 0.");
        }

        if (num1>10 || num2>10){
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arab = calculate(num1,num2,operand);
        if (isRoman) {
            if(arab <=0) {
                throw new Exception("Римсмкое число должно быть больше нуля");
            }
            result = Rim.convertToRim(arab);
        } else {
            result = String.valueOf(arab);
        }
        return result;
    }
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calculate(int a,int b, String operand) {
        return switch (operand) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}
class Rim {
        static String[] rimArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};

        public static boolean isRoman(String value) {
            for (String s : rimArray) {
                if (value.equals(s)) {
                    return true;
                }
            }
            return false;
        }

        public static int convertToArab(String rim) {
            for (int i = 0; i < rimArray.length; i++) {
                if (rim.equals(rimArray[i])) {
                    return i;
                }
            }
            return -1;
        }


        public static String convertToRim(int arab) {
        return rimArray[arab];
        }
}