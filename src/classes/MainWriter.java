package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс для записи значения в текстовый файл.
 * Запись выполняется в поток с буферизацией
 * для снижения количества обращений к физическому носителю.
 */
public class MainWriter {
    protected final String fileName; // имя файла
    private final boolean isAppend;  // флаг режима дозаписи
    private boolean isOpen;          // флаг открытого файла
    private BufferedWriter writer;   // поток вывода в файл

    private boolean isError;         // ошибка операций с файлом

    /**
     * Конструктор создания нового объекта
     *
     * @param filename - имя файла
     * @param append   - режим дозаписи
     */
    public MainWriter(String filename, boolean append) {
        /* Инициализация полей класса */
        fileName = filename;
        isAppend = append;

        isOpen = false;
        isError = false;
    }

    public String getFileName() {
        return fileName;
    }
    public boolean getIsOpen() { return isOpen; }
    public boolean getIsError() { return isError; }

    /**
     * Метод открытия файла для буферизированного потока записи
     */
    public void openFile() {
        try {
            writer = new BufferedWriter(new FileWriter(fileName, isAppend)); // создаем экзепляр буферезированного потока
            isOpen = true;
        } catch (IOException e) {
            isError = true;
        }
    }

    /**
     * Метод записи значения в файл.
     * Если файл не открыт для записи, то открываем файл
     * @param str значение
     */
    public void writeFile(String str) {
        try {
            /* Записивываем значение в буферизированный поток */
            writer.write(str);
            writer.newLine();
        } catch (IOException e) {
            isError = true;
        }
    }

    /**
     * Метод закрытия файла (закрытие потока с предворительным сохранением данных в файл)
     */
    public void closeFile() {
        try {
            writer.close(); // закрываем поток
            isOpen = false;
        } catch (IOException e) {
            isError = true;
        }
    }

    public static void main(String[] args) {
        MainWriter writer = new MainWriter("result\\test.txt", false);
        writer.writeFile("test");
        writer.closeFile();
    }
}
