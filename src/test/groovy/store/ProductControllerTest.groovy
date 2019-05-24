package store

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

class ProductControllerTest extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())


    void "test index"() {
        given:
        List<Product> products = client.toBlocking().retrieve(HttpRequest.GET('/store/product'), Argument.listOf(Product).type)

        expect:
        products == []
    }

    void "test create and get"() {
        setup:
        Product sentProduct = new Product(name: name, description: description, price: price, idealTemperature: idealTemperature)

        when:
        String id = client.toBlocking().retrieve(HttpRequest.POST('/store/product', sentProduct))
        Product getProduct = client.toBlocking().retrieve(HttpRequest.GET('/store/product/'+id), Argument.of(Product).type)

        then:
        id != ""
        getProduct != null
        getProduct.equals(sentProduct)

        where:
        name | description | price | idealTemperature
        "myProduct" | "product" | 15 | 10
        "myProduct2" | "product2" | 200 | 51
    }

    void "test update"() {
        setup:
        String id = client.toBlocking().retrieve(HttpRequest.POST('/store/product', new Product(name: name, description: description, price: price, idealTemperature: idealTemperature)))

        when:
        Product newProduct = new Product(name: name1, description: description1, price: price1, idealTemperature: idealTemperature1)
        HttpStatus status = client.toBlocking().retrieve(HttpRequest.PATCH('/store/product/'+id, newProduct), Argument.of(HttpStatus).type)
        Product afterProduct = client.toBlocking().retrieve(HttpRequest.GET('/store/product/'+id), Argument.of(Product).type)

        then:
        status.equals(HttpStatus.OK)
        newProduct.equals(afterProduct)

        where:
        name | description | price | idealTemperature | name1 | description1 | price1| idealTemperature1
        "myProduct" | "product" | 15 | 10 | "myProduct2" | "product2" | 200 | 51
        "myProduct2" | "product2" | 200 | 51 | "myProduct" | "product" | 15 | 10
    }

    void "test delete"() {
        setup:
        String id = client.toBlocking().retrieve(HttpRequest.POST('/store/product', new Product(name: name, description: description, price: price, idealTemperature: idealTemperature)))

        when:
        HttpStatus status = client.toBlocking().retrieve(HttpRequest.DELETE('/store/product/'+id), Argument.of(HttpStatus).type)
        Product product = client.toBlocking().retrieve(HttpRequest.GET('/store/product/'+id), Argument.of(Product).type)

        then:
        status.equals(HttpStatus.OK)
        thrown HttpClientResponseException
        product == null

        where:
        name | description | price | idealTemperature
        "myProduct" | "product" | 15 | 10
        "myProduct2" | "product2" | 200 | 51
    }
}