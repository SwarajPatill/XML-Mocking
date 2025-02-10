package xmlFiles;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ReadXMLFile {
//DOM PARSER 
	//document builder factory class
	//documnet builder
	//document
	// we have to extract root node from xml documet
	
	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document =builder.parse(".\\Data\\MyData.xml");
		
				Element root = document.getDocumentElement();
				NodeList nodeList = root.getChildNodes();
				
				
				for(int i =0;i<nodeList.getLength();i++) {
					Node node = nodeList.item(i);
					
					if(node.getNodeType()==Node.ELEMENT_NODE) {
					  Element element =(Element)node;
					  String tagName = element.getTagName();
					  String text = element.getTextContent();
					  System.out.println("TagName :- " + tagName);
					  System.out.println("text" + text);
					  System.out.println("-----------------------------");
					}
				}
				
				
	}

}
