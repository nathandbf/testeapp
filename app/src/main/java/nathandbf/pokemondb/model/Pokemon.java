package nathandbf.pokemondb.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Pokemon {
    private String nome;
    private String urlPokemon;
    private String altura;
    private String peso;
    private String urlFoto;
    private ArrayList<String> nomeHabilidades;
    private ArrayList<String> nomeMovimentos;

    public Pokemon() {
        nomeMovimentos = new ArrayList<>();
        nomeHabilidades = new ArrayList<>();
    }

    public Pokemon(String nome, String urlPokemon) {
        this.nome = nome;
        this.urlPokemon = urlPokemon;
    }

    public String getNome() {

        return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlPokemon() {
        return urlPokemon;
    }

    public void setUrlPokemon(String urlPokemon) {
        this.urlPokemon = urlPokemon;
    }

    public String getAltura() {
        return altura;
    }

    public String getAlturaFormatada() {
        StringBuilder builder = new StringBuilder();
        builder.append(new BigDecimal(getAltura()).multiply(new BigDecimal(10)));
        builder.append(" cm");
        return builder.toString();
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public String getPesoFormatado() {
        StringBuilder builder = new StringBuilder();
        builder.append(new BigDecimal(getPeso()).divide(new BigDecimal(10)));
        builder.append(" Kg");
        return builder.toString();
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public ArrayList<String> getNomeHabilidades() {
        return nomeHabilidades;
    }

    public void setNomeHabilidades(ArrayList<String> nomeHabilidades) {
        this.nomeHabilidades = nomeHabilidades;
    }

    public ArrayList<String> getNomeMovimentos() {
        return nomeMovimentos;
    }

    public void setNomeMovimentos(ArrayList<String> nomeMovimentos) {
        this.nomeMovimentos = nomeMovimentos;
    }
}
