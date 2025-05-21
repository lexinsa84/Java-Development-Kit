package homework4;

public class Main {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();

        directory.addEmployee(new Employee(101, 79001234567L, "Анна", 5));
        directory.addEmployee(new Employee(102, 79001112233L, "Борис", 3));
        directory.addEmployee(new Employee(103, 79009998877L, "Анна", 5));

        directory.printAll();

        System.out.println("Поиск по стажу (5 лет):");
        for (Employee e : directory.findByExperience(5)) {
            System.out.println(e);
        }

        System.out.println("\nТелефоны по имени 'Анна':");
        for (Long phone : directory.findPhoneNumbersByName("Анна")) {
            System.out.println(phone);
        }

        System.out.println("\nПоиск по табельному номеру 102:");
        System.out.println(directory.findById(102));
    }
}

