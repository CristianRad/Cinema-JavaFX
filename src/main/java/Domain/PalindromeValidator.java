package Domain;

public class PalindromeValidator {

    /**
     * Verifies if a number is palindrome.
     * @param id is the ID of the entity to validate.
     * @return true if the number is palindrome and false otherwise.
     */

    public static boolean isPalindrome(String id) {
        int number = Integer.parseInt(id);
        int dumy = number;
        int reversedNumber = 0;

        while (dumy != 0) {
            reversedNumber = reversedNumber * 10 + dumy % 10;
            dumy = dumy / 10;
        }

        if (number == reversedNumber)
            return true;
        return false;
    }

}
