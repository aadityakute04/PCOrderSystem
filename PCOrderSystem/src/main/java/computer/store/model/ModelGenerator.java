package computer.store.model;

import java.util.*;

/**
 * this class is used to create and manage different computer models
 * it can make both preset and custom model and also can keep record of all custom model names that have been created
 */

public final class ModelGenerator {

    private final Map<String, CustomModel> customModels = new HashMap<>();

    /**
     * it makes a new preset model with the given details
     * @param manufacturer its the name of the company that makes that model
     * @param name its the model name
     * @param parts its the list of parts in the model
     * @return returns a new PresetModel object
     */
    public PresetModel createPresetModel(String manufacturer, String name, List<String> parts) {
        return new PresetModelImpl(manufacturer, name, parts);
    }

    /**
     * it creates a new custom model and store it in a list
     * if there exist a model with the similar name it will throw an error
     * @param name it is the name of the custom model
     * @param parts it is the list of the parts for the model
     * @return returns a new CustomModel object
     * @throws IllegalArgumentException if the model name already exists
     */

    public synchronized CustomModel createCustomModel(String name, List<String> parts) {
        if (customModels.containsKey(name))
            throw new IllegalArgumentException("THE CUSTOM MODEL ALREADY EXISTS: " + name);
        CustomModel custmodel = new CustomModel(name, parts);
        customModels.put(name, custmodel);
        return custmodel;
    }

    /**
     * it gets the names of all the saved custom model
     * this set cannot be changed from outside the class
     * @return it returns a unchangeable set of model name
     */

    public Set<String> getCustomModelNames() {
        return Collections.unmodifiableSet(customModels.keySet());
    }
}
