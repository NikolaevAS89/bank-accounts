<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <div class="box accounts">
            <h1>Счета</h1>
            <table id="accountTable">
                <tbody>
                    <tr>
                        <th class="th account">Счет</th>
                        <th class="th type">Тип</th>
                        <th class="th summa">Остаток</th>
                        <th class="th description">Описание</th>
                    </tr>
                    <xsl:for-each select="accounts/account">
                        <tr>
                            <td>
                                <button class="bttn in_cell" onclick="chooseAccount(this);">
                                <xsl:value-of select="id"/>
                                </button>
                            </td>
                            <td>
                                <xsl:value-of select="type"/>
                            </td>
                            <td>
                                <xsl:value-of select="amount"/>
                            </td>
                            <td>
                                <xsl:value-of select="description"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </tbody>
            </table>
        </div>
    </xsl:template>
</xsl:stylesheet>