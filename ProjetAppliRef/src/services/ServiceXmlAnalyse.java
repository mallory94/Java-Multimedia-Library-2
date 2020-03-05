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
	   String email = "malloga94@gmail.com";
	   String chemin = "test.xml"; //contient aussi le nom du fichier
	   new Thread(new ServiceEmail(email , analyseTest(chemin))).start();
   }
   
   
   
   public static String analyseTest(String chemin) 
   {
    /*
     * Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
     */
    final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      	
    try 
    {
	      /*
	       * Etape 2 : création d'un parseur
	       */
	      final DocumentBuilder builder = factory.newDocumentBuilder();
				
		  /*
		   * Etape 3 : création d'un Document
		   */
		  final Document document= builder.parse(new File(chemin));
		  
		  StringBuilder strEmail = new StringBuilder();
		  //Affichage du prologue
		  strEmail.append("\n\n*************METADONNEES************\n\n");
		  strEmail.append("\nversion : " + document.getXmlVersion());
		  strEmail.append("\nencodage : " + document.getXmlEncoding());		
		  strEmail.append("\nstandalone : " + document.getXmlStandalone());
		  
		  /*
		   * Etape 4 : récupération de l'Element racine
		   */
		  final Element racine = document.getDocumentElement();
		
		  //Affichage de l'élément racine
		  strEmail.append("\n\n*************CONTENU************\n\n");
		  
		  /*
		   * Etape 5 : récupération des personnes
		   */
		  final NodeList racineNoeuds = racine.getChildNodes();
		  final int nbRacineNoeuds = racineNoeuds.getLength();
		  int compteurLivres = 0;
		  
		  for (int i = 0; i<nbRacineNoeuds; i++) 
		  {
		    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) 
		    {
		    	
		    	++compteurLivres;
		    	final Element element = (Element) racineNoeuds.item(i);
		    	strEmail.append("\ncatégorie = " + element.getAttribute("categorie"));

		    	strEmail.append("\ntitre = " + element.getElementsByTagName("titre").item(0).getTextContent());
		    	strEmail.append("\nNom de l'auteur = " + element.getElementsByTagName("auteur").item(0).getTextContent());
		    	strEmail.append("\nannée de parution = " + element.getElementsByTagName("annee").item(0).getTextContent());
		    	strEmail.append("\nprix = " + element.getElementsByTagName("prix").item(0).getTextContent() + " euros\n");
			  }
		    }
		  return "\n\nLe document renseigne sur " + compteurLivres + " livres " + strEmail.toString();
		  
	}		
	catch (final ParserConfigurationException e) 
	{
	  e.printStackTrace();
	}
	catch (final SAXException e) 
	{
	  e.printStackTrace();
	}
	catch (final IOException e) 
	{
	  e.printStackTrace();
	}
	return "Une erreur s'est produite lors de l'analyse de votre fichier .xml . Vérifier que votre fichier respecte la norme de l'analyseur.";		
  }
}