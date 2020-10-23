package org.utpe.freeopenuniversity.intelligentdocumentclassifier.controller;


import edu.stanford.nlp.classify.ColumnDataClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.beans.PredictionResult;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess.ClassifierOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess.Constant;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

//接收前端信息， 返回后端数据
@Controller                              //  set request response
public class MainController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        System.out.println("welcome!");
        return "hello world";
    }
// 在config产生的bean，在这就直接用
    private ColumnDataClassifier columnDataClassifier;   // can antowired here ?

    @Autowired                            //  upload beans
    public void setCDC(ColumnDataClassifier columnDataClassifier) {
        this.columnDataClassifier = columnDataClassifier;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public PredictionResult engine(@RequestBody String terms) {
        System.out.println(terms);

        return new PredictionResult(
                ClassifierOperation.predictSentence(columnDataClassifier, terms));
//                documentClassifier.columnDataClassifier.classOf(terms);
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload/{color}")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,
                         @PathVariable("color") String color) {
        if (file.isEmpty()) {
            return "upload failed";
        }

        String fileName = file.getOriginalFilename();
        String filePath = Constant.DATAPATH.value + File.separator + "category" + File.separator + color;
        File dest = new File(filePath + File.separator + fileName);
        try {
            new File(filePath).mkdirs();
            file.transferTo(dest);
            LOGGER.info("upload successfully in data/category/" + color + " folder");
            return "upload successfully in data/category/" + color + " folder";
        } catch (IOException e) {
            System.out.println(dest);
            LOGGER.error(e.toString(), e);
        }
        return "upload failed！";
    }

}

// 连接前端静态页面
@Configuration    // why configuration here?  viewController connect java and js
class MvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);
    }
}  

