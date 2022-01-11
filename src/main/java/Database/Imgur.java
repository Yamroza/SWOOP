package Database;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Imgur {

    public static String putImgurContent(String path) throws Exception {

        byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
        String encodedString = java.util.Base64.getEncoder().encodeToString(fileContent);
        String data = URLEncoder.encode("image", "UTF-8") + "="
                + URLEncoder.encode(encodedString, "UTF-8");

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

        // Get the response
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            stb.append(line).append(" ");
        }
        wr.close();
        rd.close();
        String ret = stb.toString();
        ret = ret.substring(436, 470);
        System.out.println(ret);

        return ret;
    }
}
