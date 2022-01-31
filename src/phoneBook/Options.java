package phoneBook;

import java.util.NoSuchElementException;

public enum Options {
    ADD_NEW(1, "Dodaj nowy kontakt"),
    SEARCH_BY_NAME(2, "Wyszukaj po nazwie"),
    SEARCH_BY_NUMBER(3, "Wyszukaj po numerze"),
    REMOVE(4, "Usuń kontakt"),
    EXIT(0, "Wyjście z programu");

    private final int id;
    private final String description;

    Options(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Options setFromNumber(int number) {
//        Options[] options = Options.values();
//        if (number > options.length || number < 1) {
        if(number > values().length || number < 1) {
            throw new NoSuchElementException();
        }
//        for (Options option : options) {
//            if (option.id == number) {
//                return option;
//            }
        return values()[number];
    }


    public static String printMenu() {
        StringBuilder stringBuilder = new StringBuilder("OPCJE PROGRAMU:");
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
