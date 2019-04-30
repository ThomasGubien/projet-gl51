package store
import spock.lang.Specification

class MemoryProductStorageTest extends Specification {

    ProductStorage store = new MemoryProductStorage()

    def "empty storage returns empty list"() {
        expect:
            store.all() == []
    }


    def "adding a product returns the product in the list"(){
        setup:
        store.save(new Product(name: "myProduct"))

        when:
        def all = store.all()

        then:
        all.size() == 1
        all.first().name == "myProduct"
    }

    def "adding a product will generate a new id"(){
        setup:
        store.save(new Product(name: "myProduct"))

        when:
        store.save(new Product(name: "myNewProduct"))
        def all = store.all()

        then:
        all.find({p -> p.name == "myProduct"}).id != all.find({p -> p.name == "myNewProduct"}).id
    }
}