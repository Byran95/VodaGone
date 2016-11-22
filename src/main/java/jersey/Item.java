package jersey;

/**
 * Created by Bryan on 22-11-2016.
 */

public class Item {
    private String sku;
    private String category;
    private String title;

    public Item(String sku, String category, String title) {
        this.sku = sku;
        this.category = category;
        this.title = title;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() { return "sku: " + this.sku + ", category: " + this.category + ", title: " + this.title; }
}
