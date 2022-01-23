package Database;
import javafx.scene.image.Image;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class Imgur {

    // uploading picture to Imgur - returns link to picture
    public static String putImgurContent(File photo_file) throws Exception {

        byte[] fileContent = FileUtils.readFileToByteArray(photo_file);
        String encodedString = java.util.Base64.getEncoder().encodeToString(fileContent);
        String data = URLEncoder.encode("image", StandardCharsets.UTF_8) + "="
                + URLEncoder.encode(encodedString, StandardCharsets.UTF_8);

        // our passes:
        String clientID = "da82141bf85ccad";
        URL url;
        url = new URL("https://api.imgur.com/3/image");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Client-ID " + clientID);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        conn.connect();
        StringBuilder stb = new StringBuilder();
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response - link
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            stb.append(line).append(" ");
        }
        wr.close();
        rd.close();
        String ret = stb.toString();
        int index_s = ret.indexOf("http");
        int index_f = ret.indexOf("success");
        ret = ret.substring(index_s, index_f-4);
        StringBuilder sb = new StringBuilder(ret);
        sb.deleteCharAt(6);
        sb.deleteCharAt(7);
        sb.deleteCharAt(19);
        return sb.toString();
    }

    // downloading photo from Imgur link
    public static Image showImageFromLink(String link) throws IOException {
        URL url = new URL(link);
        File file = new File("work_image.jpg");
        int connectionTimeout = 10 * 1000; // 10 sec
        int readTimeout = 300 * 1000; // 3 min
        FileUtils.copyURLToFile(url, file, connectionTimeout, readTimeout);
        return new Image(file.toURI().toString());
    }
}
