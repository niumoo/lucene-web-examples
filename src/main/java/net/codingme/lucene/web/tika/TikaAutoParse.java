package net.codingme.lucene.web.tika;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>
 * 使用Tika自动判断文件类型进行文件解析
 *
 * @Author niujinpeng
 * @Date 2018/7/22 10:44
 */
public class TikaAutoParse {

    public static void main(String[] args) throws IOException, TikaException, SAXException {
        Tika tika = new Tika();
        // 指定存放文件的文件夹
        File files = new File("files");
        if (!files.exists()) {
            System.out.println("文件夹不存在...");
            System.exit(0);
        }

        // 遍历文件
        File[] fileArr = files.listFiles();

        BodyContentHandler handler = new BodyContentHandler();
        // 创建元数据对象
        Metadata metadata = new Metadata();
        FileInputStream inputStream = null;
        AutoDetectParser parser = new AutoDetectParser();
        ParseContext context = new ParseContext();
        for (File file : fileArr) {
            // String fileContent = tika.parseToString(file);
            inputStream = new FileInputStream(file);
            parser.parse(inputStream, handler, metadata, context);
            System.out.println("文件名称：" + file.getName());
            System.out.println("文件内容：" + handler.toString());
            System.out.println("--------------------------");
        }
    }
}












