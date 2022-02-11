import java.sql.*;

public class HomeWorkDB {
    private static final String USER_NAME = "NWQ0FOHzgJ";
    private static final String DATABASE_NAME = "NWQ0FOHzgJ";
    private static final String PASSWORD = "lfylQYAyhT";
    private static final String PORT = "3306";
    private static final String SERVER = "remotemysql.com";

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://"+SERVER+":"+PORT, USER_NAME, PASSWORD);

//        createTable(con);
//        insertDogs(con, 1, "max", 2, "pitbull");
//        insertDogs(con, 2, "ricky", 1, "chawchaw");
//        insertDogs(con, 3, "simba", 3, "german");
//        getTableContent(con);
//        deleteDogsByName(con, "simba");
//        updateDogsName(con,5, "ricky");
        con.close();
    }

    private static void createTable(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`dogs`(`id` INT NOT NULL,`name` VARCHAR(40) NOT NULL,`age` INT NOT NULL,`breed` VARCHAR(40) NOT NULL, PRIMARY KEY (`id`));";
        con.createStatement().execute(statementToExecute);
    }

    private static void insertDogs(Connection con, int id, String name, int age, String breed) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".dogs (`id`, `name`, `age`, `breed`) VALUES ('" + id + "', '" + name + "', '" + age + "', '" + breed + "');";
        con.createStatement().execute(statementToExecute);
    }

    private static void getTableContent(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".dogs;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while(rs.next()){
            //Retrieve by column name
            String name = rs.getString("name");

            //Display values
            System.out.print(", Name: " + name);
        }
        rs.close();
    }
    private static void deleteDogsByName(Connection con, String name) throws SQLException {
        String statementToExecute = "DELETE FROM `" + DATABASE_NAME + "`.`dogs` WHERE `name`='"+name+"';";
        con.createStatement().execute(statementToExecute);
    }

    private static void updateDogsName(Connection con, int age, String name) throws SQLException {
        String statementToExecute = "UPDATE `" + DATABASE_NAME + "`.`dogs` SET `age`='"+age+"' WHERE `name`='"+name+"';";
        con.createStatement().executeUpdate(statementToExecute);
    }
}