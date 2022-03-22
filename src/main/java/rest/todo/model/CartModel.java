package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class CartModel {
    private int idPanier;
    private int idUser;
    private Date date;
    private int quantite;

    public CartModel(int idPanier, int idUser, Date date,int quantite) {
        this.idPanier = idPanier;
        this.idUser = idUser;
        this.date = date;
        this.quantite=quantite;
    }

    public CartModel(int idUser, Date date,int quantite) {
        this.idUser = idUser;
        this.date = date;
        this.quantite=quantite;

    }

    public int getIdPanier() {
        return idPanier;
    }

    public int getIdUser() {
        return idUser;
    }

    public Date getDate() {
        return date;
    }

    public int getQuantite() {
        return quantite;
    }

    @Override
    public String toString() {
        return "cart{" +
                "idPanier=" + idPanier +
                ", idUser=" + idUser +
                ", date=" + date +
                '}';
    }
}
