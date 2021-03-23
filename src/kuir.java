import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class kuir {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
        if(args[0].equals("-k")){
            makeKeyword mkw = new makeKeyword();
            mkw.makeIndexXml(args[1]);
        }
        else if(args[0].equals("-c")){

        }

    }
}
