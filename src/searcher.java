import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
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

public class searcher {
            public void getsearch(String[] args)throws Exception{
                HashMap indexpost = getpost(args[1]);
                HashMap <String, Integer> Q = new HashMap<>();
                String keywordBody = "";
                if(args[2].equals("-q")){
                    KeywordExtractor ke = new KeywordExtractor();
                    KeywordList kl = ke.extractKeyword(args[3], true);

                    for(int i = 0; i < kl.size(); i++) {
                        Keyword kwd = kl.get(i);
                        keywordBody = kwd.getString();
                        Q.put(keywordBody, 1);
                    }
                    CalcSim(indexpost, Q);
                }
            }

            public HashMap getpost(String route) throws Exception{
                FileInputStream fileStream = new FileInputStream(route);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileStream);

                Object object = objectInputStream.readObject();
                objectInputStream.close();

                HashMap hashMap = (HashMap)object;

                return hashMap;
                //Iterator<String> it = hashMap.keySet().iterator();1
        /*while(it.hasNext()){
            String key = it.next();
            System.out.println(key + " → " + hashMap.get(key));
        }*/
        }
        public void CalcSim(HashMap hashMap, HashMap<String,Integer> Q){
            double[] sum = new double[5];
            double [] sort = new double[5];
            String[] title={"떡", "라면", "아이스크림", "초밥", "파스타"};
            sum = InnerProduct(hashMap, Q);
            sort = InnerProduct(hashMap, Q);
            Arrays.sort(sort);
            String[] top = new String[3];
            int a = 0;
            for(int i =4;i>=0;i--){
                for(int j=0;j<5;j++){
                    if(a==3)break;
                    if(sort[i]==sum[j]){
                        top[a] = title[j];
                        if(a<=2)
                            a++;
                    }
                }
                if(a==3)break;
            }
            for(int i=0;i<3;i++){
                System.out.println(top[i]);
            }
        }
    public double[] InnerProduct(HashMap hashMap, HashMap Q){
        Iterator<String> it = hashMap.keySet().iterator();
        ArrayList <String> idf = new ArrayList<>();
        double[] sim = new double[5];
        int[] Qvalue = new int[5];
        double[] postidf = new double[5];
        double[] sum = new double[5];
        String[] title={"떡", "라면", "아이스크림", "초밥", "파스타"};
        double [] sort = new double[5];
        while(it.hasNext()){
            String postkey =it.next();
            int index=-1;
            if(Q.containsKey(postkey)){
                idf = (ArrayList)(hashMap.get(postkey));
                for(int j=0; j<idf.size(); j++){
                    if((j%2) == 0){
                        index = Integer.parseInt((String)idf.get(j));
                        postidf[index] = Double.parseDouble((String)idf.get(j+1));
                        Qvalue[index] = (int)Q.get(postkey);
                    }
                }
                for(int i=0;i<5; i++){
                    sim[i] = postidf[i] * (double)Qvalue[i];
                    //System.out.println(postkey + "의 Q*id"+ i + "=" + sim[i]);

                    sum[index] += sim[i];
                    //System.out.println(sum[j]);

                }

            }
        }
        for(int i=0;i<5; i++){
            sum[i]=Math.round(sum[i]*100)/100.0;
            System.out.println(sum[i]);
            sort[i] = sum[i];
        }
        return sort;
    }
}
