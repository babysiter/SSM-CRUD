package zjut.lhd.test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class first {
	public void generator() throws SQLException, IOException, InterruptedException, InvalidConfigurationException, XMLParserException{
		List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		File configFile = new File("generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config =cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator =new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
	public static void main(String[] args) throws SQLException, IOException, InterruptedException, XMLParserException, InvalidConfigurationException {
		  try {
			  first f =new first();
	            f.generator();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
