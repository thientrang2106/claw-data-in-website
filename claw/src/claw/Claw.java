/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package claw;

import java.applet.Applet;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Thien Trang
 */
public class Claw extends Applet{

    /**
     * @param args the command line arguments
     */
    static  void writeFile(String t){
        try {
        //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
        FileOutputStream fos = new FileOutputStream("text/output.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        //Bước 2: Ghi dữ liệu
        dos.writeChars(t);

        //Bước 3: Đóng luồng
        fos.close();
        dos.close();
        System.out.println("Done!");
       } catch (IOException ex) {
         ex.printStackTrace();
       } 
    }
    private static  Document connect(String url) throws IOException{
        Connection.Response res = Jsoup.connect(url)
                .method(Method.POST)
                .userAgent("a")
                .timeout(100 * 1000)
                .ignoreHttpErrors(true)
                .execute();
        Map<String, String> cookies = res.cookies();
        Document doc = Jsoup.connect(url)
                .method(Method.POST)
                .userAgent("a")
                .timeout(100 * 1000)
                .ignoreHttpErrors(true)
                .cookies(cookies)
                .get();
        return doc;
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int numPage = 1;
        String url = "https://www.amazon.com/Samsung-Chromebook-Wi-Fi-11-6-Inch-Refurbished/product-reviews/B00M9K7L8S/ref=cm_cr_dp_d_show_all_btm?ie=UTF8&reviewerType=all_reviews";
        Document doc = connect(url);
        Element com = doc.getElementsByClass("page-button").last();
        int num = 0;
        String title = "";
        if(com != null){
            title = com.toString();
            int len = title.length() - 9;
            title = title.substring(0, len);
            String v = ">";
            while(title.charAt(0) == '<'){
                title = title.substring(title.indexOf(v) + 1);
            }
            num = Integer.parseInt(title);
        }else{
            System.out.println("null");
        }
        System.out.println(num);
        
        String data = "";
        for(int i = 1; i <= num; i++){
            System.out.println(i);
            url = "https://www.amazon.com/Samsung-Chromebook-Wi-Fi-11-6-Inch-Refurbished/product-reviews/B00M9K7L8S/ref=cm_cr_arp_d_paging_btm_+"+i+"?ie=UTF8&reviewerType=all_reviews&pageNumber="+i;
            Document doc1 = connect(url);
            //Element com1 = doc1.getElementsByClass("a-section a-spacing-none reviews-content a-size-base").first();
            Elements com1 = doc1.select("span[data-hook=\"review-body\"]");
            if(com1 != null){
                String te = ">";
                for(int j = 0; j < com1.size(); j++){
                    String c = com1.get(j).toString();
                    data += c.substring(c.indexOf(te) + 1, c.length() - 7) + "\n";
                }
                
            }else{
                System.out.println("null");
            }
        }
        
        writeFile(data);
    }
    
}
