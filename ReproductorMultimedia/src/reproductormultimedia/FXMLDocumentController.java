package reproductormultimedia;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 *
 * @author jgr97
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button;

    @FXML
    private MediaView mediaView;

    /**
     * Ruta del archivo a buscar
     */
    private String RutaArchivo;

    /**
     * Clase que nos permite reproducir archivos multimedia
     */
    private MediaPlayer reproductor;
    
    /**
     * Variable booleana para no tener la necesidad hacer un boton de pausa
     */
    private boolean play = true;

    @FXML
    private void buscarArchivo(ActionEvent event) {
        FileChooser chooser = new FileChooser();

        /**
         * Extensiones permitidas por el filtro
         */
        List<String> lista_extensiones = new ArrayList<>();
        lista_extensiones.add("*.mp4");
        lista_extensiones.add("*.mp3");
        lista_extensiones.add("*.wma");
        lista_extensiones.add("*.ogg");
        lista_extensiones.add("*.ogv");
        lista_extensiones.add("*.gif");
        lista_extensiones.add("*.flv");
        lista_extensiones.add("*.avi");
        lista_extensiones.add("*.wmv");
        lista_extensiones.add("*.wav");
        lista_extensiones.add("*.mpeg");
        lista_extensiones.add("*.jpg");
        lista_extensiones.add("*.png");

        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Selecciona un archivo", lista_extensiones);

        /**
         * Se le añaden los filtros
         */
        chooser.getExtensionFilters().add(filtro);

        File archivo = chooser.showOpenDialog(null);

        RutaArchivo = archivo.toURI().toString();

        if (RutaArchivo != null) {
            Media media = new Media(RutaArchivo);
            reproductor = new MediaPlayer(media);
            mediaView.setMediaPlayer(reproductor);

            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty hight = mediaView.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            hight.bind(Bindings.selectDouble(mediaView.sceneProperty(), "hight"));
        }
    }
    
    @FXML
    private void play(ActionEvent event){
        
        reproductor.setRate(1);
        
        if(play){
            reproductor.play();
            play=false;
        }
        else{
            reproductor.pause();
            play=true;
        }
    }
    
    @FXML
    private void stop(ActionEvent event){
        reproductor.stop();
    }
    
    @FXML
    private void rapido(ActionEvent event){
        reproductor.setRate(1.5);
    }
    
    @FXML
    private void masRapido(ActionEvent event){
        reproductor.setRate(2);
    }
    
    @FXML
    private void lento(ActionEvent event){
        reproductor.setRate(0.75);
    }
    
    @FXML
    private void masLento(ActionEvent event){
        reproductor.setRate(0.5);
    }
    
    @FXML
    private void exit(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
