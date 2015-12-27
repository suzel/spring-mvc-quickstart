package ${package}.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "${package}.repository" })
class DatabaseConfig {

	@Value("${dataSource.driverClassName}")
	private String driver;
	
	@Value("${dataSource.url}")
	private String url;
	
	@Value("${dataSource.username}")
	private String username;
	
	@Value("${dataSource.password}")
	private String password;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddlAuto;
	
	@Value("${hibernate.default.schema}")
	private String defaultschema;
	
	@Value("${hibernate.ejb.naming_strategy}")
	private String ejbNamingStrategy;
	
	@Value("${hibernate.show_sql}")
	private String showSQL;
	
	@Value("${hibernate.format_sql}")
	private String formatSQL;

	@Bean(destroyMethod = "close")
	DataSource dataSource() {

		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(driver);
		dataSourceConfig.setJdbcUrl(url);
		dataSourceConfig.setUsername(username);
		dataSourceConfig.setPassword(password);
		// dataSourceConfig.addDataSourceProperty("cachePrepStmts", "true");
		// dataSourceConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		// dataSourceConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		// dataSourceConfig.addDataSourceProperty("useServerPrepStmts", "true");
		
		return new HikariDataSource(dataSourceConfig);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("${package}.domain");

		Properties jpaProperties = new Properties();
		jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
		jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
		jpaProperties.put(org.hibernate.cfg.Environment.DEFAULT_SCHEMA, defaultschema);
		jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, showSQL);
		jpaProperties.put(org.hibernate.cfg.Environment.FORMAT_SQL, formatSQL);
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		
		return transactionManager;
	}

}