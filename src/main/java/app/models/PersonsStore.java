package app.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonsStore {
    private final ObservableList<Person> persons = FXCollections.observableArrayList();

    public PersonsStore() {
        this.persons.addAll(
                new Person("Charles", 26, 3345674, "Miami"),
                new Person("Jane", 32, 3456712, "NY"),
                new Person("Fred", 24, 3345534, "CA"),
                new Person("John", 25, 3456712, "LA")
        );

    }

    public ObservableList<Person> getPersonsList() {
        return persons;
    }

    public void addPerson(Person person) {
        if (person != null) {
            this.persons.add(person);
        }
    }

    public void removePerson(Person person) {
        if (person != null) {
            this.persons.remove(person);
        }
    }

    public void updatePerson(Person person, String name, int age, long phone, String address) {
        if (person != null) {
            person.setName(name);
            person.setAge(age);
            person.setPhone(phone);
            person.setAddress(address);
        }
    }
}
