package block1.lambda.functional_style;

import java.util.Arrays;
import java.util.List;

public class FunctionalStyleExample {
    public static void main(String[] args) {
        Client client1 = new Client("client1", 15, true);
        Client client2 = new Client("client2", 26, false);
        Client client3 = new Client("client3", 63, true);
        Client client4 = new Client("client4", 124, true);
        Client client5 = new Client("client5", 88, false);
        List<Client> clients = Arrays.asList(client1, client2, client3, client4, client5);


        //старый подход
        for (Client client : clients) {
            client.setSum(client.getSum() - 1);
        }

        //функциональное програмирование
        clients.forEach(client -> client.setSum(client.getSum() - 1));

        int allBalanceClients = clients.stream().mapToInt(elem -> elem.getSum()).sum();
        System.out.println("allBalanceClients = " + allBalanceClients);

        allBalanceClients = clients
                .stream()
                .filter(Client::isActive)
                .mapToInt(Client::getSum).sum();

        //можно вместо sum использовать reduce
        allBalanceClients = clients
                .stream()
                .filter(Client::isActive)
                .reduce(0, (integer, client) -> integer + client.getSum(), Integer::sum); //обязательно нужно указать Integer::sum
                // она задает порядок совмещения двух потоков которые обсчитали паралельно
        System.out.println("allBalanceClients where is active true = " + allBalanceClients);
    }
}
