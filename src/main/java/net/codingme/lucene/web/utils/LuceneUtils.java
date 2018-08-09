package net.codingme.lucene.web.utils;

import net.codingme.lucene.web.model.FileModel;
import net.codingme.lucene.web.model.ik.IKAnalyzer6x;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>
 * Lucene工具类 - 创建索引
 *
 *
 * @Author niujinpeng
 * @Date 2018/7/22 11:22
 */
public class LuceneUtils {

    public static String fileDir = "src/main/resources/static/files";
    public static String indexDir = "src/main/resources/static/indexdir";

    public static void main(String[] args) throws IOException {
        createIndex();
    }

    /**
     * 创建索引
     * @throws IOException
     */
    public static void createIndex() throws IOException {
        IKAnalyzer6x ikAnalyzer6x = new IKAnalyzer6x();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(ikAnalyzer6x);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        // 索引信息存放目录
        Path indexPath = Paths.get(indexDir);
        if (!Files.isReadable(indexPath)) {
            System.out.println(indexPath.toAbsolutePath() + "目录不存在或者不可读！");
            System.exit(0);
        }

        File filesDir = new File(fileDir);
        if (!filesDir.exists()) {
            System.out.println(filesDir.getAbsolutePath() + "不存在..");
            System.exit(0);
        }

        FSDirectory fsDirectory = FSDirectory.open(indexPath);
        IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);

        FieldType fieldType = getFieldType();
        Long startTime = System.currentTimeMillis();
        File[] files = filesDir.listFiles();
        for (File file : files) {
            FileModel fileModel = TikaUtils.getFileInfo(file);
            // 创建Document
            Document document = new Document();
            document.add(new Field("title",fileModel.getTitle(),fieldType));
            document.add(new Field("content",fileModel.getContent(),fieldType));
            indexWriter.addDocument(document);
            System.out.println("正在索引"+fileModel.getTitle());
        }

        indexWriter.commit();
        indexWriter.close();
        fsDirectory.close();
        Long endTime = System.currentTimeMillis();
        System.out.println("索引文档完成，耗时："+(endTime - startTime) +"ms");
    }

    /**
     * 获取一个FieldType
     * @return
     */
    public static FieldType getFieldType() {
        FieldType fieldType = new FieldType();
        fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        fieldType.setStored(true);
        fieldType.setTokenized(true);
        fieldType.setStoreTermVectors(true);
        fieldType.setStoreTermVectorPositions(true);
        fieldType.setStoreTermVectorOffsets(true);
        return fieldType;
    }



}
