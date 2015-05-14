package cn.lesaas.osb.service;

import org.junit.Test;

import cn.lesaas.osb.util.Constants;
import cn.lesaas.osb.util.LoadPopertiesFile;

public class loadSystemFile {
	@Test  
    public void loadSqlFile() {  
        String filePath = "system.properties";       
        LoadPopertiesFile.loadSystemFile(filePath);          
        String system = Constants.loadSystemMap.get("10050".toUpperCase());  
        System.out.println(system);
    } 
}
