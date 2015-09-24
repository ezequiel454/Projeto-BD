package controller;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean
public class DownloadAlunoComAvaliacao {
     
    private StreamedContent file;
     
    public DownloadAlunoComAvaliacao() {       
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/relatorios/AlunoComAvaliacao.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "AlunoComAvaliacao.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}