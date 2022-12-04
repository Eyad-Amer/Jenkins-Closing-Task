package com.example.YnetNewsApp;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

@RestController
public class Controler {

    @RequestMapping("/")
    public ModelAndView breakingNews(Model model){

        String URL = "http://www.ynet.co.il/Integration/StoryRss2.xml";
        String xmltotable = XML_to_Table(URL);
        model.addAttribute("content", xmltotable);
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("index");
        return modelView;
    }


    public static String XML_to_Table(String URL) {
        String html_string = "";

        try {
            // Getting the xml from the URL
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(URL).openStream());

            // Normalize the data
            doc.getDocumentElement().normalize();

            // Split by tag name - item
            NodeList list = doc.getElementsByTagName("item");

            int len= list.getLength();
            for (int i=0; i< len; i++) {
                Node item = list.item(i);
                Element element = (Element) item;
                html_string += "<div class=\"item\">" +
                        "<div class=\"title\"><h2>"+ element.getElementsByTagName("title").item(0).getTextContent() +"</h2></div>" +
                        "  <div class=\"description\">" + element.getElementsByTagName("description").item(0).getTextContent() + "</div>\n" +
                        "<div class=\"link\">"+ element.getElementsByTagName("link").item(0).getTextContent() +"</div>\n" +
                        "<div class=\"pubDate\">"+ element.getElementsByTagName("pubDate").item(0).getTextContent() +"</div>" +
                        "</div>";

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return html_string;

    }


}