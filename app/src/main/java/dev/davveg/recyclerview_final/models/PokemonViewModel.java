package dev.davveg.recyclerview_final.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dev.davveg.recyclerview_final.pokemon.Pokemon;

public class PokemonViewModel extends AndroidViewModel {

    PokemonRepositorio pokemonRepositorio;

    MutableLiveData<List<Pokemon>> listaPokemonsMutableData = new MutableLiveData<>();

    MutableLiveData<Pokemon> pokemonSeleccionado = new MutableLiveData<>();

    public PokemonViewModel(@NonNull Application application) {
        super(application);

        pokemonRepositorio = new PokemonRepositorio();

        listaPokemonsMutableData.setValue(pokemonRepositorio.obtener());
    }

    public MutableLiveData<List<Pokemon>> obtener(){
        return listaPokemonsMutableData;
    }

    void insertar(Pokemon elemento){
        pokemonRepositorio.insertar(elemento, new PokemonRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Pokemon> elementos) {
                listaPokemonsMutableData.setValue(elementos);
            }
        });
    }

    public void eliminar(Pokemon elemento){
        pokemonRepositorio.eliminar(elemento, new PokemonRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Pokemon> elementos) {
                listaPokemonsMutableData.setValue(elementos);
            }
        });
    }

    void actualizar(Pokemon elemento, String descripcion){
        pokemonRepositorio.actualizar(elemento, descripcion, new PokemonRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Pokemon> elementos) {
                listaPokemonsMutableData.setValue(elementos);
            }
        });
    }

    public void seleccionar(Pokemon elemento){
        pokemonSeleccionado.setValue(elemento);
    }

    public MutableLiveData<Pokemon> seleccionado(){
        return pokemonSeleccionado;
    }
}
