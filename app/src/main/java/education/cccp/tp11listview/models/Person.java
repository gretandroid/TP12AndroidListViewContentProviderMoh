package education.cccp.tp11listview.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int counter = 0;

    private static int idGenerator() {
        return ++counter;
    }

    private Integer id;
    private String firstName;
    private String lastName;

    public Person(String firstName,
                  String lastName) {
        this.id = idGenerator();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NonNull
    @Override
    public String toString() {
        //noinspection StringBufferReplaceableByString
        return new StringBuilder("Person{")
                .append("id=").append(id)
                .append(", firstName='").append(firstName).append('\'')
                .append(", lastName='").append(lastName).append('\'')
                .append('}')
                .toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
