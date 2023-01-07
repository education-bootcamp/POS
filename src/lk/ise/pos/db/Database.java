package lk.ise.pos.db;


import lk.ise.pos.entity.Customer;
import lk.ise.pos.entity.Item;
import lk.ise.pos.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    public static ArrayList<User> users= new ArrayList();
    public static ArrayList<Customer> customers= new ArrayList();
    public static ArrayList<Item> items= new ArrayList();

    static {
        users.add(new User("linda",encryptPassword("1234")));
        users.add(new User("anna",encryptPassword("1234")));
        users.add(new User("tom",encryptPassword("1234")));

        Item item1 = new Item("D-001","Description 1",25,2500);
        Item item2 = new Item("D-002","Description 1",34,4355);
        Item item3 = new Item("D-003","Description 2",20,2234);
        Item item4 = new Item("D-004","Description 3",30,5854);

        items.addAll(
                Arrays.asList(item1,item2,item3,item4)
        );

    }

    private static String encryptPassword(String rowPassword){
        return BCrypt.hashpw(rowPassword, BCrypt.gensalt());
    }

}
