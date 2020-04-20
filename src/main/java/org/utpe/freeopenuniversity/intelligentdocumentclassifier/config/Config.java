package org.utpe.freeopenuniversity.intelligentdocumentclassifier.config;

import edu.stanford.nlp.classify.ColumnDataClassifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess.ClassifierOperation;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess.Constant;
import org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.ui.UI;

import java.io.*;

//config used for?   create beans
//开启后端服务。 生产2个bean实体，供controller调用function
@Configuration
public class Config {
	//这个bean供QueryService用和controller用
	@Bean
	public ColumnDataClassifier columnDataClassifier() {
		String propPath = Constant.DATAPATH.value + File.separator + "property" + File.separator + "my.prop";
		ColumnDataClassifier columnDataClassifier =  new ColumnDataClassifier(propPath);
		System.out.println(columnDataClassifier.toString());
		return ClassifierOperation.scanDataSet(columnDataClassifier);
	}
//	@Bean
//	public UI ui() {
//		System.setProperty("java.awt.headless","false");
//		return new UI();
//	}
}


