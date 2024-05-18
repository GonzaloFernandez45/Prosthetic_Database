package Prosthetic.db.interfaces;


public interface XmlPatientManager{
	
	public void createXml();
	public void DTDCheckerPatient();
	public void Xml2JavaPatient();
	public void Xml2Html(String sourcePath, String xsltPath,String resultDir);

}
