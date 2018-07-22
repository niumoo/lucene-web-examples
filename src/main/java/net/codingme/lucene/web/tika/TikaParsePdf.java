package net.codingme.lucene.web.tika;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.asm.ClassParser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 * 使用Tika解析PDF文件
 * // 解析MS Office
 * new OOXMLParser();
 * // 解析文本
 * new TXTParser();
 * // 解析HTML
 * new HtmlParser();
 * // 解析XML
 * new XMLParser();
 * // 解析Class
 * new ClassParser();
 *
 * @Author niujinpeng
 * @Date 2018/7/21 23:12
 */
public class TikaParsePdf {

    public static void main(String[] args) throws IOException, TikaException, SAXException {
        // 文件路径
        String filepath = "files/Linux命令行大全.pdf";
        // 创建Fiel对象
        File pdfFile = new File(filepath);
        // 创建内容处理器对象
        BodyContentHandler handler = new BodyContentHandler();
        // 创建元数据对象
        Metadata metadata = new Metadata();
        // 读取文件
        FileInputStream inputStream = new FileInputStream(pdfFile);
        // 创建内容解析器对象
        ParseContext parseContext = new ParseContext();
        // 实例化PDFParser
        PDFParser pdfParser = new PDFParser();
        // 调用parse（）方法解析文件
        pdfParser.parse(inputStream, handler, metadata, parseContext);

        // 遍历元数据内容
        System.out.println("文件属性信息：");
        for (String name : metadata.names()) {
            System.out.println(name + "：" + metadata.get(name));
        }
        // 输出PDF文件内容
        System.out.println("PDF文件中的内容：");
        System.out.println(handler.toString());

    }

}





















