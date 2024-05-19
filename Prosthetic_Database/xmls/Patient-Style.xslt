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
                        <li><a href="#">PATIENT</a></li>
                        <li><a href="#">SURGEON</a></li>
                        <li><a href="#">COMPANY</a></li>
                        <li><a href="#">MANAGER</a></li>
                        <li><a href="#">HELP</a></li>
                    </ul>
                    <!-- End Top Menu -->

                    <div id="sidebar-a">
                        <img class="border" src="images/image02.jpg" alt="box" />
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
                        <p> LO QUE QUERAIS PONER </p>
                        
                        <img class="border" src="imagen1.jpg" alt="box" />
                        
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
                            <p class="update">LO QUE QUERAIS PONER ME CAIGO DE SUEÑO</p>
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
                         2006 Anyone |  Design by 
                        <a href="http://www.mitchinson.net">www.mitchinson.net</a>
                    </div>
                </div>
                
            </body>
        </html>
   </xsl:template>
</xsl:stylesheet>

