// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: ITransactionValidator.java

public interface ITransactionValidator {

    /**
     * Validates the given transaction according to specific validation rules.
     *
     * @param transaction the transaction to be validated
     * @return true if the transaction is valid, otherwise false
     */
    public boolean validate(ITransaction transaction);

    /**
     * Sets the next validator in the chain of responsibility.
     * This allows multiple validators to be chained together, where each
     * validator can delegate validation to the next one in the chain if needed.
     *
     * @param next the next validator to be set in the chain
     */
    public void setNext(ITransactionValidator next);
}
