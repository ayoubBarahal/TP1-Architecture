//package project.ensa.Entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FactoryWithTransitStock  {
//
//    private String countryCode ;
//    private List<Warehouse> warehouses =  new ArrayList<>() ;
//    private int transitStock;
//
//    public FactoryWithTransitStock(Factory factory, Integer transitStock) {
//        this.countryCode = factory.getCountryCode() ;
//        this.transitStock = transitStock ;
//    }
//
//
//    public String getCountryCode() {
//        return countryCode.substring(0, 1).toUpperCase();
//    }
//
//    public String report() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getCountryCode()).append(":<");
//
//        for (int i = 0; i < warehouses.size(); i++) {
//            if (i > 0) {
//                sb.append(",");
//            }
//            sb.append(getCountryCode()).append("e").append(i + 1).append(":");
//            sb.append(warehouses.get(i).report());
//        }
//
//        sb.append(">").append("-" + transitStock);
//        return sb.toString();
//    }
//
//
//    public int getProductionCapacity() {
//        int total = 0  ;
//        for(Warehouse warehouse : warehouses) {
//            total+= warehouse.getStock() ;
//        }
//        return total ;
//    }
//}
