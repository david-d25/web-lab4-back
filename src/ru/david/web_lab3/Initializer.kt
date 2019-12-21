package ru.david.web_lab3

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import javax.servlet.ServletContext

class Initializer : WebApplicationInitializer {

    companion object {
        private const val DISPATCHER_SERVLET_NAME = "dispatcher"
    }

    override fun onStartup(servletContext: ServletContext) {
        val ctx = AnnotationConfigWebApplicationContext()
        ctx.register(WebAppConfig::class.java)
        ctx.servletContext = servletContext
        servletContext.addListener(ContextLoaderListener(ctx))

        val servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, DispatcherServlet(ctx))
        servlet.setLoadOnStartup(1)
        servlet.addMapping("/")
    }
}
