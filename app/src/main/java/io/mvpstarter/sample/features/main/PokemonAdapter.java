package io.mvpstarter.sample.features.main;

import android.databinding.DataBindingUtil;
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
import io.mvpstarter.sample.databinding.ItemPokemonBinding;
import io.mvpstarter.sample.util.Utils;
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
        ItemPokemonBinding itemPokemonBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_pokemon,
                parent,
                false
        );
        return new PokemonViewHolder(itemPokemonBinding);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        String pokemon = this.pokemonList.get(position);
        holder.onBind(Utils.toCameCase(pokemon));
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    Observable<String> getPokemonClick() {
        return pokemonClickSubject;
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {

        private ItemPokemonBinding mBinding;

        PokemonViewHolder(ItemPokemonBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void onBind(String pokemon) {
            mBinding.setPokemon(pokemon);
            mBinding.setClickSubject(pokemonClickSubject);
        }
    }
}
