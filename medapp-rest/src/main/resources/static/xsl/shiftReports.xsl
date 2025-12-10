<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Shift Reports</title>
                <style>
                    body { font-family: Arial; padding: 20px; }
                    table { border-collapse: collapse; width: 100%; }
                    th, td { border: 1px solid #ccc; padding: 8px; }
                    th { background-color: #2196F3; color: white; }
                    a { margin-right: 15px; color: #2196F3; }
                </style>
            </head>
            <body>
                <h1>Shift Reports</h1>

                <div>
                    <a href="/api/shiftReports/xml">XML</a>
                    <a href="/api/shiftReports/json">JSON</a>
                    <a href="/api/doctors/xml">Doctors</a>
                </div>

                <table>
                    <tr>
                        <th>ID</th>
                        <th>Doctor</th>
                        <th>Date</th>
                        <th>Notes</th>
                    </tr>

                    <xsl:for-each select="shiftReports/shiftReport">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="doctor/name"/></td>
                            <td><xsl:value-of select="reportDate"/></td>
                            <td><xsl:value-of select="notes"/></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
