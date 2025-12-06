package Payment;

import computer.store.payment.CreditCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @BeforeEach
    void setup() {
        CreditCard.resetRegistry(); //it creates all the previous card details
    }

    @Test
    void testValidCard() {
        Calendar calenderr = Calendar.getInstance();
        calenderr.add(Calendar.MONTH, 6);
        CreditCard card = CreditCard.valueOf("88300737", calenderr.getTime(), "Adixx Kute");
        assertTrue(card.isValid());
        assertEquals("Adixx Kute", card.getHolderName());
    }

    @Test
    void testDuplicateCard() {
        Calendar calenderr = Calendar.getInstance();
        calenderr.add(Calendar.MONTH, 6);
        CreditCard.valueOf("73850466", calenderr.getTime(), "Adixx Kute");
        assertThrows(IllegalArgumentException.class, () ->
                CreditCard.valueOf("73850466", calenderr.getTime(), "Adixx Kute"));
    }

    @Test
    void testInvalidCardNumber() {
        Calendar calenderr = Calendar.getInstance();
        calenderr.add(Calendar.MONTH, 6);
        assertThrows(IllegalArgumentException.class,
                () -> CreditCard.valueOf("123", calenderr.getTime(), "Adixx Kute"));
    }
}
