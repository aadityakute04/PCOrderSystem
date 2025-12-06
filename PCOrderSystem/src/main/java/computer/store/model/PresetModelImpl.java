package computer.store.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * This class is used for preset computer models
 * These models are made by a manufacturer and cannot be changed
 */

public final class PresetModelImpl implements PresetModel {

    private final String name;
    private final String manufacturer;
    private final List<String> parts;

    /**
     * it creates a new preset model with a manufacturer, name, and list of parts
     * all the fields are checked so that they are not empty
     * @param manufacturer its the company that made the model
     * @param name its the name of the model
     * @param parts its the list of the parts in that model
     * @throws IllegalArgumentException if any field is null or empty
     */
    public PresetModelImpl(String manufacturer, String name, List<String> parts) {
        if (manufacturer == null || manufacturer.isBlank())
            throw new IllegalArgumentException("Manufacturer cannot be empty ");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be empty");
        if (parts == null)
            throw new IllegalArgumentException("Parts cannot be empty ");
        this.manufacturer = manufacturer;
        this.name = name;
        this.parts = List.copyOf(parts);
    }

    /**
     * it gets the name of the model
     * @return it returns the model name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * it gets the list of part in this preset model
     * the list cannot be changed from outside the class
     * @return it returns an unchangeable list of part
     */

    @Override
    public List<String> getParts() {
        return Collections.unmodifiableList(parts);
    }

    /**
     * gets the name of the manufacturer who made the model
     * @return returns the manufacturer's name
     */
    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * it shows the model detail as text
     * @return it returns a short description of the model
     */
    @Override
    public String toString() {
        return "PresetModel{" + manufacturer + ", " + name + "}";
    }

    /**
     * checks whether two models have the same manufacturer and name
     * @param Obj another object to compare
     * @return returns true if both models match otherwise it returns false
     */
    @Override
    public boolean equals(Object Obj) {
        if (this == Obj)
            return true;
        if (!(Obj instanceof PresetModelImpl))
            return false;
        PresetModelImpl temp = (PresetModelImpl) Obj;
        return manufacturer.equals(temp.manufacturer) && name.equals(temp.name);
    }

    /**
     * it returns a number based on the manufacturer and name
     * @return it returns hash code value
     */

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, name);
    }
}
