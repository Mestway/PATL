package org.openfuxml.test.renderer;

import net.sf.exlp.exception.ExlpConfigurationException;
import net.sf.exlp.util.config.ConfigLoader;
import net.sf.exlp.util.io.ExlpCentralConfigPointer;
import net.sf.exlp.util.io.LoggerInit;
import net.sf.exlp.util.xml.JaxbUtil;

import org.apache.commons.configuration.Configuration;
import org.openfuxml.xml.OfxNsPrefixMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfxClientTestBootstrap
{
	final static Logger logger = LoggerFactory.getLogger(OfxClientTestBootstrap.class);
		
	public static final String cfgKeyLatexTarget = "ofx.latex.basedir";
	
	public static String xmlConfig = "config.ofx-client.test/ofx.xml";
	
	public static Configuration init()
	{
		return init(xmlConfig);
	}
	
	public static Configuration init(String configFile)
	{
		LoggerInit loggerInit = new LoggerInit("log4j.xml");
			loggerInit.addAltPath("config.ofx-client.test");
			loggerInit.init();

		JaxbUtil.setNsPrefixMapper(new OfxNsPrefixMapper());
		
		try
		{
			String cfn = ExlpCentralConfigPointer.getFile("ofx","core").getAbsolutePath();
			ConfigLoader.add(cfn);
			logger.info("Using additional config in: "+cfn );
		}
		catch (ExlpConfigurationException e)
		{
			logger.warn("No additional "+ExlpCentralConfigPointer.class.getSimpleName()+": "+e.getMessage());
		}
		ConfigLoader.add(configFile);
		Configuration config = ConfigLoader.init();					
		logger.debug("Config and Logger initialized");

		return config;
	}
}