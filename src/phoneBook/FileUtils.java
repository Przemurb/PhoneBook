package phoneBook;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FileUtils {
    private static final String FILE_NAME = "Files/phonebook.csv";

    public static void save(PhoneBook phoneBook) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Contact contact : phoneBook) {
            writer.write(contact.toCSV());
            writer.newLine();
        }
        writer.close();
    }

    public static PhoneBook readFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
//  #1
//        PhoneBook phoneBook = new PhoneBook();
//        while (reader.ready()) {
//            String[] elements = reader.readLine().split(";");
//            phoneBook.addContact(elements[0], elements[1]);
//        }
//        return phoneBook;

//  #2
        Map<String, Contact> map = reader.lines()
                .map(s -> s.split(";"))
                .map(element -> new Contact(element[0], element[1]))
                .collect(Collectors.toMap(Contact::getName, Function.identity()));
        return map!= null ? new PhoneBook(map) : new PhoneBook();
    }
}
