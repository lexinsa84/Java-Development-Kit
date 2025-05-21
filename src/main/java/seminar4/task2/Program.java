package seminar4.task2;

import java.util.*;

//Создайте коллекцию мужских и женских имен с помощью интерфейса List -
//добавьте повторяющиеся значения
//● Получите уникальный список Set на основании List
//● Определите наименьший элемент (алфавитный порядок)
//● Определите наибольший элемент (по количеству букв в слове но в обратном
//        порядке)
//● Удалите все элементы содержащие букву ‘A’
public class Program {

    public static void main(String[] args) {
        List<String> listNames = new ArrayList<>(Arrays.asList
                ("Василий", "Ольга", "Федр", "Анастасия", "Анастасия", "Алексей", "Алексей"));
        Set<String> setList = convertOfList(listNames);
        System.out.println(setList);
        System.out.println(minLengthOfName(setList));
        List<String> minMaxNames = minAndMaxLengthNames(setList);
        System.out.printf("Наименьшее по длинне имя: %s, Наибольшее по длине имя: %s\n"
                , minMaxNames.get(0), minMaxNames.get(1));
        removeNameBySymbolA(setList, "А");
        System.out.println("Список имен после удаления имени с буквой А: " + setList);
    }

    public static Set<String> convertOfList(List<String> list) {
        return new HashSet<>(list);
    }

    public static String minLengthOfName(Set<String> names) {
        return names.stream().min(Comparator.naturalOrder()).orElse(null);
    }

    public static List<String> minAndMaxLengthNames(Set<String> names) {
        List<String> resultNames = new ArrayList<>();
        resultNames.add(names.stream().min(Comparator.comparing(String::length)).orElse(null));
        resultNames.add(names.stream().max(Comparator.comparing(String::length)).orElse(null));
        return resultNames;
    }

    public static void removeNameBySymbolA(Set<String> names, String A) {
        names.removeIf(name -> name.contains(A));
    }

}
