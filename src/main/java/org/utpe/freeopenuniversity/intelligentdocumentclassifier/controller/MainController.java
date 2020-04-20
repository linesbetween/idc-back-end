package org.utpe.freeopenuniversity.intelligentdocumentclassifier.controller;

//////////////////////   how to remember
import edu.stanford.nlp.classify.ColumnDataClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess.ClassifierOperation;

//接收前端信息， 返回后端数据
@Controller                              //  set request response
public class MainController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
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
    public String engine(@RequestParam(value = "terms") String terms) {
        System.out.println(terms);
        return ClassifierOperation.predictSentence(columnDataClassifier, terms);
//                documentClassifier.columnDataClassifier.classOf(terms);
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

