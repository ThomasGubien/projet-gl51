package store

class Product {
    String id
    String name
    String description
    double price
    double idealTemperature

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
}