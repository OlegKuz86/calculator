import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) {
        String[] data = input.split(" ");

        if (data.length != 3) throw new CalculationException("format of the mathematical operation doesn't satisfy " +
                "the task");

        boolean isRoman = false;
        int a;
        int b;

        try {
            a = Integer.parseInt(data[0]);
            b = Integer.parseInt(data[2]);
        } catch (NumberFormatException e) {
            a = RomanNumeral.convertToArabicUpToTen(data[0]);
            b = RomanNumeral.convertToArabicUpToTen(data[2]);
            isRoman = true;
        }

        if (!isRoman) checkArabNumber(a, b);

        int result;

        switch (data[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new CalculationException("the operation isn't supported");
        }

        return isRoman ? RomanNumeral.convertToString(result) : String.valueOf(result);
    }

    private static void checkArabNumber(int... elements) {
        for (int element : elements) {
            if (element < 1 || element > 10) throw new NumberException("the number must be from 1 to 10");
        }
    }
}

