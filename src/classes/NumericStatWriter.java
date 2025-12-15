package classes;

/**
 * Абстрактный класс для записи числовых значений в текстовый файл со сбором подробной статистики
 * @param <T> числовой тип
 */
public abstract class NumericStatWriter<T extends Number> extends StatWriter { // наследуем от класса StatWriter
    protected T
            minValue, // минимальное значение чисел
            maxValue, // максимальное значение чисел
            sumValues; // сумма значений чисел

    /**
     * Конструктор создания нового объекта
     * @param filename - имя файла
     * @param append - режим дозаписи
     */
    public NumericStatWriter(String filename, boolean append) {
        super(filename, append); // вызываем конструктор базового класса
    }

    /**
     * Метод вывода подробной статистики для числовых значений
     */
    @Override // переопределяем метод базового класса
    public void printStatistics() {
        System.out.println("Полная статистика числовых значений");
        System.out.println("\tФайл: " + fileName);
        System.out.println("\tКоличество значений: " + countValues);
        System.out.println("\tМинимальное значение: " + minValue);
        System.out.println("\tМаксимальное значение: " + maxValue);
        System.out.println("\tСумма значений: " + sumValues);
    }
}
