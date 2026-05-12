// This is Groovy Flowstep Version 2.x, running with Groovy runtime 4, Downgrade the script if older behaviour needed.

import com.sap.it.script.v2.api.Message
import groovy.xml.XmlSlurper
import java.io.Reader

def Message processData(Message message) {

    // Stream message body as Reader
    Reader reader = message.getBody(Reader)

    // Parse XML directly from stream
    def xml = new XmlSlurper().parse(reader)

    // Extract values
    def name  = xml.Name.text()
    def clazz = xml.Class.text()

    // Build HTML
    def html = """
    <html>
        <body>
            <table border="1" style="border-collapse: collapse;">
                <tr>
                    <th>Name</th>
                    <th>Class</th>
                </tr>
                <tr>
                    <td>${name}</td>
                    <td>${clazz}</td>
                </tr>
            </table>
        </body>
    </html>
    """

    // Set response body
    message.setBody(html)

    // Set content type
    message.setHeader("Content-Type", "text/html")

    return message
}