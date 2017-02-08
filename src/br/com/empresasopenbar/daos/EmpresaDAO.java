package br.com.empresasopenbar.daos;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.empresasopenbar.daos.interfaces.IEmpresaDAO;
import br.com.empresasopenbar.entidades.Empresa;


@LocalBean
@Stateless
public class EmpresaDAO implements IEmpresaDAO {
	@PersistenceContext
	protected EntityManager em;
	
	@Transactional
	public void incluir(Empresa empresa) {
		em.persist(empresa);
	}
		
	@Transactional
	public void alterar(Empresa empresa) {
		em.merge(empresa);
		em.flush();
	}

	@Transactional
	public void excluir(Integer codigo) {
		em.remove(retornar(codigo));
	}

	@Transactional
	public Empresa retornar(Integer codigo) {
		return em.find(Empresa.class, codigo);
	}
		
	@SuppressWarnings("unchecked")
	@Transactional
	public ArrayList<Empresa> listar() {
		Query q = em.createQuery("from " + Empresa.class.getName() + " e order by razao");
		
		return (ArrayList<Empresa>) q.getResultList();
	}
}