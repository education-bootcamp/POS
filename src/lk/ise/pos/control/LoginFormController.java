package lk.ise.pos.control;

import lk.ise.pos.db.Database;
import lk.ise.pos.entity.User;

public class LoginFormController {
    public void initialize(){
       User u= Database.users.get(1);

    }
}
