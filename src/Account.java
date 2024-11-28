// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: Account.java

public class Account implements IAccount, INameable {

    private String name;
    private double value;

    // The policy that defines how the account's value can be set
    private final ISetValuePolicy setValuePolicy;

    /**
     * Constructor to initialize an Account with a name and a value-setting policy.
     * Sets the account's value to 0 by default.
     *
     * @param name the name of the account
     * @param setValuePolicy the policy that defines how the account's value can be set
     */
    public Account(String name, ISetValuePolicy setValuePolicy) {
        this(name, 0, setValuePolicy);
    }

    /**
     * Constructor to initialize an Account with a name, an initial value, and a value-setting policy.
     *
     * @param name the name of the account
     * @param value the initial value (balance) of the account
     * @param setValuePolicy the policy that defines how the account's value can be set
     */
    public Account(String name, double value, ISetValuePolicy setValuePolicy) {
        this.name = name;
        this.setValuePolicy = setValuePolicy;
        setValue(value);
    }

    /**
     * Sets the name of the account.
     *
     * @param name the new name for the account
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the account.
     *
     * @return the name of the account
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the current value (balance) of the account.
     *
     * @return the current value (balance) of the account
     */
    @Override
    public double getValue() {
        return this.value;
    }

    /**
     * Sets the value (balance) of the account if it is valid according to the value-setting policy.
     *
     * @param newValue the new value to set for the account
     * @return true if the value was successfully set; false if the new value is invalid according to the policy
     */
    public boolean setValue(double newValue) {
        // Check if the new value is valid based on the policy
        if (setValuePolicy.isValidValue(newValue)) {
            this.value = newValue; // Set the value if valid
            return true; // Indicate success
        }
        return false; // Return false if the value is not valid
    }
}
