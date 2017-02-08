package br.com.empresasopenbar.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "dispositivo")
public class Dispositivo {
	@Id
	@SequenceGenerator(name="DISPOSITIVOS_SEQUENCE", sequenceName="SQ_CADASTROS_SEQUENCE", allocationSize=1, initialValue=2)
	@GeneratedValue(generator="DISPOSITIVOS_SEQUENCE",strategy=GenerationType.SEQUENCE)
	private int codigo;
	@Column
	private String registro;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return registro;
	}	
}