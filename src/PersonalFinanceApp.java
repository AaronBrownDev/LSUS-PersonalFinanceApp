// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: PersonalFinanceApp.java

import java.util.*;

// Facade class
public class PersonalFinanceApp implements IPersonalFinance {

    // Singleton instance of the PersonalFinanceApp
    private static PersonalFinanceApp instance;

    // Core components of the personal finance system
    private final Account cash; // Account for cash balance
    private final NameableRepository<Account> creditCards; // Repository for credit card accounts
    private final NameableRepository<Account> debitAccounts; // Repository for debit accounts
    private final CategoryRepository categories; // Repository for categories
    private final AllAccountsRepository allAccounts; // Repository managing all accounts (combines credit and debit)
    private final TransactionRepository transactions; // Repository managing transactions

    // Private constructor
    private PersonalFinanceApp() {
        // Initialize cash account with a value policy
        this.cash = new Account("Cash", new PersonalFinanceSetValuePolicy());

        // Initialize repositories
        this.creditCards = new NameableRepository<>();
        this.debitAccounts = new NameableRepository<>();
        this.categories = new CategoryRepository();
        this.allAccounts = new AllAccountsRepository();

        // Add credit and debit accounts to all accounts repository
        this.allAccounts.addRepository(creditCards);
        this.allAccounts.addRepository(debitAccounts);

        // Initialize the transaction repository using singleton
        this.transactions = TransactionRepository.getInstance();
    }


    /**
     * Singleton getInstance method for lazy initialization of PersonalFinanceApp
     * @return the single instance of PersonalFinanceApp
     */
    public static PersonalFinanceApp getInstance() {
        if (instance == null) {
            instance = new PersonalFinanceApp();
        }
        return instance;
    }

    /**
     * Gets the current cash balance
     * @return the current cash value
     */
    @Override
    public double getCash() {
        return cash.getValue();
    }

    /**
     * Updates the cash balance with a new amount
     * @param newAmount the new cash amount to set
     * @return true if the value is valid, otherwise false
     */
    @Override
    public boolean updateCash(double newAmount) {
        return cash.setValue(newAmount);
    }

    /**
     * Retrieves all credit card accounts as an immutable collection
     * @return a collection of IAccount representing credit cards
     */
    @Override
    public Collection<IAccount> getCreditCards() {
        return Collections.unmodifiableCollection(creditCards.getObjects());
    }

    /**
     * Adds a new credit card account if the debt is valid and the name is unique
     * @param name the name of the credit card (must be unique)
     * @param debt the initial debt on the credit card
     * @return true if the account was added successfully, otherwise false
     */
    @Override
    public boolean addCreditCard(String name, double debt) {
        if (debt < 0) {
            return false;
        }
        return creditCards.addObject(new Account(name, debt, new PersonalFinanceSetValuePolicy()));
    }

    /**
     * Removes the credit card account of the given name
     * @param name - the name of the credit card to remove
     * @return true if the account was removed successfully, otherwise false
     */
    @Override
    public boolean removeCreditCard(String name) {
        return creditCards.removeObject(name);
    }

    /**
     * Updates an existing account's name to be the given name
     * @param name - name of the credit card to be updated
     * @param newName - the new name (must be unique)
     * @return true if the account's name was changed successfully, otherwise false
     */
    @Override
    public boolean updateCreditCardName(String name, String newName) {
        return creditCards.updateObjectName(name, newName);
    }

    /**
     * Helper method to update the value of an account (either credit or debit)
     * @param cards the repository of accounts (credit or debit)
     * @param name the name of the account
     * @param value the new value to set on the account
     * @return true if the value was successfully updated, otherwise false
     */
    public boolean updateCardValue(NameableRepository<Account> cards,String name, double value) {
        // Checks if arguments are valid
        if (name == null || value < 0) {
            return false;
        }
        // Finds card then updates value
        Account card = cards.getObject(name);
        if (card != null) {
            card.setValue(value);
            return true;
        }
        // Card was not found
        return false;
    }

    /**
     * Updates the debt of a specific credit card account
     * @param name the name of the credit card account
     * @param newDebt the new debt amount to set for the credit card
     * @return true if the debt was successfully updated, otherwise false
     */
    @Override
    public boolean updateCreditCardDebt(String name, double newDebt) {
        return updateCardValue(creditCards, name, newDebt);
    }

    /**
     * Retrieves all debit accounts as an immutable collection
     * @return a collection of IAccount representing debit accounts
     */
    @Override
    public Collection<IAccount> getDebitAccounts() {
        return Collections.unmodifiableCollection(debitAccounts.getObjects());
    }

    /**
     * Adds a new debit account with a specified balance
     * @param name the name of the debit account
     * @param balance the initial balance of the debit account
     * @return true if the account was added successfully, otherwise false
     */
    @Override
    public boolean addDebitAccount(String name, double balance) {
        if (balance < 0) {
            return false;
        }
        return debitAccounts.addObject(new Account(name, balance, new PersonalFinanceSetValuePolicy()));
    }

    /**
     * Removes a debit account from the repository by its name
     * @param name the name of the debit account to remove
     * @return true if the account was removed successfully, otherwise false
     */
    @Override
    public boolean removeDebitAccount(String name) {
        return debitAccounts.removeObject(name);
    }

    /**
     * Updates the name of an existing debit account
     * @param name the current name of the debit account
     * @param newName the new name for the debit account (must be unique)
     * @return true if the name was successfully updated, otherwise false
     * 
     */
    @Override
    public boolean updateDebitAccountName(String name, String newName) {
        return debitAccounts.updateObjectName(name, newName);
    }

    /**
     * Updates the balance of a debit account
     * @param name the name of the debit account
     * @param newBalance the new balance to set
     * @return true if the balance was updated successfully, otherwise false
     */
    @Override
    public boolean updateDebitAccountBalance(String name, double newBalance) {
        return updateCardValue(creditCards, name, newBalance);
    }

    /**
     * Retrieves all categories in the system as an immutable collection
     * @return a collection of ICategory representing all categories
     */
    @Override
    public Collection<ICategory> getCategories() {
        return Collections.unmodifiableCollection(categories.getObjects());
    }

    /**
     * Adds a new category to the system
     * @param name the name of the category
     * @param transactionType the type of transactions associated with this category
     * @return true if the category was added successfully, otherwise false
     */
    @Override
    public boolean addCategory(String name, ICategory.TransactionType transactionType) {
        return categories.addObject(new Category(name, transactionType));
    }

    /**
     * Removes a category from the repository by its name
     * @param name the name of the category to remove
     * @return true if the category was successfully removed, otherwise false
     */
    @Override
    public boolean removeCategory(String name) {
        return categories.removeObject(name);
    }

    /**
     * Updates the name of a specific category
     * @param name the current name of the category
     * @param newName the new name for the category (must be unique)
     * @return true if the name was successfully updated, otherwise false
     */
    @Override
    public boolean updateCategoryName(String name, String newName) {
        return categories.updateObjectName(name, newName);
    }

    /**
     * Updates the type of transactions allowed for a specific category
     * @param name the name of the category
     * @param newType the new transaction type for the category
     * @return true if the category's type was updated successfully, otherwise false
     */
    @Override
    public boolean updateCategoryType(String name, ICategory.TransactionType newType) {
        return categories.updateCategoryType(name, newType);
    }

    @Override
    public Collection<IBudget> getBudgets() {
        return List.of();
    }

    @Override
    public boolean addBudget(String name, Date startDate, Date endDate, Map<String, Double> items) {
        return false;
    }

    @Override
    public boolean removeBudget(String name) {
        return false;
    }

    @Override
    public boolean updateBudgetName(String name, String newName) {
        return false;
    }

    @Override
    public boolean updateBudgetStartDate(String name, Date newStartDate) {
        return false;
    }

    @Override
    public boolean updateBudgetEndDate(String name, Date newEndDate) {
        return false;
    }

    @Override
    public boolean addOrUpdateBudgetItem(String name, String categoryName, double value) {
        return false;
    }

    @Override
    public boolean removeBudgetItem(String name, String categoryName) {
        return false;
    }

    /**
     * Retrieves the transactions that fall within a given date range
     * @param startDate the start date of the transaction period
     * @param endDate the end date of the transaction period
     * @return a collection of ITransaction objects that match the date range
     */
    @Override
    public Collection<ITransaction> getTransactions(Date startDate, Date endDate) {
        return transactions.getTransactions(startDate, endDate);
    }

    /**
     * Adds new transaction to the transactions repository
     * @param date - the date of the transaction
     * @param description - a description of the transaction
     * @param amount - the amount of the transaction
     * @param categoryName - the name of the category object associated with this transaction
     * @param accountName - the name of the account object to which the transaction is linked
     * @return the id integer of the new transaction added
     */
    @Override
    public int addTransaction(Date date, String description, double amount, String categoryName, String accountName) {
        Category category = categories.getObject(categoryName);
        Account account = allAccounts.getObject(accountName);

        return transactions.addTransaction(date, description, amount, category, account);
    }

    /**
     * Removes a transaction based on the id given
     * @param id - the id of the transaction to remove
     * @return true if transaction was successfully removed, otherwise false
     */
    @Override
    public boolean removeTransaction(int id) {
        return transactions.deleteTransaction(id);
    }

    @Override
    public boolean updateTransactionDate(int id, Date newDate) {
        return false;
    }

    @Override
    public boolean updateTransactionDescription(int id, String newDescription) {
        return false;
    }

    @Override
    public boolean updateTransactionAmount(int id, double newAmount) {
        return false;
    }

    @Override
    public boolean updateTransactionCategory(int id, String newCategoryName) {
        return false;
    }

    @Override
    public boolean getStatus(String budgetName, Map<ICategory, Double> projected, Map<ICategory, Double> actual) {
        return false;
    }
}
