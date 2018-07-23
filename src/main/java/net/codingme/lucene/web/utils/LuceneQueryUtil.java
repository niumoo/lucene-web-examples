package net.codingme.lucene.web.utils;

import net.codingme.lucene.web.model.FileModel;
import net.codingme.lucene.web.model.ik.IKAnalyzer6x;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Lucene查询常用操作封装
 *
 * @author niujinpeng
 * @date 2018年7月8日下午11:41:52
 */
public class LuceneQueryUtil {

	// 索引存放目录
	private static String INDEX_DIR =  "src/main/resources/static/indexdir";
	
	private static IndexSearcher indexSearcher;

	private static FSDirectory directory;

	private static DirectoryReader directoryReader;

	private static String HIGH_LIGHT_FIELD = "content";

	public static void main(String[] args) throws ParseException, InvalidTokenOffsetsException, IOException {
		getTopN("删除",10);
	}

	public static List<FileModel> getTopN(String keywrod,Integer num) throws IOException, ParseException, InvalidTokenOffsetsException {
        ArrayList<FileModel> fileModels = new ArrayList<>();
        // 获取搜索对象
		IndexSearcher indexSearch = LuceneQueryUtil.getIndexSearch();
		// 获取IK分词器
		IKAnalyzer6x ikAnalyzer6x = new IKAnalyzer6x();
		// 高亮字段
		QueryParser queryParser = new QueryParser(HIGH_LIGHT_FIELD, ikAnalyzer6x);
		Query query = queryParser.parse(keywrod);
		System.out.println("查询Query：" + query.toString());
		TopDocs topDocs = indexSearch.search(query, num);

		// 定义高亮
		QueryScorer queryScorer = new QueryScorer(query, HIGH_LIGHT_FIELD);
		// 定制高亮标签
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, queryScorer);

		// 输出
		for (ScoreDoc sdoc : topDocs.scoreDocs) {
			Document document = indexSearch.doc(sdoc.doc);
			// 获取TokenStream
			TokenStream tokenStream = TokenSources.getAnyTokenStream(indexSearch.getIndexReader(), sdoc.doc, HIGH_LIGHT_FIELD, ikAnalyzer6x);
			SimpleSpanFragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
			highlighter.setTextFragmenter(fragmenter);
			// 获取高亮
			String bestFragment = highlighter.getBestFragment(tokenStream, document.get(HIGH_LIGHT_FIELD));
            fileModels.add(new FileModel(document.get("title"),null,bestFragment));
		}
		LuceneQueryUtil.close();
        return fileModels;
    }

	/**
	 * 输出查询结果信息
	 * 
	 * @param query
	 * @throws IOException
	 */
	public static void printQueryInfo(Query query, TopDocs topDocs) throws IOException {
		System.out.println("query语句：" + query.toString());
		for (ScoreDoc sdoc : topDocs.scoreDocs) {
			Document document = indexSearcher.doc(sdoc.doc);
			System.out.println("DocId：" + sdoc.doc);
			System.out.println("id：" + document.get("id"));
			System.out.println("title：" + document.get("title"));
			System.out.println("content："+document.get("content"));
			System.out.println("文档评分：" + sdoc.score);
		}
	}

	/**
	 * 获取索引信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public static IndexSearcher getIndexSearch() throws IOException {
		Path indexPath = Paths.get(INDEX_DIR);
		directory = FSDirectory.open(indexPath);
		directoryReader = DirectoryReader.open(directory);
		indexSearcher = new IndexSearcher(directoryReader);
		return indexSearcher;
	}

	/**
	 * 释放资源
	 * 
	 * @throws IOException
	 */
	public static void close() throws IOException {
		if (directoryReader != null) {
			directoryReader.close();
		}
		if (directory != null) {
			directory.close();
		}
	}

}
