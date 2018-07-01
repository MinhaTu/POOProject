package twitter;

import java.util.Vector;

import twitter.excecoes.*;
import twitter.perfis.Perfil;
import twitter.repositorios.IRepositorioUsuario;
import twitter.tweets.Tweet;
import twitter.tweets.*;

public class MyTwitter implements ITwitter {
	IRepositorioUsuario repositorio;
	
	public MyTwitter (IRepositorioUsuario repositorio) {
		this.repositorio = repositorio; 
	}
	
	public void criarPerfil (Perfil usuario) throws PEException {
		try {
			repositorio.cadastrar(usuario);
		}catch (UJCException ujc) {
			throw new PEException(usuario.getUsuario());
		}
	}
	
	public void cancelarPerfil (String usuario) throws PIException, PDException {
		Perfil usuarioAuxiliar = repositorio.buscar(usuario);
		if(usuarioAuxiliar == null) {
			throw new PIException(usuario);
		}else if(!usuarioAuxiliar.isAtivo()) {
			throw new PDException(usuario);
		}else {
			usuarioAuxiliar.setAtivo(false);
		}
	}
	
	public void tweetar (String usuario, String mensagem) throws PIException, PDException, MFPException{
		Perfil usuarioAuxiliar = repositorio.buscar(usuario);
		if(usuarioAuxiliar == null) {
			throw new PIException(usuario);
		}else if (!usuarioAuxiliar.isAtivo()) {
			throw new PDException(usuario);
		}else if (mensagem.length() < 1 || mensagem.length() > 140) {
			throw new MFPException(mensagem);
		}else {
			Tweet tweet = new Tweet();
			tweet.setUsuario(usuario);
			tweet.setMensagem(mensagem);
			usuarioAuxiliar.addTweet(tweet);
			Vector<Perfil> seguidores = usuarioAuxiliar.getSeguidores();
			for(int i = 0; i < seguidores.size(); i++) {
				if(seguidores.get(i).isAtivo()) {
					seguidores.get(i).addTweet(tweet);
				}
			}
		}
	}
	
	public Vector<Tweet> timeline (String usuario) throws PIException, PDException {
		Perfil usuarioAuxiliar = repositorio.buscar(usuario);
		if(usuarioAuxiliar == null) {
			throw new PIException(usuario);
		}else if(!usuarioAuxiliar.isAtivo()) {
			throw new PDException(usuario);
		}
		return usuarioAuxiliar.getTimeline();
	}
	
	public Vector<Tweet> tweets(String usuario) throws PIException, PDException{
		Perfil usuarioAuxiliar = repositorio.buscar(usuario);
		Vector<Tweet> timeline = new Vector<Tweet>();
		Vector<Tweet> tweets = new Vector<Tweet>();
		if(usuarioAuxiliar == null) {
			throw new PIException(usuario);
		}else if(!usuarioAuxiliar.isAtivo()) {
			throw new PDException(usuario);
		}else {
			timeline = timeline(usuario);
			for(int i = 0; i < timeline.size();i++) {
				if(timeline.get(i).getUsuario() == usuario) {
					tweets.add(timeline.get(i));
				}
			}
		}
		return tweets;
	}
	
	public void seguir (String seguidor, String seguido) throws PIException, PDException, SIException{
		Perfil segR = repositorio.buscar(seguidor);
		Perfil segO = repositorio.buscar(seguido);
		if(segR  == null || segO == null) {
			if(segR == null) {
				throw new PIException(seguidor); 
			}else {
				throw new PIException(seguido); 
			}
		}else if(!segR.isAtivo() || !segO.isAtivo()) {
			if(!segR.isAtivo()) {
				throw new PDException(seguidor);
			}else {
				throw new PDException(seguido);
			}
		}else if(seguidor == seguido) {
			throw new SIException(seguidor);
		}else {
			segO.addSeguidor(segR);
			segR.addSeguido(segO);
		}
	}
	
	public int numeroSeguidores (String usuario) throws PIException, PDException{
		Perfil usuarioAuxiliar = repositorio.buscar(usuario);
		int numSeguidores = 0;
		if(usuarioAuxiliar == null) {
			throw new PIException(usuario);
		}else if(!usuarioAuxiliar.isAtivo()) {
			throw new PDException(usuario);
		}else {
			Vector<Perfil> seguidores = usuarioAuxiliar.getSeguidores();
			for(int i = 0; i < seguidores.size(); i++) {
				if(seguidores.get(i).isAtivo()) {
					numSeguidores++;
				}
			}
		}
		return numSeguidores;
	}
	
	public Vector<Perfil> seguidores (String usuario) throws PIException, PDException {
		Perfil usuarioAuxiliar = repositorio.buscar(usuario);
		Vector<Perfil> seguidoresAtivos = new Vector<Perfil>();
		if(usuarioAuxiliar == null) {
			throw new PIException(usuario);
		}else if(!usuarioAuxiliar.isAtivo()) {
			throw new PDException(usuario);
		}else {
			Vector<Perfil> seguidores = usuarioAuxiliar.getSeguidores();
			for(int i = 0; i < seguidores.size(); i++) {
				if(seguidores.get(i).isAtivo()) {
					seguidoresAtivos.add(seguidores.get(i));
				}
			}
		}
		return seguidoresAtivos;
	}
	
	public Vector<Perfil> seguidos (String usuario) throws PIException , PDException {
		Perfil usuarioAuxiliar = repositorio.buscar(usuario);
		Vector<Perfil> seguidosAtivos = new Vector<Perfil>();
		if(usuarioAuxiliar == null) {
			throw new PIException(usuario);
		}else if(!usuarioAuxiliar.isAtivo()) {
			throw new PDException(usuario);
		}else {
			Vector<Perfil> seguidos = usuarioAuxiliar.getSeguidos();
			for(int i = 0; i < seguidos.size(); i++) {
				if(seguidos.get(i).isAtivo()) {
					seguidosAtivos.add(seguidos.get(i));
				}
			}

		}
		return seguidosAtivos;
	}
	
}
