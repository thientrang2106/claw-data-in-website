/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package claw;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Thien Trang
 */
public class Claw {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Document doc = Jsoup.connect("https://www.amazon.com/Samsung-Chromebook-Wi-Fi-11-6-Inch-Refurbished/dp/B00M9K7L8S/ref=br_asw_pdt-2?pf_rd_m=ATVPDKIKX0DER&pf_rd_s=&pf_rd_r=PE7EVYS6Z114V2CT7KQR&pf_rd_t=36701&pf_rd_p=bdec4768-ebeb-4bd3-96e7-efd23aabb29d&pf_rd_i=desktop").get();
        String tilte = doc.title();
        System.out.println(tilte);
    }
    
}
