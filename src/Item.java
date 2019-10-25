public class Item extends TrackModel<Item>{

    private String name;
    private boolean easilyLost;
    private double price;

    public String getName() {
        return this.name;
    }

    public boolean easilyLost() {
        return this.easilyLost;
    }

    public double getPrice() {
        return this.price;
    }

    public Item(String name, boolean easilyLost, double price) {
        this.name = name;
        this.easilyLost = easilyLost;
        this.price = price;
    }
}
