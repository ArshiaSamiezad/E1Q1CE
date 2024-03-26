import java.util.ArrayList;

public class Loan {
    private static ArrayList<Loan> allLoans = new ArrayList<Loan>();
    private Customer customer;
    private int duration;
    private int remainingPayments;
    private int interest;
    private int amount;

    public Loan(Customer customer, int duration, int interest, int amount) {
        this.customer = customer;
        this.duration = duration;
        this.interest = interest;
        this.amount = amount;
        this.remainingPayments = duration;
        allLoans.add(this);
    }

    public static void passMonth() {
        for(int i=0; i<allLoans.size();i++){
            allLoans.get(i).passMonthEach();
            // TODO check if i must compare 2 accounts before and after this function
        }
    }

    private double getPaymentAmount() {
        double paymentAmount = (Double.valueOf(amount) * (1.0 + (Double.valueOf(interest)) / 100.0))
                / Double.valueOf(duration);
        return paymentAmount;
    }

    private void passMonthEach() {
        if(customer.canPayLoan(getPaymentAmount())){
            remainingPayments--;
            customer.payLoan(getPaymentAmount());
            if(remainingPayments==0){
                for(int i=0; i<allLoans.size();i++){
                    if(allLoans.get(i).equals(this)){
                        allLoans.remove(i);
                        i--;
                    }
                }
            }
        }
        else{
            customer.addNegativeScore();
            // System.out.println("Customer "+customer.getName()+" got negative score. Total: " + customer.getNegativeScore());
        }
    }
}
