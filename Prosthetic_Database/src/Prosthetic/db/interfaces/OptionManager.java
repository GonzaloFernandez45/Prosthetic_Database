package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface OptionManager {
	public void addOption(Option o);
	public Option getOption (int id);
	public List<Option> listOptions();
	public void insertFulfill(Option o, Prosthetic p);
	public List<Option> listOptionsOfProsthetic(int Prosthetic_ID);
	public Option getOptionByType(String type);
}
