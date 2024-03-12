package block1.lambda.higher_order_function.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

 class CollectionUtils {
    static ArrayList<User> filter(List<User> users, Predicate<User> fn) {
        var filteredUsers = new ArrayList<User>();
        for (var user : users) {
            if (fn.test(user)) { // запуск лямбда-функции. Здесь будет вызываться функция u.getFriends().isEmpty()
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

}
