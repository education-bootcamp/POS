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
import javafx.stage.Window;
import lk.ise.pos.db.Database;
import lk.ise.pos.entity.Customer;
import lk.ise.pos.entity.Item;
import lk.ise.pos.view.tm.CartTm;

import java.io.IOException;
import java.util.Optional;

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
    public TableView<CartTm> tblCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colOption;
    public Label lblTotal;
    public AnchorPane context;

    public void initialize() {
        //======
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        //======

        loadCustomerIds();
        loadItemCodes();


        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setCustomerData(newValue);
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setItemData(newValue);
            }
        });

    }

    private void setItemData(String code) {
        Item item = Database.items.stream().filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
        if (item != null) {
            txtDescription.setText(item.getDescription());
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        } else {
            new Alert(Alert.AlertType.WARNING, "Not Found").show();
        }
    }

    private void loadItemCodes() {
        for (Item data : Database.items) cmbItemCode.getItems().add(data.getCode());
    }

    private void setCustomerData(String id) {
        Customer customer =
                Database.customers.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        if (customer != null) { // null!=customer==> fast
            txtCustomerName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            // String x="nimal"==> literal, String a= new String("bandara"); =>
            // String(literal,dynamic),StringBuilder,StringBuffer// (Threads, String API)
            txtSalary.setText(String.valueOf(customer.getSalary()));
            txtSalary.setText(customer.getSalary() + "");//
        } else {
            new Alert(Alert.AlertType.WARNING, "Not Found").show();
        }
    }

    private void loadCustomerIds() {
        for (Customer data : Database.customers) {
            cmbCustomerId.getItems().add(data.getId());
        }
    }

    ObservableList<CartTm> tmList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtRequestQty.getText());
        double total = unitPrice * qty;

        if (isExists(cmbItemCode.getValue())) {
            for (CartTm t : tmList
            ) {
                if (t.getCode().equals(cmbItemCode.getValue())) {
                    t.setQty(t.getQty() + qty);
                    t.setTotal(t.getTotal() + total);
                    tblCart.refresh();
                }
            }
            // update
        } else {
            Button btn = new Button("Delete");
            CartTm tm = new CartTm(cmbItemCode.getValue(),
                    txtDescription.getText(), unitPrice, qty, total, btn);

            btn.setOnAction(e -> {
                tmList.remove(tm);
                tblCart.refresh();
                calculateTotal();
            });

            tmList.add(tm);
        }
        clear();
        tblCart.setItems(tmList);
        calculateTotal();

    }

    private void calculateTotal() {
        double total = 0;
        for (CartTm tm : tmList) {
            total += tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    private boolean isExists(String code) {
        Optional<CartTm> selectedCartTm =
                tmList.stream().filter(e -> e.getCode().equals(code)).findFirst();
        return selectedCartTm.isPresent();
    }

    private void clear() {
        cmbItemCode.setValue(null);
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtRequestQty.clear();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader
                .load(getClass().getResource("../view/DashboardForm.fxml"))));
    }
}
