// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: NameableRepository.java

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class NameableRepository<T extends INameable> implements INameableRepository<T> {
    protected final HashMap<String, T> namedObjects = new HashMap<String, T>();

    /**
     * Adds a new object to the repository. The object is added only if its name is valid.
     * The validity check ensures that the name is not already taken by another object in the repository.
     *
     * @param namedObject the object to be added to the repository
     * @return true if the object was added successfully, otherwise false
     */
    public boolean addObject(T namedObject) {
        if (isNameValid(namedObject.getName())) {
            namedObjects.put(namedObject.getName(), namedObject);
            return true;
        }
        return false;
    }

    /**
     * Removes an object from the repository by its name.
     *
     * @param objectName the name of the object to remove
     * @return true if the object was removed successfully, otherwise false
     */
    public boolean removeObject(String objectName) {
        return namedObjects.remove(objectName) != null;
    }

    /**
     * Updates the name of an object in the repository. The new name must be valid,
     * and the object to be renamed must exist in the repository.
     *
     * @param objectName the current name of the object to update
     * @param newName the new name for the object
     * @return true if the name was updated successfully, otherwise false
     */
    public boolean updateObjectName(String objectName, String newName) {
        if (isNameValid(newName) && namedObjects.containsKey(objectName)) {
            T namedObject = namedObjects.get(objectName);
            namedObject.setName(newName);
            namedObjects.put(newName, namedObject);
            namedObjects.remove(objectName);
            return true;
        }
        return false;
    }

    /**
     * Retrieves an object from the repository by its name.
     *
     * @param objectName the name of the object to retrieve
     * @return the object with the given name, or null if not found
     */
    public T getObject(String objectName) {
        return namedObjects.get(objectName);
    }

    /**
     * Retrieves all objects in the repository.
     *
     * @return an unmodifiable collection of all objects in the repository
     */
    public Collection<T> getObjects() {
        return Collections.unmodifiableCollection(namedObjects.values());
    }

    /**
     * Validates the given name. A name is valid if it is not null and is not already taken
     * by another object in the repository.
     *
     * @param name the name to validate
     * @return true if the name is valid, otherwise false
     */
    public boolean isNameValid(String name) {
        return name != null && !namedObjects.containsKey(name);
    }
}
