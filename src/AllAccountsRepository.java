// Name: Aaron Brown
// Course: CSC 285
// Project: Final Project
// File Name: AllAccountsRepository.java

import java.util.ArrayList;

public class AllAccountsRepository implements INameableRepository<Account> {

    // List to store multiple repositories of accounts
    ArrayList<INameableRepository<Account>> repositories = new ArrayList<>();

    /**
     * Adds a repository to the list of repositories.
     * This allows for managing multiple repositories of accounts.
     *
     * @param repo the repository to be added
     */
    public void addRepository(INameableRepository<Account> repo) {
        repositories.add(repo);
    }

    /**
     * Searches for an account by its name across all repositories.
     * Iterates through all repositories and checks if an account with the
     * specified name exists in any of them.
     *
     * @param objectName the name of the account to find
     * @return the account if found, otherwise null
     */
    @Override
    public Account getObject(String objectName) {
        for (INameableRepository<Account> repo : repositories) {
            Account account = repo.getObject(objectName);
            if (account != null) {
                return repo.getObject(objectName);
            }
        }
        return null;
    }
}
