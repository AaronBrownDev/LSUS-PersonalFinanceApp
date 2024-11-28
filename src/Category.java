// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: Category.java

public class Category implements ICategory, INameable {
    private String name;

    // The type of transaction associated with the category (e.g., "INCOME", "EXPENDITURE")
    private TransactionType transactionType;

    /**
     * Constructor to create a new Category with a specified name and transaction type.
     *
     * @param name the name of the category
     * @param transactionType the type of transaction associated with the category
     */
    public Category(String name, TransactionType transactionType) {
        this.name = name;
        this.transactionType = transactionType;
    }

    /**
     * Gets the name of the category.
     *
     * @return the name of the category
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name the new name of the category
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the transaction type associated with the category.
     *
     * @return the transaction type (e.g., INCOME, EXPENSE)
     */
    @Override
    public TransactionType getType() {
        return transactionType;
    }

    /**
     * Sets the transaction type associated with the category.
     *
     * @param transactionType the new transaction type for the category
     */
    public void setType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
