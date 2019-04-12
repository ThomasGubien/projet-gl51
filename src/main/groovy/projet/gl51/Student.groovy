package projet.gl51

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Student {
    String firstName
    String lastName

    String toString(){
        'firstName - lastName'
    }
}
