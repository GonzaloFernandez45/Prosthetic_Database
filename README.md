# Prosthetic_Database
This is a DataBase in Eclipse that uses:
- Java
- SQLite-JDBC 

It´s designed to help collecting the relevant information about prosthetics and their related surgeries. This Database works for patients, surgeons and companies to build a bridge between them in a database that allows every of this users to access the medical information.
The project source code (src folder) is structured as follows:
- The Prosthetic.db.pojos package contains ten POJOs that represent the managing of companies, materials, needs, options, patients, prosthetics, roles, surgeons, surgeries and users.
- The Prosthetic.db.interfaces package contains the methos that then will be implemented by the JDBC: CompanyManager, MaterialManager, NeedManager, OptionManager, PatientManager, ProstheticManager, SurgeonManager, SurgeryManager and UserManager.
- The Prosthetic.db.JDBC package as mention before implements the methods define in the interfaces: JDBCCompanyManager, JDBCMaterialManager, JDBCNeedManager, JDBCOptionManager, JDBCPatientManager, JDBCProstheticManager, JDBCSurgeonManager and JDBCSurgeryManager.

For JDBC 

