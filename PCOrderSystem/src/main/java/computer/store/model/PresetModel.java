package computer.store.model;
// preset models are made by manufacturer and its specifications cannot change.

/**
 * this interface represent a preset computer model
 * preset model are made by manufacturer
 * the parts and specifications of the preset model cannot be changed
 */
public interface PresetModel extends COMPUTERMODEL {
    String getManufacturer();
}

