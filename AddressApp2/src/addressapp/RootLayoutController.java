/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package addressapp;

import addressapp.models.Contact;
import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author miigu
 */
public class RootLayoutController implements Initializable {
    private AddressApp address_app;
    /**
         * Initializes the controller class.
         */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
         *      s'obte desde la clase principal
         */
    
    public void setAddressApp(AddressApp address_app){
        this.address_app = address_app;
    }
    
    /**
         * tanca l'aplicacio
         */
    
    @FXML
    public void exit(){
        System.exit(0);
    }
    
    /**
         * Es carrega l'observableList des de l'arxiu
         */
    
    @FXML 
    public void openFile(){
        File arxiu = this.mostraDialeg("open");
        if(arxiu != null){
            this.address_app.loadContactDataFromFile(arxiu);
        }
        
    }
    
    /**
         * es guarda en l'arciu l'observablelist que existeix o crida a saveAsFile()
         */
    
    @FXML 
    public void saveFile(){
        File arxiu = this.address_app.getContactFilePath();
        if(arxiu != null){
            this.address_app.saveContactDataToFile(arxiu);
        }else{
            this.saveAsFile();
        }
    }
    /**
     * Es mostra l'explorador per a posar-li nom a l'arxiu i el guarda.
     */
    
    @FXML
    public void saveAsFile(){
        File arxiu = this.mostraDialeg("save");
        
        //si s'ha obtingut el nom i ruta d'un arxiu
        if (arxiu != null){
            //Asegurem la extensio per si l'usuari ha pogut evitar el filtre
            if(!arxiu.getPath().endsWith(".txt")){
                //Creem l' arxiu en l'extensio ".txt"
                arxiu = new File(arxiu.getPath() + ".txt");
            }
            this.address_app.saveContactDataToFile(arxiu);
        }
    }
    
    /**
     * Carrega l'explorador per a poder elegir el nom o el arxiu
     */
    private File mostraDialeg(String tipus){
        File arxiu;
        //Clase per a poder seleccionar l'arxiu desde l'explorador
        
        FileChooser seleccionador = new FileChooser();
        
        //filte per a evitar que se seleccionen arxius d'extensions no permeses
        FileChooser.ExtensionFilter extensio = new FileChooser.ExtensionFilter("Archivos de texto","*.txt");
        
        //Afegim al seleccionador la restriccio de l'extensio
        seleccionador.getExtensionFilters().add(extensio);
        
        //mostrem l'explorador amb un dialog que apareixera sobre primary_stage
        if(tipus.equals("save")){
            
            //si el tipus d'arxiu es "save" obri l'explorador per a guardar
            return arxiu = seleccionador.showSaveDialog(address_app.getPrimaryStage());
            
        }else{
            //so el tipus es diferent a "save obri l'explorador per a seleccionar l'arxiu
            return arxiu = seleccionador.showOpenDialog(address_app.getPrimaryStage());
        }
    }
    @FXML
    public void sobreMi(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre mi");
        alert.setHeaderText("Informació de l'Autor");
        alert.setContentText("Miguel Ángel Gutiérrez Gómez");
        Optional<ButtonType> result = alert.showAndWait(); 
        
    }
   
    
}
