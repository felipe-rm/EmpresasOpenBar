package br.com.empresasopenbar.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa {
	@Id
	@SequenceGenerator(name="EMPRESAS_SEQUENCE", sequenceName="SQ_CADASTROS_SEQUENCE", allocationSize=1, initialValue=2)
	@GeneratedValue(generator="EMPRESAS_SEQUENCE",strategy=GenerationType.SEQUENCE)
	private Integer codigo;
	@Column(length=40)
	private String razao;
	@Column(length=15)
	String ie = "";
	@Column(length=18)
	String cpfCnpj = "";
	@Column(length=60)
	private String endereco = "";
	@Column(length=60)
	private String bairro = "";
	@Column(length=60)
	private String cidade = "";
	@Column(length=2)
	private String uf = "";
	@Column(length=15)
	private String telefone = "";
	@Column(length=15)
	private String celular = "";
	@Column(length=60)
	private String email = "";
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="empresa_dispositivos", 
    		   joinColumns={@JoinColumn(name="codempresa", referencedColumnName="codigo")}, 
    		   inverseJoinColumns={@JoinColumn(name="codDispositivo", referencedColumnName="codigo")})
	private List<Dispositivo> lstDispositivos;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Dispositivo> getLstDispositivos() {
		return lstDispositivos;
	}

	public void setLstDispositivos(List<Dispositivo> lstDispositivos) {
		this.lstDispositivos = lstDispositivos;
	}
	
	public Empresa() {
		lstDispositivos = new ArrayList<Dispositivo>();
	}
	
	@Override
	public String toString() {
		return razao;
	}
}