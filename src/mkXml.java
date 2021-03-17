import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class mkXml      {
    public void htmltoXml()throws ParserConfigurationException{
        htmlparser mkp = new htmlparser();
        File [] htmlsdir = mkp.htmlfiles();
        int htmlnum = 5;

        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();
            Element docs = document.createElement("docs");
            document.appendChild(docs);
            for(int i = 0; i <htmlnum; i++){
                String [] gettag = mkp.htmltag(htmlsdir[i]);
                String Xmltitle = gettag[0];
                String Xmlp = gettag[1];
                Element doc = document.createElement("doc");
                docs.appendChild(doc);
                String num = Integer.toString(i);
                doc.setAttribute("id", num);
                Element title =  document.createElement("title");
                title.appendChild(document.createTextNode(Xmltitle));
                Element body = document.createElement("body");
                doc.appendChild(body);
                body.appendChild(document.createTextNode(Xmlp));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource src = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(new File("./collection.xml")));
            transformer.transform(src, result);

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
