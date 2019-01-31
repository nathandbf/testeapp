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
import nathandbf.pokemondb.model.TipoPokemon;
import nathandbf.pokemondb.R;
import nathandbf.pokemondb.view.MenuListagemPokemon;


public class AdapterTipoPokemon extends RecyclerView.Adapter  {

    private class TipoPokemonView extends RecyclerView.ViewHolder  implements View.OnClickListener{
        final TextView titulo;
        final ImageView simbolo;
        private String link;
        private ItemClickListener itemClickListener;
        public TipoPokemonView(View view) {
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

    private List<TipoPokemon> listaTipoPokemons;
    private Context context;

    public AdapterTipoPokemon(List<TipoPokemon> lista, Context context) {
        this.listaTipoPokemons = lista;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.linha_tipo_pokemon, parent, false);
        return  new TipoPokemonView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        TipoPokemonView lineTypeView = (TipoPokemonView) viewHolder;
        TipoPokemon tipoPokemon =  listaTipoPokemons.get(position);
        lineTypeView.titulo.setText(tipoPokemon.getNome());
        lineTypeView.link = tipoPokemon.getUrl();

        try{
            lineTypeView.simbolo.setImageResource(context.getResources().getIdentifier(tipoPokemon.getPathFoto(),
                    "mipmap", context.getPackageName()));
        }
        catch(Exception e){
            lineTypeView.simbolo.setImageResource(R.mipmap.ic_launcher);
        }

        ((TipoPokemonView) viewHolder).setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, MenuListagemPokemon.class);
                intent.putExtra("url_tipo", listaTipoPokemons.get(position).getUrl());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaTipoPokemons.size();
    }

    private static ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}


