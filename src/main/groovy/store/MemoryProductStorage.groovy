package store

class MemoryProductStorage implements ProductStorage{

    private Map<UUID, Product> products = [:]

    @Override
    String save(Product p) {
        p.id = UUID.randomUUID()
        products.put(p.id, p)
        all()
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
