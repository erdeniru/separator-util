import classes.MainParameter;
import classes.Utils;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        MainParameter param = new MainParameter(args); // параметры программы

        /* Фильтрация содержимого входных файлов */
        System.out.println("Фильтрация содержимого файлов");

        /* Обрабатываем список входных файлов */
        for (String inputFile : param.inputFiles) {
            // Проверяем существует ли входной файл
            if (!(new File(inputFile)).exists()) { /* если файл не сущестует */
                System.err.println("\t" + inputFile + " - файл не найден"); // выводим сообщение
                continue; // и пропускаем обработку файла
            }

            // Чтение входного файла
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // DEBUG
                    if (Utils.isInteger(line)) {
                        System.out.println(line + "\t - целое");
                    } else if (Utils.isFloat(line)) {
                        System.out.println(line + "\t - вещественное");
                    } else {
                        System.out.println(line + "\t - строковое");
                    }
                }
                System.out.println("\t" + inputFile + " - успешно"); // выводим сообщение об успешном выполнении
            } catch (IOException e) { // если произошла ошибка обработки файла
                System.err.println("\t" + inputFile + " - ошибка обработки файла"); // выводим сообщение об ошибке
            }
        }

    }
}