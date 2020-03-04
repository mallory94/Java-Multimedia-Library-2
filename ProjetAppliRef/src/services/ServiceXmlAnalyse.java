package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ServiceXmlAnalyse {
   public static void main(String[] args) {
      // Nous r�cup�rons une instance de factory qui se chargera de nous fournir
      // un parseur
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      try {
         // Cr�ation de notre parseur via la factory
         DocumentBuilder builder = factory.newDocumentBuilder();
         File fileXML = new File("note.xml");

         // parsing de notre fichier via un objet File et r�cup�ration d'un
         // objet Document
         // Ce dernier repr�sente la hi�rarchie d'objet cr��e pendant le parsing
         Document xml = builder.parse(fileXML);

         // Via notre objet Document, nous pouvons r�cup�rer un objet Element
         // Ce dernier repr�sente un �l�ment XML mais, avec la m�thode ci-dessous,
         // cet �l�ment sera la racine du document
         Element root = xml.getDocumentElement();
         System.out.println(toStringRoot(root));

         // Parsing d'un XML via une URL
//         String uri = "http://www.w3schools.com/xml/note.xml";
//         xml = builder.parse(uri);
//         root = xml.getDocumentElement();
//         System.out.println(root.getNodeName());

         // Parsing d'un XML via un flux
//         InputStream is = new FileInputStream(fileXML);
//         xml = builder.parse(is);
//         root = xml.getDocumentElement();
//         System.out.println(root.getNodeName());

      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   public static String toStringRoot(Element root){
	   
	   String str = toStringNode(root.getChildNodes().item(0)) + root.getNodeName() + toStringNode(root.getChildNodes().item(1));
	   return str;
   }
   
   public static String toStringNode(Node node) {
	   System.out.println(node.getChildNodes().getLength());
	   if (node.getChildNodes().getLength() != 0) {
		   return(toStringNode(node.getChildNodes().item(0)) + node.getNodeName() + toStringNode(node.getChildNodes().item(1)));
	   }
	   return(node.getNodeName());
   }
}