// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: INameable.java

/**
 * The INameable interface defines the contract for objects that have a name.
 * Any class that implements this interface must provide methods to get and set a name.
 */
public interface INameable {

    /**
     * Gets the name of the object.
     *
     * @return the name of the object
     */
    public String getName();

    /**
     * Sets the name of the object.
     *
     * @param name the new name to set for the object
     */
    public void setName(String name);
}
