package homework3.task3;

public class Main {
    public static void main(String[] args) {
        Pair<String, Integer> personAge = new Pair<>("Иван", 30);
        Pair<Double, Boolean> data = new Pair<>(12.5, true);

        System.out.println(personAge);
        System.out.println(data);

        System.out.println("Имя: " + personAge.getFirst());
        System.out.println("Возраст: " + personAge.getSecond());
    }
}
