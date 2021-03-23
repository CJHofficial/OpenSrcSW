import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.lang.String;


public class kuir {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
        if(args[0].equals("-k")){
            makeKeyword mkw = new makeKeyword();
            mkw.makeIndexXml(args[1]);
        }
        else if(args[0].equals("-c")){
            makeCollection createXml = new makeCollection();
            createXml.htmltoXml(args[1]);
        }

    }
}
