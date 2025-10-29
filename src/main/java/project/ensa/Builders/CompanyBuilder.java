package project.ensa.Builders;

import project.ensa.Creators.FactoryCreator;
import project.ensa.Entity.Company;
import project.ensa.Entity.Factory;

import java.util.ArrayList;
import java.util.List;

public class CompanyBuilder {

    public Company company = new Company() ;
    public FactoryCreator factoryCreator = FactoryCreator.getInstance() ;
    private List<Factory> factories = new ArrayList<>() ;
    private Integer transitStock = null ;


    public CompanyBuilder setCEO(String ceoName) {
        company.setCeo(ceoName);
        return this ;
    }

    public CompanyBuilder addFactory(String country, String... warehouseData) {
        Factory factory = factoryCreator.getInstance().createFactory(country, warehouseData);
        this.factories.add(factory);
        company.addFactory(factory);
        return this;
    }

    public CompanyBuilder addTransitStock(String transitStock) {
        this.transitStock = Integer.parseInt(transitStock);
        return this;
    }

    public Company build() {
        if (this.transitStock != null) {
            for (Factory factory : this.factories) {
                factory.setTransitStock(this.transitStock);
            }
        }
        return this.company;
    }

}
