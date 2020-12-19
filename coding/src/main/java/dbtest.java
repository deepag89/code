import java.sql.Connection;
import java.sql.DriverManager;

public class dbtest {
    public static void main(String ar[]) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:3309/app5ingeqa_cu_config",
                "runtime", "MOTO5n7xRFOHxVOrIdscgCVBWQiXsW8Z");
            System.out.println(conn.getMetaData().getUserName());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
