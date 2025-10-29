package project.ensa.Creators;


import project.ensa.Entity.Factory;
import project.ensa.Entity.Warehouse;

public  class FactoryCreator {

    private static FactoryCreator instance ;

    public static synchronized FactoryCreator getInstance() {
        if (instance == null)
            instance = new FactoryCreator();
        return instance;
    }

    public Factory createFactory(String country, String... warehouseData) {
        Factory factory = new Factory(country);
        WarehouseCreator warehouseFactory = new WarehouseCreator();

        for (int i = 0; i < warehouseData.length; i += 2) {
            int stock = Integer.parseInt(warehouseData[i]);
            int employees = Integer.parseInt(warehouseData[i + 1]);

            String warehouseId = factory.getCountryCode() + "e" + ((i / 2) + 1);
            Warehouse warehouse = warehouseFactory.createWarehouse(warehouseId ,  stock, employees);
            factory.addWarehouse(warehouse);
        }

        return factory;
    }

}
