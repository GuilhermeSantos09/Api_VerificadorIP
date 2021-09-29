package com.example.api_verificadorip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Http_Util {
    public String buscarIPV4(){
        return realizarGET("https://api4.my-ip.io/ip");
    }
    public String buscarIPV6(){
        return realizarGET("https://api6.my-ip.io/ip");
    }

    public List<String> buscarIPs() {

        String jsonString =  realizarGET("http://192.168.0.105:5000/api/ip/list");

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonString);

            List<String> result = new ArrayList<>(jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {
                result.add(jsonArray.getString(i));
            }

            return result;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public void enviarIP(String ip) {

        realizarPOST("http://192.168.0.105:5000/api/ip/add", "\"" + ip + "\"");

    }

    public String realizarGET(String s){
        try {
            URL url = new URL(s);

            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.connect();
            return lerEntrada(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

    public String realizarPOST(String link, String corpo){
        try {
            URL url = new URL(link);

            HttpURLConnection connection = null;
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream output = connection.getOutputStream();
            output.write(corpo.getBytes());

            connection.connect();

            return lerEntrada(connection.getInputStream());
        } catch (FileNotFoundException ex) {
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;

        }

    }
    private String lerEntrada(InputStream input) throws IOException {

        byte[] buffer = new byte[4096];

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        int read;

        while ((read = input.read(buffer)) != -1) {
            output.write(buffer, 0, read);
        }

        return new String(output.toByteArray());
    }
}
