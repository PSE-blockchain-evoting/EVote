package edu.kit.iti.formal.pse2018.control;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;
import edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol.SupervisorControl;
import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;

public class SupervisorControlTest {
	@Test
	public void auth() {
		View view = new View();
		Model model = new Model();
		SupervisorControl control = new SupervisorControl(view, model);
		view.setCredentials("user", "p@ss");
		assertEquals(State.AUTH_PAGE, view.getState());
		control.getFirstAuthenticationListener().actionPerformed(null);
		assertEquals(State.ALLGEMEIN, view.getState());
		view = new View();
		control = new SupervisorControl(view, model);
		view.setCredentials("Jake", "p@ss");
		control.getFirstAuthenticationListener().actionPerformed(null);
		assertEquals(State.AUTH_PAGE, view.getState());
	}

	@Test
	public void bundle() {
        Locale.setDefault(new Locale("de", "DE"));
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorControl");
	    assertEquals("Konfiguration wurde erfolgreich akzeptiert!", lang.getString("confirmedConfigSuccess"));
    }
}

enum State {
	AUTH_PAGE, ALLGEMEIN, ZEITRAUM, KANDIDATEN, WAHLER, FERTIGSTELLEN
}

class View implements SupervisorControlToViewIF {
	private State state;
	private String userName;
	private String password;

	public View() {
		this.state = State.AUTH_PAGE;
	}

	public void setCredentials(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public State getState() {
		return state;
	}

    public void showFrontpage() {
    	this.state = State.ALLGEMEIN;
    }

    public void startConfigMenu() {
    }

    public void loadConfigData() {
    }

    public String[] getVoters() {
    	return new String[]{
    		"Bob", "Ann"
    	};
    }

    public ElectionDataIF getElectionData() {
    	return null;
    }

    public String getImportPath() {
    	return "some/import/path";
    }

    public String getExportPath() {
    	return "some/export/path";
    }

    public void showConfigIssues() {
    }

    public String getPassword() {
    	return this.password;
    }

    public String getUsername() {
    	return this.userName;
    }

    public void updateResult() {
    }

    public void badAuthentication() {
    	//
    }

	@Override
	public void showResults() {

	}

	@Override
	public void exit() {

	}

	@Override
	public String getAuthenticationPath() {
		return null;
	}

	@Override
	public void showError(String message) {

	}

	@Override
	public void showWarning(String message) {

	}

	@Override
	public void showSuccess(String message) {

	}

	@Override
	public boolean electionOver() {
		return false;
	}
}

class Model implements SupervisorControlToModelIF {
	ElectionDataIF electionData;

    public void importConfig(String path) {

    }

    public void exportConfig(String path) {

    }

    public void setVoters(String[] names) {

    }

    public boolean setElectionData(ElectionDataIF electionData) {
    	this.electionData = electionData;
    	return true;
    }

    public boolean firstAuthentication(String username, String password) {
    	return username == "user" && password == "p@ss";
    }

    public String[] getVotes() {
    	return new String[] {
    		"Vote1", "Vote2"
    	};
    }

    public boolean startElection() {
    	return true;
    }

	@Override
	public boolean authenticate(String path) {
		return false;
	}

	@Override
	public ElectionDataIF getElectionData() {
		return null;
	}

	@Override
	public String getWinner() {
		return null;
	}
}