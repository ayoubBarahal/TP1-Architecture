package project.ensa.Builders;

import project.ensa.Entity.Company;
import project.ensa.Entity.SupplyChain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplyChainBuilder {
    SupplyChain supplyChain =  new SupplyChain() ;
    List<Company> companies = new ArrayList<>();
    Map<String, Map<String, Integer>> distances = new HashMap<>();

    public SupplyChainBuilder addCompany(Company c) {
        companies.add(c);
        return this;
    }

    public SupplyChainBuilder addLogisticsNetwork(String config) {
        String[] routes = config.split(",");
        for (String route : routes) {
            String[] parts = route.split(":");
            if (parts.length == 3) {
                String from = parts[0].trim();
                int dist = Integer.parseInt(parts[1]);
                String to = parts[2].trim();

                from = convertCountryNameToCode(from);
                to = convertCountryNameToCode(to);

                distances.computeIfAbsent(from, k -> new HashMap<>()).put(to, dist);
                distances.computeIfAbsent(to, k -> new HashMap<>()).put(from, dist);
            }
        }
        return this;
    }

    private String convertCountryNameToCode(String name) {
        switch (name.toLowerCase()) {
            case "maroc": return "M";
            case "senegal": return "S";
            case "france": return "F";
            default: return name.substring(0, 1).toUpperCase();
        }
    }

    public SupplyChain build() {
        supplyChain.companies = this.companies; // mettre à jour les sociétés
        supplyChain.distances = this.distances; // mettre à jour les distances
        return supplyChain;
    }

    public SupplyChainBuilder setOrderSize(int orderSize) {
        supplyChain.setOrderSize(orderSize);
        return this;
    }



}
