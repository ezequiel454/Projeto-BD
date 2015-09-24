package controller;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean
public class DownloadAlunoModalidade {
     
    private StreamedContent file;
     
    public DownloadAlunoModalidade() {       
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/relatorios/AlunoPorModalidade.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "AlunoPorModalidade.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}