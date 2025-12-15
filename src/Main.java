import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.*;

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
        MainParameter param = new MainParameter(args); // параметры программы

        /* Проверяем - имеет ли командная строка параметры */
        if (args.length == 0) {
            System.out.println("Ошибка! Параметры командной строки не указаны");
            System.exit(1); // завершаем программу с ошибкой
        }

        /* Проверяем - существует ли папка результатов */
        if (!(param.outputFolder.trim().equals(""))
                && !(new File(param.outputFolder).exists())) {
            System.out.println("Ошибка! Папка \"" + param.outputFolder + "\" не найдена");
            System.exit(2); // завершаем программу с ошибкой
        }

        /* Фильтрация содержимого входных файлов */
        System.out.println("Фильтрация содержимого файлов");

        /* Создание потоков для записи в выходной файл */
        Writer
                integerWriter,
                floatWriter,
                stringWriter;

        if (param.isFullStatistics) {
            integerWriter = new IntegerStatWriter(param.getOutputIntegerFile(), param.isAppendFile);
            floatWriter = new FloatStatWriter(param.getOutputFloatFile(), param.isAppendFile);
            stringWriter = new StringStatWriter(param.getOutputStringFile(), param.isAppendFile);
        } else if (param.isShortStatistics) {
            integerWriter = new StatWriter(param.getOutputIntegerFile(), param.isAppendFile);
            floatWriter = new StatWriter(param.getOutputFloatFile(), param.isAppendFile);
            stringWriter = new StatWriter(param.getOutputStringFile(), param.isAppendFile);
        } else {
            integerWriter = new Writer(param.getOutputIntegerFile(), param.isAppendFile);
            floatWriter = new Writer(param.getOutputFloatFile(), param.isAppendFile);
            stringWriter = new Writer(param.getOutputStringFile(), param.isAppendFile);
        }

        /* Флаги открытия выходного файла */
        boolean
                isOpenInteger = false,
                isOpenFloat = false,
                isOpenString = false;

        /* Флаги ошибки записи в выходной файл */
        boolean
                isErrorInteger = false,
                isErrorFloat = false,
                isErrorString = false;

        /* Обрабатываем список входных файлов */
        System.out.println("Обработка входных файлов:");
        for (String inputFile : param.inputFiles) {
            // Проверяем существует ли входной файл
            if (!(new File(inputFile)).exists()) { /* если файл не сущестует */
                System.out.println("\t" + inputFile + " - файл не найден"); // выводим сообщение
                continue; // и пропускаем обработку файла
            }

            // Чтение входного файла
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!isErrorInteger && Utils.isInteger(line)) { // если нет ошибки и значение целое число
                        /* Запись в файл с целочисленными значениями */
                        try {
                            if (!isOpenInteger) { // если файл не открыт
                                integerWriter.openFile(); // открываем файл для записи
                                isOpenInteger = true;
                            }
                            integerWriter.writeFile(line); // пишем в файл
                        } catch (IOException e) {
                            isErrorInteger = true; // ошибка записи в файл
                        }
                    } else if (!isErrorFloat && Utils.isFloat(line)) { // если нет ошибки и значение вещественное число
                        /* Запись в файл с вещественныеми значениями */
                        try {
                            if (!isOpenFloat) { // если файл не открыт
                                floatWriter.openFile(); // открываем файл для записи
                                isOpenFloat = true;
                            }
                            floatWriter.writeFile(line); // пишем в файл
                        } catch (IOException e) {
                            isErrorFloat = true; // ошибка записи в файл
                        }
                    } else if (!isErrorString) { // если нет ошибки и значение строковое значение
                        /* Запись в файл со строковыми значениями */
                        try {
                            if (!isOpenString) { // если файл не открыт
                                stringWriter.openFile(); // открываем файл для записи
                                isOpenString = true;
                            }
                            stringWriter.writeFile(line); // пишем в файл
                        } catch (IOException e) {
                            isErrorString = true; // ошибка записи в файл
                        }
                    }
                }
                System.out.println("\t" + inputFile + " - успешно"); // выводим сообщение об успешном выполнении
            } catch (IOException e) { // если произошла ошибка обработки файла
                System.out.println("\t" + inputFile + " - ошибка обработки файла"); // выводим сообщение об ошибке
            }
        }

        /* Закрываем открытые файлы */
        try {
            if (isOpenInteger) { // если файл открыт
                integerWriter.closeFile(); // закрываем файл
            }
        } catch (IOException e) {
            isErrorInteger = false;
        }
        try {
            if (isOpenFloat) { // если файл открыт
                floatWriter.closeFile(); // закрываем файл
            }
        } catch (IOException e) {
            isErrorFloat = false;
        }
        try {
            if (isOpenString) { // если файл открыт
                stringWriter.closeFile(); // закрываем файл
            }
        } catch (IOException e) {
            isErrorString = false;
        }

        /* Выводим результат записи выходных файлов */

        List<Writer> writerList = new ArrayList<>(); // создаем список потоков для удобства

        System.out.println("Запись выходных файлов:");
        if (!isErrorInteger) {
            System.out.println("\t" + integerWriter.getFileName() + " - успешно");
            writerList.add(integerWriter);
        } else {
            System.out.println("\t" + integerWriter.getFileName() + " - ошибка записи");
        }
        if (!isErrorFloat) {
            System.out.println("\t" + floatWriter.getFileName() + " - успешно");
            writerList.add(floatWriter);
        } else {
            System.out.println("\t" + floatWriter.getFileName() + " - ошибка записи");
        }
        if (!isErrorString) {
            System.out.println("\t" + stringWriter.getFileName() + " - успешно");
            writerList.add(stringWriter);
        } else {
            System.out.println("\t" + stringWriter.getFileName() + " - ошибка записи");
        }

        /* Выводим статистику */
        for (Writer writer : writerList) {
            if (writer instanceof StatWriter) { // если объект является экзепляром класса со статистикой
                ((StatWriter) writer).printStatistics(); // выводим статистику
            }
        }
    }
}