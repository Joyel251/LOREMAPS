<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:template match="/">
        <html>
        <head>
            <title>LoreMaps - Interactive World Regions</title>
            <style>
                body {
                    background-color: #f0f4f8;
                    color: #2c3e50;
                    font-family: Arial, sans-serif;
                    padding: 20px;
                }
                h1 {
                    text-align: center;
                    color: #4a7ba7;
                    text-shadow: 0 1px 2px rgba(74,123,167,0.2);
                }
                .container {
                    max-width: 1200px;
                    margin: 0 auto;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    background: white;
                    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                    margin-top: 20px;
                }
                th {
                    background-color: #4a7ba7;
                    color: white;
                    padding: 15px;
                    text-align: left;
                    font-weight: bold;
                }
                td {
                    padding: 12px 15px;
                    border-bottom: 1px solid #e0e0e0;
                }
                tr:hover {
                    background-color: #f5f9fc;
                }
                .region-card {
                    background: white;
                    padding: 20px;
                    margin: 15px 0;
                    border-radius: 8px;
                    border-left: 4px solid #4a7ba7;
                    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                }
                .region-header {
                    font-size: 24px;
                    color: #4a7ba7;
                    margin-bottom: 10px;
                }
                .region-id {
                    color: #7fb3d5;
                    font-weight: bold;
                }
                .region-type {
                    display: inline-block;
                    background: #c8dff0;
                    padding: 5px 15px;
                    border-radius: 20px;
                    font-size: 12px;
                    margin: 5px 0;
                }
                .level-beginner { color: #27ae60; font-weight: bold; }
                .level-intermediate { color: #f39c12; font-weight: bold; }
                .level-advanced { color: #e74c3c; font-weight: bold; }
            </style>
        </head>
        <body>
            <div class="container">
                <h1>🗺️ LoreMaps - Explore Interactive World Regions</h1>
                
                <h2 style="color: #4a7ba7; border-bottom: 2px solid #b8d4e8; padding-bottom: 10px;">Region List (Table View)</h2>
                <table>
                    <tr>
                        <th>Region ID</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Coordinates</th>
                        <th>Landmarks</th>
                        <th>Level</th>
                    </tr>
                    <xsl:for-each select="loremaps/mapregion">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><strong><xsl:value-of select="name"/></strong></td>
                            <td><xsl:value-of select="type"/></td>
                            <td><xsl:value-of select="coordinates/latitude"/>, <xsl:value-of select="coordinates/longitude"/></td>
                            <td><xsl:value-of select="landmarks"/></td>
                            <td>
                                <xsl:attribute name="class">
                                    <xsl:choose>
                                        <xsl:when test="explorationlevel='Beginner'">level-beginner</xsl:when>
                                        <xsl:when test="explorationlevel='Intermediate'">level-intermediate</xsl:when>
                                        <xsl:when test="explorationlevel='Advanced'">level-advanced</xsl:when>
                                    </xsl:choose>
                                </xsl:attribute>
                                <xsl:value-of select="explorationlevel"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
                
                <h2 style="color: #4a7ba7; border-bottom: 2px solid #b8d4e8; padding-bottom: 10px; margin-top: 40px;">Detailed Region Information (Card View)</h2>
                <xsl:for-each select="loremaps/mapregion">
                    <div class="region-card">
                        <div class="region-header">
                            <xsl:value-of select="name"/>
                            <span class="region-id"> (<xsl:value-of select="id"/>)</span>
                        </div>
                        <span class="region-type"><xsl:value-of select="type"/> Region</span>
                        <p style="margin: 10px 0; color: #555;"><xsl:value-of select="description"/></p>
                        <p><strong>📍 Coordinates:</strong> Lat: <xsl:value-of select="coordinates/latitude"/>, Long: <xsl:value-of select="coordinates/longitude"/></p>
                        <p><strong>🏛️ Landmarks:</strong> <xsl:value-of select="landmarks"/> points of interest</p>
                        <p><strong>🎯 Exploration Level:</strong> 
                            <span>
                                <xsl:attribute name="class">
                                    <xsl:choose>
                                        <xsl:when test="explorationlevel='Beginner'">level-beginner</xsl:when>
                                        <xsl:when test="explorationlevel='Intermediate'">level-intermediate</xsl:when>
                                        <xsl:when test="explorationlevel='Advanced'">level-advanced</xsl:when>
                                    </xsl:choose>
                                </xsl:attribute>
                                <xsl:value-of select="explorationlevel"/>
                            </span>
                        </p>
                    </div>
                </xsl:for-each>
            </div>
        </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>
