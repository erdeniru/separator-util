import classes.MainParameter;

public class Main {
    /**
     * Программа фильтрации содержимого файлов
     *
     * @param args параметры командной строки
     *             -p префикс имен выходных файлов
     *             -o "папка" путь к папке выходных файлов
     *             -a режим добавления в существующий файл
     *             -s вывод краткой статистики
     *             -f вывод полной статистики
     *             "файл 1" "файл 2" и т.д. имена входных файлов
     */
    public static void main(String[] args) {
        /* Проверяем - имеет ли командная строка параметры */
        if (args.length == 0) {
            System.err.println("Ошибка! Параметры командной строки не указаны");
            System.exit(1); // завершаем программу с ошибкой
        }

        MainParameter param = new MainParameter(args);
        /* Обработка параметров командной строки */


        // DEBUG
        System.out.println("prefixOutputFile: " + param.prefixOutputFile);
        System.out.println("outputFolder: " + param.prefixOutputFile);
        System.out.println("isAppendFile: " + param.isAppendFile);
        System.out.println("isShortStatistics: " + param.isShortStatistics);
        System.out.println("isFullStatistics: " + param.isFullStatistics);
        System.out.println("inputFiles:");
        for (String file : param.inputFiles) {
            System.out.println("\t" + file);
        }

    }
}