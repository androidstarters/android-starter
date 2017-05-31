package io.mvpstarter.sample.features.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.mvpstarter.sample.R;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<String> pokemonList;
    private Subject<String> pokemonClickSubject;

    @Inject
    PokemonAdapter() {
        pokemonClickSubject = PublishSubject.create();
        pokemonList = Collections.emptyList();
    }

    public void setPokemon(List<String> pokemon) {
        this.pokemonList = pokemon;
        notifyDataSetChanged();
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        String pokemon = this.pokemonList.get(position);
        holder.onBind(pokemon);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    Observable<String> getPokemonClick() {
        return pokemonClickSubject;
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView nameText;

        private String pokemon;

        PokemonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> pokemonClickSubject.onNext(pokemon));
        }

        void onBind(String pokemon) {
            this.pokemon = pokemon;
            nameText.setText(
                    String.format(
                            "%s%s", pokemon.substring(0, 1).toUpperCase(), pokemon.substring(1)));
        }
    }
}
