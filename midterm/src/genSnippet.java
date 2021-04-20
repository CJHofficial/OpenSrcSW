import java.io.FileInputStream;
import java.io.ObjectInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;
public class genSnippet {
        public static void main(String[] args) throws Exception {
                if(args[0].equals("-f")){
                    FileInputStream fileStream = new FileInputStream(args[1]);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);
                    Object object = objectInputStream.readObject();
                    objectInputStream.close();
                    String file = (String) object;
                    System.out.println(file);


                }
                else{
                    System.out.println("입력인자 다시 입력해주세요 ㅠㅠ");
                }
            }

    }

