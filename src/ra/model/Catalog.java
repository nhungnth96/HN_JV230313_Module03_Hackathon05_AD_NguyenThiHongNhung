package ra.model;

import ra.config.InputMethods;

public class Catalog {
    private int catalogId;
    private String catalogName;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    @Override
    public String toString() {
        return
                "ID: " + catalogId +
                        " | Name: " + catalogName
                ;
    }

    public void inputData() {
        System.out.println("Nhập ID: ");
        this.catalogId = InputMethods.getInteger();
        System.out.println("Nhập name: ");
        this.catalogName = InputMethods.getString();
    }


}
