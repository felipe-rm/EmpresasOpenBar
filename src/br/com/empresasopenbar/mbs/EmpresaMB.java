package br.com.empresasopenbar.mbs;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.empresasopenbar.bos.interfaces.IEmpresaBO;
import br.com.empresasopenbar.entidades.Dispositivo;
import br.com.empresasopenbar.entidades.Empresa;
import br.com.empresasopenbar.utils.Mensagem;

@ManagedBean(name = "empresaMB")
@ViewScoped
public class EmpresaMB {
	@EJB
	IEmpresaBO empresaBO;
	private Empresa empresa;
	private List<Empresa> lista = new ArrayList<Empresa>();
	private Dispositivo dispositivo = new Dispositivo();
	//private Produto produto;
	//private ItemCompra itemCompra = new ItemCompra();
	
	
		
	/*public ItemCompra getItemCompra() {
		return itemCompra;
	}

	public void setItemCompra(ItemCompra itemCompra) {
		this.itemCompra = itemCompra;
	}*/

	@PostConstruct
	public void iniciarPosConstrucao()
	{
		limpar();
		listar();
		/*lstFornecedores = fornecedorBO.listar();
		lstFormasPagamento = formaPagamentoBO.listar();
		lstFuncionarios = funcionarioBO.listar();
		lstProdutos = produtoBO.listar();*/
	}
	
	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getLista() {
		return lista;
	}

	public void setLista(List<Empresa> lista) {
		this.lista = lista;
	}
	
	public void alterar() {
		empresaBO.alterar(empresa);
		listar();
		Mensagem.exibirMensagemSucesso("Registro alterado com sucesso!");
	}

	public void excluir() {
		try {
			empresaBO.excluir(empresa.getCodigo());
			listar();
			Mensagem.exibirMensagemSucesso("Registro excluído com sucesso!");
		} catch (Exception e) {
			Mensagem.exibirMensagemErro(e.getMessage().split(System.getProperty("line.separator")));
			e.printStackTrace();
		}
	}

	public void incluir() {
		empresaBO.incluir(empresa);
		listar();
		Mensagem.exibirMensagemSucesso("Registro incluído com sucesso!");
	}
	
	public void salvar(){
		if (!validarCampos())
			try {
				if ((empresa.getCodigo() == null) || (empresa.getCodigo() == 0)) {
					incluir();
				} else {
					alterar();
				}
				
				RequestContext.getCurrentInstance().update("formCadastrar");
			} catch (Exception e) {
				Mensagem.exibirMensagemErro(e.getMessage().split(System.getProperty("line.separator")));
				e.printStackTrace();
			}
	}

	public boolean validarCampos(){
		boolean erro = false;
		
		if (empresa.getRazao() == null || empresa.getRazao().length() == 0){
			erro = true;
			Mensagem.exibirMensagemErro("Informe a razão social.");
		}
		
		if (empresa.getCpfCnpj() == null || empresa.getCpfCnpj().length() == 0){
			erro = true;
			Mensagem.exibirMensagemErro("Informe o CPF/CNPJ.");
		}
		
		return erro;
	}
	
	public void adicionarItem()
	{	
		if (dispositivo.getRegistro().length() == 0)
			Mensagem.exibirMensagemErro("Informe o registro.");
		else {
			empresa.getLstDispositivos().add(dispositivo);
			dispositivo = new Dispositivo();
			limparCamposItens();
		}
	}
	
	public void removerItem()
	{
		empresa.getLstDispositivos().remove(dispositivo);
		dispositivo = new Dispositivo();
	}
	
	public void carregarItens(){
		Empresa empresaAux = empresaBO.retornar(empresa.getCodigo());
		
		if (empresaAux != null)
			empresa.setLstDispositivos(empresaAux.getLstDispositivos());
	}
	
	public void limpar() {
		empresa = new Empresa();
	}

	public void listar() {
		lista = empresaBO.listar();
	}
	
	public void limparCamposItens() {
        RequestContext.getCurrentInstance().reset("formCadastrar:tvwEmpresa:pnlRegistrosDispositivos");
        RequestContext.getCurrentInstance().update("formCadastrar:tvwEmpresa:pnlRegistrosDispositivos");
    }
}