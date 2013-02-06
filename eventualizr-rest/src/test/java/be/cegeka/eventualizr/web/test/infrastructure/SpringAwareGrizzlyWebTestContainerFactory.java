package be.cegeka.eventualizr.web.test.infrastructure;

import java.net.URI;

import javax.servlet.ServletContext;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletHandler;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainer;
import com.sun.jersey.test.framework.spi.container.grizzly2.web.GrizzlyWebTestContainerFactory;

public class SpringAwareGrizzlyWebTestContainerFactory extends
		GrizzlyWebTestContainerFactory {
	
	private Object springTarget;
	
	public SpringAwareGrizzlyWebTestContainerFactory(Object springTarget) {
		this.springTarget = springTarget;
	}

	@Override
	public TestContainer create(URI baseUri, AppDescriptor ad) {
		TestContainer container = super.create(baseUri, ad);
		autoWireSpringTarget(servletContext(container));
		return container;
	}

	private ServletContext servletContext(TestContainer container) {
		HttpServer httpServer = (HttpServer) Whitebox.getInternalState(container, "httpServer");
		ServletHandler servletHandler = (ServletHandler) httpServer.getServerConfiguration().getHttpHandlers().entrySet().iterator().next().getKey();
		ServletContext servletContext = (ServletContext) Whitebox.getInternalState(servletHandler, "servletCtx");
		return servletContext;
	}
	
	private void autoWireSpringTarget(ServletContext servletContext) {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		AutowireCapableBeanFactory beanFactory = ctx
				.getAutowireCapableBeanFactory();
		beanFactory.autowireBean(springTarget);
	}

}
