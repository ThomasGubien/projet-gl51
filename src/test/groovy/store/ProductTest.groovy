package store

import spock.lang.Specification

class ProductTest extends Specification {
    def "create product test"(){

        when:
        Product product = new Product(id: "1", name: "myProduct", description: "Product description", price: 10, idealTemperature: 20)

        then:
        product.id == "1"
        product.name == "myProduct"
        product.description == "Product description"
        product.price == 10
        product.idealTemperature == 20
    }


}
