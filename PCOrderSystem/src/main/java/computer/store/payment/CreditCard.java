package computer.store.payment;

import java.util.*;

/**
 * the CreditCard class represents a simple and unchangeable credit card
 * each card has a unique number an expiry date and a cardholder name
 * once created, a credit card object cannot be changed (it’s immutable)
 */

public final class CreditCard {
    private static final Set<String> issuedNumbers = new HashSet<>();

    private final String number;
    private final Date expiry;
    private final String holderName;

    private CreditCard(String number, Date expiry, String holderName) {
        this.number = number;
        this.expiry = new Date(expiry.getTime());
        this.holderName = holderName;
    }

    /**
     * it creates a new Credit Card after checking that all details are valid
     * @param number the 8-digit card number
     * @param expiry the expiry date of the card
     * @param holderName the name of the cardholder
     * @return it returns a new Credit Card object
     * @throws IllegalArgumentException if the detail are invalid or the number already exist
     */
    public static CreditCard valueOf(String number, Date expiry, String holderName) {
        if (number == null || number.length() != 8 || !number.chars().allMatch(Character::isDigit))
            throw new IllegalArgumentException("Card must have 8 digits");

        if (expiry == null || holderName == null || holderName.isBlank())
            throw new IllegalArgumentException("Invalid card details");

        if (issuedNumbers.contains(number))
            throw new IllegalArgumentException("Duplicate card number");

        issuedNumbers.add(number);
        return new CreditCard(number, expiry, holderName);
    }

    /**
     * it clears all saved card numbers
     * this is mainly used for testing, not in real applications.
     */

    public static void resetRegistry() {
        issuedNumbers.clear();
    }

    /**
     * it checks if the card is still valid
     * @return returns true if the expiry date is in the future, otherwise false
     */

    public boolean isValid() {
        return expiry.after(new Date());
    }

    /**
     * it gets the cardholder name
     * @return returns the cardholder name
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * it gets the card number
     * @return returns the 8-digit card number
     */
    public String getNumber() {
        return number;
    }

    /**
     * it gets a copy of the card’s expiry date
     * @return returns the expiry date
     */
    public Date getExpiry() {
        return new Date(expiry.getTime());
    }

    /**
     * returns a simple text version of the card details
     * @return it returns a string with the holder name and card number
     */
    @Override
    public String toString() {
        return holderName + " (" + number + ")";
    }
}
