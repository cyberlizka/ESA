<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Doctors List</title>
                <style>
                    body { font-family: Arial; padding: 20px; }
                    table { border-collapse: collapse; width: 100%; }
                    th, td { border: 1px solid #ccc; padding: 8px; }
                    th { background-color: #4CAF50; color: white; }
                    a { margin-right: 15px; color: #4CAF50; }
                </style>
            </head>
            <body>
                <h1>Doctors</h1>

                <div>
                    <a href="/api/doctors/xml">XML</a>
                    <a href="/api/doctors/json">JSON</a>
                    <a href="/api/shiftReports/xml">Shift Reports</a>
                </div>

                <table>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Specialty</th>
                    </tr>

                    <xsl:for-each select="doctors/doctor">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="specialty"/></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
