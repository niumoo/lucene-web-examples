package net.codingme.lucene.web.controller;

import net.codingme.lucene.web.model.FileModel;
import net.codingme.lucene.web.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *
 * @Author niujinpeng
 * @Date 2018/7/22 13:24
 */
@Controller
@RequestMapping(value = "/search")
public class LuceneController {

    @Autowired
    private LuceneService luceneService;


    @GetMapping(value = "/list")
    public String searchList(String keyword, Integer num, Model model) throws Exception {
        List<FileModel> fileModelList = luceneService.getTopN(keyword, num);
        model.addAttribute("fileModelList", fileModelList);
        return "result";
    }
}
