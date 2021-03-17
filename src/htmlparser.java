import java.io.File;
import java.io.IOException;
import java.lang.String;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class htmlparser {
    public String[] htmltag(File fileName) throws IOException{
        String [] strtag = new String[2];
        try{
            Document doc = Jsoup.parse(fileName, "UTF-8");
            Elements title = doc.select("title");
            Elements p = doc.select("p");
            strtag[0] = title.text();
            strtag[1] = p.text();
        }catch(Exception e){
            System.out.println(e);
        }
        return strtag;
    }

    public File[] htmlfiles(){
        File loc = new File("./2주차 실습 html");
        File htmls[] = loc.listFiles();
        return htmls;
    }


}
