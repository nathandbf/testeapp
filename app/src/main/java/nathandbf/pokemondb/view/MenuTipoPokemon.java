package nathandbf.pokemondb.view;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import nathandbf.pokemondb.adapter.AdapterTipoPokemon;
import nathandbf.pokemondb.controller.Controller;
import nathandbf.pokemondb.R;
import nathandbf.pokemondb.utils.Dialog;

public class MenuTipoPokemon extends Activity {

    private RecyclerView listTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_type);

        listTypes = findViewById(R.id.list_types);
        listTypes.setLayoutManager(new LinearLayoutManager(this));
        Controller controller = new Controller(this);
        Dialog.dialogCarregandoSimples(this);

        AsyncTaskMenuTipo async = new AsyncTaskMenuTipo(listTypes, controller, this);
        async.execute();
    }


    private class AsyncTaskMenuTipo extends AsyncTask<String, String, String> {

        private RecyclerView listTypes;
        private Controller controller;
        private Activity activity;
        public AsyncTaskMenuTipo(RecyclerView listTypes, Controller controller, Activity activity) {
            this.listTypes = listTypes;
            this.controller = controller;
            this.activity = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            String jsonString = controller.getJsonFromAPI(activity.getResources().getString(R.string.link_tipos_pokemon));
            publishProgress(jsonString);
            return jsonString  ;

        }

        @Override
        protected void onProgressUpdate(String... listaTipoPokemons) {
            if(listaTipoPokemons[0]!=null){
                AdapterTipoPokemon adapter = new AdapterTipoPokemon(controller.carregarTiposPokemon(listaTipoPokemons[0]),getApplicationContext());
                listTypes.setAdapter(adapter);
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
