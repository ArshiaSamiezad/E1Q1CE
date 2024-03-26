import java.util.ArrayList;

public class Account {
    private static ArrayList<Account> allAccounts = new ArrayList<Account>();
    private Bank bank;
    private int id;
    private int money;
    private int remainingDuration;
    private int interest;
    private Customer customer;

    public Account(Bank bank, int id, int money, int remainingDuration, int interest, Customer customer) {
        this.bank = bank;
        this.id = id;
        this.money = money;
        this.remainingDuration = remainingDuration;
        this.interest = interest;
        this.customer = customer;
        allAccounts.add(this);
    }

    public static void passMonth() {
        for (int i = 0; i < allAccounts.size(); i++) {
            allAccounts.get(i).passMonthEach();
        }
    }

    public static void deleteAccount(Account account) {
        for (int i = 0; i < allAccounts.size(); i++) {
            if (allAccounts.get(i).equals(account)) {
                allAccounts.remove(i);
            }
        }
    }

    public int getId() {
        return id;
    }

    public double getAmountOfMoneyForLeaving() { // It will be included in passMonth or passEachMonth
        if(remainingDuration == 0){
            return money * (1.0+(Double.valueOf(interest))/100.0);
        }
        return money;
    }

    public Bank getBank() {
        return bank;
    }

    private void passMonthEach() {
        remainingDuration--;
        if (remainingDuration == 0) {
            customer.leaveAccount(this.getId());
        }
    }
}
