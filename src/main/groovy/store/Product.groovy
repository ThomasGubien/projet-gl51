package store
import groovy.transform.EqualsAndHashCode
@EqualsAndHashCode(excludes="id")
class Product {
    String id
    String name
    String description
    double price
    double idealTemperature
}