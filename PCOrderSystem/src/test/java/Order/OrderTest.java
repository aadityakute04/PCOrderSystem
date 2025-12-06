package Order;

import computer.store.order.*;
import computer.store.customer.Customer;
import computer.store.model.COMPUTERMODEL;
import computer.store.model.CustomModel;
import computer.store.model.ModelGenerator;
import computer.store.model.PresetModel;
import computer.store.order.Order;
import computer.store.order.OrderStatus;
import org.junit.jupiter.api.Test;
import computer.store.payment.CreditCard;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testOrderCreationValid() {
        // Arrange
        Customer customer = new Customer("Adixxx", "Kute");

        Calendar calenderr = Calendar.getInstance();
        calenderr.add(Calendar.MONTH, 6);
        CreditCard card = CreditCard.valueOf("12345678", calenderr.getTime(), customer.getFullName());

        ModelGenerator factory = new ModelGenerator();
        PresetModel preset = factory.createPresetModel("PH", "DESKELITS", List.of("CPU", "RAM"));
        CustomModel custom = factory.createCustomModel("CUSTOMMODEL001", List.of("GPU", "SSD"));
        List<COMPUTERMODEL> models = List.of(preset, custom);

        Date now = new Date();

        // Act
        Order order = new Order("ord1", models, customer, card, now);

        // Assert
        assertEquals("ord1", order.getId());
        assertEquals(OrderStatus.PLACED, order.getStatus());
        assertEquals(customer, order.getCustomer());
        assertEquals(card, order.getCreditCard());
        assertEquals(models.size(), order.getModels().size());
        assertEquals(models, order.getModels(), "Contains in the model list should match");

    }

    @Test
    void testOrderStatusChange() {
        Customer customer = new Customer("Adixx", "Kute");

        Calendar calenderr = Calendar.getInstance();
        calenderr.add(Calendar.MONTH, 6);
        CreditCard card = CreditCard.valueOf("43728743", calenderr.getTime(), customer.getFullName());

        ModelGenerator factory = new ModelGenerator();
        PresetModel preset = factory.createPresetModel("elld", "abc", List.of("CPU", "SSD"));
        List<COMPUTERMODEL> models = List.of(preset);

        Order order = new Order("ord2", models, customer, card, new Date());

        assertEquals(OrderStatus.PLACED, order.getStatus());
        order.setStatus(OrderStatus.CANCELLED);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        order.setStatus(OrderStatus.FULFILLED);
        assertEquals(OrderStatus.FULFILLED, order.getStatus());
    }

    @Test
    void testInvalidOrderCreation() {
        Customer customer = new Customer("Adixx", "Kute");
        Calendar calenderr = Calendar.getInstance();
        calenderr.add(Calendar.MONTH, 6);
        CreditCard card = CreditCard.valueOf("74832223", calenderr.getTime(), customer.getFullName());

        ModelGenerator factory = new ModelGenerator();
        PresetModel preset = factory.createPresetModel("Levono", "bcd", List.of("CPU", "RAM"));
        Date now = new Date();

        assertThrows(IllegalArgumentException.class,
                () -> new Order("", List.of(preset), customer, card, now));

        assertThrows(IllegalArgumentException.class,
                () -> new Order("ord3", null, customer, card, now));

        assertThrows(IllegalArgumentException.class,
                () -> new Order("ord4", List.of(preset), null, card, now));

        assertThrows(IllegalArgumentException.class,
                () -> new Order("ord5", List.of(preset), customer, null, now));

        assertThrows(IllegalArgumentException.class,
                () -> new Order("ord6", List.of(preset), customer, card, null));
    }
}
