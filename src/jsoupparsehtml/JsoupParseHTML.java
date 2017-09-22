/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoupparsehtml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
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
        
        //priceElement = doc.getElementById("priceblock_dealprice");
        
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
//            System.out.println(priceElement.toString());

            String val = priceElement.text().trim();
            for (int i = 0; i < val.length(); i++) {
                if (Character.isDigit(val.charAt(i))||val.charAt(i)=='.') {
                    fin = fin + val.charAt(i);
                }
                
            }

            System.out.println("Price as of now is:"+fin);
//
//            for (int i = 0; i < fin.length(); i++) {
//                System.out.print((int) fin.charAt(i) + " ");
//
//            }

        }else{
            if(connSuccess==true)
            System.out.println("ID not found");
        }
    }

}
