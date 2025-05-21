package seminar4.task3;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        Map<Long, String> phoneBook = new HashMap<>();

        // Добавим несколько записей
        phoneBook.put(79001234567L, "Ольга");
        phoneBook.put(79001112233L, "Борис");
        phoneBook.put(79009998877L, "Георгий");
        phoneBook.put(79000000001L, "Алексей");

        findByMinPhone(phoneBook);
        findPhoneByMaxName(phoneBook);
    }

    public static void findByMinPhone(Map<Long, String> book) {
        Long minPhone = Collections.min(book.keySet());
        String minPhoneName = book.get(minPhone);
        System.out.println("Человек с самым маленьким номером телефона: " + minPhoneName + " (" + minPhone + ")");
    }

    public static void findPhoneByMaxName(Map<Long, String> book) {
        String maxName = Collections.max(book.values());
        Long phoneOfMaxName = null;

        for (Map.Entry<Long, String> entry : book.entrySet()) {
            if (entry.getValue().equals(maxName)) {
                phoneOfMaxName = entry.getKey();
                break;
            }
        }

        System.out.println("Телефон человека с именем, самым большим по алфавиту: " + maxName + " (" + phoneOfMaxName + ")");
    }
}

