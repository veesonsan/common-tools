package org.veeson.common.tools.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class StartWeb {
	public static void main(String[] args) {
		try {
			Server server = new Server();
			ClassPathResource configPath = new ClassPathResource("config.properties");
			Properties p = new Properties(System.getProperties());
			p.load(configPath.getInputStream());

			ClassPathResource classpath = new ClassPathResource("jetty.xml");
			XmlConfiguration xmlConfig = new XmlConfiguration(classpath.getInputStream());
			Map<String, String> props = new HashMap<String, String>();
			for (Object key : p.keySet()) {
				props.put(key.toString(), String.valueOf(p.get(key)));
			}
			xmlConfig.getProperties().putAll(props);
			xmlConfig.configure();
			xmlConfig.configure(server);

			WebAppContext webAppContext = new WebAppContext();
			FileSystemResource webappFile = new FileSystemResource("./src/main/webapp/");
			if (!webappFile.exists()) {
				throw new Exception("webapp path is wrong!");
			}
			webAppContext.setResourceBase(webappFile.getFile().getCanonicalPath());
			webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
			webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.redirectWelcome", "false");
			webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.welcomeServlets", "exact");
			webAppContext.setContextPath("/");
			webAppContext.setWelcomeFiles(new String[] { "index.htm" });
			server.setHandler(webAppContext);
			server.start();
			server.join();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
}
