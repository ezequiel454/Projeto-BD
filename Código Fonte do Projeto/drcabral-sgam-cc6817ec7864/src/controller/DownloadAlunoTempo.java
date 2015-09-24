package controller;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean
public class DownloadAlunoTempo {
     
    private StreamedContent file;
     
    public DownloadAlunoTempo() {       
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/relatorios/AlunoPorTempo.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "AlunoPorTempo.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}