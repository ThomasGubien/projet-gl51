package store

import spock.lang.Specification

class ProductTest extends Specification {
    def "create product test"(){

        when:
        Product product = new Product(id: "1", name: "myProduct", description: "Product description", price: 10, idealTemperature: 20)
        Product product2 = new Product(id: "2", name: "myProduct2")
        Product product3 = new Product()

        then:
        product.id == "1"
        product.name == "myProduct"
        product.description == "Product description"
        product.price == 10
        product.idealTemperature == 20

        product2.id == "2"
        product2.name == "myProduct2"

        product3.id == null
    }


}
