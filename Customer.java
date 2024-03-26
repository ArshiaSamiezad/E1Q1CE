import enums.Errors;

import java.util.ArrayList;

public class Customer {
    private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private String name;
    private double moneyInSafe;
    private ArrayList<Account> allActiveAccounts = new ArrayList<Account>();
    private int totalNumberOfAccountsCreated;
    private int negativeScore;

    public Customer(String name, double moneyInSafe) {
        this.name = name;
        this.moneyInSafe = moneyInSafe;
        this.totalNumberOfAccountsCreated = 0;
        this.negativeScore = 0;
        allCustomers.add(this);
    }

    public static Customer getCustomerByName(String name) {
        for (int i = 0; i < allCustomers.size(); i++) {
            if (allCustomers.get(i).name.equals(name)) {
                return allCustomers.get(i);
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void createNewAccount(Bank bank, int money, int duration, int interest) {
        this.totalNumberOfAccountsCreated++;
        Account account = new Account(bank, this.totalNumberOfAccountsCreated, money, duration, interest, this);
        allActiveAccounts.add(account);
        this.setMoneyInSafe(moneyInSafe - money);
    }

    public void leaveAccount(int accountId) {
        Account account = getAccountWithId(accountId);
        if (account==null) {
            System.out.println(Errors.InvalidAccount.error);
            return;
        }
        double accountMoney = account.getAmountOfMoneyForLeaving();
        setMoneyInSafe(moneyInSafe + accountMoney);
        Account.deleteAccount(account);
        for (int i = 0; i < allActiveAccounts.size(); i++) {
            if (allActiveAccounts.get(i).equals(account)) {
                allActiveAccounts.remove(i);
            }
        }
    }

    public boolean canPayLoan(double amount) {
        if (amount > moneyInSafe) {
            return false;
        }
        return true;
    }

    public double getMoneyInSafe() {
        return moneyInSafe;
    }

    public void setMoneyInSafe(double moneyInSafe) {
        this.moneyInSafe = moneyInSafe;
    }

    public void getLoan(int duration, int interest, int money) {
        Loan loan = new Loan(this, duration, interest, money);
        moneyInSafe = moneyInSafe + money;
    }

    public void payLoan(double amount) {
        moneyInSafe = moneyInSafe - amount;
    }

    public boolean canGetLoan() {
        if (this.negativeScore < 5) {
            return true;
        }
        return false;
    }

    public int getNegativeScore() {
        return this.negativeScore;
    }

    public void addNegativeScore() {
        this.negativeScore++;
    }

    public boolean hasActiveAccountInBank(Bank bank) {
        for (int i = 0; i < allActiveAccounts.size(); i++) {
            if (allActiveAccounts.get(i).getBank().equals(bank)) {
                return true;
            }
        }
        return false;
    }

    private Account getAccountWithId(int id) {
        for (int i = 0; i < allActiveAccounts.size(); i++) {
            if (allActiveAccounts.get(i).getId() == id) {
                return allActiveAccounts.get(i);
            }
        }
        return null;
    }
}
