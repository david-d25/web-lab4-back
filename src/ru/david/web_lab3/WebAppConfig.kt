package ru.david.web_lab3

import org.hibernate.ejb.HibernatePersistence
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.util.*
import javax.annotation.Resource
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.mail.Session
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

const val PROP_SMTP_HOST = "smtp.host"
const val PROP_SMTP_PORT = "smtp.port"
const val PROP_SMTP_SSL = "smtp.ssl"
const val PROP_SMTP_USERNAME = "smtp.username"
const val PROP_SMTP_PASSWORD = "smtp.password"
const val PROP_SMTP_FROM = "smtp.from"

@Configuration
@EnableWebMvc
@EnableScheduling
@EnableTransactionManagement
@EnableJpaRepositories("ru.david.web_lab3")
@PropertySource("classpath:app.properties")
@ComponentScan("ru.david.web_lab3")
open class WebAppConfig {

    @Resource
    private val env: Environment? = null

    @Bean
    open fun preSalt(): String = env!!.getRequiredProperty(PROP_SERVER_PRE_SALT)

    @Bean
    open fun postSalt(): String = env!!.getRequiredProperty(PROP_SERVER_POST_SALT)

    @Bean
    open fun emailFrom(): String = env!!.getRequiredProperty(PROP_SMTP_FROM)

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

    @Bean
    open fun mailSession(): Session {
        env!!

        val properties = Properties()
        properties["mail.smtp.host"] = env.getRequiredProperty(PROP_SMTP_HOST)
        properties["mail.smtp.port"] = env.getRequiredProperty(PROP_SMTP_PORT)
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.ssl.enable"] = env.getRequiredProperty(PROP_SMTP_SSL)
        properties["mail.mime.charset"] = "UTF-16"
        properties["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"

        val mailAuth: Authenticator = object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(
                        env.getRequiredProperty(PROP_SMTP_USERNAME),
                        env.getRequiredProperty(PROP_SMTP_PASSWORD))
            }
        }

        return Session.getInstance(properties, mailAuth)
    }

    private fun getHibernateProperties(): Properties {
        val properties = Properties()
        properties["hibernate.dialect"] = env!!.getRequiredProperty(PROP_HIBERNATE_DIALECT)
        properties["hibernate.show_sql"] = env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL)
        properties["hibernate.hbm2ddl.auto"] = env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO)
        return properties
    }
}
