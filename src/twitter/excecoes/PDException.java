package twitter.excecoes;

public class PDException extends java.lang.Exception {
	private String usuario;
	
	public PDException(String numero) {
		super("Perfil desativado!");
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}
