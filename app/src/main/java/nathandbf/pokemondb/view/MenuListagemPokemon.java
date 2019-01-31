package nathandbf.pokemondb.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import nathandbf.pokemondb.R;
import nathandbf.pokemondb.adapter.AdapterPokemon;
import nathandbf.pokemondb.controller.Controller;
import nathandbf.pokemondb.utils.Dialog;

public class MenuListagemPokemon extends Activity {

    private RecyclerView listPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_listagem_pokemon);
        listPokemon = findViewById(R.id.list_pokemon);
        listPokemon.setLayoutManager(new LinearLayoutManager(this));
        String url = null;
        if(getIntent().hasExtra("url_tipo")){
            url = getIntent().getStringExtra("url_tipo");
            Controller controller = new Controller(this);
            Dialog.dialogCarregandoSimples(this);
            AsyncTaskMenuPokemon async = new AsyncTaskMenuPokemon(listPokemon, controller, this, url);
            async.execute();
        }
        else{
            Dialog.fecharDialogCarregando();
            ImageView pikachuTriste = findViewById(R.id.pikachusad);
            pikachuTriste.setVisibility(View.VISIBLE);
            //TODO dialog para voltar para a tela anterior.
            Toast.makeText(getApplicationContext(),this.getResources().getString(R.string.erroInesperado),Toast.LENGTH_SHORT).show();
        }
    }


    private class AsyncTaskMenuPokemon extends AsyncTask<String, String, String> {

        private RecyclerView listPokemon;
        private Controller controller;
        private Activity activity;
        private String url;
        public AsyncTaskMenuPokemon(RecyclerView listPokemon, Controller controller, Activity activity, String url) {
            this.listPokemon = listPokemon;
            this.controller = controller;
            this.activity = activity;
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            String jsonString = controller.getJsonFromAPI(this.url);
            publishProgress(jsonString);
            return jsonString ;
        }

        @Override
        protected void onProgressUpdate(String... listaPokemons) {
            if(listaPokemons[0]!=null){
                AdapterPokemon adapter = new AdapterPokemon(controller.carregarPokemonSimples(listaPokemons[0]),getApplicationContext());
                listPokemon.setAdapter(adapter);
                Dialog.fecharDialogCarregando();
            }
            else{
                Dialog.fecharDialogCarregando();
                ImageView pikachuTriste = activity.findViewById(R.id.pikachusad);
                pikachuTriste.setVisibility(View.VISIBLE);
            }

        }
    }

}
