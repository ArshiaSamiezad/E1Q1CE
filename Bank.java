import java.util.ArrayList;

public class Bank {
    private static ArrayList<Bank> allBanks = new ArrayList<Bank>();
    private String name;

    public Bank(String name) {
        this.name = name;
        allBanks.add(this);
    }

    public static Bank getBankWithName(String name) {
        for (int i = 0; i < allBanks.size(); i++) {
            if (allBanks.get(i).name.equals(name)) {
                return allBanks.get(i);
            }
        }
        return null;
    }

    public static boolean isThereBankWithName(String name) {
        for (int i = 0; i < allBanks.size(); i++) {
            if (allBanks.get(i).name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static int getAccountInterestFromName(String type) {
        if (type.equals("KOOTAH"))
            return 10;
        if (type.equals("BOLAN"))
            return 30;
        if (type.equals("VIZHE"))
            return 50;

        return 0;
    }

    public String getName() {
        return name;
    }
}



