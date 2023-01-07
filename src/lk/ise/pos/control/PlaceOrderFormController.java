package lk.ise.pos.control;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ise.pos.db.Database;
import lk.ise.pos.entity.Customer;
import lk.ise.pos.entity.Item;

public class PlaceOrderFormController {
    public TextField txtCustomerName;
    public ComboBox<String> cmbCustomerId;
    public TextField txtAddress;
    public TextField txtSalary;
    public ComboBox<String> cmbItemCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TextField txtRequestQty;

    public void initialize(){
        loadCustomerIds();
        loadItemCodes();

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setCustomerData(newValue);
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setItemData(newValue);
            }
        });

    }

    private void setItemData(String code) {
        Item item = Database.items.stream().filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
        if (item!=null){
            txtDescription.setText(item.getDescription());
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        }else{
            new Alert(Alert.AlertType.WARNING,"Not Found").show();
        }
    }

    private void loadItemCodes() {
        for(Item data: Database.items) cmbItemCode.getItems().add(data.getCode());
    }

    private void setCustomerData(String id){
        Customer customer =
                Database.customers.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        if (customer!=null){ // null!=customer==> fast
            txtCustomerName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            // String x="nimal"==> literal, String a= new String("bandara"); =>
            // String(literal,dynamic),StringBuilder,StringBuffer// (Threads, String API)
            txtSalary.setText(String.valueOf(customer.getSalary()));
            txtSalary.setText(customer.getSalary()+"");//
        }else{
            new Alert(Alert.AlertType.WARNING,"Not Found").show();
        }
    }

    private void loadCustomerIds() {
        for (Customer data: Database.customers){
            cmbCustomerId.getItems().add(data.getId());
        }
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtRequestQty.getText());
        double total=unitPrice*qty;

    }
}
