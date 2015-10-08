package chapter4;

import chapter4.ex8.Company;
import chapter4.ex8.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Ex8 implements Initializable {

    @FXML
    private Company company;

    @FXML
    private Person jamesMarcus;

    private SimpleStringProperty jamesMarcusName = new SimpleStringProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jamesMarcusName.set(jamesMarcus.getName());

        jamesMarcus.nameProperty().bind(jamesMarcusName);
    }

    public static void main(String[] args) throws Exception {
        FXMLLoader loader = new FXMLLoader(Ex8.class.getResource("/ex8.fxml"));
        Company company = loader.load();
        Ex8 controller = loader.getController();
        
        company.printOrganization();

        //time passes, plot progresses...
        controller.jamesMarcusName.set("QUEEN LEECH");

        System.out.println();

        company.printOrganization();
    }



}
