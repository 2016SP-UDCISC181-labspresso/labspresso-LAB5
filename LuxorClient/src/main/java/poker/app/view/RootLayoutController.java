package poker.app.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import poker.app.MainApp;
import pokerBase.Card;
import pokerEnums.eGame;
import pokerEnums.eRank;
import pokerEnums.eSuit;


/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController implements Initializable {

	// Reference to the main application
	private MainApp mainApp;

	@FXML
	private MenuBar mb;

	@FXML
	private Menu mnuGame;


	public String getRuleName()
	{	
		String strRuleName = null;
		for (Menu m: mb.getMenus())
		{
			if (m.getText() == "Pick Game")
			{
				for (MenuItem mi: m.getItems())
				{
					if (mi.getClass().equals(RadioMenuItem.class))
					{
						RadioMenuItem rmi = (RadioMenuItem)mi;
						if (rmi.isSelected() == true)
						{
							strRuleName = rmi.getText();
							break;
						}
					}
				}
			}
		}
		
		return strRuleName;
	}
	
	public void initialize(URL location, ResourceBundle resources) {

		BuildMenus();
	}
	
	public void BuildMenus()
	{
		Menu mnuGame = new Menu();
		mnuGame.setText("Pick Game");
		mb.getMenus().add(0,mnuGame);
				
		ToggleGroup tglGrpGame = new ToggleGroup();
		
		for (eGame eGame : eGame.values()) {
			RadioMenuItem rmi = new RadioMenuItem(eGame.toString());
			rmi.setId("PokerGame" + String.valueOf(eGame.getGame()));
			rmi.setToggleGroup(tglGrpGame);
			if (eGame.getDefault())
			{
				rmi.setSelected(true);
			}
			mnuGame.getItems().add(rmi);
		}
		
		// 	DONEDONEDONE
		//	TODO - Lab #5...  Add a new menu item that will display the betting rules...
		//	Two choices:
		//	x No Limit (set this as default)
		//	x Pot Limit (this is NOT the default)
		//	x Group them together with a Toggle Group
		//	x Write a method to return which is selected.. .check out getRuleName()

		ToggleGroup tglGrpPot = new ToggleGroup();
		Menu mnuPotLimit = new Menu();
		mnuPotLimit.setText("Betting Rules");
		mb.getMenus().add(1,mnuPotLimit);		
		
		RadioMenuItem rmi_nolimit = new RadioMenuItem("No Limit...");
		rmi_nolimit.setId("PokerGame NoLimit");
		rmi_nolimit.setToggleGroup(tglGrpPot);
		rmi_nolimit.setSelected(true);
		mnuPotLimit.getItems().add(0,rmi_nolimit);
		
		
		RadioMenuItem rmi_potlimit = new RadioMenuItem("Pot Limit...");
		rmi_potlimit.setId("PokerGame PotLimit");
		rmi_potlimit.setToggleGroup(tglGrpPot);
		mnuPotLimit.getItems().add(1,rmi_potlimit);
		
	}
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}


	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AddressApp");
		alert.setHeaderText("About");
		alert.setContentText("Author: Bert Gibbons");

		alert.showAndWait();
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}



	

}