package com.switchfully.digibooky.domain;

public class IsbnValidation {
    public static boolean isIsbn13(String isbnNumber) {
        isbnNumber = formatIsbn(isbnNumber);
        return hasValidPrefix(isbnNumber) && hasValidLength(isbnNumber) && hasValidCheckSum(isbnNumber);
    }

    private static String formatIsbn(String isbnNumber) {
        return isbnNumber.replace("-", "").replace(" ", "");
    }

    private static boolean hasValidPrefix(String isbn) {
        return isbn.startsWith("978");
    }

    private static boolean hasValidLength(String isbn) {
        return isbn.length() == 13;
    }

//    formula for ISBN13 checksum
//    We sum up all digits in the ISBN number based on the following requirements:
//    - For each digit, check if its position in the ISBN number is even or odd.
//    - If the position is even, add the value of the current digit directly to the sum.
//    - If the position is odd, multiply the value of the current digit by 3 and add the result to the sum.
//    The final sum should be a multiple of 10, if it is, then the ISBN13 is valid
    private static boolean hasValidCheckSum(String isbnNumber) {
        int sum = 0;

        for (int i = 0; i < 13; i += 1) {
            int currentDigit = Character.getNumericValue(isbnNumber.charAt(i));
            if (i % 2 == 0) {
                sum += currentDigit;
            } else {
                sum += currentDigit * 3;
            }
        }
        return sum % 10 == 0;
    }

}
