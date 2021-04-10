import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.lang.String;


public class kuir {
        public static void main(String[] args) throws Exception {
        if(args[0].equals("-k")){
            makeKeyword mkw = new makeKeyword();
            mkw.makeIndexXml(args[1]);
        }
        else if(args[0].equals("-c")){
            makeCollection createXml = new makeCollection();
            createXml.htmltoXml(args[1]);
        }
        else if(args[0].equals("-i")){
            indexer idx = new indexer();
            idx.indexer(args[1]);
        }
        else if(args[0].equals("-s")){
            //String query = args[3];
            searcher search = new searcher();
            search.getsearch(args);
        }
    }
}
