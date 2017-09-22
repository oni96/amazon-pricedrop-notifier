/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoupparsehtml;

import java.io.File;
import java.io.IOException;
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
     */
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        Document doc = Jsoup.connect(s.nextLine()).get();
        Element priceElement=null;
        ArrayList<String> priceIds = new ArrayList<String>();
        priceIds.add("priceblock_dealprice");
        priceIds.add("priceblock_ourprice");
        
        priceElement = doc.getElementById("priceblock_dealprice");
        
        int j = 0;
        while(priceElement==null){
            priceElement = Jsoup.connect(priceIds.get(j)).get();
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
                if (val.charAt(i) < 127 && val.charAt(i) > 32) {
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
            System.out.println("ID not found");
        }
    }

}
