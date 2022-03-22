package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product_Panier_Model {

    private int idProduct;
    private int idPanier;

    public Product_Panier_Model(int idProduct, int idPanier) {
        this.idProduct = idProduct;
        this.idPanier = idPanier;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getIdPanier() {
        return idPanier;
    }

    @Override
    public String toString() {
        return "Product_Panier_Model{" +
                "idProduct=" + idProduct +
                ", idPanier=" + idPanier +
                '}';
    }
}
