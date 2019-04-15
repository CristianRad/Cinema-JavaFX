package Domain;

public class PalindromeValidator {

    /**
     * Finds the palindrome of a given number.
     * @param number is the number whose palindrome needs to be found.
     */

    public static int getPalindrome(int number) {
        int dumy = number;
        int reversedNumber = 0;
        while (dumy != 0) {
            reversedNumber = reversedNumber * 10 + dumy % 10;
            dumy = dumy / 10;
        }
        return reversedNumber;
    }

    /**
     * Validates an ID.
     * @param id is the ID to validate.
     * @throws PalindromeValidatorException if there are validation errors.
     */

    public static void validate(String id) {
        String errors = "";

        if (Integer.parseInt(id) != getPalindrome(Integer.parseInt(id)))
            errors += String.format("The ID (%s) is not palindrome!", id);

        if (!errors.isEmpty())
            throw new PalindromeValidatorException("\n" + errors);
    }

}
