package classes;

/**
 * Класс для записи вещественных значений в текстовый файл со сбором подробной статистики
 */
public class FloatStatWriter extends NumericStatWriter<Double> { // наследуем от класса NumericStatWriter
    /**
     * Конструктор создания нового объекта
     * @param filename - имя файла
     * @param append - режим дозаписи
     */
    public FloatStatWriter(String filename, boolean append) {
        super(filename, append); // вызываем конструктор базового класса

        /* Инициализация полей класса */
        minValue = Double.MIN_VALUE;
        maxValue = Double.MAX_VALUE;
        sumValues = 0d;
    }

    /**
     * Метод сбора подробной статистики для вещественных значений
     * @param str значение
     */
    @Override // переопределяем метод базового класса
    public void calculateStatistics(String str) {
        super.calculateStatistics(str); // вызываем метод базового класса

        double value = Double.parseDouble(str); // преобразуем строковое значение в вещественное
        /* Определяем минимальное значение */
        if (minValue == Double.MIN_VALUE || minValue > value) {
            minValue = value;
        }
        /* Определяем максимальное значение */
        if (maxValue == Double.MAX_VALUE || maxValue < value) {
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
