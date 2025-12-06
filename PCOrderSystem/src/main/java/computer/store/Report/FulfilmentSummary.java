

package computer.store.Report;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * this class represents a report of all orders grouped by manufacturer
 * along with the total number of parts to collect
 * it provides immutable access to order and collection data to ensure data consistency throughout the system
 * defensive programming: input parameters are checked for null values and converted into unmodifiable maps to prevent accidental modification
 */

public final class FulfilmentSummary {
    private final Map<String, Map<String, Integer>> manufacturerOrders;
    private final Map<String, Integer> partsToCollect;

    /**
     * Constructs a new FulfilmentSummary instance with the specified manufacturer order and parts to collect.
     * @param manufacturerOrders a map where each key represents a manufacturer and its value is another map containing part name and their corresponding quantities ordered
     * @param partsToCollect it is all the parts that are to be collected and there required quantity
     * @throws IllegalArgumentException if either manufacturerOrders or partsToCollect is null or empty
     */
    public FulfilmentSummary(Map<String, Map<String, Integer>> manufacturerOrders,
                             Map<String, Integer> partsToCollect) {
        //checks if the input is null
        if (manufacturerOrders == null || partsToCollect == null) {
            throw new IllegalArgumentException("manufacturerOrders or partsToCollect is null which is not allowed ");
        }

        // convert the input map into stream of entries(basically key-value pair)
        this.manufacturerOrders = manufacturerOrders.entrySet().stream()

                //collects the entries into unmodifiable map
                .collect(Collectors.toUnmodifiableMap(
                        Map.Entry::getKey,
                        e -> Map.copyOf(e.getValue())
                ));

        this.partsToCollect = Map.copyOf(partsToCollect);
    }

    /**
     * it returns an unchangeable view of all the manufacturer order.
     * @return an unmodifiable map containing manufacturers and their corresponding
     *         parts with quantities
     */
    public Map<String, Map<String, Integer>> getManufacturerOrders(){
        return manufacturerOrders;
    }

    /**
     * returns an unmodifiable view of all parts that need to be collected.
     * @return an unmodifiable map containing parts and their required quantities
     */
    public Map<String, Integer> getPartsToCollect() {
        return partsToCollect;
    }
}
