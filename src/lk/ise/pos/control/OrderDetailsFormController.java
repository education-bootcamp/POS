package lk.ise.pos.control;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ise.pos.db.Database;
import lk.ise.pos.entity.Order;

import java.text.SimpleDateFormat;
import java.util.Optional;

public class OrderDetailsFormController {
    private TextField txtId;
    private TextField txtName;
    private TextField txtDate;
    private TableView<?> tblOrderDetails;
    private TableColumn<?, ?> colItem;
    private TableColumn<?, ?> colDescription;
    private TableColumn<?, ?> colQty;
    private TableColumn<?, ?> colUnitPrice;
    private TextField txtCost;

    public void setOrder(String orderId){
        Optional<Order> order = Database.orders.stream().filter(e -> e.getOrderId().equals(orderId)).findFirst();
        if (!order.isPresent()) {
            new Alert(Alert.AlertType.WARNING,"Not Found").show();
            return;
        }
        txtId.setText(order.get().getOrderId());
        txtName.setText(Database.customers.stream()
                .filter(e->e.getId().equals(order.get().getCustomer()))
                .findFirst().get().getName());
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(order.get().getDate()));
        txtCost.setText(String.valueOf(order.get().getTotal()));
    }

}
