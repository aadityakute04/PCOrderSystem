package computer.store.order;

import computer.store.Report.FulfilmentSummary;
import computer.store.customer.Customer;
import computer.store.payment.CreditCard;
import computer.store.model.COMPUTERMODEL;
import computer.store.model.PresetModel;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * this class handles all order related things
 * it can place, cancel and fulfill orders
 * it can also find details like top customer and the most ordered item
 */

public class OrderManager {

    private final Map<String, Order> allOrders = new HashMap<>();
    private final List<Order> fulfilledOrders = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    /**
     * it creates and places a new order
     * checks if the payments card is valid before placing
     * @param models list of computer models in the order
     * @param customer the customer placing the order
     * @param card the credit card used for payment
     * @return it returns the newly created Order object
     * @throws IllegalArgumentException if the card is invalid
     */

    public Order placeOrder(List<COMPUTERMODEL> models, Customer customer, CreditCard card) {
        if (!card.isValid()) throw new IllegalArgumentException("Your card is invalid");
        String id = "Ord-" + idCounter.getAndIncrement();
        Order ord = new Order(id, models, customer, card, new Date());
        allOrders.put(id, ord);
        return ord;
    }

    /**
     * it cancels an existing order
     * orders that are already fulfilled cannot be cancelled
     * @param order the order to cancel
     * @throws IllegalArgumentException if the order is not found
     * @throws IllegalStateException if the order is already been fulfilled
     */

    public void cancelOrder(Order order) {
        Order ord = allOrders.get(order.getId());
        if (ord == null) throw new IllegalArgumentException("This Order is Unknown");
        if (ord.getStatus() == OrderStatus.FULFILLED)
            throw new IllegalStateException("The order that are fulfilled can not be cancelled");
        ord.setStatus(OrderStatus.CANCELLED);
    }

    /**
     * it marks an order as fulfilled and creates a summary report
     * it can add fulfilled order to the list for record keeping
     * @param order the order to fulfill
     * @return it returns a FulfilmentSummary with detail about part and manufacturer
     * @throws IllegalArgumentException if the order is null
     * @throws IllegalStateException if the order is cancelled or already fulfilled
     */

    public FulfilmentSummary fulfillOrder(Order order) {
        Order ord = allOrders.get(order.getId());
        if (ord == null) throw new IllegalArgumentException("order cannot be null");
        if (ord.getStatus() == OrderStatus.CANCELLED)
            throw new IllegalStateException("Cancelled order cannot be fulfilled");
        if (ord.getStatus() == OrderStatus.FULFILLED)
            throw new IllegalStateException("The order is already fulfilled");

        Map<String, Map<String, Integer>> manuf = new HashMap<>();
        Map<String, Integer> parts = new HashMap<>();

        for (COMPUTERMODEL model : ord.getModels()) {
            if (model instanceof PresetModel) {
                PresetModel presetmodel = (PresetModel) model;
                manuf.computeIfAbsent(presetmodel.getManufacturer(), k -> new HashMap<>())
                        .merge(presetmodel.getName(), 1, Integer::sum);
            } else {
                for (String part : model.getParts())
                    parts.merge(part, 1, Integer::sum);
            }
        }

        ord.setStatus(OrderStatus.FULFILLED);
        fulfilledOrders.add(ord);
        return new FulfilmentSummary(manuf, parts);
    }

    /**
     * it finds the customer who placed most fulfilled order
     * @return it returns the top customer and their order count if it found
     */

    public Optional<Map.Entry<Customer, Integer>> getLargestCustomer() {
        Map<Customer, Integer> counts = new HashMap<>();
        for (Order ord : fulfilledOrders)
            counts.merge(ord.getCustomer(), 1, Integer::sum);

        return counts.entrySet().stream()
                .sorted(Map.Entry.<Customer, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(e -> e.getKey().getFullName()))
                .findFirst();
    }

    /**
     * it finds the most ordered preset computer model
     * @return it returns the model name and how many times that model was ordered
     */

    public Optional<Map.Entry<String, Integer>> getMostOrderedModel() {
        Map<String, Integer> counts = new HashMap<>();
        for (Order ord : fulfilledOrders) {
            for (COMPUTERMODEL pcmodel : ord.getModels()) {
                if (pcmodel instanceof PresetModel) {
                    PresetModel presetmodel = (PresetModel) pcmodel;
                    String key = presetmodel.getManufacturer() + "::" + presetmodel.getName();
                    counts.merge(key, 1, Integer::sum);
                }
            }
        }
        return counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry::getKey))
                .findFirst();
    }

    /**
     * finds the most commonly ordered computer part from all fulfilled custom model orders
     * @return it returns the part name and how many time it was ordered
     */

    public Optional<Map.Entry<String, Integer>> getMostOrderedPart() {
        Map<String, Integer> counts = new HashMap<>();
        for (Order ord : fulfilledOrders) {
            for (COMPUTERMODEL pcmodel : ord.getModels()) {
                if (!(pcmodel instanceof PresetModel)) {
                    for (String part : pcmodel.getParts())
                        counts.merge(part, 1, Integer::sum);
                }
            }
        }
        return counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry::getKey))
                .findFirst();
    }
}
