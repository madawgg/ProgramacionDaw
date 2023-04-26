
package addressapp;

import addressapp.models.Contact;
import static com.sun.javafx.animation.TickCalculation.add;
import static com.sun.javafx.scene.transform.TransformHelper.add;
import static java.awt.AWTEventMulticaster.add;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ContactEditDialogController implements Initializable {
    //variables i objectes
    private Stage dialogStage;
    private boolean okClicked = false;
    private Contact contacte;
    
    
    @FXML 
    private TextField cNom;
    @FXML
    private TextField cCognoms;
    @FXML
    private TextField cDomicili;
    @FXML
    private TextField cCiutat;
    @FXML
    private TextField cCodiPostal;
    @FXML
    private TextField cDataDeNaixement;
    
    //constructor
    public ContactEditDialogController() {
        dialogStage = new Stage();
        contacte = new Contact();
    }

    //getters per a recuperar String introduit en el textfield
    public TextField getcNom() {
        return cNom;
    }

    public TextField getcCognoms() {
        return cCognoms;
    }

    public TextField getcDomicili() {
        return cDomicili;
    }

    public TextField getcCiutat() {
        return cCiutat;
    }

    public TextField getcCodiPostal() {
        return cCodiPostal;
    }

    public TextField getcDataDeNaixement() {
        return cDataDeNaixement;
    }
    
    public void setDialogStage(Stage stage){
        if(stage != null)
            dialogStage = stage;
    }
    public boolean getOKClicked(){
        return this.okClicked;
    }
    
 
    
    public void loadContacte(Contact contacte) {
    this.contacte = contacte; 
    cNom.setText(contacte.getNom().get());
    cCognoms.setText(contacte.getCognoms().get());
    cDomicili.setText(contacte.getDomicili().get());
    cCiutat.setText(contacte.getCiutat().get());
    cCodiPostal.setText(Integer.toString(contacte.getCodi_postal().get()));
    cDataDeNaixement.setText(DateUtil.format(contacte.getData_de_naixement().get())); 
}
    @FXML
    private void cancel(){
        dialogStage.close();
    }
    @FXML
    private void ok(){
        
        if(areFormInputsValid()){
            contacte.setNom(new SimpleStringProperty(cNom.getText()));
            contacte.setCognoms(new SimpleStringProperty(cCognoms.getText()));
            contacte.setDomicili(new SimpleStringProperty(cDomicili.getText()));
            contacte.setCiutat(new SimpleStringProperty(cCiutat.getText()));
            contacte.setCodi_postal(new SimpleIntegerProperty(Integer.parseInt(cCodiPostal.getText())));
            contacte.setData_de_naixement(new SimpleObjectProperty<>(DateUtil.parse(cDataDeNaixement.getText())));
            okClicked = true;
            cancel();
            
        }
        
    }
    
    public boolean areFormInputsValid() {
    String errorMessage =  "";
    System.out.println("Valor de cNom: " + cNom.getText());
    System.out.println("Valor de ccog: " + cCognoms.getText());
    System.out.println("Valor de cdom: " + cDomicili.getText());
    System.out.println("Valor de cciu: " + cCiutat.getText());
    System.out.println("Valor de ccp: " + cCodiPostal.getText());
    System.out.println("Valor de cdn: " + cDataDeNaixement.getText());
    if (cNom.getText().equals("")  || !cNom.getText().matches("[a-zA-Z áàéèíóòúÁÀÉÈÍÓÒÚÑñ]+")) {
        errorMessage += "Nom invàlid. \n"; 
       
    }
    if (cCognoms.getText().equals("")|| !cCognoms.getText().matches("[a-zA-Z áàéèíóòúÁÀÉÈÍÓÒÚÑñ]+")) {
        errorMessage+= "Cognoms invàlids. \n"; 
    }
    if (cDomicili.getText().equals("") || !cDomicili.getText().matches("[a-zA-Z áàéèíóòúÁÀÉÈÍÓÒÚÑñ/]+"))  {
        errorMessage += "Domicili invàlid. \n"; 
        
    }
    if (cCiutat.getText().equals("") || !cCiutat.getText().matches("[a-zA-Z áàéèíóòúÁÀÉÈÍÓÒÚÑñ]+")) {
        errorMessage += "Ciutat invàlida. \n"; 
       
    }

    // validació del codi postal
    if (cCodiPostal.getText().isEmpty() || cCodiPostal.equals(-1)) {
        errorMessage += "Codi postal invàlid.\n";
      
    } else {
        try {
            Integer.parseInt(cCodiPostal.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Codi postal ha de ser un número enter. \n";
            
        }
    }

    // validacio de la data de naixement
    if (cDataDeNaixement.getText().equals(" ") || cDataDeNaixement.getText() == null ) {
        errorMessage += "Data de naixement invàlida. \n";
    
    } else {
        if (!DateUtil.validDate(cDataDeNaixement.getText())) {
            errorMessage += "Format de data de naixement invàlid. \n Utilitza el format dd.mm.aaaa \n";
        }
    }

    // mostrar mensaje de error si hay algún error
    if (!errorMessage.isEmpty()) {
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Errors en els camps del formulari");
        alert.setContentText(errorMessage);
        alert.showAndWait();
        return false;
    }
   
    return true;
}

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    
}
