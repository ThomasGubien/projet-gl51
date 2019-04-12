package projet.gl51

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class StudentControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)
    @Shared @AutoCleanup RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())


    void "test index"() {
        given:
        HttpResponse response = client.toBlocking().exchange("/student")
        List<Student> l = client.toBlocking().retrieve(HttpRequest.GET("/student"),List)

        expect:
        response.status == HttpStatus.OK
        l == [new Student(firstName: "Eloïse", lastName: "Grillet"),
              new Student(firstName: "Thomas", lastName: "Gubien")]
    }
}
