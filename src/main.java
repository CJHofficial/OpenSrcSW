import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class main {
    public String[] htmltag(File fileName) throws IOException{
        String [] strtag = new String[2];
        try{
            Document doc = Jsoup.parse(fileName, "UTF-8");
            Elements title = doc.select("title");
            strtag[0] = title.text();
            Elements p = doc.select("p");
            strtag[1] = p.text();
        }catch(Exception e){
            System.out.println(e);
        }
        return strtag;
    }
    public static void main(String[] args) throws ParserConfigurationException{

        mkXml createXml = new mkXml();
        createXml.htmltoXml();
    }
}
