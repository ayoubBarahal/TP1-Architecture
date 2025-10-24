package project.ensa.Entity;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String ceo;
    List<Factory> factories = new ArrayList<>() ;

    public Company() {
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public void addFactory(Factory factory) {
        factories.add(factory);
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(ceo.replace(" ", "")).append(":|");

        for (int i = 0; i < factories.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(factories.get(i).report());
        }

        sb.append("|");
        return sb.toString();
    }

    public int currentProductionCapacity(){
        int total = 0 ;
        for(Factory factory : factories) {
            total+= factory.getProductionCapacity() ;
        }
        return total ;
    }

    public List<Factory> getFactories() {
        return factories;
    }


}
