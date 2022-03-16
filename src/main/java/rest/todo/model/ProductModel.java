package rest.todo.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductModel {
    private int idProduct;
    private String brand;
    private String warding;
    private float price;
    private int idCat;

    public ProductModel(int idProduct, String brand, String warding, float price, int idCat) {
        this.idProduct = idProduct;
        this.brand = brand;
        this.warding = warding;
        this.price = price;
        this.idCat = idCat;
    }

    public ProductModel(String brand, String warding, float price, int idCat) {
        this.brand = brand;
        this.warding = warding;
        this.price = price;
        this.idCat = idCat;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarding() {
        return warding;
    }

    public void setWarding(String warding) {
        this.warding = warding;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    @Override
    public String toString() {
        return "idProduct=" + idProduct +
                ", brand='" + brand + '\'' +
                ", warding='" + warding + '\'' +
                ", price=" + price +
                ", idCat=" + idCat ;
    }
}