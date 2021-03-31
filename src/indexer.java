import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.lang.*;

import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class indexer {
    ArrayList <ArrayList<String>> cutKwdList = new ArrayList();
    ArrayList <ArrayList<String>> cutOnlyKwd = new ArrayList();
    ArrayList <ArrayList<String>> KWDnIDFlist = new ArrayList();


    public void indexer(String Xmlfileloc)throws IOException, SAXException, ParserConfigurationException {
        String[] gettag = new String[5];
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(Xmlfileloc);
            Element doc = document.getDocumentElement();
            NodeList children = doc.getChildNodes();
            int docId = 0;
            for (int i = 0; i < children.getLength(); i++) {
                Node node = children.item(i);
                NodeList docChild = node.getChildNodes();
                for (int j = 0; j < docChild.getLength(); j++) {
                    Node docChildNode = docChild.item(j);
                    if (docChildNode.getNodeType() == Node.ELEMENT_NODE) {
                        String nodeName = ((Element) docChildNode).getNodeName();
                        if (nodeName.equals("body")) {
                            gettag[docId] = ((Element) docChildNode).getTextContent();
                            docId++;
                        }
                    }

                }
            }


            String [][]cutH = new String[5][];
            String []cutS = new String[2];
            for(int i=0; i<5; i++){
                cutH[i] = gettag[i].split("#");
            }
            for(int i=0; i<5; i++){
                ArrayList cutSin1doc = new ArrayList<String>();
                ArrayList cutKwdin1doc = new ArrayList<String>();
                for(int j = 0 ; j < cutH[i].length ; j++){
                    cutS=cutH[i][j].split(":");
                    cutSin1doc.add(cutS[0]);
                    cutSin1doc.add(cutS[1]);
                    cutKwdin1doc.add(cutS[0]);

                }
                cutKwdList.add(cutSin1doc);
                cutOnlyKwd.add(cutKwdin1doc);
            }
            //System.out.println(cutKwdList.size());
            //System.out.println(cutKwdList.get(1));


            for(int i =0; i<cutKwdList.size();i++){
                for(int j=0; j<(cutKwdList.get(i)).size();j++){
                    if(j%2 ==0){
                        String kwd = (cutKwdList.get(i)).get(j);
                        String kwdnum = (cutKwdList.get(i)).get(j+1);
                        KWDnIDFlist.add(makeValuelist(kwd, kwdnum));
                    }
                }
            }
            //System.out.println(KWDnIDFlist);
            HashSet<ArrayList<String>> makeonly = new HashSet<>(KWDnIDFlist);
            ArrayList <ArrayList<String>> OnlyKWDnIDF = new ArrayList(makeonly);
            //System.out.println(OnlyKWDnIDF);

            FileOutputStream fileStream = new FileOutputStream("./index.post");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileStream);
            HashMap kwdmap = new HashMap();
            for(int i=0;i<OnlyKWDnIDF.size();i++){
                ArrayList valuelist = new ArrayList<String>();
                for(int j=0;j<(OnlyKWDnIDF.get(i)).size();j++){
                    if(j != 0){
                        valuelist.add((OnlyKWDnIDF.get(i)).get(j));
                    }
                }
                kwdmap.put((OnlyKWDnIDF.get(i)).get(0), valuelist);
            }
            objectOutputStream.writeObject(kwdmap);
            objectOutputStream.close();

            printpost("./index.post");
        }catch(Exception e){
            System.out.println(e);
        }
    }

   public ArrayList<String> makeValuelist(String kwd, String kwdnum){
        ArrayList valuelist = new ArrayList<String>();
        int dfx = 0;
        for(int a =0 ; a<5; a++){
            if((cutOnlyKwd.get(a)).contains(kwd)){
                    dfx++;
            }
        }
        for(int i=0; i<5; i++){
            ArrayList KWDIDFin1doc = new ArrayList<String>(); //쓰고 버릴거
            if((cutOnlyKwd.get(i)).contains(kwd)){
                if(!valuelist.contains(kwd)){
                    KWDIDFin1doc.add(kwd);
                }
                KWDIDFin1doc.add(Integer.toString(i));
                double idf = Double.parseDouble(kwdnum) * Math.log((double)5/dfx);
                String idf02 = Double.toString(Math.round(idf*100)/100.0);
                //if(idf!=0) {
                    KWDIDFin1doc.add(idf02);//그문서에서의 가중치
                //}
                valuelist.addAll(KWDIDFin1doc);
            }
        }

        return valuelist;
    }

    public void printpost(String route) throws IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream(route);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);

        Object object = objectInputStream.readObject();
        objectInputStream.close();

        HashMap hashMap = (HashMap)object;
        Iterator<String> it = hashMap.keySet().iterator();

        while(it.hasNext()){
            String key = it.next();
            System.out.println(key + " → " + hashMap.get(key));
        }
    }
}
