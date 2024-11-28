// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: INameableRepository.java

public interface INameableRepository<T extends INameable> {

    /**
     * Retrieves an object from the repository by its name.
     *
     * @param objectName the name of the object to retrieve
     * @return the object with the specified name, or null if no object with that name exists
     */
    T getObject(String objectName);
}
