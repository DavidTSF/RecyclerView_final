package dev.davveg.recyclerview_final;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dev.davveg.recyclerview_final.databinding.FragmentRecyclerPokemonsBinding;
import dev.davveg.recyclerview_final.databinding.ViewholderPokemonBinding;
import dev.davveg.recyclerview_final.models.PokemonViewModel;
import dev.davveg.recyclerview_final.pokemon.Pokemon;


public class RecyclerPokemonsFragment extends Fragment {
    private FragmentRecyclerPokemonsBinding binding;
    private PokemonViewModel pokemonViewModel;
    private NavController navController;

    class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPokemonBinding binding;

        public PokemonViewHolder(ViewholderPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

        List<Pokemon> pokemons;

        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PokemonViewHolder(ViewholderPokemonBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

            Pokemon elemento = pokemons.get(position);

            holder.binding.nombre.setText(elemento.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokemonViewModel.seleccionar(elemento);
                    navController.navigate(R.id.action_recyclerPokemonsFragment_to_mostrarPokemonFragment);
                }
            });

        }
        @Override
        public int getItemCount() {
            return pokemons != null ? pokemons.size() : 0;
        }

        public void establecerLista(List<Pokemon> elementos){
            this.pokemons = elementos;
            notifyDataSetChanged();
        }

        public Pokemon obtenerPokemon(int posicion){
            return pokemons.get(posicion);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerPokemonsBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        navController = Navigation.findNavController(view);

        PokemonAdapter pokemonAdapter = new PokemonAdapter();
        binding.recyclerView.setAdapter(pokemonAdapter);


        pokemonViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                pokemonAdapter.establecerLista(pokemons);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemonAdapter.obtenerPokemon(posicion);
                pokemonViewModel.eliminar(pokemon);

            }
        }).attachToRecyclerView(binding.recyclerView);


    }


}
