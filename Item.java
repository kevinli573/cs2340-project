import javafx.scene.control.Button;
import java.text.DecimalFormat;

public class Item {
    private String name;
    private int weight;
    private double buyPrice;
    private double sellPrice;

    private int quantity;

    private Button buy = new Button("Buy");
    private Button sell = new Button("Sell");

    public Item(String name, int weight, int quantity) {
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
        buy.setOnAction(e -> {
            setQuantity(getQuantity() - 1);
        });
        sell.setOnAction(e -> {
            setQuantity(getQuantity() + 1);
        });
    }

    public String getName() {
        return name;
    }
    public int getWeight() {
        return weight;
    }
    public int getQuantity() {
        return quantity;
    }
    public Double getBuyPrice() {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(buyPrice));
    }
    public Button getBuy() {
        return buy;
    }
    public Button getSell() {
        return sell;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }
    public double getSellPrice() {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(sellPrice));
    }
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
    public String toString() {
        return name;
    }
}
