// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: PersonalFinanceSetValuePolicy.java

public class PersonalFinanceSetValuePolicy implements ISetValuePolicy {

    /**
     * Checks if the provided value is valid according to the policy.
     * In this case, the value is considered valid if it is greater than or equal to zero.
     *
     * @param value the value to check
     * @return true if the value is valid (greater than or equal to zero), otherwise false
     */
    @Override
    public boolean isValidValue(double value) {
        return value >= 0;
    }
}

