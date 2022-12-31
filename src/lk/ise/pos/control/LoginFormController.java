package lk.ise.pos.control;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ise.pos.db.Database;
import lk.ise.pos.entity.User;

public class LoginFormController {
    public AnchorPane loginFormContext;
    public TextField txtUsername;
    public PasswordField pwd;

    public void initialize(){
    }

    public void loginOnAction(ActionEvent actionEvent) {
        Database.users.stream().forEach(user -> {});
    }
}
