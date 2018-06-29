package twitter.excecoes;

public class PEException extends java.lang.Exception {
	private String usuario;
	
	public PEException(String usuario){
		super("Usuario ja cadastrado");
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}
