package computer.store.customer;

import java.util.Objects;

/**
 * the class is used to store the customers first name and last name
 * it also provides methods to get the full name of the customer and compare customer details
 */

public final class Customer {
    private final String firstName;
    private final String lastName;

    /**
     * it creates a new customer using the first name and the last name
     * checks weather the first name and last name are not null
     * @param first the customer first name
     * @param last the customer last name
     * @throws IllegalArgumentException if the name fields are kept empty
     */
    public Customer(String first, String last) {
        if (first == null || first.isBlank() || last == null || last.isBlank())
            throw new IllegalArgumentException("Your name is required");
        this.firstName = first.trim();
        this.lastName = last.trim();
    }

    /**
     * it creates a customer object from its full name
     * the full name must have the first name and the last name
     * @param fullName its the full name of the customer
     * @return returns a new customer object with its first and last name
     * @throws IllegalArgumentException if the full name is missing some parts
     */
    public static Customer valueOf(String fullName) {
        String[] parts = fullName.trim().split("\\s+", 2);
        if (parts.length < 2)
            throw new IllegalArgumentException("Enter your first and last name");
        return new Customer(parts[0], parts[1]);
    }

    /**
     * fetches the customers first name
     * @return returns the first name
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * fetches the customers last name.
     * @return returns the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * it basically combines the customers first name and last name into one string
     * @return returns the full name of the customer
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * verifies whether two customers have the same first and last name
     * @param Obj object provided for the comparison purpose
     * @return returns true if both are having the same name otherwise returns false
     */
    @Override
    public boolean equals(Object Obj) {
        if (this == Obj)
            return true;
        if (!(Obj instanceof Customer))
            return false;
        Customer cust = (Customer) Obj;
        return firstName.equals(cust.firstName) && lastName.equals(cust.lastName);
    }

    /**
     * it returns hash code based on the first and last name
     * @return returns the hash code value
     */

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    /**
     * it returns the full name of the customer as a string
     * @return customer full name
     */
    @Override
    public String toString() {
        return getFullName();
    }
}
