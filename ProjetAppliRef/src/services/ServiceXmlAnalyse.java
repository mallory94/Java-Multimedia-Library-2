package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import appli.FTPUploadFile;
import bri.Service;


public class ServiceXmlAnalyse implements Service {
	private final Socket socket;
	
	public ServiceXmlAnalyse(Socket socket) {
		this.socket = socket;
	}
   
   @Override
   public void run() {
	   System.out.println("*********Connexion démarrée");
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
			PrintWriter out = new PrintWriter (socket.getOutputStream ( ), true);
			out.println("Bienvenue sur le service d'analyse xml.##"
					+ "Entrez le chemin de votre fichier suivi d'un pipe puis de votre mail comme ceci \"C:/Users/Jean/Desktop/test.xml|example@gmail.com\":##");
			String line = in.readLine();
			System.out.println(line);
			String[] lines = line.split("\\|");
			for (String tmpLine : lines) {
				System.out.println(tmpLine);
			}
			String chemin = lines[0];
			String email = lines[1];
			new FTPUploadFile(chemin).start();
			new ServiceEmail(email,analyseTest(chemin)).start();;
			out.println("Vous allez bientôt recevoir un mail contenant les résultats de l'analyse du fichier");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//Fin du service d'inversion
		System.out.println("*********Connexion terminée");
		try {socket.close();} catch (IOException e2) {}
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
   
   
   public static String toStringue() {
		return "Analyse de fichier .xml";
	}




}