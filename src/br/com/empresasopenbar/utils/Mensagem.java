package br.com.empresasopenbar.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {
	
	public static void exibirMensagemSucesso(String mensagem) {
		/*FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		facesMessage.setSummary(mensagem);
		context.addMessage(null, facesMessage);*/
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", mensagem);
		
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public static void exibirMensagemErro(String[] mensagens) {
	
		//FacesContext context = FacesContext.getCurrentInstance();
		
		for (int i = 0; i < mensagens.length; i++) {
		    String mensagem = mensagens[i];
		
		    /*FacesMessage facesMessage = new FacesMessage();
		    facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    facesMessage.setSummary(mensagem);
		    context.addMessage(null, facesMessage);*/
		    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem);
		    
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }        
    
    public static void exibirMensagemErro(String mensagem) {
		/*FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary(mensagem);
		msg.setDetail(detalhes);
		context.addMessage(null, msg);*/
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem);
		
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public static void exibirMensagemNoTitulo(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
		
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
