package classes;

/**
 * Класс для записи строковых значений в текстовый файл со сбором подробной статистики
 */
public class StringStatWriter extends StatWriter { // наследуем от класса StatWriter
    protected int
            minLengthValue, // минимальная длина строки
            maxLengthValue; // максимальная длина строки

    /**
     * Конструктор создания нового объекта
     * @param filename - имя файла
     * @param append - режим дозаписи
     */
    public StringStatWriter(String filename, boolean append) {
        super(filename, append); // вызываем конструктор базового класса

        /* Инициализация полей класса */
        minLengthValue = Integer.MIN_VALUE;
        maxLengthValue = Integer.MAX_VALUE;
    }

    /**
     * Метод сбора подробной статистики для вещественных значений
     * @param str значение
     */
    @Override // переопределяем метод базового класса
    public void calculateStatistics(String str) {
        super.calculateStatistics(str); // вызываем метод базового класса

        int length = str.length(); // определяем длину строкового значения
        /* Опеределяем минимальную длину строки */
        if (minLengthValue == Integer.MIN_VALUE || minLengthValue > length) {
            minLengthValue = length;
        }
        /* Опеределяем максимальную длину строки */
        if (minLengthValue == Integer.MAX_VALUE || maxLengthValue < length) {
            maxLengthValue = length;
        }
    }

    /**
     * Метод вывода подробной статистики для строковых значений
     */
    @Override // переопределяем метод базового класса
    public void printStatistics() {
        System.out.println("Полная статистика строковых значений");
        System.out.println("\tФайл: " + fileName);
        System.out.println("\tКоличество значений: " + countValues);
        System.out.println("\tМинимальная длина: " + minLengthValue);
        System.out.println("\tМаксимальная длина: " + maxLengthValue);
    }
}
