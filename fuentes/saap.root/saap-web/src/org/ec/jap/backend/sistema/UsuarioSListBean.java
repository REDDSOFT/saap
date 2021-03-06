/**
 * 
 */
package org.ec.jap.backend.sistema;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.ec.jap.backend.pagina.Bean;
import org.ec.jap.backend.utilitario.Mensaje;
import org.ec.jap.bo.saap.UsuarioBO;
import org.ec.jap.bo.sistema.FiltroBO;
import org.ec.jap.entiti.saap.Usuario;
import org.ec.jap.entiti.sistema.Filtro;

/**
 * @author Freddy G Castillo C
 * 
 */
@ManagedBean
@ViewScoped
public class UsuarioSListBean extends Bean {

	/**
	 * 
	 */
	@EJB
	UsuarioBO usuarioBO;

	@EJB
	FiltroBO filtroBO;

	Filtro filtro;

	private List<Usuario> listUsuarios = new ArrayList<>();

	public UsuarioSListBean() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			super.init();
			search(null);
		} catch (Exception e) {
			e.printStackTrace();
			displayMessage(e.getMessage(), Mensaje.SEVERITY_ERROR);
		}
	}

	@Override
	public void search(ActionEvent event) {
		try {
			filtro = filtroBO.getFiltro(getUsuarioCurrent(), "FILTRO", getPage(), filtro != null ? filtro.getValorCadena() : "",filtro,false);
			filtroBO.getFiltro(getUsuarioCurrent(), "TIPO_USUARIO", getPage(), getParam20String());
			map = new HashMap<>();
			map.put("filtro", filtro.getValorCadena() != null ? filtro.getValorCadena() : "");
			map.put("tipoUsuario", getParam20String());
			listUsuarios = usuarioBO.findAllByNamedQuery("Usuario.findByCedNom", map);
			setNombreArchivo("Usuarios_Sistema" + Calendar.getInstance().get(Calendar.YEAR));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeName(AjaxBehaviorEvent event) {
		try {
			filtroBO.update(getUsuarioCurrent(), filtro);
			map.put("filtro", filtro.getValorCadena() != null ? filtro.getValorCadena() : "");
			map.put("tipoUsuario", getParam20String());
			listUsuarios = usuarioBO.findAllByNamedQuery("Usuario.findByCedNom", map);
		} catch (Exception e) {
			e.printStackTrace();
			displayMessage(e.getMessage(), Mensaje.SEVERITY_ERROR);
		}
	}

	@Override
	public String insert() {
		setAccion("INS");
		return "usuarioSEdit?faces-redirect=true";
	}

	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

}
