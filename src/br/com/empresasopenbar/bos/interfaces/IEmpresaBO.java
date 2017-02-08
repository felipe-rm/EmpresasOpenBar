package br.com.empresasopenbar.bos.interfaces;

import java.util.ArrayList;

import javax.ejb.Local;

import br.com.empresasopenbar.entidades.Empresa;

@Local
public interface IEmpresaBO {
	void incluir(Empresa empresa);
	void alterar(Empresa empresa);
	void excluir(Integer codigo);
	Empresa retornar(Integer codigo);
	ArrayList<Empresa> listar();
}