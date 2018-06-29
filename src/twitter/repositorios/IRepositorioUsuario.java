package twitter.repositorios;

import twitter.excecoes.UJCException;
import twitter.excecoes.UNCException;
import twitter.perfis.Perfil;
public interface IRepositorioUsuario {
	
	public void cadastrar (Perfil usuario) throws UJCException;
	public Perfil buscar (String usuario);
	public void atualizar (Perfil usuario) throws UNCException;
	
}
