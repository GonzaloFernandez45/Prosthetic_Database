# Prosthetic_Database
This is a DataBase in Eclipse that uses:
- Java
- SQLite-JDBC
- Eclipselink

It´s designed to help collecting the relevant information about prosthetics and their related surgeries. This Database works for patients, surgeons and companies to build a bridge between them in a database that allows every of this users to access the medical information.
The project source code (src folder) is structured as follows:
- The Prosthetic.db.pojos package contains ten POJOs that represent the managing of companies, materials, needs, options, patients, prosthetics, roles, surgeons, surgeries and users.
- The Prosthetic.db.interfaces package contains the methos that then will be implemented by the JDBC: CompanyManager, MaterialManager, NeedManager, OptionManager, PatientManager, ProstheticManager, SurgeonManager, SurgeryManager and UserManager.
- The Prosthetic.db.diagrams folder contains the diagrams of this database that are used to strcuture it. They are the E-R diagram, the UML, the use case diagram and the MOCK-UP. 
- The Prosthetic.db.JDBC package as mention before implements the methods define in the interfaces: JDBCCompanyManager, JDBCMaterialManager, JDBCNeedManager, JDBCOptionManager, JDBCPatientManager, JDBCProstheticManager, JDBCSurgeonManager and JDBCSurgeryManager.

For JDBC we also implement a ConnectionManager class where the connection between the Database and the managers will be made. Here the tables will also be created. 

- The Prosthetic.db.jpa contains only one class called JPAUserManager that implements the methods define in UserManager interface. We must also implement an EntityManager to make the connection, this would be as ConnectionManager for JDBC but now for JPA.
- The META-INF folder contains the persistence.xml file that configures the persistance provider. In this case our persistence provider is "prosthetic-provider".

The database this project manages is a file called Prosthetic.db inside the db folder. This database can be created by running the ConnectionManager class as mention before. Then to open it we go to DBBrowser and click in "open a dataBase" and selecting the Prosthetic.db file. 


- The Prosthetic.ui package contains a class named Menu where the interaction between the user and the database takes place. Here the methods declare in the MOCK-UP are defined.

 The lib folder contains the SQLite-JDBC, JPA and Eclipselink libraries.
