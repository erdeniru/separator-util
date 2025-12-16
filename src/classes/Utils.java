package classes;

public class Utils {
    /**
     * Фукнция проверки является ли строка целым числом
     * @param str строковое значение
     * @return возвращает true если значение целое число, false - не является целым числом
     */
    public static boolean isInteger(String str) {
//        return str.matches("[-+]?\\d+");
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Фукнция проверки является ли строка вещественным числом
     * @param str строковое значение
     * @return возвращает true если значение вещественное число, false - не является вещественным числом
     */    public static boolean isFloat(String str) {
//        return str.matches("[-+]?\\d+\\.?\\d+(E-)?\\d+");
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        // DEBUG
        System.out.println("-12233454\t" + isInteger("-12233454"));
        System.out.println("1234567890123456789\t" + isInteger("1234567890123456789"));
        System.out.println("12.233454\t" + isFloat("12.23345.4"));
        System.out.println("12.23345.4\t" + isFloat("12.23345.4"));
        System.out.println("1.528535047E-25\t" + isFloat("1.528535047E-25"));
    }
}
