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
    public final List<String>
            inputFiles = new ArrayList<>(); // массив входных файлов

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
                    i++;
                    prefixOutputFile = args[i];
                    break;
                case "-o":
                    outputFolder = args[i++]; // следующий параметр - это имя папки
                    if (!outputFolder.endsWith("\\")) { // если имя папки не заканчивается слэшем
                        outputFolder = outputFolder + "\\"; // добавляем слэш
                    }
                    break;
                default: // считаем, что остальные параметры - это имена файлов
                    inputFiles.add(args[i]);
            }
        }
    }
}
