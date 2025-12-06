package computer.store.order;

import computer.store.payment.CreditCard;
import computer.store.model.COMPUTERMODEL;
import computer.store.customer.Customer;

import java.util.*;

/**
 * this class is used to store all the details of an order
 * it keeps track of the customer, models, payment card, date, and the current order status
 */

public final class Order {
    private final String id;
    private final List<COMPUTERMODEL> models;
    private final Customer customer;
    private final CreditCard card;
    private final Date placedAt;
    private OrderStatus status;

    /**
     * Creates a new order with all the required details
     * Checks that all important fields are not empty or null
     * @param id        the order ID
     * @param models    the list of computer models in the order
     * @param customer  the customer who placed the order
     * @param card      the credit card used for payment
     * @param placedAt  the date when the order was placed
     * @throws IllegalArgumentException if any required field is missing or empty
     */
    public Order(String id, List<COMPUTERMODEL> models, Customer customer, CreditCard card, Date placedAt) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("ID is required");
        if (models == null || models.isEmpty()) throw new IllegalArgumentException("Model is required");
        if (customer == null || card == null || placedAt == null)
            throw new IllegalArgumentException("Details of order are missing");
        this.id = id;
        this.models = List.copyOf(models);
        this.customer = customer;
        this.card = card;
        this.placedAt = new Date(placedAt.getTime());
        this.status = OrderStatus.PLACED;
    }

    /**
     * gets the ID of this order
     * @return returns the order ID
     */

    public String getId() {
        return id;
    }

    /**
     * it gets the list of computer model included in this order
     * @return it returns list of computer model
     */
    public List<COMPUTERMODEL> getModels() {
        return models;
    }

    /**
     * it gets the customer who placed this order
     * @return it returns customer details
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * it gets the credit card used for this order
     * @return it returns the credit card details
     */
    public CreditCard getCreditCard() {
        return card;
    }

    /**
     * it gets the date when the order was placed
     * it returns a copy in order to keep the original date safe
     * @return it returns the date on which order was placed
     */
    public Date getPlacedAt() {
        return new Date(placedAt.getTime());
    }

    /**
     * it gets the current status of the ordre
     * @return it returns the order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * it changes the status of the order
     * @param orderstatus the new status to set
     */
    public void setStatus(OrderStatus orderstatus) {
        status = orderstatus;
    }
}
