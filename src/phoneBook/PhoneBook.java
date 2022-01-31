package phoneBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PhoneBook {
    private Map<String, Contact> phoneBook = new HashMap<>();

    public PhoneBook() {
    }

    public PhoneBook(Map<String, Contact> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public boolean addContact(String name, String phoneNumber) {
        if (name == null || phoneNumber == null) {
            throw new NullPointerException("name and phone number cannot by null");
        }
        if (name.isEmpty() || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("name and phone number cannot by empty");
        }
        if (!phoneBook.containsKey(name)) {
            phoneBook.put(name, new Contact(name, phoneNumber));
            return true;
        } else {
            return false;
        }
    }

    public boolean removeContact(String name) {
        return phoneBook.remove(name) != null;
    }

    public List<Contact> searchForName(String name) {
        return phoneBook.entrySet().stream()
                .filter(k -> k.getKey().contains(name))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<Contact> searchForNumber(String number) {
        return phoneBook.values().stream()
                .filter(contact -> contact.getPhoneNumber().contains(number))
                .collect(Collectors.toList());
    }
}
