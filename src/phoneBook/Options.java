package phoneBook;

import java.util.NoSuchElementException;

public enum Options {
    ADD_NEW(1, "Dodaj nowy kontakt"),
    SEARCH_BY_NAME(2, "Wyszukaj po nazwie"),
    SEARCH_BY_NUMBER(3, "Wyszukaj po numerze"),
    REMOVE(4, "Usuń kontakt"),
    ALL(5, "Wyświetl wszystkie kontakty"),
    EXIT(0, "Wyjście z programu");

    private final int id;
    private final String description;

    Options(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Options setOptionFromNumber(int number) {
        Options[] options = Options.values();
        Options option = null;
        if (number > options.length - 1 || number < 0) {
//        if (number > values().length - 1 || number < 0) {
            throw new NoSuchElementException();
        }
        for (Options opt : options) {
            if (opt.id == number) {
                option = opt;
            }
        }
        return option;
    }

    public static String printMenu() {
        StringBuilder stringBuilder = new StringBuilder("OPCJE PROGRAMU:\n");
        Options[] options = Options.values();
        for (Options option : options) {
            stringBuilder.append(option).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
