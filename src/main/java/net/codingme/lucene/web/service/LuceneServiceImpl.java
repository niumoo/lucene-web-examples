package net.codingme.lucene.web.service;

import net.codingme.lucene.web.model.FileModel;
import net.codingme.lucene.web.utils.LuceneQueryUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2018/7/22 12:24
 */
@Service
public class LuceneServiceImpl implements LuceneService {

    /**
     * 查询前N条数据
     *
     * @param keywrod
     * @param num
     * @return
     * @throws Exception
     */
    @Override
    public List<FileModel> getTopN(String keywrod, Integer num) throws Exception {
        if (StringUtils.isEmpty(keywrod)) {
            throw new Exception("关键词不能为空");
        }
        if (num == null) {
            num = 10;
        }
        return LuceneQueryUtil.getTopN(keywrod, num);
    }
}
