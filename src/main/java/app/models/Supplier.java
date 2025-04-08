package app.models;

import javafx.beans.property.*;


public class Supplier {
    private final IntegerProperty supplierId;
    private final StringProperty name;
    private final StringProperty contactPerson;
    private final StringProperty phone;
    private final StringProperty email;
    private final StringProperty address;
    private final DoubleProperty rating;
    private final BooleanProperty isActive;

    public Supplier(int supplierId, String name, String contactPerson,
                    String phone, String email, String physicalAddress,
                    double rating, boolean isActive) {
        this.supplierId = new SimpleIntegerProperty(supplierId);
        this.name = new SimpleStringProperty(name);
        this.contactPerson = new SimpleStringProperty(contactPerson);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(physicalAddress);
        this.rating = new SimpleDoubleProperty(rating);
        this.isActive = new SimpleBooleanProperty(isActive);
    }

    public IntegerProperty supplierIdProperty() {
        return supplierId;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty contactPersonProperty() {
        return contactPerson;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public DoubleProperty ratingProperty() {
        return rating;
    }

    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    public int getSupplierId() {
        return supplierId.get();
    }

    public String getName() {
        return name.get();
    }

    public String getContactPerson() {
        return contactPerson.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getAddress() {
        return address.get();
    }

    public double getRating() {
        return rating.get();
    }

    public boolean isActive() {
        return isActive.get();
    }

    public void setSupplierId(int supplierId) {
        this.supplierId.set(supplierId);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson.set(contactPerson);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setRating(double rating) {
        this.rating.set(rating);
    }

    public void setIsActive(boolean isActive) {
        this.isActive.set(isActive);
    }

    public boolean validate() {
        if (name.get().matches(".*\\d.*")) {
            return false;
        }
        if (contactPerson.get().matches(".*\\d.*")) {
            return false;
        }
        return phone.get().matches("\\d+");
    }

    public String getSummary() {
        return String.format("%s (ID: %d) - %s",
                name.get(), supplierId.get(), contactPerson.get());
    }

    @Override
    public String toString() {
        return String.format(
                "Supplier [ID=%d, Name=%s, Contact=%s, Phone=%s, Email=%s, Rating=%.1f]",
                supplierId.get(), name.get(), contactPerson.get(),
                phone.get(), email.get(), rating.get());
    }
}
