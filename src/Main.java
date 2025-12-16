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
        MainParameter param = new MainParameter(args); // инициализируем параметры программы

        /* Проверяем - имеет ли командная строка параметры */
        if (args.length == 0) {
            System.out.println("Ошибка! Параметры командной строки не указаны");
            System.exit(1); // завершаем программу с ошибкой
        }

        /* Проверяем - существует ли папка выходных файлов */
        if (!(param.outputFolder.trim().equals(""))
                && !(new File(param.outputFolder).exists())) {
            System.out.println("Ошибка! Папка \"" + param.outputFolder + "\" не найдена");
            System.exit(2); // завершаем программу с ошибкой
        }

        /* Фильтрация содержимого входных файлов */
        System.out.println("Фильтрация содержимого файлов");

        /* Создание потоков для записи в выходной файл */
        MainWriter
                integerWriter,
                floatWriter,
                stringWriter;

        if (param.isFullStatistics) { // если выбрана опция полной статистики
            integerWriter = new IntegerStatWriter(param.getOutputIntegerFile(), param.isAppendFile);
            floatWriter = new FloatStatWriter(param.getOutputFloatFile(), param.isAppendFile);
            stringWriter = new StringStatWriter(param.getOutputStringFile(), param.isAppendFile);
        } else if (param.isShortStatistics) { // если выбрана опция краткой статистики
            integerWriter = new StatWriter(param.getOutputIntegerFile(), param.isAppendFile);
            floatWriter = new StatWriter(param.getOutputFloatFile(), param.isAppendFile);
            stringWriter = new StatWriter(param.getOutputStringFile(), param.isAppendFile);
        } else { // без статистики
            integerWriter = new MainWriter(param.getOutputIntegerFile(), param.isAppendFile);
            floatWriter = new MainWriter(param.getOutputFloatFile(), param.isAppendFile);
            stringWriter = new MainWriter(param.getOutputStringFile(), param.isAppendFile);
        }

        /* Обрабатываем список входных файлов */
        for (String inputFile : param.inputFiles) {

            // Проверяем существует ли входной файл
            if (!(new File(inputFile)).exists()) { /* если файл не сущестует */
                System.out.println("\t" + inputFile + " - файл не найден"); // выводим сообщение
                continue; // и пропускаем обработку файла
            }

            // Чтение входного файла
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) { // читаем строку пока значение не пустое значение
                    if (Utils.isInteger(line) && !integerWriter.getIsError()) { // если целое число и нет ошибки записи
                        integerWriter.writeFile(line); // поток записи целочисленных значений
                    } else if (Utils.isFloat(line) && !floatWriter.getIsError()) { // если вещественное и нет ошибки
                        floatWriter.writeFile(line); // поток записи вещественных значений
                    } else if (!stringWriter.getIsError()) { // если строковое и нет ошибки
                        stringWriter.writeFile(line); // поток записи строковых значений
                    }
                }
                System.out.println("\t" + inputFile + " - успешно"); // выводим сообщение об успешном выполнении
            } catch (IOException e) { // если произошла ошибка обработки файла
                System.out.println("\t" + inputFile + " - ошибка обработки файла"); // выводим сообщение об ошибке
            }
        }

        /* Список потоков для удобства */
        List<MainWriter> writerList = new ArrayList<>();
        writerList.add(integerWriter);
        writerList.add(floatWriter);
        writerList.add(stringWriter);

        /* Выводим статистику записи файлов и закрываем потоки */
        for (MainWriter writer : writerList) {
            if (writer.getIsOpen()) { // если файл открыт
                if (writer instanceof StatWriter) { // если объект является экзепляром класса со статистикой
                    ((StatWriter) writer).printStatistics(); // выводим статистику
                }
                writer.closeFile(); // закрываем файл
            }
        }

        /* Выводим результат записи выходных файлов */
        System.out.println("Запись выходных файлов:");
        for (MainWriter writer : writerList) {
            if (!writer.getIsError()) { // если ошибок нет
                // выводим сообщение об успешном обработке
                System.out.println("\t" + stringWriter.getFileName() + " - успешно");
            } else { // иначе
                // выводим сообщение об ошибке
                System.out.println("\t" + stringWriter.getFileName() + " - ошибка записи");
            }
        }
    }
}