package sandbox.my_examples.lambda.higher_order_function.example1;

import java.util.Arrays;
import java.util.List;

class Main {
     public static void main(String[] args) {
         User user1=new User("Vasiay");
         User user2=new User("Petia");
         User user3=new User("Kolya");

         user1.setFriends(Arrays.asList(user2));
         user2.setFriends(Arrays.asList(user1,user3));

         List<User> users=Arrays.asList(user1,user2,user3);


         var filteredUsers = CollectionUtils.filter(users, (u) -> u.getFriends().isEmpty());
     }
}
