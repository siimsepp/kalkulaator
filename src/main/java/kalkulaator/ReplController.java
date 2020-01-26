package kalkulaator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class ReplController {
    
    @FXML
    private TextField sisend = new TextField();
    @FXML
    private Button nupp = new Button();
    @FXML
    private Label tulemus;
    
    Valemid valemid = new Valemid();
    
    @FXML
    private void processUserInput(ActionEvent event) throws ScriptException {
        ReplModel model = new ReplModel();
        String sisendTekst = "";
        if (!sisend.getText().isEmpty()) {
            sisendTekst = sisend.getText();
        }
//        Nende sümbolitega võib sisestus alata, et ta läheks valemina hindamiseks.
        List<Character> arvSymbolid = new ArrayList<>(List.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '(', ')'));
        if (arvSymbolid.contains(sisendTekst.charAt(0))) {
            tulemus.setText(model.evalFormula(sisendTekst));
        } else {
            tulemus.setText(model.evalString(sisendTekst));
        }
    }
    
    @FXML
    public void processAC(ActionEvent event) {
        sisend.clear();
        tulemus.setText("");
    }
    
    
    @FXML
    public void processPrime(ActionEvent event) {
        
        try {
            long num = Long.parseLong(sisend.getText());
            boolean algarv = valemid.kasAlgarv(num);
            if (algarv) {
                tulemus.setText(String.valueOf(num) + " on algarv");
            } else {
                tulemus.setText(String.valueOf(num) + " ei ole algarv");
            }
        } catch (Exception e) {
            tulemus.setText("Sisesta täisarv");
        }
    }

//    2*(4+5)
    
    
}
