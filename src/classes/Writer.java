package classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс для записи значения в текстовый файл.
 * Запись выполняется в поток с буферизацией
 * для снижения количества обращений к физическому носителю.
 */
public class Writer {
    protected final String fileName; // имя файла
    private final boolean isAppend;  // флаг режима дозаписи
    private boolean isOpen;          // флаг открытого файла
    private BufferedWriter writer;   // поток вывода в файл
    private boolean isError;
    private String errorMessage;

    /**
     * Конструктор создания нового объекта
     *
     * @param filename - имя файла
     * @param append   - режим дозаписи
     */
    public Writer(String filename, boolean append) {
        /* Инициализация полей класса */
        fileName = filename;
        isAppend = append;
    }

    /**
     * Метод получения значения поля {@link Writer#fileName}
     *
     * @return возвращает имя файла
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Метод открытия файла для буферизированного потока записи
     */
    public void openFile() throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName, isAppend)); // создаем экзепляр буферезированного потока
    }

    /**
     * Метод записи значения в файл
     *
     * @param str значение
     */
    public void writeFile(String str) throws IOException {
        /* Записивываем значение в буферизированный поток */
        writer.write(str);
        writer.newLine();
    }

    /**
     * Метод закрытия файла (закрытие потока с предварительным сохранением данных в файл)
     */
    public void closeFile() throws IOException {
        writer.close(); // закрываем поток
    }

    public static void main(String[] args) throws IOException {
        Writer writer = new Writer("result\\test.txt", false);
        writer.openFile();
        writer.writeFile("test");
        writer.closeFile();
    }
}
