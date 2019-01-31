package nathandbf.pokemondb.model;

public class TipoPokemon {
    private String nome;
    private String url;

    public TipoPokemon(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    public String getNome() {
        return nome.substring(0,1).toUpperCase() + nome.substring(1).toLowerCase();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPathFoto(){
        return "icon_" + nome + "_foreground";
    }
}
