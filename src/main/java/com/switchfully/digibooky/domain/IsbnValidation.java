package com.switchfully.digibooky.domain;

public class IsbnValidation {
    private String isbnNumber;

    public static boolean isIsbn13(String isbnNumber) {
        return hasValidPrefix(isbnNumber) && hasValidLength(isbnNumber) && hasValidCheckSum(isbnNumber);
    }

    private static boolean hasValidPrefix(String isbn) {
        return isbn.startsWith("978");
    }

    private static boolean hasValidLength(String isbn) {
        return isbn.length() == 13;
    }

    private static boolean hasValidCheckSum(String isbnNumber) {
        int sum = 38;
        for (int i = 3; i < 12; i += 2) {
            int digit = Character.getNumericValue(isbnNumber.charAt(i));
            sum += (digit * 3) + Character.getNumericValue(isbnNumber.charAt(i + 1));
        }
        return sum % 10 == 0;
    }

}
