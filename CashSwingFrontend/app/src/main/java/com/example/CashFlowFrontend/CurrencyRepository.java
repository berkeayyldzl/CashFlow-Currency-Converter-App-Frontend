package com.example.CashFlowFrontend;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class CurrencyRepository {
    //THIS CLASS IF FOR DOWNLOADING DATA FROM THE DATABASE

    public void getAllCurrencies(ExecutorService srv, Handler uiHandler){ //get all currencies method


        srv.submit(()->{
            try {

                List<Currency> data = new ArrayList<>();

                URL url = new URL("http://10.0.2.2:8080/api/exchangerates/getAllCurrencies"); //our api


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();//opens the connection

                BufferedReader reader //since this is a string, we are using buffered reader
                        = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String line ="";
                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());

                for (int i = 0; i <arr.length() ; i++) { //for parsing the json array

                    JSONObject current = arr.getJSONObject(i);

                    Currency currency = new Currency(
                            current.getString("mainCurrency"),
                            current.getDouble("exchangeRate"),
                            current.getDouble("max"),
                            current.getDouble("min"));

                    data.add(currency);//adds as a currency
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);



            } catch (MalformedURLException e) { //if the task could not be completed, returns exceptions
                Log.e("DEV",e.getMessage());
            } catch (IOException e) {
                Log.e("DEV",e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV",e.getMessage());
            }


        });

    }

    //This is the post method for saving new currencies
    public void save(ExecutorService srv, String mainCurrency, double exchangeRate, double min,double max){
        srv.execute(()->{

            try {
                URL url = new URL("http://10.0.2.2:8080/api/exchangerates/save");// our api

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/JSON");


                JSONObject outputData  = new JSONObject();//gets the strings and makes them a json

                outputData.put("mainCurrency",mainCurrency);
                outputData.put("exchangeRate",exchangeRate);
                outputData.put("min",min);
                outputData.put("max",max);

                BufferedOutputStream writer = //again we are using buffered output stream
                        new BufferedOutputStream(conn.getOutputStream());


                writer.write(outputData.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();

                String line ="";

                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject retVal = new JSONObject(buffer.toString());
                conn.disconnect();


                String retValStr = retVal.getString("result");



            } catch (MalformedURLException e) { //if not completed successfully, return an exception
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });
    }
}
