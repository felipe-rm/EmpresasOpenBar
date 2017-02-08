package br.com.empresasopenbar.daos.interfaces;

import java.util.ArrayList;
import javax.ejb.Local;
import br.com.empresasopenbar.entidades.Empresa;

@Local
public interface IEmpresaDAO {
	void incluir(Empresa empresa);
	void alterar(Empresa empresa);
	void excluir(Integer codigo);
	Empresa retornar(Integer codigo);
	ArrayList<Empresa> listar();
}