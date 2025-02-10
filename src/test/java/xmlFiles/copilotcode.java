package xmlFiles;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import PolicyNumber.PolicyNumberGenerator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

public class copilotcode {
	
	public static Properties p;
	 

    public static void main(String[] args) {
        try {
        	
        	FileReader file = new FileReader(".//src/test/resources/data.properties");
        	p = new Properties();   
        	p.load(file);
            // Load the XML file
            File xmlFile = new File(".\\Data\\MyData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

                
             // Get the root element (Department)
            Element root = document.getDocumentElement();

            // Find the <Address> elements
            NodeList addressList = root.getElementsByTagName("Address");


            // Iterate through each <Address> element
            for (int j = 0; j < addressList.getLength(); j++) {
                Node addressNode = addressList.item(j);
                if (addressNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element addressElement = (Element) addressNode;

                    // Updating  value
                    NodeList zipList = addressElement.getElementsByTagName("Zip");
                    if (zipList.getLength() > 0) {
                        Node zipNode = zipList.item(0);
                        zipNode.setTextContent(p.getProperty("Zip"));
                    }

                    NodeList cityList = addressElement.getElementsByTagName("City");
                    if (cityList.getLength() > 0) {
                        Node cityNode = cityList.item(0);
                        cityNode.setTextContent(p.getProperty("City"));
                    }

                    NodeList stateList = addressElement.getElementsByTagName("State");
                    if (stateList.getLength() > 0) {
                        Node stateNode = stateList.item(0);
                        stateNode.setTextContent(p.getProperty("State"));
                    }
                
                }
            }
            

    // Find all Employee and Contractor elements
    NodeList TagList = document.getElementsByTagName("*");
    
    for (int i = 0; i < TagList.getLength(); i++) {
        Node node = TagList.item(i);
        
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            
            // Check if it's an Employee or Contractor element
            if ((element.getElementsByTagName("PolicyNumber").getLength() > 0)) {
                NodeList policyNumberList = element.getElementsByTagName("PolicyNumber");
                
                // Check if the PolicyNumber tag exists
                if (policyNumberList.getLength() > 0) {
                    // Update the existing PolicyNumber tag
                    Node policyNumberNode = policyNumberList.item(0);
                    policyNumberNode.setTextContent(PolicyNumberGenerator.generateUniquePolicyNumber());
               } 
                
            }
        }
    


            // Write the updated content to a new XML file
            javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
            java.io.FileOutputStream fileOutputStream = new java.io.FileOutputStream(".\\Data\\updatedata.xml");
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(fileOutputStream);
            transformer.transform(source, result);

            //this code is used to convert DOM document into xml document
            //the transformer is used to convert the dom document into xml
            //source act as holder for dom document that we want to transform
            //Fileoutstream object to write the transformed xml data into a file named in our specified klocation
            //result will hold the output of the transformation
            //this last line transform the Dom document into a xml file

            }
            
            System.out.println("XML file updated successfully.");


            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
                
