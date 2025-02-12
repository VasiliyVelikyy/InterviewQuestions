package sandbox.interview_tasks.yandex_atm_app;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Банкомат.
 * Взаимодействует с SDK, контракты для которого описаны ниже.
 * Необходимо реализовать запрос на выдачу определенной суммы (в рублях).
 * В случае, если нужную сумму выдать невозможно, отвечать отказом.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 */
public class AtmYandex {
    private int commansAmout;
    private Map<Banknote, Integer> banknotes;
    private final Sdk sdk;

    public AtmYandex(Sdk sdk) {
        this.sdk = sdk;
    }

    public Map<Integer, Integer> takeOf(int targetSum) {
        checkCommonAmount(targetSum);
        chackMultiple(targetSum);
        Map<Integer, Integer> takeOfFoClient = new HashMap<>();
        Banknote banknote = null;

        for (Map.Entry<Banknote, Integer> entry : banknotes.entrySet()) {
            banknote = entry.getKey();
            var banknoteAmount = entry.getValue();
            if (banknoteAmount > 0) {
                if (banknote.getNominate() <= targetSum) {
                    int countBanknote = targetSum / banknote.getNominate();
                    takeOfFoClient.put(banknote.getNominate(), countBanknote);
                    targetSum = targetSum - banknote.getNominate() * countBanknote;
                    entry.setValue(banknoteAmount - countBanknote);
                }
            }
        }
        if (targetSum > 0) {
            throw new IllegalArgumentException("Not enough banknotes. remain amount=" + targetSum + " banknote=" + banknote);
        }
        commansAmout -= targetSum;
        return takeOfFoClient;
    }

    private void chackMultiple(int targetSum) {
        if (targetSum % 50 != 0) {
            throw new IllegalArgumentException("commansAmout must be multiple of 50");
        }
    }

    private void checkCommonAmount(int targetSum) {
        if (targetSum > commansAmout) {
            throw new IllegalArgumentException("commansAmout not enougt");
        }
    }


    public void initBankomat(List<Integer> listOfbanknotes) {
        var map = listOfbanknotes.stream().map(elem -> {
            int count = intCountBanknote(elem);
            return new Banknote(elem, count);
        }).collect(Collectors.toMap(elem -> elem, Banknote::getCount));


        banknotes =
                new TreeMap<>(new Comparator<Banknote>() {
                    @Override
                    public int compare(Banknote o1, Banknote o2) {
                        if (o2.getNominate() > o1.getNominate()) {
                            return 1;
                        } else if (o2.getNominate() == o1.getNominate()) {
                            return 0;
                        } else return -1;
                    }
                });
        banknotes.putAll(map);
        commansAmout = banknotes.entrySet()
                .stream()
                .map(pair -> pair.getValue() * pair.getKey().getNominate())
                .reduce(0, Integer::sum);

    }

    private int intCountBanknote(int i) {
        return sdk.countBanknotes(i);
    }

}
