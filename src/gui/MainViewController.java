package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import javafx.scene.control.ScrollPane;


public class MainViewController implements Initializable{
    
    @FXML
    private MenuItem menuItemSeller;

    @FXML
    private MenuItem menuItemDepartment;

    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("onMenuItemSellerAction");
    }

    @FXML
    public void onMenuItemDepartmentAction() {
        loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
            controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();
        });
    }

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/About.fxml", x -> {});
    }


    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        // TODO Auto-generated method stub
        
    }

    // syncronized garante que o processamento dessa função não seja interrompido
    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();

            Scene mainScene = Main.getMainScene(); // chama a MainView

            // referencia o VBox da MainView:
            // getRoot() pega o primeiro elemento da View, que é o ScrollPane no caso
            // (ScrollPane) precisa fazer o casting do getRoot para ScrollPane, importar ScrollPane manualmente
            // getContent pega o <content> dentro do ScrollPane
            // (VBox) precisa fazer o casting para colocar na variável
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); 

            // pega o primeiro filho do mainVBox (posição 0), no caso o MenuBar
            Node mainMenu = mainVBox.getChildren().get(0);

            mainVBox.getChildren().clear(); // limpa todos os filhos do mainVBox 

            mainVBox.getChildren().add(mainMenu); // adiciona o mainMenu (<MenuBar>) no mainVBox

            // adiciona os filhos do newVBox, parâmetro que virá na função loadView()
            mainVBox.getChildren().addAll(newVBox.getChildren()); 

            // Executar a função recebida como parâmetro
            // T (Generics), parâmetro recebido definirá o tipo da variável
            // DepartmentListController no caso do MenuItemDepartment
            T controller = loader.getController();
            initializingAction.accept(controller);

        }
        catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
        }
    }
    
}
