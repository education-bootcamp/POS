package lk.ise.pos.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderFormController {
    public AnchorPane context;
    public TableView tblOrders;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCost;
    public TableColumn colDate;

    public void backToHome(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader
                .load(getClass().getResource("../view/DashboardForm.fxml"))));
    }
}
