// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: TransactionRepository.java

import java.util.*;

public class TransactionRepository {

    // HashMap to store transactions with unique Transaction IDs
    private final HashMap<Integer, Transaction> transactions = new HashMap<>();

    // Tracks the next available Transaction ID
    private int TransactionID = 0;

    // List to store IDs of deleted transactions for reuse
    private final ArrayList<Integer> deletedTransactionIDs = new ArrayList<>();

    // Validator for transaction date (could be extended for other validation)
    private ITransactionValidator transactionValidator;

    // Singleton instance of TransactionRepository
    private static TransactionRepository instance;

    /**
     * Private constructor for the TransactionRepository class.
     * Initializes the transaction validator with a default implementation.
     */
    private TransactionRepository() {
        this.transactionValidator = new TransactionDateValidator();
    }

    /**
     * Gets the singleton instance of the TransactionRepository class.
     * Ensures that only one instance exists.
     *
     * @return the single instance of TransactionRepository
     */
    public static TransactionRepository getInstance() {
        if (instance == null) {
            instance = new TransactionRepository();
        }
        return instance;
    }

    /**
     * Retrieves all transactions that fall within a specific date range.
     *
     * @param startDate the starting date for the transaction period
     * @param endDate the ending date for the transaction period
     * @return an immutable collection of transactions within the given date range
     */
    public Collection<ITransaction> getTransactions(Date startDate, Date endDate) {
        ArrayList<ITransaction> transactions = new ArrayList<>();

        // Iterate through all transactions and select those within the date range
        for (Transaction t : this.transactions.values()) {
            Date transactionDate = t.getDate();
            if (!transactionDate.before(startDate) && !transactionDate.after(endDate)) {
                transactions.add(t);
            }
        }
        return Collections.unmodifiableCollection(transactions);
    }

    /**
     * Adds a new transaction to the repository. If there are deleted transaction IDs,
     * it reuses one of them. Otherwise, a new ID is generated.
     *
     * @param date the date of the transaction
     * @param description a description of the transaction
     * @param amount the amount of the transaction
     * @param category the category to which the transaction belongs
     * @param account the account associated with the transaction
     * @return the ID of the newly added transaction, or -1 if validation fails
     */
    public synchronized int addTransaction(Date date, String description, double amount, ICategory category, IAccount account) {

        int addID;

        // Reuse a deleted transaction ID if available
        if (!deletedTransactionIDs.isEmpty()) {
            addID = deletedTransactionIDs.removeFirst(); // Reuse the first deleted ID
        } else {
            addID = TransactionID; // Create a new ID if no deleted IDs are available
        }

        // Create a new Transaction object
        Transaction transaction = new Transaction(addID, date, description, amount, category, account);

        // Validate the transaction
        if (transactionValidator.validate(transaction)) {
            // If valid, add the transaction to the repository
            transactions.put(transaction.getId(), transaction);

            // Increment the TransactionID only if a new ID was used
            if (addID == TransactionID) {
                TransactionID++;
            }

            return transaction.getId(); // Return the ID of the newly added transaction
        } else {
            // If validation fails, return -1 to indicate failure
            return -1;
        }
    }

    /**
     * Deletes a transaction from the repository by its ID. If successfully removed,
     * the ID is added to the list of deleted transaction IDs for potential reuse.
     *
     * @param id the ID of the transaction to delete
     * @return true if the transaction was successfully deleted, otherwise false
     */
    public boolean deleteTransaction(int id) {
        // Remove the transaction with the given ID
        if (transactions.remove(id) != null) {
            // Add the deleted ID to the list for future reuse
            deletedTransactionIDs.add(id);
            return true; // Successfully deleted
        }
        return false; // Transaction not found
    }


}
