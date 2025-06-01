package pojo;

import java.util.List;

public class TransportOrder {

    private int number;
    private String orderId;
    private String created;
    private Status status;
    private Cargo cargo;
    private List<Item> items;

    // геттеры и сеттеры
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCreated() { return created; }
    public void setCreated(String created) { this.created = created; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}
