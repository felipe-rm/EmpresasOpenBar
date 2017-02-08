package br.com.empresasopenbar.bos;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.empresasopenbar.bos.interfaces.IEmpresaBO;
import br.com.empresasopenbar.daos.interfaces.IEmpresaDAO;
import br.com.empresasopenbar.entidades.Empresa;

@Stateless
@LocalBean
public class EmpresaBO implements IEmpresaBO {	
	@EJB
	IEmpresaDAO empresaDAO;
	
	public void incluir(Empresa empresa) {
		empresaDAO.incluir(empresa);
	}
		
	public void alterar(Empresa empresa) {
		empresaDAO.alterar(empresa);
	}

	public void excluir(Integer codigo) {
		empresaDAO.excluir(codigo);
	}

	public Empresa retornar(Integer codigo) {
		return empresaDAO.retornar(codigo);
	}
		
	public ArrayList<Empresa> listar() {
		return empresaDAO.listar();
	}
}