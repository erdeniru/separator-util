package classes;

import java.io.IOException;

/**
 * Класс для записи значения в текстовый файл со сбором краткой статистики
 */
public class StatWriter extends MainWriter { // наследуем от класса MainWriter
    protected long countValues; // количество записей значений

    /**
     * Конструктор создания нового объекта
     *
     * @param filename - имя файла
     * @param append   - режим дозаписи
     */
    public StatWriter(String filename, boolean append) {
        super(filename, append); // вызываем конструктор базового класса

        /* Инициализация полей класса */
        countValues = 0;
    }

    /**
     * Метод записи значения в файл со сбором статистики
     *
     * @param str значение для записи в файл
     */
    @Override // переопределяем метод базового класса
    public void writeFile(String str) {
        super.writeFile(str); // вызываем метод базавого класса

        calculateStatistics(str); // вызываем метод сбора статистики
    }

    /**
     * Метод сбора краткой статистики
     *
     * @param str значение для сбора статистики
     */
    public void calculateStatistics(String str) {
        countValues++; // увеличиваем счетчик количества значений
    }

    /**
     * Метод вывода краткой статистики
     */
    public void printStatistics() {
        System.out.println("Краткая статистика");
        System.out.println("\tФайл: " + fileName);
        System.out.println("\tКоличество значений: " + countValues);
    }
}
