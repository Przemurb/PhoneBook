package phoneBook;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PhoneBookController {
    private PhoneBook phoneBook = new PhoneBook();
    private final Scanner scan = new Scanner(System.in);

    public PhoneBookController() {
        try {
            phoneBook = FileUtils.readFromFile();
        } catch (IOException e) {
            System.err.println("Brak lub błąd odczytu pliku danych!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Błąd odczytu danych!");
        }
    }

    public void loop() {
        Options option;
        do {
            System.out.println(Options.printMenu());
            option  = readOptionFromUser();
            runOption(option);
        } while (option != Options.EXIT);
    }

    private Options readOptionFromUser() {
        int option = 0;
        boolean correct = false;
        while (!correct) {
            try {
                option = scan.nextInt();
                correct = true;
            } catch (InputMismatchException e) {
                System.err.println("Ne ma takiej opcji. Spróbuj jeszcze raz");
                scan.nextLine();
            }
        }
        scan.nextLine();
        return Options.setOptionFromNumber(option);
    }

    private void runOption(Options option) {
        switch (option) {
            case ADD_NEW -> addContact();
            case REMOVE -> deleteContact();
            case SEARCH_BY_NAME -> searchContactByName();
            case SEARCH_BY_NUMBER -> searchContactByNumber();
            case ALL -> allContacts();
            case EXIT -> exit();
//            default -> System.out.println("Nie ma takiej opcji");
        }
    }

    private void allContacts() {
        System.out.println("Kontakty w bazie danych:");
        if(!phoneBook.iterator().hasNext())
            System.out.println("Baza danych jest pusta.");
        else {
            phoneBook.forEach(System.out::println);
        }
        System.out.println();
    }

    private void addContact() {
        System.out.println("Podaj nazwę nazwę kontaktu");
        String name = scan.nextLine();
        System.out.println("Podaj numer telefonu");
        String phoneNumber = scan.nextLine();
        try {
            if (phoneBook.addContact(name, phoneNumber))
                System.out.println("Kontakt został dodany\n");
            else
                System.out.println("Kontakt o podanej nazwie już istnieje!\n");
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Nazwa ani numer nie mogą być puste!");
        }
    }

    private void deleteContact() {
        System.out.println("Który kontakt chcesz usunąć?");
        String name = scan.nextLine();
        if(phoneBook.removeContact(name))
            System.out.println("Kontakt został usunięty\n");
        else
            System.out.println("nie znaleziono kontaktu do usunięcia\n");
    }

    private void searchContactByName() {
        System.out.println("Wyszukiwanie kontaktów wg nazwy\n" +
                "Podaj nazwę do wyszukania:");
        String name = scan.nextLine();
        List<Contact> result = phoneBook.searchForName(name);
        result.forEach(System.out::println);
        System.out.println();
        if(result.isEmpty())
            System.out.println("Brak szukanych kontaktów.\n");
    }

    private void searchContactByNumber() {
        System.out.println("Wyszukiwanie kontaktów wg numerów\n" +
                "Podaj numer do wyszukania:");
        String name = scan.nextLine();
        List<Contact> result = phoneBook.searchForNumber(name);
        result.forEach(System.out::println);
        System.out.println();
        if(result.isEmpty())
            System.out.println("Brak kontaktów o podanych numerach.\n");
    }

    private void exit() {
        try {
            FileUtils.save(phoneBook);
        } catch (IOException e) {
            System.err.println("Błąd zapisu pliku!" + e.getMessage());;
        }
        scan.close();
        System.out.println("Bye!");
    }
}