package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bank.
 * @author Ivan Belyaev
 * @since 17.01.2018
 * @version 1.0
 */
public class Bank {
    /** The collection holds customers and their accounts. */
    private Map<User, List<Account>> customers = new HashMap<>();

    /**
     * Method adds a new client.
     * If the client is already there, it adds nothing.
     * @param user - new client.
     */
    public void addUser(User user) {
        customers.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Method removes the client.
     * @param user - client.
     */
    public void deleteUser(User user) {
        customers.remove(user);
    }

    /**
     * Method adds the account to the client.
     * If the account already exists, adds nothing.
     * @param passport - the client's passport.
     * @param account - the account which you want to add.
     */
    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> customer : customers.entrySet()) {
            if (passport.equals(customer.getKey().getPassport())) {
                List<Account> accounts = customer.getValue();

                if (accounts.indexOf(account) == -1) {
                    accounts.add(account);
                }

                break;
            }
        }
    }

    /**
     * Method removes the account from the client.
     * @param passport - the client's passport.
     * @param account - the account which you want to delete.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> customer : customers.entrySet()) {
            if (passport.equals(customer.getKey().getPassport())) {
                List<Account> accounts = customer.getValue();
                int index = accounts.indexOf(account);

                if (index != -1) {
                    accounts.remove(index);
                }

                break;
            }
        }
    }

    /**
     * The method returns all the customer account.
     * @param passport - the client's passport.
     * @return returns all the customer account.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = null;

        for (Map.Entry<User, List<Account>> customer : customers.entrySet()) {
            if (passport.equals(customer.getKey().getPassport())) {
                result = customer.getValue();
            }
        }

        return result;
    }

    /**
     * Method for transferring money from one account to another account.
     * If the account is not found or not enough money in the account from which you transfer money returns false.
     * @param srcPassport - the passport of the client from whose account transfer money.
     * @param srcRequisite - details of the account from which you transferred the money.
     * @param destPassport - the passport of the client on whose account transfer money.
     * @param destRequisite - details of the account to which you transferred the money.
     * @param amount - the amount of money to transfer.
     * @return returns true if the operation was successful, otherwise returns false.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findAccount(srcPassport, srcRequisite);
        Account destAccount = findAccount(destPassport, destRequisite);

        boolean result = false;
        if (srcAccount != null && destAccount != null && srcAccount.getValue() > amount) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            destAccount.setValue(destAccount.getValue() + amount);
            result = true;
        }

        return result;
    }

    /**
     * The method returns the client's account.
     * @param passport  - the client's passport.
     * @param requisite - account details.
     * @return returns the client's account.
     */
    private Account findAccount(String passport, String requisite) {
        Account result = null;

        for (Map.Entry<User, List<Account>> customer : customers.entrySet()) {
            if (passport.equals(customer.getKey().getPassport())) {
                for (Account account : customer.getValue()) {
                    if (requisite.equals(account.getRequisites())) {
                        result = account;
                        break;
                    }
                }
                break;
            }
        }

        return result;
    }
}
