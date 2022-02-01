package phoneBook;

import java.io.IOException;

public class PhoneBookApp {
    public static void main(String[] args){
        PhoneBookController pb = new PhoneBookController();

        pb.loop();
    }
}
