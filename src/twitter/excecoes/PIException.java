package twitter.excecoes;

public class PIException extends java.lang.Exception {
		private String usuario;
		
		public PIException(String usuario) {
			super("Perfil inexistente!");
			this.usuario = usuario;
		}
		
		public String getUsuario() {
			return this.usuario;
		}
}
