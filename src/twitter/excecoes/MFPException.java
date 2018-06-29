package twitter.excecoes;

public class MFPException extends java.lang.Exception{
	private String mensagem;
	
	public MFPException (String mensagem) {
		super("Mensagem grande demais!");
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
}
