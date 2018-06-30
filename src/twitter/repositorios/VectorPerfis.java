package twitter.repositorios;

import java.util.Vector;

import twitter.perfis.Perfil;

import twitter.excecoes.*;

public class VectorPerfis implements IRepositorioUsuario  {
	private Vector<Perfil> usuarios;
	
	public VectorPerfis() {
		this.usuarios = new Vector<Perfil>();
	}
	
	public void cadastrar (Perfil usuario) throws UJCException{
		if(buscar(usuario.getUsuario()) == null){
            usuarios.add(usuario);
        }else{
            throw new UJCException(usuario);
        }
	}
	
	public Perfil buscar (String usuario) {
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getUsuario() == usuario) {
				return usuarios.get(i);
			}
		}
		return null;
	}
	
	public void atualizar (Perfil usuario) throws UNCException {
		Perfil usuarioAuxiliar = buscar(usuario.getUsuario());
		if(usuarioAuxiliar != null) {
			usuarios.remove(usuarioAuxiliar);
			usuarios.add(usuario);
		}else {
			throw new UNCException(usuario);
		}
		
	}
	
}
