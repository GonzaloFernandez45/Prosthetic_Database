package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface OptionManager {
	public void addOption(Option o);
	//public Option getOptionByType (String type);
	public Option getOption (int id);
	public List<Option> listOptions();
	
}
