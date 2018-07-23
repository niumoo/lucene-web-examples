package net.codingme.lucene.web.utils;

import net.codingme.lucene.web.model.FileModel;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户创建索引信息的工具类
 *
 * @Author niujinpeng
 * @Date 2018/7/22 11:09
 */
public class TikaUtils {

    /**
     * 传入文件夹路径，返回FileMode对象
     *
     * @param filePath
     * @return
     */
    public static List<FileModel> parseFile(String filePath) {
        ArrayList<FileModel> fileModeList = new ArrayList<>();
        File fileDir = new File(filePath);
        File[] files = fileDir.listFiles();

        for (File file : files) {
            FileModel fileMode = getFileInfo(file);
            fileModeList.add(fileMode);
        }
        return fileModeList;
    }

    /**
     * 使用Tika解析出文件内容
     *
     * @param file 文件file对象
     * @return
     */
    public static FileModel getFileInfo(File file) {
        FileModel fileModel = null;
        Tika tika = new Tika();
        // 创建元数据对象
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputStream = null;
        AutoDetectParser parser = new AutoDetectParser();
        ParseContext context = new ParseContext();
        try {
            inputStream = new FileInputStream(file);
            parser.parse(inputStream, handler, metadata, context);
            fileModel = new FileModel(file.getName(), handler.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileModel;
    }


}
