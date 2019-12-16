package com.fitcheck.ui.elementAdapter;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateChecked {
    public static String server_name = "94.141.168.185:8008";

    private int id;
    private int val;

    public void UpdateCheckVoid(int id_ex, int true_or_false) {
        id = id_ex;
        if (true_or_false == 0) {
            val = 0;
        } else {
            val = 1;
        }
        try {
            new UpdateCheck().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class UpdateCheck extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String myURL = "http://" + server_name +"/updateEx?id=" + id + "&val=" + val;
                byte[] data = null;
                InputStream is = null;


                try {
                    URL url = new URL(myURL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");

                    conn.connect();
                    int responseCode = conn.getResponseCode();


                    OutputStream os = conn.getOutputStream();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    if (responseCode == 200) {
                        is = conn.getInputStream();

                        byte[] buffer = new byte[8192];
                        int bytesRead;

                        while ((bytesRead = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, bytesRead);
                        }

                        data = baos.toByteArray();
                        String resultString = new String(data, "UTF-8");
                    }
                    else {
                        conn.disconnect();
                    }

                }  catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}