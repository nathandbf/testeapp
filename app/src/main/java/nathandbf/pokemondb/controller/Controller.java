package nathandbf.pokemondb.controller;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nathandbf.pokemondb.R;
import nathandbf.pokemondb.model.Pokemon;
import nathandbf.pokemondb.model.TipoPokemon;
import nathandbf.pokemondb.utils.Dialog;
import nathandbf.pokemondb.utils.InternetValidator;

public class Controller {
    Activity activity;

    public Controller(Activity activity) {
        this.activity = activity;
    }
    private Pokemon pokemonInTela;

    public String getJsonFromAPI(String url){
        if(verificarInternet()){
           return RestPokemonApi.getJsonFromAPI(url);
        }
        else{
            Dialog.fecharDialogCarregando();
            Dialog.dialogOkSimples(activity,activity.getResources().getString(R.string.erro),activity.getResources().getString(R.string.internetSemConexao));
        }
        return null;
    }

    public List<TipoPokemon> carregarTiposPokemon(String jsonString){
        boolean erro = false;
        List<TipoPokemon> tipoPokemon = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject tipoJson;
            JSONArray listaTiposJson = jsonObject.getJSONArray("results");
            for (int i=0; i < listaTiposJson.length(); i++) {
                tipoJson = listaTiposJson.getJSONObject(i);
                tipoPokemon.add( new TipoPokemon(tipoJson.getString("name"),tipoJson.getString("url")));
            }
        }
        catch (JSONException e){
            e.printStackTrace();
            erro = true;
        }

        if(erro){
            Dialog.fecharDialogCarregando();
            //erro TODO
        }
        return tipoPokemon;
    }

    public List<Pokemon> carregarPokemonSimples(String jsonString){
        boolean erro = false;
        List<Pokemon> pokemon = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject pokemonJson;
            JSONArray listaPokemonJson = jsonObject.getJSONArray("pokemon");
            for (int i=0; i < listaPokemonJson.length(); i++) {
                pokemonJson = listaPokemonJson.getJSONObject(i);
                JSONObject aux = pokemonJson.getJSONObject("pokemon");
                pokemon.add( new Pokemon(aux.getString("name"),aux.getString("url")));
            }
        }
        catch (JSONException e){
            e.printStackTrace();
            erro = true;
        }

        if(erro){
            Dialog.fecharDialogCarregando();
            //erro TODO
        }
        return pokemon;
    }

    public void carregarPokemon(Activity activity, String stringPokemon ){
        boolean erro = false;
        Pokemon pokemon = new Pokemon();
        try{
            JSONObject jsonPokemon = new JSONObject(stringPokemon);
            pokemon.setNome(jsonPokemon.getString("name"));
            pokemon.setAltura(jsonPokemon.getString("height"));
            pokemon.setPeso(jsonPokemon.getString("weight"));
            pokemon.setUrlFoto(jsonPokemon.getJSONObject("sprites").getString("front_default"));
            pokemon.setAltura(jsonPokemon.getString("height"));
            JSONArray listaJson;
            JSONObject aux;
            listaJson = jsonPokemon.getJSONArray("abilities");
            for (int i=0; i < listaJson.length(); i++) {
                aux = listaJson.getJSONObject(i).getJSONObject("ability");
                pokemon.getNomeHabilidades().add((aux.getString("name")));
            }
            listaJson = jsonPokemon.getJSONArray("moves");
            for (int i=0; i < listaJson.length(); i++) {
                aux = listaJson.getJSONObject(i).getJSONObject("move");
                pokemon.getNomeMovimentos().add((aux.getString("name")));
            }
            carregarPokemonTela(activity,pokemon);
        }
        catch (JSONException e){
            e.printStackTrace();
            erro = true;
        }

        if(erro){
            Dialog.fecharDialogCarregando();
            //erro TODO
        }
    }

    private void carregarPokemonTela(Activity activity, Pokemon pokemon) {
        ImageView imagemPokemon = activity.findViewById(R.id.imagemPokemon);
        Picasso.get().load(pokemon.getUrlFoto())
                .resize(imagemPokemon.getMeasuredWidth(), imagemPokemon.getMeasuredHeight()).centerInside()
                .into(imagemPokemon);
        TextView textView = activity.findViewById(R.id.nomePokemon);
        textView.setText(pokemon.getNome());
        textView = activity.findViewById(R.id.alturaPokemon);
        textView.setText(pokemon.getAlturaFormatada());
        textView = activity.findViewById(R.id.pesoPokemon);
        textView.setText(pokemon.getPesoFormatado());

        Dialog.fecharDialogCarregando();

    }


    private boolean verificarInternet(){
       return InternetValidator.verificaInternet(activity);
    }
}
