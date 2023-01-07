package lk.ise.pos.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ise.pos.db.Database;
import lk.ise.pos.entity.Customer;
import lk.ise.pos.view.tm.CustomerTM;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Optional;

public class CustomerFormController {
    public AnchorPane customerFormContext;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TableView<CustomerTM> tbl;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public Button btn;

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAll("");

        tbl.getSelectionModel()
                .selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setData(newValue);
                    }
                }));

    }

    private void setData(CustomerTM newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtSalary.setText(String.valueOf(newValue.getSalary()));
        btn.setText("Update Customer");
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) customerFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader
                .load(getClass().getResource("../view/DashboardForm.fxml"))));
    }

    public void saveCustomer(ActionEvent actionEvent) {
        Customer c1 = new Customer(
                txtId.getText(), txtName.getText(), txtAddress.getText()
                , Double.parseDouble(txtSalary.getText())
        );


        if (btn.getText().equals("Save Customer")) {
            Database.customers.add(c1);
            new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
            loadAll("");
        } else {
            for (Customer c : Database.customers) {
                if (c.getId().equals(txtId.getText())) {
                    c.setName(c1.getName());
                    c.setAddress(c1.getAddress());
                    c.setSalary(c1.getSalary());

                    new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
                    loadAll("");
                    btn.setText("Save Customer");
                }
            }
        }


        clearData();
    }

    private void clearData() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }

    private void loadAll(String searchText) {
        ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
        for (Customer c : Database.customers) {
            Button btn = new Button("Delete");
            CustomerTM tm = new CustomerTM(
                    c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn
            );


            btn.setOnAction(e->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are yiu sure?",
                        ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> type = alert.showAndWait();
                if (type.get()==ButtonType.YES){
                    Database.customers.remove(c);
                    new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                    loadAll("");
                }

            });

            tmList.add(tm);
        }
        tbl.setItems(tmList);
    }

    public void newCustomer(ActionEvent actionEvent) {
        clearData();
        btn.setText("Save Customer");
    }
}
