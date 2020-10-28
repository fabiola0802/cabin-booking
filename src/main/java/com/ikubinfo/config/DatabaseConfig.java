package com.ikubinfo.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ikubinfo.utils.AppProperties;
import com.ikubinfo.utils.AppProperties.HibernateProperties;
import com.ikubinfo.utils.AppProperties.JdbcProperties;

@Configuration
@EnableTransactionManagement
@PropertySources({ @PropertySource("classpath:" + AppProperties.DATABASE_PROPERTIES),
		@PropertySource("classpath:" + AppProperties.HIBERNATE_PROPERTIES) })
public class DatabaseConfig {

	@Autowired
	private Environment environment;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty(JdbcProperties.DRIVER_CLASS_NAME));
		dataSource.setUrl(environment.getProperty(JdbcProperties.URL));
		dataSource.setUsername(environment.getProperty(JdbcProperties.USERNAME));
		dataSource.setPassword(environment.getProperty(JdbcProperties.PASSWORD));
		return dataSource;
	}

	@Bean
	public Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put(HibernateProperties.DIALECT, environment.getProperty(HibernateProperties.DIALECT));
		hibernateProperties.put(HibernateProperties.SHOW_SQL, environment.getProperty(HibernateProperties.SHOW_SQL));
		hibernateProperties.put(HibernateProperties.FORMAT_SQL,
				environment.getProperty(HibernateProperties.FORMAT_SQL));
		hibernateProperties.put(HibernateProperties.USE_SQL_COMMENTS,
				environment.getProperty(HibernateProperties.USE_SQL_COMMENTS));
		hibernateProperties.put(HibernateProperties.HBM2DDL_AUTO,
				environment.getProperty(HibernateProperties.HBM2DDL_AUTO));
		return hibernateProperties;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPackagesToScan(AppProperties.PACKAGE_ENTITIES);
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean.getNativeEntityManagerFactory();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

}
