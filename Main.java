package progect4;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("""
                    1. Вывести все таблицы из MySQL.
                    2. Создать таблицу в MySQL.
                    3. Ввод двух строк
                    4. Возвращение подстроки по индексам, результат сохранить в MySQL с последующим выводом в
                    консоль.
                    5. Перевод строк в верхний и нижний регистры, результат сохранить в MySQL с последующим выводом
                    в консоль.
                    6. Поиск подстроки результат сохранить в MySQL с последующим
                    выводом в консоль.
                    7. Определение окончания подстроки, результат сохранить в MySQL с последующим
                    выводом в консоль.
                    8. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран.
                    0. Выход из программы.""");

            String s = scanner.nextLine();
            choice = Integer.parseInt(s);
            handleUserChoice(choice, scanner, DHandler.getTableName());
        } while (choice != 0);
    }

    private static void handleUserChoice (int choice, Scanner scanner, String tableName) {
        String a, b;
        switch (choice) {
            case 1:
                DHandler.showAllTables();
                break;
            case 2:
                System.out.println("Введите название для создания таблицы:");
                DHandler.setTableName(scanner.nextLine());
                DHandler.createTable();
                break;
            case 3:
                StringOperations.inputStrings(scanner);
                break;
            case 4:
                StringOperations.StringByIndex(scanner);
                break;
            case 5:
                StringOperations.ToUpperLowerCase();
                break;
            case 6:
                StringOperations.SearchSubstring(scanner);
                break;
            case 7:
                StringOperations.StrEndsWith(scanner);
            case 8:
                ExcelExporter exporter = new ExcelExporter();
                exporter.export();
            case 0:
                System.out.println("Выход из программы...");
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}

