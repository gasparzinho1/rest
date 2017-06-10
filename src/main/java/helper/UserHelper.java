package helper;

import java.util.ArrayList;
import java.util.List;

import com.rest.entity.User;

public class UserHelper {

    public static User createUser() {
        return new User("testing user name 1", "testing user login 1", "testing user password 1");
    }

    public static List<User> createUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("testing user name 1", "testing user login 1", "testing user password 1"));
        users.add(new User("testing user name 2", "testing user login 2", "testing user password 2"));
        return users;
    }

}