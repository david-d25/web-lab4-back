package ru.david.web_lab3

import org.hibernate.ejb.HibernatePersistence
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.Ordered
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.HttpStatus
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*
import javax.annotation.Resource
import javax.sql.DataSource


const val PROP_DATABASE_DRIVER = "db.driver"
const val PROP_DATABASE_PASSWORD = "db.password"
const val PROP_DATABASE_URL = "db.url"
const val PROP_DATABASE_USERNAME = "db.username"
const val PROP_HIBERNATE_DIALECT = "db.hibernate.dialect"
const val PROP_HIBERNATE_SHOW_SQL = "db.hibernate.show_sql"
const val PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages_to_scan"
const val PROP_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto"

const val PROP_SERVER_PRE_SALT = "server.pre_salt"
const val PROP_SERVER_POST_SALT = "server.post_salt"

const val PROP_MAIL_API_KEY = "mail.api_key"
const val PROP_MAIL_FROM = "mail.from"

@Configuration
@EnableWebMvc
@EnableScheduling
@EnableTransactionManagement
@EnableJpaRepositories("ru.david.web_lab3")
@PropertySource("classpath:app.properties")
@ComponentScan("ru.david.web_lab3")
open class WebAppConfig : WebMvcConfigurer {

    @Resource
    private val env: Environment? = null

    @Bean
    open fun preSalt(): String = env!!.getRequiredProperty(PROP_SERVER_PRE_SALT)

    @Bean
    open fun postSalt(): String = env!!.getRequiredProperty(PROP_SERVER_POST_SALT)

    @Bean
    open fun emailFrom(): String = env!!.getRequiredProperty(PROP_MAIL_FROM)

    @Bean
    open fun mailApiKey(): String = env!!.getRequiredProperty(PROP_MAIL_API_KEY)

    @Bean
    open fun dataSource(): DataSource {
        env!!

        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER))
        dataSource.url = env.getRequiredProperty(PROP_DATABASE_URL)
        dataSource.username = env.getRequiredProperty(PROP_DATABASE_USERNAME)
        dataSource.password = env.getRequiredProperty(PROP_DATABASE_PASSWORD)
        return dataSource
    }

    @Bean
    open fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean? {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = dataSource()
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence::class.java)
        entityManagerFactoryBean.setPackagesToScan(env!!.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN))
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties())
        return entityManagerFactoryBean
    }

    @Bean
    open fun transactionManager(): JpaTransactionManager? {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory()!!.getObject()
        return transactionManager
    }

    private fun getHibernateProperties(): Properties {
        val properties = Properties()
        properties["hibernate.dialect"] = env!!.getRequiredProperty(PROP_HIBERNATE_DIALECT)
        properties["hibernate.show_sql"] = env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL)
        properties["hibernate.hbm2ddl.auto"] = env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO)
        return properties
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/*").addResourceLocations("/")
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("forward:/index.html")
        registry.addViewController("/login").setViewName("forward:/index.html")
        registry.addViewController("/main").setViewName("forward:/index.html")
        registry.addViewController("/register").setViewName("forward:/index.html")
    }
}
