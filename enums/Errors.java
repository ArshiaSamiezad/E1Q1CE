package enums;

public enum Errors {
    InvalidAccountBank("In dige banke koodoom keshvarie?"),
    InsufficientMoney("Boro baba pool nadari!"),
    InvalidAccount("Chizi zadi?!"),
    InvalidLoanBank("Gerefti maro nesfe shabi?"),
    LoanBlocked("To yeki kheyli vazet bade!");

    public final String error;

    Errors(String error) {
        this.error = error;
    }
}
