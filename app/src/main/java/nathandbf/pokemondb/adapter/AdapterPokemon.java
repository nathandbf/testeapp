package nathandbf.pokemondb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nathandbf.pokemondb.interfaces.ItemClickListener;
import nathandbf.pokemondb.model.Pokemon;
import nathandbf.pokemondb.R;
import nathandbf.pokemondb.view.DetalhesPokemon;


public class AdapterPokemon extends RecyclerView.Adapter  {

    private class PokemonView extends RecyclerView.ViewHolder  implements View.OnClickListener{
        final TextView titulo;
        final ImageView simbolo;
        private String link;
        private ItemClickListener itemClickListener;
        public PokemonView(View view) {
            super(view);
            titulo = view.findViewById(R.id.tipo_texto);
            simbolo = view.findViewById(R.id.tipo_simbolo);
            link = null;
            view.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }

    private List<Pokemon> listaPokemons;
    private Context context;

    public AdapterPokemon(List<Pokemon> lista, Context context) {
        this.listaPokemons = lista;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.linha_pokemon, parent, false);
        return  new PokemonView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PokemonView linePokemonView = (PokemonView) viewHolder;
        Pokemon pokemon =  listaPokemons.get(position);
        linePokemonView.titulo.setText(pokemon.getNome());
        linePokemonView.link = pokemon.getUrlPokemon();

        ((PokemonView) viewHolder).setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, DetalhesPokemon.class);
                intent.putExtra("urlPokemon", listaPokemons.get(position).getUrlPokemon());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPokemons.size();
    }

    private static ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}


