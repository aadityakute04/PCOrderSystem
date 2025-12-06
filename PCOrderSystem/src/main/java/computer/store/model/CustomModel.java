package computer.store.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * this class is used to create a custom computer model
 * you can customise it as needed(you can add or remove parts as you want)
 * it also gives the model name and list of all parts
 */

public class CustomModel implements COMPUTERMODEL {

    private final String name;
    private final List<String> parts;

    /**
     * makes the new custom model with name and the starting part
     * @param name its the model name
     * @param initialParts the list of parts (in the start)
     * @throws IllegalArgumentException if name or part are empty(null)
     */
    public CustomModel(String name, List<String> initialParts) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("The name cannot be empty");
        if (initialParts == null)
            throw new IllegalArgumentException("The part cannot be empty");
        this.name = name;
        this.parts = new ArrayList<>(initialParts);
    }

    /**
     * It adds a new part to the model
     * @param part its the part to add
     * @throws IllegalArgumentException if the part is empty(null)
     */

    public void addPart(String part) {
        if (part == null || part.isBlank()) throw new IllegalArgumentException("Part required");
        parts.add(part);
    }

    /**
     * it removes a part from the model
     * @param part its the part to remove
     * @return returns true if the part was found and removed otherwise it gives false
     */

    public boolean removePart(String part) {
        return parts.remove(part);
    }

    /**
     * it gets the name of the model
     * @return returns the model name
     */

    @Override
    public String getName() {
        return name;
    }

    /**
     *  it gets the list of all the part that cannot be changed
     * @return returns the list of parts
     */

    @Override
    public List<String> getParts() {
        return Collections.unmodifiableList(parts);
    }

    /**
     * returns a simple text version of the model with its name and part
     * @return returns the model details as text
     */

    @Override
    public String toString() {
        return "CustomModel{" + name + ", parts=" + parts + "}";
    }

    /**
     * checks if 2 custom model have the same name
     * @param Obj another object to compare
     * @return returns true if both models have the same name
     */
    @Override
    public boolean equals(Object Obj) {
        if (this == Obj) return true;
        if (!(Obj instanceof CustomModel)) return false;
        CustomModel temp = (CustomModel) Obj;
        return name.equals(temp.name);
    }

    /**
     * returns a number based on a model name for comparison purpose
     * @return it returns hash code value
     */

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
