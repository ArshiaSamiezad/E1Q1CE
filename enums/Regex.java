package enums;

public enum Regex {
    AddCustomer("[\\s]*Add a customer with name (?<name>[\\S\\s]+) and (?<initialMoney>[\\d]+) unit initial money\\.[\\s]*"),
    AddBank("Create bank (?<bankName>[\\s\\S]+)\\."),
    CreateAccount("[\\s]*Create a (?<accountType>[\\w]+) account for (?<name>[\\S\\s]+) in (?<bankName>[\\S\\s]+), " +
            "with duration (?<duration>[\\d]+) and initial deposit of (?<initialDeposit>[\\d]+)\\.[\\s]*"),
    WithdrawAll("[\\s]*Give (?<name>[\\S\\s]+)'s money out of his account number (?<accountNumber>[\\d]+)\\.[\\s]*"),
    PayLoan("[\\s]*Pay a (?<loanUnit>[\\d]+) unit loan with %(?<interest>[\\d]+) interest and (?<duration>[\\d]+) " +
            "payments from (?<bankName>[\\S\\s]+) to (?<name>[\\S\\s]+)\\.[\\s]*"),
    PassTime("[\\s]*Pass time by (?<time>[\\d]+) unit\\.[\\s]*"),
    PrintSafeValue("[\\s]*Print (?<name>[\\S\\s]+)'s GAVSANDOOGH money\\.[\\s]*"),
    PrintNegativeScore("[\\s]*Print (?<name>[\\S\\s]+)'s NOMRE MANFI count\\.[\\s]*"),
    DoesHaveAccount("[\\s]*Does (?<name>[\\S\\s]+) have active account in (?<bankName>[\\S\\s]+)\\?[\\s]*");

    public final String pattern;

    Regex(String pattern) {
        this.pattern = pattern;
    }


}
