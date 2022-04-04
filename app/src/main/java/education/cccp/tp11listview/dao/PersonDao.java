package education.cccp.tp11listview.dao;

import java.util.ArrayList;
import java.util.List;

import education.cccp.tp11listview.models.Person;

public class PersonDao {
    private static final List<Person> persons = new ArrayList<>();

    public static Person save(Person person) throws Exception {
        if (persons.add(person)) return person;
        else throw new Exception("malformed exception : " + person);
    }

    public static Person save(int index, Person person){
        return persons.set(index, person);
    }

    public static void delete(int index) {
        persons.remove(index);
    }

    public static List<Person> findAll() {
        return persons;
    }
}