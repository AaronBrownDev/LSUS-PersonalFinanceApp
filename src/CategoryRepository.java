// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: CategoryRepository.java

public class CategoryRepository extends NameableRepository<Category> {

    /**
     * Adds a Category object to the repository.
     * The category is only added if its type is not null.
     *
     * @param obj the Category object to be added
     * @return true if the Category was added successfully, otherwise false
     */
    @Override
    public boolean addObject(Category obj) {
        if (obj.getType() != null) {
            return super.addObject(obj); // Call the superclass method to add the object
        }
        return false; // Return false if the category type is null (invalid)
    }

    /**
     * Updates the type of an existing category in the repository.
     * The category is identified by its name and the new type is applied if the category exists and the type is not null.
     *
     * @param name the name of the category to be updated
     * @param type the new transaction type to set for the category
     * @return true if the category's type was updated successfully, otherwise false
     */
    public boolean updateCategoryType(String name, ICategory.TransactionType type) {
        Category category = namedObjects.get(name);
        if (category != null && type != null) {
            category.setType(type);
            return true;
        }
        return false;
    }
}
