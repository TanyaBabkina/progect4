package progect4;


import java.util.Scanner;

public class StringOperations {
    public static void inputStrings(Scanner scanner) {
        System.out.println("Введите первую строку: ");
        String str1 = scanner.nextLine();
        System.out.println("Введите вторую строку: ");
        String str2 = scanner.nextLine();
        // Вывод строк в терминал
        System.out.println("Введены строки:\n(1) " + str1 + "\n(2) " + str2);
        // Сохранение в бд
        DHandler.saveString(str1);
        DHandler.saveString(str2);

    }


    public static void StringByIndex(Scanner scanner){
        System.out.print("Введите индекс начала подстроки: ");
        int start = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите индекс конца подстроки: ");
        int end = Integer.parseInt(scanner.nextLine());
        String substring1 = DHandler.getStrFromDB(1).substring(start, end);
        String substring2 = DHandler.getStrFromDB(2).substring(start, end);
        DHandler.insertSubstring(1, start, end, substring1);
        DHandler.insertSubstring(2, start, end, substring2);

        System.out.println("[1] "+ substring1 + "\n[2] " + substring2);
    }

    public static void ToUpperLowerCase(){

        String Upper1 = DHandler.getStrFromDB(1).toUpperCase();
        String Upper2 = DHandler.getStrFromDB(2).toUpperCase();
        String Lower1 = DHandler.getStrFromDB(1).toLowerCase();
        String Lower2 = DHandler.getStrFromDB(2).toLowerCase();

        DHandler.insertCase(Upper1, Lower1, 1);
        DHandler.insertCase(Upper2, Lower2, 2);

        System.out.println("[1] "+ Upper1 + ", " + Lower1);
        System.out.println("[2] "+ Upper2 + ", " + Lower2);
    }

    public static void SearchSubstring(Scanner scanner){
        System.out.print("Введите подстроку, которую хотите найти в строках: ");
        String searchStr = scanner.nextLine();
        String str1 = DHandler.getStrFromDB(1);
        String str2 = DHandler.getStrFromDB(2);
        if (str1.contains(searchStr)){
            System.out.println("["+str1+"]" + " содержит подстроку " + "["+searchStr+"]");
            DHandler.insertContainRes(searchStr+" in string", 1);
        }else{
            System.out.println("["+str1+"]" + " не содержит подстроку " + "["+searchStr+"]");
            DHandler.insertContainRes(searchStr+" NOT in string", 1);
        }

        if (str2.contains(searchStr)){
            System.out.println("["+str2+"]" + " содержит подстроку " + "["+searchStr+"]");
            DHandler.insertContainRes(searchStr+" in string", 2);
        }else{
            System.out.println("["+str2+"]" + " не содержит подстроку " + "["+searchStr+"]");
            DHandler.insertContainRes(searchStr+" NOT in string", 2);
        }
    }

    public static void StrEndsWith(Scanner scanner){
        System.out.print("Введите подстроку, которую хотите найти в строках: ");
        String searchStr = scanner.nextLine();
        String str1 = DHandler.getStrFromDB(1);
        String str2 = DHandler.getStrFromDB(2);
        if (str1.endsWith(searchStr)){
            System.out.println("["+str1+"]" + " заканчивается подстрокой " + "["+searchStr+"]");
            DHandler.insertEndsWithRes(searchStr+" in string", 1);
        }else{
            System.out.println("["+str1+"]" + " не заканчивается подстрокой " + "["+searchStr+"]");
            DHandler.insertEndsWithRes(searchStr+" NOT in string", 1);
        }

        if (str2.endsWith(searchStr)){
            System.out.println("["+str2+"]" + " заканчивается подстрокой " + "["+searchStr+"]");
            DHandler.insertEndsWithRes(searchStr+" in string", 2);
        }else{
            System.out.println("["+str2+"]" + " не заканчивается подстрокой " + "["+searchStr+"]");
            DHandler.insertEndsWithRes(searchStr+" NOT in string", 2);
        }
    }

}
