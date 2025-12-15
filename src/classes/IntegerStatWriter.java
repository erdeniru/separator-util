package classes;

/**
 * Класс для записи целочисленных значений в текстовый файл со сбором подробной статистики
 */
public class IntegerStatWriter extends NumericStatWriter<Long> { // наследуем от класса NumericStatWriter
    /**
     * Конструктор создания нового объекта
     * @param filename - имя файла
     * @param append - режим дозаписи
     */
    public IntegerStatWriter(String filename, boolean append) {
        super(filename, append); // вызываем конструктор базового класса

        /* Инициализация полей класса */
        minValue = Long.MIN_VALUE;
        maxValue = Long.MAX_VALUE;
        sumValues = 0L;
    }

    /**
     * Метод сбора подробной статистики для целочисленных значений
     * @param str значение
     */
    @Override // переопределяем метод базового класса
    public void calculateStatistics(String str) {
        super.calculateStatistics(str); // вызываем метод базового класса

        long value = Long.parseLong(str); // преобразуем строковое значение в целочисленное
        /* Определяем минимальное значение */
        if (minValue == Long.MIN_VALUE || minValue > value) {
            minValue = value;
        }
        /* Определяем максимальное значение */
        if (maxValue == Long.MAX_VALUE || maxValue < value) {
            maxValue = value;
        }
        /* Суммируем значения */
        sumValues += value;
    }

    /**
     * Метод вывода подробной статистики для целочисленных значений
     */
    @Override // переопределяем метод базового класса
    public void printStatistics() {
        super.printStatistics(); // вызываем метод базового класса

        System.out.println("\tСреднее значение: " + sumValues / countValues);
    }
}
