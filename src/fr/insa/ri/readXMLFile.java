package fr.insa.ri;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


import java.io.File;
  
public class readXMLFile {
  
    private static int nbEnfants;
	private static String str;

	public static void main(String argv[]) {
  
      try {
  
        File fXmlFile = new File("D:\\Mes Documents\\INSA\\5IL\\UF RI et RC\\Collection\\Collection\\d001.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
  
        Node root = doc.getDocumentElement();
        System.out.println("Element racine:" + root.getNodeName());
        NodeList nListRoot = root.getChildNodes();
         
        for (int i = 0; i < nListRoot.getLength(); i++)
        {
            parcourirArbre(nListRoot.item(i));
        }
         
 
      } catch (Exception e) {
        e.printStackTrace();
      }
  }
  
    public static void parcourirArbre(Node node) {
        
        
        if (node.getNodeName() == "#text")
        {
            if (node.getNodeValue() != null)
            {
                System.out.print(node.getNodeValue());
            }
        }
        else
        {
        	if (node.getNodeName() == "P"){
        		System.out.print(getXPath(node));
       	}
        	Node parent = node.getParentNode();
			NodeList enfants = parent.getChildNodes();
			nbEnfants = enfants.getLength();
        	System.out.print(node.getNodeName()+" : "+nbEnfants+"    ");

        }
         
        if (node.hasChildNodes())
        {
            NodeList nList = node.getChildNodes();
             
            for (int i = 0; i < nList.getLength(); i++)
            {
                parcourirArbre(nList.item(i));
            }
        }
 
    }
    
    public static String getXPath(Node node){    	

    	String name = node.getNodeName();
        String number = node.getNodeValue();
    	
    	if (node.getParentNode() == null || node.getParentNode().getNodeName().equals("#document")){
    		return "/" + name + "[" + number + "]";
    	} else
    	{
    		return getXPath(node.getParentNode()) + "/" + name + "[" + node.getParentNode().getNodeValue()+ "]";
    	}
    }
    
    public static String[] getWords(Node node){
    	
		NodeList enfants = node.getChildNodes();
		nbEnfants = enfants.getLength();
		str = "lalala";
    	for (int i=0;i<nbEnfants;i++){    
    		str = enfants.item(i).getNodeValue();
    	}

    	String delims = "[ .,?!]+";
    	String[] tokens = str.split(delims);
    	return tokens;
    	/*for (int i =0; i<tokens.length;i++){
    		System.out.println(tokens[i]);
    	}*/
    }
    
 
   /* public static void evaluerDOM(Document document, String expression, QName retour){
    	try{
    		//création du XPath 
    		XPathFactory fabrique = XPathFactory.newInstance();
    		XPath xpath = fabrique.newXPath();
    		
    		//évaluation de l'expression XPath
    		XPathExpression exp = (XPathExpression) xpath.compile(expression);
    		Object resultat = exp.evaluate(document,(short) 0, retour);
    		
    		System.out.println(resultat);
    	}catch(XPathExpressionException xpee){
    		xpee.printStackTrace();
    	}
    }*/
     
}