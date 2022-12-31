package lk.ise.pos.db;

import lk.ise.pos.entity.User;

import java.util.ArrayList;

public class Database {
    public static ArrayList<User> users= new ArrayList();

    static {
        users.add(new User("linda",encryptPassword("1234")));
        users.add(new User("anna",encryptPassword("1234")));
        users.add(new User("tom",encryptPassword("1234")));
    }

    private static String encryptPassword(String rowPassword){
        ///// return encrypted password
        return null;
    }

}
