package com.example.bindu.homework04;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by bindu on 2/23/2018.
 */
/**
 * Assignment: Homework04
 * FileName: MainActivity.java
 * Group 15: Hima Bindu Sigili
 *           Bryson Shannon
 */


        import android.util.Log;
        import android.util.Xml;

        import org.xml.sax.Attributes;
        import org.xml.sax.ContentHandler;
        import org.xml.sax.SAXException;
        import org.xml.sax.helpers.DefaultHandler;
        import org.xmlpull.v1.XmlPullParser;
        import org.xmlpull.v1.XmlPullParserException;
        import org.xmlpull.v1.XmlPullParserFactory;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

/**
 * Created by bindu on 2/18/2018.
 */

public class ArticleParser {
    public static class ArticleSAXParser extends DefaultHandler {

        ArrayList<Article> articles;
        Article article;
        StringBuilder innerxml;
        int count,count1 = 0;
        static public ArrayList<Article> ParseArticles(InputStream inputStream) throws IOException, SAXException {
            ArticleSAXParser parser = new ArticleSAXParser();
            Xml.parse(inputStream,Xml.Encoding.ISO_8859_1,parser);
            return  parser.articles;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            this.articles= new ArrayList<>();
            innerxml= new StringBuilder();
        }
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName=="item"){
                count=1;
                article = new Article();
            }
            else if(qName=="media:group"){
                count1=1;
            }else if(qName=="media:content"){
                if(count1==1){
                    article.mediaImage=attributes.getValue("url");
                    count1++;
                }
            }else if(qName=="media:thumbnail"){
                article.mediaImage=attributes.getValue("url");
            }

        }
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            String text="";
            if(innerxml.toString()!=null)
                text=innerxml.toString().trim();
            if(localName=="title"){
                if(count==1){
                        article.title= text;
                }

            }else if(localName=="link"){
                if(count==1)
                    article.link = text;
                }
            else if(localName=="description"){
                if(count==1) {
                    article.description = text;
                }
            }else if(localName=="pubDate"){
                if(count==1){
                    article.publishedDate =text;
                }

            }else if(localName=="media:group"){
                count1=0;
            }
            else if(localName=="item"){
                articles.add(article);
            }
            innerxml.setLength(0);
        }
        /*[Person{namep='Bob Smith', idp=800000001, agep=25, addressp=Address{line1a='1447 Bryan Street', citya='Greensboro', statea='NC', zipa='27407'}},
        Person{namep='Keely Buttars', idp=800000002, agep=22, addressp=Address{line1a='377 Michigan Avenue', citya='Pittsburgh', statea='PA', zipa='15206'}},
        Person{namep='Douglas Turk', idp=800000003, agep=27, addressp=Address{line1a='1966 Concord Street', citya='Charlotte', statea='NC', zipa='28262'}},
        Person{namep='Lionel Lee', idp=800000004, agep=35, addressp=Address{line1a='2787 Pearl Street', citya='Sacramento', statea='CA', zipa='95826'}},
         Person{namep='Marilyn Gough', idp=800000005, agep=30, addressp=Address{line1a='3955 West Drive', citya='Burr Ridge', statea='IL', zipa='60527'}}]
    */
        @Override
        public void characters(char[] ch, int start, int length)  {
            try {
                super.characters(ch, start, length);
                innerxml.append(ch,start,length);
                Log.d("demo1",innerxml.toString());
            } catch (SAXException e) {
                e.printStackTrace();
            }


        }
    }
   public static class ArticlesPULLParser{
        static public ArrayList<Article> parseArticles(InputStream inputStream) throws XmlPullParserException, IOException {
            ArrayList<Article> articles= new ArrayList<>();
            Article article=null;
            int count=0, count1=0;
            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream,"ISO_8859_1");
            int event = parser.getEventType();
            while (event!=XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("item")){
                            count=1;
                            article= new Article();
                        }else if(parser.getName().equals("title")){
                            if(count==1)
                                article.title=parser.nextText().trim();
                        }

                        else if(parser.getName().equals("description")){
                            if(count==1)
                                article.description=parser.nextText().trim();

                        }else if(parser.getName().equals("link")){
                            if(count==1)
                                article.link=parser.nextText().trim();
                        }
                        else if(parser.getName().equals("pubDate")){
                            if(count==1)
                            article.publishedDate=parser.nextText().trim();
                        }
                        else if(parser.getName().equals("media:group"))
                            count1=1;
                        else if(parser.getName().equals("media:content")){
                            if(count1==1){
                                article.mediaImage=parser.getAttributeValue("","url").trim();
                                count1++;
                            }
                        }else if(parser.getName().equals("media:thumbnail"))
                            article.mediaImage=parser.getAttributeValue("","url").trim();
                        break;
                    case  XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            articles.add(article);
                        }
                        break;
                    default:
                        break;
                }
                event=parser.next();
            }

            return articles;
        }
    }
}
