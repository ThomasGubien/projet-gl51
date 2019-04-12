package store

class MemoryProductStorage implements ProductStorage{

    private Map<String, Product> products = [:]

    @Override
    String save(Product p) {
        products.put(UUID.randomUUID(), p)
        all()
        products
    }

    @Override
    void update(String id, Product p) {

    }

    @Override
    Product getByID(String id) {
        return null
    }

    @Override
    void delete(String id) {

    }

    @Override
    List<Product> all() {
        products.values().toList()
    }
}
