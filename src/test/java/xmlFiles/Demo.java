package xmlFiles;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.UUID;

public class Demo {

		// TODO Auto-generated method stub
	private static String generateUniqueZip() {
        Random random = new Random();
        int firstPart = 10000 + random.nextInt(90000); // Generate a random number between 10000 and 99999
        int secondPart = 1000 + random.nextInt(9000);  
        return firstPart + "-" + secondPart; // Combine both parts with a hyphen
    }
	
	
	public static String generateUniquePolicyNumber() {
		String uniqueIdentifier = UUID.randomUUID().toString();
		
		uniqueIdentifier = uniqueIdentifier.replaceAll("-", "");
		
		return uniqueIdentifier.substring(0,10);
		
	}

	

		    public static void main(String[] args) {
		        try {
		            // Load the XML file
		            File xmlFile = new File(".\\Data\\MyData.xml");
		            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder builder = factory.newDocumentBuilder();
		            Document document = builder.parse(xmlFile);

		            // Get the root element (Department)
		            Element root = document.getDocumentElement();

		            // Find the <OwnerName> elements
		            NodeList ownerList = root.getElementsByTagName("OwnerName");

		            // Iterate through each <OwnerName> element
		            for (int i = 0; i < ownerList.getLength(); i++) {
		                Node ownerNode = ownerList.item(i);
		                if (ownerNode.getNodeType() == Node.ELEMENT_NODE) {
		                    // Updating OwnerName value
		                    ownerNode.setTextContent("name" + (i + 1));
		                }
		            }

		            // Find the <Address> elements and update them
		            NodeList addressList = root.getElementsByTagName("Address");

		            // Iterate through each <Address> element
		            for (int j = 0; j < addressList.getLength(); j++) {
		                Node addressNode = addressList.item(j);
		                if (addressNode.getNodeType() == Node.ELEMENT_NODE) {
		                    // Updating Address value
		                    addressNode.setTextContent("address" + (j + 1));
		                }
		            }
		            
		         // Find the <Zip> elements
		            NodeList zipList = root.getElementsByTagName("Zip");

		            // Iterate through each <Zip> element
		            for (int i = 0; i < zipList.getLength(); i++) {
		                Node zipNode = zipList.item(i);
		                if (zipNode.getNodeType() == Node.ELEMENT_NODE) {
		                    // Generate a unique ZIP code
		                    String uniqueZip = generateUniqueZip();
		                    zipNode.setTextContent(uniqueZip);
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
		                            policyNumberNode.setTextContent(generateUniquePolicyNumber());
		                       } 
		                        
		                    }
		                }
		            }

		            // Write the updated content to a new XML file
		            TransformerFactory transformerFactory = TransformerFactory.newInstance();
		            Transformer transformer = transformerFactory.newTransformer();
		            DOMSource source = new DOMSource(document);
		            FileOutputStream fileOutputStream = new FileOutputStream(".\\Data\\updatedata.xml");
		            StreamResult result = new StreamResult(fileOutputStream);
		            transformer.transform(source, result);

		            fileOutputStream.close();
		            System.out.println("Updated XML file saved as 'updatedata.xml'");

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }   		


	}

