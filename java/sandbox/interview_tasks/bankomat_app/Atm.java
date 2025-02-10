package sandbox.interview_tasks.bankomat_app;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Atm {
    private ServerAtm serverAtm;
    private int commonBalance;
    private Map<Banknote, Integer> mapOfBanknote;

    public void fill(int amount) {
        commonBalance = +amount;
    }

    public Map<Banknote, Integer> takeOf(int amount, DebitCard card) {
        int currenClientBalance = card.getClientBalance();
        checkClientBalance(amount, currenClientBalance);
        checkAtmBalance(amount, currenClientBalance);
        checkMultiple(amount);
        int tempAmount = amount;

        Map<Banknote, Integer> takeOfAmount = new HashMap<>();
        Banknote banknote = null;
        for (var entry : mapOfBanknote.entrySet()) {
            banknote = entry.getKey();
            Integer banknoteAmount = entry.getValue();

            if (banknoteAmount > 0) {
                if (banknote.getNominate() <= amount) {
                    int countBanknote = amount / banknote.getNominate();
                    takeOfAmount.put(banknote, countBanknote);
                    amount = amount - countBanknote * banknote.getNominate();
                    entry.setValue(banknoteAmount - countBanknote);
                }
            }
        }

        if (amount > 0 && banknote != null) {
            throw new IllegalArgumentException("Not enough banknotes=" + amount + " Please enter sum multiple banknotes=" + banknote.getNominate());
        }
        commonBalance -= tempAmount;
        card.setClientBalance(currenClientBalance - tempAmount);
        return takeOfAmount;
    }

    private void checkMultiple(int amount) {
        if (amount % 100 != 0) {
            throw new IllegalArgumentException("Please enter amount multiple of 100");
        }
    }

    private void checkAtmBalance(int amount, int currenClientBalance) {
        if (commonBalance < amount) {
            throw new IllegalArgumentException("ATM has not enough money");
        }
    }

    private void checkClientBalance(int amount, int currenClientBalance) {
        if (currenClientBalance < amount) {
            throw new IllegalArgumentException("Not enough client money");
        }
    }

    public void authClient(DebitCard card, String pincode) throws IllegalAccessException {
        serverAtm.authClient(card, pincode);
    }


    public void initializeBanknotes(Map<Banknote, Integer> mapOfBanknote) {
        Map<Banknote, Integer> treeMap = new TreeMap<>((o1, o2) -> Integer.compare(o2.getNominate(), o1.getNominate()));
        treeMap.putAll(mapOfBanknote);
        this.mapOfBanknote = treeMap;
        commonBalance = mapOfBanknote.entrySet()
                .stream()
                .mapToInt(pair -> pair.getKey().getNominate() * pair.getValue())
                .sum();

    }

}
