// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: TransactionDateValidator.java

import java.util.Date;

public class TransactionDateValidator implements ITransactionValidator {

    // The next validator in the chain of responsibility
    private ITransactionValidator next;

    /**
     * Validates the given transaction.
     * This specific validator checks if the transaction date is not null
     * and if the transaction date is not in the future.
     *
     * @param transaction the transaction to validate
     * @return true if the transaction's date is valid, otherwise false
     */
    @Override
    public boolean validate(ITransaction transaction) {
        // Get the transaction date
        Date transactionDate = transaction.getDate();

        // Check if the transaction date is null or transaction date is after the current date
        if (transactionDate == null || transactionDate.after(new Date())) {
            return false;
        }

        // If there's a next validator in the chain, delegate the validation to it
        // Return true if no further validation is needed (next is null) or
        // if the next validator also validates the transaction
        return next == null || next.validate(transaction);
    }

    /**
     * Sets the next validator in the chain of responsibility.
     *
     * @param next the next validator to be set
     */
    @Override
    public void setNext(ITransactionValidator next) {
        this.next = next;
    }
}
