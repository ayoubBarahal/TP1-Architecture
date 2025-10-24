package project.ensa.Entity;

public class Warehouse {

    private String name ;
    int stock ;
    private int employees ;


    public Warehouse(String name, int stock, int employees) {
        this.name = name;
        this.stock = stock;
        this.employees = employees;
    }

    public String report() {
        return  stock + "-" + employees;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
