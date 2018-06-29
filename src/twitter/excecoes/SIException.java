package twitter.excecoes;

public class SIException extends java.lang.Exception{
		private String usuario;
		
		public SIException(String numero) {
			this.usuario = usuario;
		}
		
		public String getUsuario() {
			return this.usuario;
		}
}
