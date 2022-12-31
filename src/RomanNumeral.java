enum RomanNumeral {

    I(1),
    II(2),
    III(3),
    IV(4),
    V(5),
    VI(6),
    VII(7),
    VIII(8),
    IX(9),
    X(10),
    XL(40),
    L(50),
    XC(90),
    C(100);

    private final int arabicValue;

    RomanNumeral(int arabicValue) {
        this.arabicValue = arabicValue;
    }

    public static int convertToArabicUpToTen(String input) {
        RomanNumeral[] romanNumerals = RomanNumeral.values();

        for (int i = 0; i < 10; i++) {
            RomanNumeral current = romanNumerals[i];
            if (input.equals(current.name())) return current.arabicValue;
        }

        throw new NumberException("Calculator rule violated");
    }

    public static String convertToString(int number) {
        if (number < 1) throw new NumberException("Roman numeral must be positive");

        RomanNumeral[] romanNumerals = RomanNumeral.values();

        if (number <= 10) return romanNumerals[number - 1].name();

        for (int i = 10; i < romanNumerals.length; i++) {
            int current = romanNumerals[i].arabicValue;

            if (number == current) return romanNumerals[i].name();

            if (number < current) {
                int index = i - 1;
                StringBuilder builder = new StringBuilder();
                while (number != 0) {
                    RomanNumeral romanNumeral = romanNumerals[index--];
                    int value = romanNumeral.arabicValue;
                    int count = number / value;
                    if (count < 1) continue;
                    builder.append(romanNumeral.name().repeat(count));
                    number -= value * count;
                }
                return builder.toString();
            }
        }

        return null;
    }
}
