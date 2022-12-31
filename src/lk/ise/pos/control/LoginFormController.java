package lk.ise.pos.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ise.pos.db.Database;
import lk.ise.pos.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginFormContext;
    public TextField txtUsername;
    public PasswordField pwd;

    public void initialize(){
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        User selectedUser = Database.users.stream()
                .filter(user -> user.getUsername()
                        .equals(txtUsername.getText()))
                .findFirst().orElse(null);
        if (selectedUser!=null){
            if (BCrypt.checkpw(pwd.getText(),selectedUser.getPassword())){

                Stage stage = (Stage) loginFormContext.getScene().getWindow();
                stage.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml")))
                );
                stage.centerOnScreen();

            }else{
                System.out.println("Wrong password!");
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "User name not found!").show();
        }
    }
}
