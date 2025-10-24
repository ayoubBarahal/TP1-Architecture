package project.ensa.Entity;

import java.util.*;

public class SupplyChain {
    public List<Company> companies = new ArrayList<>();
    public Map<String, Map<String, Integer>> distances = new HashMap<>();
    int orderSize ;

    public SupplyChain(List<Company> companies, Map<String, Map<String, Integer>> distances) {
        this.companies = companies;
        this.distances = distances;
    }

    public SupplyChain(){}

    public void prepareOrder() {

        Company maxCompany = companies.stream()
                .max(Comparator.comparingInt(Company::currentProductionCapacity))
                .orElse(null);

        if (maxCompany == null) return;

        Company other = companies.stream()
                .filter(c -> c != maxCompany)
                .findFirst()
                .orElse(null);

        if (other == null || other.getFactories().isEmpty()) return;

        String clientCountry = other.getFactories().get(0).getCountryCode();

        Factory closestFactory = null;
        int minDist = Integer.MAX_VALUE;

        for (Factory f : maxCompany.getFactories()) {
            String country = f.getCountryCode();
            int dist = distances
                    .getOrDefault(country, Collections.emptyMap())
                    .getOrDefault(clientCountry, Integer.MAX_VALUE);

            if (dist < minDist) {
                minDist = dist;
                closestFactory = f;
            }
        }

        if (closestFactory == null) return;

        int moved = 0;
        for (Warehouse w : closestFactory.getWarehouses()) {
            int move = (int) (w.getStock() * 0.4);
            w.setStock(w.getStock() - move);
            moved += move;
        }

        closestFactory.setTransitStock(closestFactory.getTransitStock() + moved);
    }

    public void setOrderSize(int orderSize) {
        this.orderSize = orderSize;
    }

    public void executeDelivery() {
        if (companies.size() < 2 || orderSize <= 0) return;

        Company supplier = companies.stream()
                .max(Comparator.comparingInt(c -> c.getFactories().stream()
                        .mapToInt(Factory::getTransitStock).sum()))
                .orElse(null);

        Company receiver = companies.stream()
                .min(Comparator.comparingInt(c -> c.getFactories().stream()
                        .mapToInt(Factory::getTransitStock).sum()))
                .orElse(null);

        if (supplier == null || receiver == null || supplier == receiver) return;

        int totalDelivered = 0;

        for (Factory f : supplier.getFactories()) {
            if (totalDelivered >= orderSize) break;

            int available = f.getTransitStock();
            if (available <= 0) continue;

            int toDeliver = Math.min(available, orderSize - totalDelivered);

            f.setTransitStock(f.getTransitStock() - toDeliver);

            Factory receiverFactory = receiver.getFactories().get(0);
            receiverFactory.setTransitStock(
                    (receiverFactory.getTransitStock() == null ? 0 : receiverFactory.getTransitStock()) + toDeliver
            );

            totalDelivered += toDeliver;
        }
    }

    public String deliveryStatus() {
        Company receiver = companies.stream()
                .min(Comparator.comparingInt(Company::currentProductionCapacity))
                .orElse(null);
        if (receiver == null) return "INEFFICIENT";

        int totalTransit = receiver.getFactories().get(0).getTransitStock();
        return totalTransit >= orderSize ? "EFFICIENT" : "INEFFICIENT";
    }


}

