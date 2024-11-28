// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: Budget.java

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Budget implements IBudget, INameable {

    private String name;

    // The start date of the budget period
    private Date startDate;

    // The end date of the budget period
    private Date endDate;

    // A map that stores budget items, where the key is a category (ICategory)
    // and the value is the budgeted amount for that category (Double)
    private HashMap<ICategory, Double> items;


    /**
     * Sets the name of the budget.
     *
     * @param name the name of the budget
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the budget.
     *
     * @return the name of the budget
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the start date of the budget period.
     *
     * @return the start date of the budget
     */
    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of the budget period.
     *
     * @return the end date of the budget
     */
    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * Gets all the budget items, represented by categories and their respective budgeted amounts.
     * The returned map is unmodifiable to prevent modification from outside the class.
     *
     * @return an unmodifiable map of budget items, where the key is the category and the value is the budgeted amount
     */
    @Override
    public Map<ICategory, Double> getItems() {
        return Collections.unmodifiableMap(items);
    }
}
