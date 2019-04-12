package projet.gl51

import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
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
        HttpResponse response = client.toBlocking().exchange("/student", Argument.listOf(Student).type)
        List<Student> l = client.toBlocking().retrieve(HttpRequest.GET("/student"),List)

        expect:
        response.status == HttpStatus.OK

        response.body()[0].firstName == 'Eloise'
        response.body()[1].firstName == 'Thomas'

        response.body()[0].lastName == 'Grillet'
        response.body()[1].lastName == 'Gubien'

        l[0].firstName == 'Eloise'
        l[1].firstName == 'Thomas'

        l[0].lastName == 'Grillet'
        l[1].lastName == 'Gubien'
    }
}
