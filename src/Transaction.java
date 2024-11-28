// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: Transaction.java

import java.util.Date;

public class Transaction implements ITransaction {

    private int id;
    private Date date;
    private String description;
    private double amount;
    private ICategory category;
    private IAccount account;

    /**
     * Constructor to initialize a new transaction with all required fields.
     *
     * @param id the unique ID for this transaction
     * @param date the date of the transaction
     * @param description a brief description of the transaction
     * @param amount the amount of money involved in the transaction
     * @param category the category of the transaction
     * @param account the account associated with the transaction
     */
    public Transaction(int id, Date date, String description, double amount, ICategory category, IAccount account) {
        this.id = id; // Set the transaction ID
        this.date = date; // Set the transaction date
        this.description = description; // Set the description
        this.amount = amount; // Set the transaction amount
        this.category = category; // Set the category
        this.account = account; // Set the associated account

    }

    /**
     * Gets the unique ID of the transaction.
     *
     * @return the transaction ID
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return the transaction date
     */
    @Override
    public Date getDate() {
        return this.date;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return a brief description of the transaction
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the amount involved in the transaction
     */
    @Override
    public Double getAmount() {
        return this.amount;
    }

    /**
     * Gets the category of the transaction.
     *
     * @return the category to which the transaction belongs
     */
    @Override
    public ICategory getCategory() {
        return this.category;
    }


    /**
     * Gets the account associated with the transaction.
     *
     * @return the account linked to this transaction
     */
    @Override
    public IAccount getAccount() {
        return this.account;
    }
}
