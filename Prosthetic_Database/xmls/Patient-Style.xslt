<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" />
<xsl:template match="/">
        <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
                <title>PROSTHETIC DATA BASE</title>
                <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
                <link href="moderna.css" rel="stylesheet" type="text/css" />
            </head>
            <body>
                <div id="container">
                    <div id="banner">
                        <a href="index.html">homepage</a> | 
                        <a href="mailto:prostheticDBinfo@gmail.com">contact</a> | 
                        <h1>www.prostheticDB.com</h1>
                        
                    </div>

                    <!-- Begin Top Menu -->
                    <ul id="navlist">
                        <li id="active"><a id="current" href="#">HOMEPAGE</a></li>
                        <li><a href="#">GET YOUR INFORMATION</a></li>
                        <li><a href="#">INPUT OPTION</a></li>
                        <li><a href="#">REPORT DELIVERY OF PROSTHETIC</a></li>
                        <li><a href="#">HELP</a></li>
                    </ul>
                    <!-- End Top Menu -->

                    <div id="sidebar-a">
                         <img class="border" src="images/Users.jpg" alt="box" width="100" height="100" />
                        <h2>USERS</h2>
                        <div class="menu">
                            <ul>
								<li><a> PATIENT</a></li>
                                <li><a>SURGEON </a></li>
                                <li><a>COMPANY </a></li>
                                 <li><a>MANAGER </a></li>
                                
                            </ul>
                        </div>
                        <p></p>
                    </div>

                    <div id="sidebar-b">
                        <h3>PROSTHETIC DB</h3>
                        <p> BEING AN AMPUTEE HAS NEVER BEEN THIS EASY. </p>
                         <p> YOUR HEALTH IS THE PRIORITY </p>
                        
                        <img class="border" src="images/ProsIm.webp" alt="box" width="100" height="100" />
                        
                    </div>

                    <div id="content">
                        <h2>Patient Information</h2>
                        <h3>Patient Name: <xsl:value-of select="//Patient/@name"/></h3> 
                         <h3>Patient Surname: <xsl:value-of select="//Patient/@surname" /></h3>
                         <h3>Sex: <xsl:value-of select="//Patient/@sex" /> </h3>
                         <h3>Date of Birth: <xsl:value-of select="//dob" /> </h3>
                         <h3>DNI: <xsl:value-of select="//@dni" /> </h3>
                         <div class="intro">
                        </div>
                        </div>
                        <div class="intro3">
                            <h4>News  Updates</h4>
                         	<li><p><a hhref="https://www.redaccionmedica.com/secciones/tecnologia/protesis-de-rodilla-y-cadera-sin-cemento-para-acelerar-la-recuperacion-6252"> New knee and hit prosthetics </a></p></li>
                         	<li><p><a href="https://www.redaccionmedica.com/secciones/sanidad-hoy/protesis-y-estimulacion-neuronal-para-que-amputados-vuelvan-a-sentir-calor-7011"> Prosthetic with neuronal stimulation </a></p></li>
                         	<li><p><a href="https://www.redaccionmedica.com/autonomias/madrid/hospital-clinico-san-carlos-madrid-pionero-protesis-rodilla-cirugia-robotica-4117"> Pioneers in knee with robotic surgery </a></p></li>
                        </div>

                       
                        
                        
			<div id="content">
                        <h2>Prosthetics</h2>
                        <table border="1">
                            <tr>
                                <th>Size</th>
                                <th>Report</th>
                                <th>Price</th>
                                <th>Need Type</th>
                            </tr>
                            <xsl:for-each select="//Prosthetic">
                                <tr>
                                    <td><xsl:value-of select="@size" /></td>
                                    <td><xsl:value-of select="@report" /></td>
                                    <td><xsl:value-of select="@price" /></td>
                                    <td><xsl:value-of select="need/@type" /></td>
                                </tr>
                            </xsl:for-each>
                        </table>
                         </div>

				<div id="content">
                        <h2>Needs</h2>
                        <ul>
                            <xsl:for-each select="//Need">
                                <li><xsl:value-of select="@type" /></li>
                            </xsl:for-each>
                        </ul>
                    </div>

                    <div id="footer">
                        <a href="index.html">homepage</a> | 
                        <a href="mailto:denise@mitchinson.net">contact</a> | 
                        <a href="http://validator.w3.org/check?uri=referer">xhtml</a> | 
                        <a href="http://jigsaw.w3.org/css-validator">css</a> |  
                         2024 Anyone |  Design by 
                        <a href="http://www.mitchinson.net">www.prostheticDB.net</a>
                    </div>
                </div>
                
            </body>
        </html>
   </xsl:template>
</xsl:stylesheet>