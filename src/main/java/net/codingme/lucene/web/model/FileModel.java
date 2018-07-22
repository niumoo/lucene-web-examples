package net.codingme.lucene.web.model;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2018/7/22 10:56
 */
public class FileModel {
    // 文件标题
    private String title;
    // 文件内容
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    FileModel(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
