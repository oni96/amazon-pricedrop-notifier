/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoupparsehtml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *Test URL1:https://www.amazon.in/dp/B06ZY69D56/ref=psdc_1389396031_t1_B01ICVLK4S
 * Test URL2:https://www.amazon.in/Sanyo-108-2-inches-XT-43S7100F-Black/dp/B01ICVLK4S/ref=gbph_tit_m-5_1e10_64066709?smid=AT95IG9ONZD7S&pf_rd_p=1d14a301-79e7-40f6-941f-97b38a401e10&pf_rd_s=merchandised-search-5&pf_rd_t=101&pf_rd_i=9899981031&pf_rd_m=A1VBAL9TL5WCBF&pf_rd_r=7WTYNVTNC5PDBZHFGANZ
 * @author oni
 */
public class JsoupParseHTML {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        Document doc = null;
        boolean connSuccess = false;
        try {            
            doc = Jsoup.connect(s.nextLine()).timeout(4000).get();
            connSuccess = true;
        } catch (Exception e) {
            System.out.println("Connection failed!");
        }

        Element priceElement=null;
        
        FileInputStream priceIdTagFile = new FileInputStream("tag.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(priceIdTagFile));
        ArrayList<String> priceIds = new ArrayList();
        
        String line;
        
        while((line=br.readLine())!=null){
            priceIds.add(line);
        }
        
        
        int j = 0;
        while(priceElement==null && connSuccess==true){
            priceElement = doc.getElementById(priceIds.get(j));
            j++;
            
            if(j==priceIds.size()){
                break;
            }
        }
        
        
        if (priceElement != null) {
            String fin = "";

            String val = priceElement.text().trim();
            for (int i = 0; i < val.length(); i++) {
                if (Character.isDigit(val.charAt(i))||val.charAt(i)=='.') {
                    fin = fin + val.charAt(i);
                }
                
            }
            
            File priceFile = new File("pricename.txt");
            BufferedReader br1 =new BufferedReader(new FileReader(priceFile));
            if(br1.readLine()!=fin){
                BufferedWriter bw = new BufferedWriter(new FileWriter(priceFile));
                bw.write(fin);
                bw.close();
            }
            
          
            
            System.out.println("Price as of now is:"+fin);

        }else{
            if(connSuccess==true)
            System.out.println("ID not found");
        }
    }

}
