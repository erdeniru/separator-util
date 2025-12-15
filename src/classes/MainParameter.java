package classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Параметры программы
 */
public class MainParameter {
    public String
            prefixOutputFile = "",          // префикс выходных файлов
            outputFolder = "";              // путь к выходным файлам
    public boolean
            isAppendFile = false,           // режим добавления в существующий файл
            isShortStatistics = false,      // вывод краткой статистики
            isFullStatistics = false;       // вывод полной статистики
    public List<String>
            inputFiles = new ArrayList<>(); // массив входных файлов

    /* Имена выходных файлов */
    static final String
            integerFile = "integers.txt", // для целых чисел
            floatFile = "floats.txt",     // для вещественных чисел
            stringFile = "strings.txt";   // для строковых значений

    /**
     * Конструктор создания нового объекта
     * @param args параметры командной строки
     */
    public MainParameter(String[] args) {
        /* Инициализация полей класса */
        parseArgs(args);
    }

    /**
     * Метод разбора командной строки
     * @param args параметры командной строки
     */
    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a":
                    isAppendFile = true;
                    break;
                case "-s":
                    isShortStatistics = true;
                    break;
                case "-f":
                    isFullStatistics = true;
                    break;
                case "-p":
                    prefixOutputFile = args[++i]; // следующий параметр - это префикс
                    break;
                case "-o":
                    outputFolder = args[++i]; // следующий параметр - это имя папки
                    if (!outputFolder.endsWith("\\")) { // если имя папки не заканчивается слэшем
                        outputFolder = outputFolder + "\\"; // добавляем слэш
                    }
                    break;
                default: // считаем, что остальные параметры - это имена файлов
                    inputFiles.add(args[i]);
            }
        }
    }

    /**
     * Геттер имени выходного файла с целыми числами
     * @return возвращает путь к файлу
     */
    public String getOutputIntegerFile() {
        return outputFolder + prefixOutputFile + integerFile;
    }

    /**
     * Геттер имени выходного файла с вещественными числами
     * @return возвращает путь к файлу
     */
    public String getOutputFloatFile() {
        return outputFolder + prefixOutputFile + floatFile;
    }

    /**
     * Геттер имени выходного файла со строковыми значениями
     * @return возвращает путь к файлу
     */
    public String getOutputStringFile() {
        return outputFolder + prefixOutputFile + stringFile;
    }
}
