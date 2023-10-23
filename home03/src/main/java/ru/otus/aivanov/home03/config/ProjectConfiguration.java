package ru.otus.aivanov.home03.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@EnableConfigurationProperties(AppProps.class)
public class ProjectConfiguration  implements TestConfig, TestFileNameProvider  {

    @Autowired
    private AppProps appProps;

    @Override
    public int getRightAnswersCountToPass() {
        return appProps.getRightAnswersCountToPass();
    }

    @Override
    public String getTestFileName() {
        Locale locale = appProps.getLocale();
        return appProps.getFileNameByLocaleTag().get(locale.toLanguageTag());
    }

}



