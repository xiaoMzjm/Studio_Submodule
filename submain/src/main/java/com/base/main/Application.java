package com.base.main;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@ComponentScan("com.base")
@EntityScan("com.base")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories("com.base")
public class Application {

	/**
	 * -Dspring.profiles.active=local
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//@Bean
	//public TomcatServletWebServerFactory servletContainer() {
	//	TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	//		@Override
	//		protected void postProcessContext(Context context) {
	//			SecurityConstraint constraint = new SecurityConstraint();
	//			constraint.setUserConstraint("CONFIDENTIAL");
	//			SecurityCollection collection = new SecurityCollection();
	//			collection.addPattern("/*");
	//			constraint.addCollection(collection);
	//			context.addConstraint(constraint);
	//		}
	//	};
	//	//tomcat.addAdditionalTomcatConnectors(httpConnector());
	//	return tomcat;
	//}
    //
	//@Bean
	//public Connector httpConnector() {
	//	Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	//	connector.setScheme("http");
	//	//Connector监听的http的端口号
	//	connector.setPort(80);
	//	connector.setSecure(false);
	//	//监听到http的端口号后转向到的https的端口号
	//	//connector.setRedirectPort(443);
	//	return connector;
	//}
}
