package Customer;

import computer.store.customer.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testFullNameAndEquality() {
        Customer cust1 = new Customer("Adixxx", "Kute");
        Customer cust2 = Customer.valueOf("Adixxx Kute");
        assertEquals(cust1, cust2);
        assertEquals("Adixxx Kute", cust1.getFullName());
    }

    @Test
    void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("", "Kute"));
        assertThrows(IllegalArgumentException.class, () -> Customer.valueOf("Adixxx"));
    }
}
