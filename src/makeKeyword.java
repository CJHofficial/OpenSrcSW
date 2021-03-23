import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class makeKeyword{
    public void makeIndexXml(String Xmlfileloc)throws IOException, SAXException, ParserConfigurationException{
        String [][] gettag = new String[5][2];
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(Xmlfileloc);
            Element docs = document.getDocumentElement();
            NodeList children = docs.getChildNodes();
            int docId = 0;
            for(int i = 0; i<children.getLength();i++){
                Node node = children.item(i);
                NodeList docChild = node.getChildNodes();
                for(int j = 0; j < docChild.getLength(); j++){
                    Node docChildNode = docChild.item(j);
                    if(docChildNode.getNodeType() == Node.ELEMENT_NODE){
                        String nodeName = ((Element)docChildNode).getNodeName();
                        if(nodeName.equals("title")){
                            gettag[docId][0] = ((Element)docChildNode).getTextContent();
                        }
                        else if(nodeName.equals("body")) {
                            gettag[docId][1] = ((Element) docChildNode).getTextContent();
                            docId++;
                        }
                    }
                }
            }

            String[][] Tagsbykkma = new String[gettag.length][gettag[0].length];
            for(int i = 0; i<gettag.length;i++){
                for(int j = 0; j<gettag[0].length;j++){
                    if(j==(gettag[0].length - 1)){
                        Tagsbykkma[i][j] = kkma(gettag[i][j]);
                    }
                    else{
                        Tagsbykkma[i][j] = gettag[i][j];
                    }
                }
            }
            DocumentBuilderFactory docFactory2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder2 = docFactory2.newDocumentBuilder();
            org.w3c.dom.Document document2 = docBuilder2.newDocument();
            Element docs2 = document.createElement("docs");
            document2.appendChild(docs2);
            for(int i = 0 ; i < Tagsbykkma.length; i++){
                String kkmatitle = Tagsbykkma[i][0];
                String kkmabody = Tagsbykkma[i][1];
                String idnum = Integer.toString(i);

                Element doc = document2.createElement("doc");
                Element title = document2.createElement("title");
                Element body = document2.createElement("body");
                docs2.appendChild(doc);
                docs2.setAttribute("id", idnum);
                docs2.appendChild(title);
                title.appendChild(document2.createTextNode(kkmatitle));
                doc.appendChild(body);
                body.appendChild(document2.createTextNode(kkmabody));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource source = new DOMSource(document2);
            StreamResult result = new StreamResult(new FileOutputStream(new File("./index.xml")));
            transformer.transform(source, result);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public String kkma(String text){


        KeywordExtractor ke = new KeywordExtractor();
        KeywordList kl = ke.extractKeyword(text, true);
        String keywordBody = "";
        for(int i = 0; i < kl.size(); i++){
            if (i != kl.size() - 1){
                keywordBody = keywordBody + kl.get(i).getString() + ":" + kl.get(i).getCnt() + "#";
            }else{
                keywordBody = keywordBody + kl.get(i).getString() +":" + kl.get(i).getCnt();
            }
        }
        return keywordBody;
    }
}