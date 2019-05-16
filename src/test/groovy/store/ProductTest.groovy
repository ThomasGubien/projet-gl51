package store

import spock.lang.Specification

class ProductTest extends Specification {


    Product p = new Product(id: "1", name: "myProduct", description: "Product description", price: 10, idealTemperature: 20)

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

    def "delete product test"(){

        when:
        p = null

        then:
        p == null
    }

    def "Update product test"(){
        setup:
        Product p2 = new Product(id: "id2", name: "product 2", description: "description 2", price: 40, idealTemperature: 5)

        when:
        p.updatePrice(30)
        p2.update(p)

        then:
        p.price == 30
        p2.name == p.name
        p2.description == p.description
        p2.price == p.price
        p2.idealTemperature == p.idealTemperature
    }

    def "get string product test"(){
        setup:
        String s = ""

        when:
        s = p.toString()

        then:
        s == "myProduct, price: 10.0"
    }

    def "get product test"(){
        when:
        Product p2 = p.getProduct()
        String s = p.getName()

        then:
        p2 == p
        s == "myProduct"
    }


}
