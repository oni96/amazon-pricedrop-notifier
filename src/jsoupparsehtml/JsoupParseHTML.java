/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoupparsehtml;

import java.io.File;
import java.io.IOException;
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
        File file = new File("2.html");
        Document doc = Jsoup.connect("https://www.amazon.in/Sanyo-108-2-inches-XT-43S7100F-Black/dp/B01ICVLK4S/ref=gbph_tit_m-5_1e10_64066709?smid=AT95IG9ONZD7S&pf_rd_p=1d14a301-79e7-40f6-941f-97b38a401e10&pf_rd_s=merchandised-search-5&pf_rd_t=101&pf_rd_i=9899981031&pf_rd_m=A1VBAL9TL5WCBF&pf_rd_r=7WTYNVTNC5PDBZHFGANZ").get();
        Element priceElement;
        
        priceElement = doc.getElementById("priceblock_dealprice");
        if(priceElement==null){
            priceElement = doc.getElementById("priceblock_ourprice");
        }

        if (priceElement != null) {
            String fin = "";
            System.out.println(priceElement.toString());

            String val = priceElement.text().trim();
            for (int i = 0; i < val.length(); i++) {
                if (val.charAt(i) < 127 && val.charAt(i) > 32) {
                    fin = fin + val.charAt(i);
                }
            }

            System.out.println(fin);
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
