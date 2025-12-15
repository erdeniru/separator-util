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
        isOpen = false;
    }

    /**
     * Метод получения значения поля {@link Writer#fileName}
     * @return возвращает имя файла
     */
    public String getFileName() { return fileName; }

    /**
     * Метод получения значения поля {@link Writer#isOpen}
     *
     * @return возвращает true - если файл открыт, false - если закрыт
     */
    public boolean getIsOpen() {
        return isOpen;
    }

    /**
     * Метод открытия файла для буферизированного потока записи
     */
    public void openFile() throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName, isAppend)); // создаем экзепляр буферезированного потока
        isOpen = true; // устанавливаем флаг октрытого файла
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
        isOpen = false; // устанавливаем флаг закрытия файла
    }

    public static void main(String[] args) throws IOException {
        Writer writer = new Writer("result\\test.txt", false);
        writer.openFile();
        writer.writeFile("test");
        writer.writeFile("123");
        if (writer.isOpen) {
            writer.closeFile();
        }
    }
}
