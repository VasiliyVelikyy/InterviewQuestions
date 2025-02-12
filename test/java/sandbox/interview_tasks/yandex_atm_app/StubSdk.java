package sandbox.interview_tasks.yandex_atm_app;

/**
 * Пример реализации SDK, которую можно использовать в тестах.
 * Реализацию можно и нужно менять.
 */
class StubSdk implements Sdk {
    @Override
    public int countBanknotes(int banknote) {
//        if(banknote == 50)return 5;
//        else if(banknote == 100)return 10;
//        else if(banknote == 500)return 2;
//        else if(banknote == 1000)return 4;
//        else return 0;

        if(banknote == 100)return 100;

        else return 0;
    }

    @Override
    public void moveBanknoteToDispenser(int banknote, int count) {
        System.out.printf("Перемещаю купюру %s в лоток выдачи, %s штук%n", banknote, count);
    }

    @Override
    public void openDispenser() {
        System.out.printf("Лоток выдачи открыт пользователю%n");
    }
}
