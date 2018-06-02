import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.json.JSONObject;
import java.io.*;
import java.util.List;

public class App {

    public static void main( String[] args ){
        App obj = new App();
        File[] files = new File("/home/gautam/Desktop/output").listFiles();
        readFiles(files);

        MongoConnect connect = new MongoConnect();

        List<String> dbnames = connect.readData();
        for(String db : dbnames)
        {
            System.out.println("DB Name:"+db);
        }
    }

    public static void readFiles(File[] files) {


        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                readFiles(file.listFiles()); // Calls same method again.
            } else if(file.isFile()) {

                try {

                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
                    CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(bis);
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(input));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br2.readLine()) != null) {
                        sb.append(line);
                    }
                    JSONObject json = new JSONObject(sb.toString());
                    System.out.println("Out:"+json.getString("auctionId"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
