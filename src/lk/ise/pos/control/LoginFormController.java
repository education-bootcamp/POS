package lk.ise.pos.control;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ise.pos.db.Database;
import lk.ise.pos.entity.User;
import org.mindrot.jbcrypt.BCrypt;

public class LoginFormController {
    public AnchorPane loginFormContext;
    public TextField txtUsername;
    public PasswordField pwd;

    public void initialize(){
    }

    public void loginOnAction(ActionEvent actionEvent) {
        User selectedUser = Database.users.stream()
                .filter(user -> user.getUsername()
                        .equals(txtUsername.getText()))
                .findFirst().orElse(null);
        if (selectedUser!=null){
            if (BCrypt.checkpw(pwd.getText(),selectedUser.getPassword())){
                System.out.println("user logged");
            }else{
                System.out.println("Wrong password!");
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "User name not found!").show();
        }
    }
}
