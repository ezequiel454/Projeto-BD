package controller;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean
public class DownloadModalidades {
     
    private StreamedContent file;
     
    public DownloadModalidades() {       
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/relatorios/ModalidadePorMensalidade.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "ModalidadePorMensalidade.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}