package seminar4.task1;

import java.util.*;

public class Program {

    public static void sortByAlphabet(List<String> arr) {
        Collections.sort(arr);
    }

    public static void sortByCountLetters(List<String> arr) {
        arr.sort(Comparator.comparing(String::length));
    }

    public static void reversArray(List<String>arr){
        Collections.reverse(arr);
    }

    public static void main(String[] args) {
        List<String> namesMan = new ArrayList<>(Arrays.asList("Василий", "Федр", "Алексей"));
        sortByAlphabet(namesMan);
        System.out.println(namesMan);
        sortByCountLetters(namesMan);
        System.out.println(namesMan);
        reversArray(namesMan);
        System.out.println(namesMan);
    }

}


