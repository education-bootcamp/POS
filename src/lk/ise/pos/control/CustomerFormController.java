package lk.ise.pos.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ise.pos.db.Database;
import lk.ise.pos.entity.Customer;
import lk.ise.pos.view.tm.CustomerTM;

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

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }

    public void saveCustomer(ActionEvent actionEvent) {
        Customer c1 = new Customer(
                txtId.getText(),txtName.getText(),txtAddress.getText()
                ,Double.parseDouble(txtSalary.getText())
        );

        Database.customers.add(c1);
        new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
        loadAll("");
clearData();
    }

    private void clearData() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }

    private void loadAll(String searchText){
        ObservableList<CustomerTM> tmList= FXCollections.observableArrayList();
        for(Customer c:Database.customers){
            Button btn = new Button("Delete");
            CustomerTM tm = new CustomerTM(
                    c.getId(),c.getName(),c.getAddress(),c.getSalary(),btn
            );
            tmList.add(tm);
        }
        tbl.setItems(tmList);
    }
}
