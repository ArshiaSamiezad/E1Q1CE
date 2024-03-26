import enums.Regex;
import enums.Errors;

import java.util.*;
import java.util.regex.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Matcher matcher;

        while (true) {
            String input = scanner.nextLine();

            if ((matcher = getMatcher(input, Regex.AddCustomer.pattern)) != null) {
                Customer customer = new Customer(matcher.group("name"),
                        Double.parseDouble(matcher.group("initialMoney")));
                continue;
            }

            if ((matcher = getMatcher(input, Regex.AddBank.pattern)) != null) {
                Bank bank = new Bank(matcher.group("bankName"));
                continue;
            }

            if ((matcher = getMatcher(input, Regex.CreateAccount.pattern)) != null) {
                if (!Bank.isThereBankWithName(matcher.group("bankName"))) {
                    System.out.println(Errors.InvalidAccountBank.error);
                    continue;
                }

                Customer customer = Customer.getCustomerByName(matcher.group("name"));
                if (customer.getMoneyInSafe() < Double.parseDouble(matcher.group("initialDeposit"))) {
                    System.out.println(Errors.InsufficientMoney.error);
                    continue;
                }

                int interest = Bank.getAccountInterestFromName(matcher.group("accountType"));

                customer.createNewAccount(Bank.getBankWithName(matcher.group("bankName")),
                        Integer.parseInt(matcher.group("initialDeposit")),
                        Integer.parseInt(matcher.group("duration")),
                        interest);
                continue;
            }

            if ((matcher = getMatcher(input, Regex.WithdrawAll.pattern)) != null) {
                Customer customer = Customer.getCustomerByName(matcher.group("name"));
                customer.leaveAccount(Integer.parseInt(matcher.group("accountNumber")));
                // InvalidAccount error is handled inside leaveAccount method, because the method getAccount is private
                continue;
            }

            if ((matcher = getMatcher(input, Regex.PayLoan.pattern)) != null) {
                Customer customer = Customer.getCustomerByName(matcher.group("name"));
                if (!Bank.isThereBankWithName(matcher.group("bankName"))) {
                    System.out.println(Errors.InvalidLoanBank.error);
                    continue;
                }
                if(!customer.canGetLoan()){
                    System.out.println(Errors.LoanBlocked.error);
                    continue;
                }
                customer.getLoan(Integer.parseInt(matcher.group("duration")),
                        Integer.parseInt(matcher.group("interest")),
                        Integer.parseInt(matcher.group("loanUnit")));
                continue;
            }

            if ((matcher = getMatcher(input, Regex.PassTime.pattern)) != null) {
                for (int i = 0; i < Integer.parseInt(matcher.group("time")); i++) {
                    System.out.println("-------------NEW DAY------------");
                    passMonth();
                }
                continue;
            }

            if ((matcher = getMatcher(input, Regex.PrintSafeValue.pattern)) != null) {
                Customer customer = Customer.getCustomerByName(matcher.group("name"));
                System.out.println((int)customer.getMoneyInSafe());
                continue;
            }

            if ((matcher = getMatcher(input, Regex.PrintNegativeScore.pattern)) != null) {
                Customer customer = Customer.getCustomerByName(matcher.group("name"));
                System.out.println(customer.getNegativeScore());
                continue;
            }

            if ((matcher = getMatcher(input, Regex.DoesHaveAccount.pattern)) != null) {
                Customer customer = Customer.getCustomerByName(matcher.group("name"));
                Boolean doesHaveAccount =
                        customer.hasActiveAccountInBank(Bank.getBankWithName(matcher.group("bankName")));
                if (doesHaveAccount) System.out.println("yes");
                else System.out.println("no");
                continue;
            }

            if (input.equals("Base dige, berid khonehatoon.")) {
                break;
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    private static void passMonth() {
        Loan.passMonth();
        Account.passMonth();
    }

    private static Matcher getMatcher(String input, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
