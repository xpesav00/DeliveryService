package pa165.deliveryservice.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Drimal
 */
public class MainApp {
    private static int doPost(URL url, Map<String, String> params) throws UnsupportedEncodingException, IOException {

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url.toString());

        if (!params.isEmpty()) {
            for (Entry<String, String> entry : params.entrySet()) {
                method.addParameter(entry.getKey(), entry.getValue());
            }
        }

        int statusCode = client.executeMethod(method);
        InputStream rstream = null;
        rstream = method.getResponseBodyAsStream();

        return statusCode;
    }

    private static int doDelete(URL url) throws UnsupportedEncodingException, IOException {
        HttpClient client = new HttpClient();

        DeleteMethod method = new DeleteMethod(url.toString());
        int statusCode = client.executeMethod(method);

        InputStream rstream = null;
        rstream = method.getResponseBodyAsStream();

        return statusCode;
    }

    private static int doGet(URL url) throws UnsupportedEncodingException, IOException {
        HttpClient client = new HttpClient();

        GetMethod method = new GetMethod(url.toString());
        int statusCode = client.executeMethod(method);

        InputStream rstream = null;
        rstream = method.getResponseBodyAsStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        return statusCode;
    }

    public static void main(String[] args) throws Exception {
        // Server test
        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8080/pa165/").openConnection();
        con.setRequestMethod("HEAD");
        try {
            String result = String.valueOf(con.getResponseCode() == HttpURLConnection.HTTP_OK);
            if (result.contains("false")) {
                //TODO show to user dialog with error
                System.out.println("Server is down");
                return;
            } else {
                System.out.println("Server is running");
               
                String restUrl = "http://localhost:8080/pa165/rest/";
                doGet(new URL(restUrl + "postman/findAll"));

            }
        } catch (IOException e) {
            //TODO show to user dialog with error
            System.out.println("Server is down");
        }

    }
}
