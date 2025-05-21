package homework4;

/**
 * @param id          Табельный номер
 * @param phoneNumber Номер телефона
 * @param name        Имя
 * @param experience  Стаж
 */
public record Employee(int id, long phoneNumber, String name, int experience) {

    @Override
    public String toString() {
        return "Сотрудник{" +
                "Табельный номер=" + id +
                ", Телефон=" + phoneNumber +
                ", Имя='" + name + '\'' +
                ", Стаж=" + experience +
                '}';
    }
}

