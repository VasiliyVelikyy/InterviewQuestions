package sandbox.interview_tasks.bankomat_app;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Atm {
    private ServerAtm serverAtm;
    private int commonBalance;
    private Map<Banknote, Integer> mapOfBanknote;

    public void fill(List<Banknote>amount) {
        amount.forEach(money-> mapOfBanknote.merge(money,1, Integer::sum));
    }

    public Map<Banknote, Integer> takeOfDynamicAlgorithm(int targetSum, DebitCard card){
        List<Integer> banknotes =  mapOfBanknote.entrySet()
                .stream()
                .flatMap(entry->{
            return Stream.generate(() -> entry.getKey().getNominate())
                    .limit(entry.getValue());
        }).collect(Collectors.toList());

        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 0); //начальное значение

        // Основной цикл по купюрам
        for (int banknot : banknotes) {
            Map<Integer, Integer> newSums = new HashMap<>(); // Новые суммы, которые можно получить с текущей купюрой

            // Обходим все существующие суммы
            for (Map.Entry<Integer, Integer> entry : sums.entrySet()) {
                int sum = entry.getKey();
                int newSum = sum + banknot;

                // Если новая сумма превышает целевую, пропускаем
                if (newSum > targetSum) {
                    continue;
                }

                // Если новой суммы еще нет в sums, добавляем её в newSums
                if (!sums.containsKey(newSum)) {
                    newSums.put(newSum, banknot);
                }
            }

            // Добавляем новые суммы в sums
            sums.putAll(newSums);

            // Если целевая сумма достигнута, выходим из цикла
            if (sums.containsKey(targetSum)) {
                break;
            }
        }

        // Проверяем, можно ли выдать сумму
        if (!sums.containsKey(targetSum)) {
            throw new IllegalArgumentException("Невозможно выдать сумму " + targetSum + " с доступными купюрами.");
        } else {
            // Восстанавливаем набор купюр
            List<Integer> resultBanknotes = new ArrayList<>();
            int remaining = targetSum;

            while (remaining > 0) {
                int bill = sums.get(remaining);
                resultBanknotes.add(bill);
                remaining -= bill;
            }
            // Выводим результат
            System.out.println("Сумма " + targetSum + " может быть выдана следующими купюрами: " + resultBanknotes);
            var map= resultBanknotes.stream().collect(Collectors.groupingBy(elem->elem,Collectors.counting()));
            return null;
        }

    }

    // не работает с наминалами которые при делении не дают целых чисел
    public Map<Banknote, Integer> takeOfLargeAlgorithm(int amount, DebitCard card) {
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
            throw new IllegalArgumentException("Not enough banknotes for amount=" + amount + " banknotes=" + banknote.getNominate());
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
