import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper
import java.io.Reader

def Message processData(Message message) {

    // Read payload as stream
    Reader reader = message.getBody(Reader)

    // Parse XML
    def xml = new XmlSlurper().parse(reader)

    // Read fields
    def name  = xml.Name.text()
    def clazz = xml.Class.text()

    // Create HTML
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

    // Set body
    message.setBody(html)

    // Set content type
    message.setHeader("Content-Type", "text/html")

    return message
}