package controller;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
 
@ManagedBean
public class DownloadGastos {
     
    private StreamedContent file;
     
    public DownloadGastos() {       
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/relatorios/DespesaPorTempo.pdf");
        file = new DefaultStreamedContent(stream, "application/pdf", "DespesaPorTempo.pdf");
    }
 
    public StreamedContent getFile() {
        return file;
    }
}