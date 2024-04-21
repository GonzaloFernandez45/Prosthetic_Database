package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface OptionManager {
	public void addOption(Option o);
	public void deleteOption(Option o);
	public Option getOptionByType (String type);
	public Option getOption (int id);

}
