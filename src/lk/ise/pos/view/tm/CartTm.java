package lk.ise.pos.view.tm;

import javafx.scene.control.Button;

public class CartTm {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;
    private Button btn;

    public CartTm() {
    }

    public CartTm(String code, String description, double unitPrice, int qty, double total, Button btn) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
        this.btn = btn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
