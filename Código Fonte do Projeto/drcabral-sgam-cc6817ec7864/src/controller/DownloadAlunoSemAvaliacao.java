package controller;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class DownloadAlunoSemAvaliacao {
    private StreamedContent file;
    
    public DownloadAlunoSemAvaliacao() {       
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/relatorios/AlunoSemAvaliacao.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "AlunoSemAvaliacao.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}
