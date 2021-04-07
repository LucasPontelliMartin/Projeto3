/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio3_compiladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Pc
 */
public class Desafio3_Compiladores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            // TODO code application logic here
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            
            
            List<String> dados = new ArrayList<>();
            Scanner input = new Scanner(System.in);
            List<String> caracterFecha = new ArrayList<>();
            String dadosLinha  = null;;
            String caminhoSalvar = null;
            int ultimaPosicao = 0;
            boolean valida = true;
            String dadosConta;
            String caminho;
            System.out.println("DIGITE O CAMINHO ONDE O TXT ESTA SALVO!!");
            caminho = input.nextLine();
            
            System.out.println("PROCESSANDO...");
            
            try {
                File arquivo = new File(caminho);
                Document doc = builder.parse(caminho);
                
                FileWriter fw = new FileWriter("./test/Arquivo-Check.txt",true);
                            fw.write( " '" + doc.getDocumentElement().getNodeName() +"':{"+ "\n{\n");
                            fw.close();
                
                if (doc.hasChildNodes()) {
                    Node elemNode;
                    for (int i = 0; i < doc.getChildNodes().getLength(); i++) {
                          elemNode = doc.getChildNodes().item(i);
                        printNodeList(elemNode.getChildNodes());
                    }
                }
                
                File arquivo2 = new File("./test/Arquivo-Check.txt");
                if (arquivo2.exists()) {
                    BufferedReader in = new BufferedReader(new FileReader(arquivo2));
                    String salvaJSON = "";
                    while (in.ready()) {
                   dadosConta = (in.readLine());
                   dados.add(dadosConta);
                   

                   System.out.println(salvaJSON);
                    }
                    int qntdade = 1;
                    for (String analisar : dados) {
                        
                            if (qntdade != dados.size()) {
                             if (analisar.contains("{") || analisar.contains("'") || analisar.contains(":") ) {
                                if (analisar.equals("',")) {
                                    salvaJSON = " }\n {\n";
                                }
                                else{
                                    if (!analisar.contains("CD")) {
                                        salvaJSON = analisar +"\n";
                                    }
                                    
                                }
                                
                            }
                            
                        }
                            else{
                                salvaJSON = " }\n}";
                            }
                            fw = new FileWriter("./src/ArquivoJSON.txt",true);
                            fw.write(salvaJSON);
                            fw.close();
                            qntdade ++;
                            salvaJSON = "";
                        }
                        
                         System.out.println("ARQUIVO SALVO EM : /src/ArquivoJSON.txt ");
                    
                } 
                
            } catch (SAXException ex) {
                Logger.getLogger(Desafio3_Compiladores.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Desafio3_Compiladores.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (ParserConfigurationException ex) {
            Logger.getLogger(Desafio3_Compiladores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void printNodeList(NodeList nodeList) {
            
        
        int contador = 0;
        
            for (int count = 0; count < nodeList.getLength(); count++) {
                Node elemNode = nodeList.item(count);

                
                 if (elemNode.hasChildNodes()) {
                            //recursive call if the node has child nodes
                            
                            printNodeList(elemNode.getChildNodes());
                        
                 }
                if (elemNode.getNodeType() == Node.ELEMENT_NODE) {
                    try {
                        // get node name and value

                        try {
                            if (contador == 0) {
                                FileWriter fw = new FileWriter("./test/Arquivo-Check.txt",true);
                            fw.write( " '" + elemNode.getNodeName() +"':");
                            fw.close();
                            }
                            else{
                                contador++;
                                FileWriter fw = new FileWriter("./test/Arquivo-Check.txt",true);
                            fw.write( " '" + elemNode.getNodeName() +"':{"+ "\n");
                            fw.close();
                            }
                            
                        } catch (IOException ex) {
                            Logger.getLogger(Desafio3_Compiladores.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                         
                
                        
                        FileWriter fw = new FileWriter("./test/Arquivo-Check.txt",true);
                            fw.write( " '" + elemNode.getTextContent() +"',"+ "\n");
                            fw.close();
                        if (elemNode.hasAttributes()) {
                            NamedNodeMap nodeMap = elemNode.getAttributes();
                            for (int i = 0; i < nodeMap.getLength(); i++) {
                                Node node = nodeMap.item(i);
                                 fw = new FileWriter("./test/Arquivo-Check.txt",true);
                            fw.write( " '" + node.getNodeValue() +"',}}}}}}}}"+ "\n");
                            fw.close();
                            }
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Desafio3_Compiladores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
  
}
