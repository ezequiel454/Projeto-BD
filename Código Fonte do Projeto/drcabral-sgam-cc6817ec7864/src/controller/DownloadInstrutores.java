package controller;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean
public class DownloadInstrutores {
     
    private StreamedContent file;
     
    public DownloadInstrutores() {       
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/relatorios/InstrutorPorSalario.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "InstrutorPorSalario.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}