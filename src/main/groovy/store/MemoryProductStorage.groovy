package store

import store.exceptions.NotExistingProductException
import store.exceptions.WrongIdException

import javax.inject.Singleton

@Singleton
class MemoryProductStorage implements ProductStorage {

    private Map<String, Product> products = new HashMap<>()

    @Override
    Product getByID(String id) throws NotExistingProductException {
        if (!products.containsKey(id)) {
            throw new NotExistingProductException()
        } // else

        return products.get(id)
    }

    @Override
    List<Product> all() {
        return products.values().toList()
    }

    @Override
    String save(Product p) {
        p.id = UUID.randomUUID().toString()
        products.put(p.id, p)
        return p.id
    }

    @Override
    void update(String id, Product p) throws WrongIdException {
        if (p.id != null && id != p.id) {
            throw new WrongIdException()
        } // else

        products.put(id, p)
    }

    @Override
    void delete(String id) {
        products.remove(id)
    }
}