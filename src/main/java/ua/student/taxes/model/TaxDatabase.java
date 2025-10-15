

package ua.student.taxes.model;

import java.util.ArrayList;
import java.util.List;

public class TaxDatabase {

    private List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        if (persons != null) {
            this.persons = persons;
        }
    }

    public void addPerson(Person person) {
        if (person != null) {
            this.persons.add(person);
        }
    }
}
