package dev.davveg.recyclerview_final.pokemon;

public class Pokemon {
    String name;
    String urlApi;
    String descripcion;

    public Pokemon(String name, String urlApi) {
        this.name = name;
        this.urlApi = urlApi;
    }

    public Pokemon(String name, String urlApi, String descripcion) {
        this.name = name;
        this.urlApi = urlApi;
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlApi() {
        return urlApi;
    }

    public void setUrlApi(String urlApi) {
        this.urlApi = urlApi;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
