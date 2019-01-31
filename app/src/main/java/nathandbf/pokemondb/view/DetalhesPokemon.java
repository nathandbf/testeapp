package nathandbf.pokemondb.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import nathandbf.pokemondb.R;
import nathandbf.pokemondb.controller.Controller;
import nathandbf.pokemondb.utils.Dialog;

public class DetalhesPokemon extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokemon);
        String url = null;
        if(getIntent().hasExtra("urlPokemon")){
            url = getIntent().getStringExtra("urlPokemon");
            Controller controller = new Controller(this);
            Dialog.dialogCarregandoSimples(this);
            AsyncTaskPokemon async = new AsyncTaskPokemon( controller, this, url);
            async.execute();
        }
        else{
            Dialog.fecharDialogCarregando();
            ImageView pikachuTriste = findViewById(R.id.pikachusad);
            pikachuTriste.setVisibility(View.VISIBLE);
            //TODO dialog para voltar para a tela anterior.
            Toast.makeText(getApplicationContext(),this.getResources().getString(R.string.erroInesperado),Toast.LENGTH_SHORT).show();
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Compartilhe este pokemon");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "pokemongo?"));
            }
        });
    }

    private class AsyncTaskPokemon extends AsyncTask<String, String, String> {

        private Controller controller;
        private Activity activity;
        private String url;
        public AsyncTaskPokemon(Controller controller, Activity activity, String url) {
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
        protected void onProgressUpdate(String... pokemon) {
            if(pokemon[0]!=null){
                controller.carregarPokemon(activity,pokemon[0]);
            }
            else{
                Dialog.fecharDialogCarregando();
                ImageView pikachuTriste = activity.findViewById(R.id.pikachusad);
                pikachuTriste.setVisibility(View.VISIBLE);
            }

        }
    }

}
