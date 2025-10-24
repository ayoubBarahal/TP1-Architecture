package project.ensa.Creators;


import project.ensa.Entity.Warehouse;

public class WarehouseCreator {

    public Warehouse createWarehouse(String name , int stock , int employees) {
        return new Warehouse(name , stock , employees) ;
    }

}
