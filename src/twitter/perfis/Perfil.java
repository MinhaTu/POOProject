package twitter.perfis;

import java.util.Vector;

public abstract class Perfil {
	private String usuario;
	private Vector<Perfil> seguidos;
	private Vector<Perfil> seguidores;
	private Vector<Tweet> timeline;
	private boolean ativo;
	
	public Perfil (String usuario) {
		
		this.usuario = usuario;
		seguidos   = new Vector<Perfil>();
		seguidores = new Vector<Perfil>();
		timeline   = new Vector<Perfil>();
		ativo = true;
		
	}
}
