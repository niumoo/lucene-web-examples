package net.codingme.lucene.web.model;

/**
 * <p>
 * 文件搜索返回实体类
 * 用于创建索引或者返回数据时
 * @Author niujinpeng
 * @Date 2018/7/22 10:56
 */
public class FileModel {
    // 文件标题
    private String title;
    // 文件内容
    private String content;
    // 文件类型
    private String fileType;
    // 文件摘要
    private String summary;
    // 时间消耗
    private float useTime;


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

    public FileModel() {
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public FileModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public FileModel(String title, String content, String summary) {
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.fileType =title.substring(title.indexOf(".")+1,title.length());
    }


    public FileModel(String title, String content, String summary,float useTime) {
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.useTime = useTime;
        this.fileType =title.substring(title.indexOf(".")+1,title.length());
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public float getUseTime() {
        return useTime;
    }

    public void setUseTime(float useTime) {
        this.useTime = useTime;
    }
}
