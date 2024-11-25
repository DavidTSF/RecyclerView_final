package dev.davveg.recyclerview_final.models;

import java.util.ArrayList;
import java.util.List;

import dev.davveg.recyclerview_final.pokemon.Pokemon;

public class PokemonRepositorio {

    List<Pokemon> pokemonList;

    interface Callback {
        void cuandoFinalice(List<Pokemon> elementos);
    }

    public PokemonRepositorio() {
        this.pokemonList = new ArrayList<>();

        pokemonList.add( new Pokemon("bulbasaur", "https://pokeapi.co/api/v2/pokemon-species/1/", "este pokemon es verde y muy verde") );
        pokemonList.add( new Pokemon("ivysaur", "https://pokeapi.co/api/v2/pokemon-species/2/", "este pokemon es verde y muy verde") );
        pokemonList.add( new Pokemon("venusaur", "https://pokeapi.co/api/v2/pokemon-species/3/", "este pokemon es verde y muy verde") );
        pokemonList.add( new Pokemon("charmander", "https://pokeapi.co/api/v2/pokemon-species/4/", "este pokemon es rojo y muy rojo") );




    }

    List<Pokemon> obtener() {
        return pokemonList;
    }

    void insertar(Pokemon elemento, Callback callback){
        pokemonList.add(elemento);
        callback.cuandoFinalice(pokemonList);
    }

    void eliminar(Pokemon elemento, Callback callback) {
        pokemonList.remove(elemento);
        callback.cuandoFinalice(pokemonList);
    }

    void actualizar(Pokemon elemento, String valoracion, Callback callback) {
        elemento.setDescripcion(valoracion);
        callback.cuandoFinalice(pokemonList);
    }


}
