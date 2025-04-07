package app.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;


public class Person {
    private final SimpleStringProperty name;
    private SimpleIntegerProperty age;
    private final SimpleLongProperty phone;
    private final SimpleStringProperty address;

    public Person(String name, int age, long phone, String address) {
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleLongProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.age = new SimpleIntegerProperty(age);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public long getPhone() {
        return phone.get();
    }

    public void setPhone(long phone) {
        this.phone.set(phone);
    }

    public SimpleLongProperty phoneProperty() {
        return phone;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }
}
