package store

class Product {
    private String id
    private String name
    private String description
    private double price
    private double idealTemperature

    Product(String id, String name, String description, double price, double idealTemperature) {
        this.id = id
        this.name = name
        this.description = description
        this.price = price
        this.idealTemperature = idealTemperature
    }

    Product(String id, String name) {
        this.id = id
        this.name = name
    }

    Product() {
    }

    void finalized(){
        null
    }

    void updatePrice(double price){
        this.price = price
    }

    void update(Product p){
        this.name = p.name
        this.description = p.description
        this.price = p.price
        this.idealTemperature = p.idealTemperature
    }

    @Override
    String toString(){
        String string = name + ", price: " + price
    }

    Product getProduct(){
        this
    }

    String getId(){
        this.id
    }

    String getName(){
        this.name
    }

    String getDescription(){
        this.description
    }

    double getPrice(){
        this.price
    }

    double getIdealTemperature(){
        this.idealTemperature
    }
}