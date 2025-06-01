package pojo;

public class Cargo {
    private double totalVolume;
    private double totalWeight;
    private TotalPrice totalPrice;

    // геттеры и сеттеры
    public double getTotalVolume() { return totalVolume; }
    public void setTotalVolume(double totalVolume) { this.totalVolume = totalVolume; }

    public double getTotalWeight() { return totalWeight; }
    public void setTotalWeight(double totalWeight) { this.totalWeight = totalWeight; }

    public TotalPrice getTotalPrice() { return totalPrice; }
    public void setTotalPrice(TotalPrice totalPrice) { this.totalPrice = totalPrice; }
}