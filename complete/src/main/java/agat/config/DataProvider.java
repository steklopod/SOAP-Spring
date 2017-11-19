package agat.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * Конфигурация соединения с БД и самого быстрого из существующих Connection Poll'ов (Hikari).
 * Все необходимые настройки находятся в application.properties (папка resources).
 */

@Configuration
@ComponentScan(basePackages = "agat")
@PropertySource("classpath:application.properties")
public class DataProvider {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;



    @Bean(name = "myDataSource")
    @Primary
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(8);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");
//        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", true);
//        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", 250);
//        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", 2048);
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", true);
        HikariDataSource ds = new HikariDataSource(hikariConfig);
        return ds;

    }


    //@EnableJpaRepositories(basePackages = "ru.sdpr.agat.database")
    //@ConfigurationProperties(value = "application.properties")
    //@EnableTransactionManagement



//    @Bean(name = "myJpaVendorAdapter")
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        hibernateJpaVendorAdapter.setShowSql(true);
//        hibernateJpaVendorAdapter.setGenerateDdl(true);
//        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
//        return hibernateJpaVendorAdapter;
//    }
//
//    @Bean (name = "myEntityManagerFactoryBean")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(/*@Qualifier("myDataSource") DataSource dataSource, JpaVendorAdapter jpaVendorAdapter*/)  {
//        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
//        lef.setDataSource(dataSource());
////        lef.setDataSource(dataSource);
//        lef.setJpaVendorAdapter(jpaVendorAdapter());
////        lef.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        lef.setPackagesToScan("ru.sdpr.agat.*");
//
//        Properties properties = new Properties();
//            properties.setProperty("hibernate.show_sql", "true");
//            properties.setProperty("hibernate.format_sql", "true");
//            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
//            properties.setProperty("hibernate.connection.shutdown", "true");
//            properties.setProperty("hibernate.classloading.use_current_tccl_as_parent", "false");
//            properties.setProperty("hibernate.proc.param_null_passing", "true");
//            properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
//            properties.setProperty("hibernate.hbm2ddl.auto", "validate");
//        lef.setJpaProperties(properties);
//        lef.afterPropertiesSet();
//
//        return lef;
//    }
//
//
//    @Bean(name = "myTransactionManager")
//    public PlatformTransactionManager transactionManager() {
//        return new JpaTransactionManager();
//    }

}