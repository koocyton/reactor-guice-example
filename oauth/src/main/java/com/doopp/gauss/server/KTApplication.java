package com.doopp.gauss.server;

import com.doopp.gauss.server.json.MyGsonHttpMessageConverter;
import com.doopp.reactor.guice.ReactorGuiceServer;
import com.doopp.reactor.guice.json.GsonHttpMessageConverter;
import com.github.pagehelper.PageInterceptor;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.doopp.gauss.server.database.HikariDataSourceProvider;
import com.doopp.gauss.server.module.ApplicationModule;
import com.doopp.gauss.server.module.RedisModule;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class KTApplication {

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(args[0]));

        Injector injector = Guice.createInjector(

                // application Properties
                binder -> Names.bindProperties(binder, properties),

                // mybatis
                new MyBatisModule() {
                    @Override
                    protected void initialize() {
                        install(JdbcHelper.MySQL);
                        bindDataSourceProviderType(HikariDataSourceProvider.class);
                        bindTransactionFactoryType(JdbcTransactionFactory.class);
                        addMapperClasses("com.doopp.gauss.oauth.dao");
                        addInterceptorClass(PageInterceptor.class);
                    }
                },

                // redis
                new RedisModule(),

                // application
                new ApplicationModule()
        );

        String host = properties.getProperty("server.host", "127.0.0.1");
        int port = Integer.valueOf(properties.getProperty("server.port", "8081"));

        ReactorGuiceServer.create()
                .bind(host, port)
                .injector(injector)
                .setHttpMessageConverter(new MyGsonHttpMessageConverter())
                .handlePackages("com.doopp.gauss.oauth.handle")
                .addFilter("/", AppFilter.class)
                .launch();
    }
}