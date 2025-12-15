import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * Программа фильтрации содержимого файлов
     * @param args параметры командной строки
     *             -p префикс имен выходных файлов
     *             -o "папка" путь к папке выходных файлов
     *             -a режим добавления в существующий файл
     *             -s вывод краткой статистики
     *             -f вывод полной статистики
     *             "файл 1" "файл 2" и т.д. имена входных файлов
     */
    public static void main(String[] args) {
        String prefixOutputFile = "",                // префикс выходных файлов
                outputFolder = "";                   // путь к выходным файлам
        boolean isAppendFile = false,                // режим добавления в существующий файл
                isShortStatistics = false,           // вывод краткой статистики
                isFullStatistics = false;            // вывод полной статистики
        List<String> inputFiles = new ArrayList<>(); // массив входных файлов

        /* Обработка параметров командной строки */
        /* Проверяем - имеет ли командная строка параметры */
        if (args.length == 0) {
            System.err.println("Ошибка! Параметры командной строки не указаны");
            System.exit(1); // завершаем программу с ошибкой
        }

        /* Обрабатываем параметры командной строки */
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
                        outputFolder += "\\"; // добавляем слэш
                    }
                    break;
                default: // считаем, что остальные параметры - это имена файлов
                    inputFiles.add(args[i]);
            }
        }

        // DEBUG
        System.out.println("prefixOutputFile: " + prefixOutputFile);
        System.out.println("outputFolder: " + prefixOutputFile);
        System.out.println("isAppendFile: " + isAppendFile);
        System.out.println("isShortStatistics: " + isShortStatistics);
        System.out.println("isFullStatistics: " + isFullStatistics);
        System.out.println("inputFiles:");
        for (String file: inputFiles) {
            System.out.println("\t" + file);
        }
    }
}