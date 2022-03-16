package rest.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CategoryModel {
    private int idCategory ;
    private String nameC;

    public CategoryModel(int idCategory, String nameC) {
        this.idCategory = idCategory;
        this.nameC = nameC;
    }

    public CategoryModel(String nameC) {
        this.nameC = nameC;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "idCategory=" + idCategory +
                ", nameC='" + nameC + '\'' +
                '}';
    }
}
