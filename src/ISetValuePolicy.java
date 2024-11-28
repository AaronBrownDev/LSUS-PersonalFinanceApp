// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: ISetValuePolicy.java

public interface ISetValuePolicy {

    /**
     * Validates if a given value is acceptable based on the specific policy.
     *
     * @param value the value to be validated
     * @return true if the value is valid according to the policy, otherwise false
     */
    public boolean isValidValue(double value);
}
