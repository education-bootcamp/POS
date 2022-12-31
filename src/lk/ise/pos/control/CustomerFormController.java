package lk.ise.pos.control;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ise.pos.db.Database;
import lk.ise.pos.entity.Customer;

public class CustomerFormController {
    public AnchorPane customerFormContext;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }

    public void saveCustomer(ActionEvent actionEvent) {
        Customer c1 = new Customer(
                txtId.getText(),txtName.getText(),txtAddress.getText()
                ,Double.parseDouble(txtSalary.getText())
        );

        Database.customers.add(c1);
        new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
clearData();
    }

    private void clearData() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }
}
