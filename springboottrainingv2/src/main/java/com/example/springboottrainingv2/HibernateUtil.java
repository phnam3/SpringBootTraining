//package com.example.springboottrainingv2;
//
//import com.example.springboottrainingv2.entity.School;
//import com.example.springboottrainingv2.entity.User;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Properties;
//
//public class HibernateUtil {
//    private static SessionFactory sessionFactory;
//
//    public static SessionFactory getSessionFactory() {
//        try {
//            Properties properties = getProperties();
//            Configuration configuration = new Configuration();
//            configuration.setProperties(properties);
//            configuration.addAnnotatedClass(User.class);
//            configuration.addAnnotatedClass(School.class);
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties()).build();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            return sessionFactory;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sessionFactory;
//    }
//
//    private static Properties getProperties() throws IOException {
//        Properties properties = new Properties();
//        URL propertiesURL = Thread.currentThread()
//                .getContextClassLoader()
//                .getResource("hibernate.properties");
//        try (FileInputStream inputStream = new FileInputStream(propertiesURL.getFile())) {
//            properties.load(inputStream);
//        }
//        return properties;
//    }
//}
