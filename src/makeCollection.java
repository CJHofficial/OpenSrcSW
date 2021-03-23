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
import java.lang.String;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class makeCollection {
    public void htmltoXml(String htmlfileloc)throws ParserConfigurationException{
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();
            Element docs = document.createElement("docs");
            document.appendChild(docs);
            int htmlnum = 5;
            File loc = new File(htmlfileloc);
            File htmls[] = loc.listFiles();
            for(int i = 0; i <htmlnum; i++){
                String [] gettag = htmltag(htmls[i]);
                Element doc = document.createElement("doc");
                docs.appendChild(doc);
                String num = Integer.toString(i);
                doc.setAttribute("id", num);
                Element title =  document.createElement("title");
                title.appendChild(document.createTextNode(gettag[0]));
                Element body = document.createElement("body");
                doc.appendChild(body);
                body.appendChild(document.createTextNode(gettag[1]));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(new File("./collection.xml")));
            transformer.transform(source, result);

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public String[] htmltag(File fileName) throws IOException{
        String [] strtag = new String[2];
        try{
            org.jsoup.nodes.Document doc = Jsoup.parse(fileName, "UTF-8");
            Elements title = doc.select("title");
            strtag[0] = title.text();
            Elements p = doc.select("p");
            strtag[1] = p.text();
        }catch(Exception e){
            System.out.println(e);
        }
        return strtag;
    }
}