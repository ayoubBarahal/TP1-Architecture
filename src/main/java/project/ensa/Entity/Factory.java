package project.ensa.Entity;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    private String countryCode ;
    List<Warehouse> warehouses =  new ArrayList<>() ;
    Integer transitStock ;


    public Factory(String country) {
        this.countryCode = country ;
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }

    public String getCountryCode() {
        return countryCode.substring(0, 1).toUpperCase();
    }

    public void setTransitStock(int transitStock) {
        this.transitStock = transitStock;
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCountryCode()).append(":<");

        for (int i = 0; i < warehouses.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(getCountryCode()).append("e").append(i + 1).append(":");
            sb.append(warehouses.get(i).report());
        }

        sb.append(">");

        if (transitStock != null) {
            sb.append("-").append(transitStock);
        }

        return sb.toString();
    }

    public int getProductionCapacity() {
        int total = 0  ;
        for(Warehouse warehouse : warehouses) {
            total+= warehouse.getStock() ;
        }
        return total ;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public Integer getTransitStock() {
        return transitStock;
    }

}
