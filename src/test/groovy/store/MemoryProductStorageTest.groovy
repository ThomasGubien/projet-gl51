package store
import spock.lang.Specification
import store.exceptions.NotExistingProductException
import store.exceptions.WrongIdException

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

    def "deleting a product will remove it from the list"(){
        setup:
        String ID = store.save(new Product(name: "myProduct"))
        store.delete(ID)

        when:
        def all = store.all()

        then:
        all.find({p -> p.id == ID}) == null
    }
    def "updating a product which has a different id than the given one will throw a WrongIdException"() {
        setup:
        String ID = store.save(new Product())
        Product p = new Product(id: "myProduct")

        when:
        store.update(ID, p)

        then:
        thrown WrongIdException
    }

    def "modifying a product will change it in the list"() {
        setup:
        String Name = "myProduct"
        String Desc = "myProduct desc"
        String ID = store.save(new Product(name: Name))
        Product newVersion = new Product(id: ID, name: Name, description: Desc)

        when:
        store.update(ID, newVersion)

        then:
        Product p = store.all().first()
        p.id == ID
        p.name == Name
        p.description == Desc
    }

    def "getting a product by its id will throw a NotExistingProductException if it does not exits"() {
        setup:
        String ID = "myProduct"

        when:
        store.getByID(ID)

        then:
        thrown NotExistingProductException
    }

    def "getting a product by its id will return it if it does exist"() {
        setup:
        Product p = new Product()
        String ID = store.save(p)

        when:
        Product p2 = store.getByID(ID)

        then:
        p == p2
    }
}