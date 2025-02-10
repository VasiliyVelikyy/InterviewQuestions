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
        if (currenClientBalance < amount) {
            throw new IllegalArgumentException("Not enough client money");
        } else if (commonBalance < amount) {
            throw new IllegalArgumentException("ATM has not enough money");
        }

        commonBalance -= amount;
        card.setClientBalance(currenClientBalance - amount);

        Map<Banknote, Integer> takeOfAmount = new HashMap<>();

        for (var entry : mapOfBanknote.entrySet()) {
            Banknote banknote = entry.getKey();
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

        return takeOfAmount;
    }


    public void authClient(DebitCard card, String pincode) throws IllegalAccessException {
        serverAtm.authClient(card, pincode);
    }


    public void initializeBanknotes(TreeMap<Banknote, Integer> mapOfBanknote) {
        this.mapOfBanknote = mapOfBanknote;
        commonBalance = mapOfBanknote.entrySet()
                .stream()
                .mapToInt(pair -> pair.getKey().getNominate() * pair.getValue())
                .sum();

    }

}
