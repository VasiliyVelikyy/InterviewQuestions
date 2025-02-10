package sandbox.interview_tasks.bankomat_app;

import java.util.Map;

public class Atm {
    private ServerAtm serverAtm;
    private int commonBalance;
    private Map<Banknote, Integer> mapOfBanknote;
    private int currenClientBalance;


    public void fill(int amount) {

        commonBalance = +amount;
    }

    public Map<Banknote,Integer> takeOf(int amount) {
        if (currenClientBalance < amount) {
            throw new IllegalArgumentException("Not enough client money");
        }
        else if(commonBalance < amount){
            throw new IllegalArgumentException("ATM has not enough money");
        }
        commonBalance -= amount;

    }


    public void authClient(DebitCard card, String pincode) throws IllegalAccessException {
        serverAtm.authClient(card, pincode);

        currenClientBalance = card.getClientBalance();
    }


    public void initializeBanknotes(Map<Banknote, Integer> mapOfBanknote) {
        this.mapOfBanknote = mapOfBanknote;
        commonBalance = mapOfBanknote.entrySet()
                .stream()
                .mapToInt(pair -> pair.getKey().getNominate() * pair.getValue())
                .sum();

    }

}
