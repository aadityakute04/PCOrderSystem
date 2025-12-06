package computer.store.model;

import java.util.List;

/**
 * this interface is used to define a basic computer model
 * it has methods to get the model name and the list of part
 */

public interface COMPUTERMODEL {
    String getName();
    List<String> getParts();
}

