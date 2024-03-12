package block1.lambda.higher_order_function.example1;

import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private List<User> friends = new ArrayList<>();


    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", friends="+friends.size()+
                '}';
    }
}
