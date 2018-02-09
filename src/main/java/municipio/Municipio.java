package municipio;

public class Municipio {

	private String id;

	private String nome;

	public Municipio(String descricao) {
		this.id = descricao.split("	")[0];
		this.nome = replace(descricao.split("	")[1]);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	private String replace(String descricao) {
		return descricao.replace(" ", "").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").replace("Á", "a").replace("É", "e").replace("Í", "i").replace("Ó", "o").replace("Ú", "u").replace("ç", "c").replace("Ç", "c").replace("ã", "a").replace("õ", "o").replace("Ã", "a").replace("Õ", "o").replace("ê", "e").replace("Ê", "e").replace("â", "a").replace("Â", "a").replace("Ô", "o").replace("ô", "o");
	}
	
	public String getLink() {
		return "https://commons.wikimedia.org/wiki/File:MinasGerais_Municip_" + getNome() + ".svg";
	}
	
}
