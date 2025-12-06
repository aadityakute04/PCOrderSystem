package computer.store.order;

public enum OrderStatus {
    /** customer has placed a order */
    PLACED,
    /** the order was cancelled and cannot be processed */
    CANCELLED,
    /** the order has been successfully fulfilled and completed */
    FULFILLED
}
