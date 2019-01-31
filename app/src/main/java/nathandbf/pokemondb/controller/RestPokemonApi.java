package nathandbf.pokemondb.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



public class RestPokemonApi {

    public static String getJsonFromAPI(String urlString){

                try{
                    URL url = new URL(urlString);
                    HttpsURLConnection conexao =
                            (HttpsURLConnection) url.openConnection();
                    if (conexao.getResponseCode() == 200) {
                        InputStream inputStream = conexao.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                        StringBuilder sb = new StringBuilder();

                        String line;
                        while ((line = reader.readLine()) != null)
                        {
                            sb.append(line + "\n");
                        }
                        return sb.toString();
                    }
                    else{
                        return null;
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                return null;
            }
}
