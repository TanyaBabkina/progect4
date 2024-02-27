package progect4;

import java.sql.*;


public class DHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";
    private static String tableName = "new";

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String newName) {
        tableName = newName;
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTable() {
        String sql_ = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "string VARCHAR(255), " +
                "Sub_string VARCHAR(255),"+
                "SearchString VARCHAR(255),"+
                "UpperCase VARCHAR(255)," +
                "DownCase VARCHAR(255)," +
                "EndWith VARCHAR(255))";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql_);
            System.out.println("Таблица " + tableName + " успешно создана.");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы " + tableName);
            System.out.println(e);
        }
    }

    public static void saveString(String string){
        String query = "INSERT INTO " +tableName + " (string) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, string);
            stmt.executeUpdate();
            System.out.println("Результат операции сохранен в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить результат операции в таблицу " + tableName);
        }
    }

    // Метод для отображения всех таблиц
    public static void showAllTables() {
        // Получаем список всех таблиц
        String sqlTables = "SHOW TABLES;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            ResultSet rsTables = stmt.executeQuery(sqlTables);
            while (rsTables.next()) {
                String name = rsTables.getString(1);
                System.out.println(name);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка таблиц");
        }
    }

    public static String getStrFromDB(int strNum){
        String str = "";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            String querySelect = "select string from "+tableName+" ORDER BY id DESC LIMIT 1 OFFSET "+(strNum-1)+";";
            ResultSet rsTables = stmt.executeQuery(querySelect);
            while (rsTables.next()){
                str = rsTables.getString(1);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении данных из таблицы((");
        }
        return str;
    }


    public static void insertSubstring(int strNum, int start, int end, String substr){
        String query = "UPDATE " + tableName + " SET Sub_string =  '(" +
                start+ ","+end+") "+ substr + "' WHERE id = (SELECT id FROM ( SELECT id FROM "+tableName
                + " ORDER BY id DESC LIMIT 1 OFFSET "+(strNum-1) + ") AS subquery);";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
            System.out.println("Результат операции сохранен в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить результат операции в таблицу " + tableName);
            System.out.println(e);
        }
    }

    public static void insertCase(String strUpper, String strLower, int strNum){

        String query = "UPDATE " + tableName + " SET UpperCase = '" +
                strUpper +"' , DownCase ='" + strLower + "' WHERE id = (SELECT id FROM ( SELECT id FROM "+tableName
                + " ORDER BY id DESC LIMIT 1 OFFSET "+(strNum-1) + ") AS subquery);";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
            System.out.println("Результат операции сохранен в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить результат операции в таблицу " + tableName);
        }
    }





    public static void insertContainRes(String res, int strNum){
        String query = "UPDATE " + tableName + " SET  SearchString = '"
                + res + "' WHERE id = (SELECT id FROM ( SELECT id FROM "+tableName
                + " ORDER BY id DESC LIMIT 1 OFFSET "+(strNum-1) + ") AS subquery);";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
            System.out.println("Результат операции сохранен в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить результат операции в таблицу " + tableName);
        }
    }

    public static void insertEndsWithRes(String res, int strNum){
        String query = "UPDATE " + tableName + " SET  EndWith = '"
                + res + "' WHERE id = (SELECT id FROM ( SELECT id FROM "+tableName
                + " ORDER BY id DESC LIMIT 1 OFFSET "+(strNum-1) + ") AS subquery);";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
            System.out.println("Результат операции сохранен в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить результат операции в таблицу " + tableName);
        }
    }
}


