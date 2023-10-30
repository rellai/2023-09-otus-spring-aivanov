package ru.otus.aivanov.home04.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;


@ConfigurationProperties(prefix = "test")
public class AppProps implements TestConfig, TestFileNameProvider, LocaleProvider {

    private int rightAnswersCountToPass;

    private Locale locale;

    private Map<String, String> fileNameByLocaleTag;

    private String localeBundlePath;


    public AppProps(Locale locale,
                    Map<String, String> fileNameByLocaleTag,
                    int rightAnswersCountToPass,
                    String localeBundlePath) {
        this.locale = locale;
        this.fileNameByLocaleTag = fileNameByLocaleTag;
        this.rightAnswersCountToPass = rightAnswersCountToPass;
        this.localeBundlePath = localeBundlePath;

    }

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    public Map<String, String> getFileNameByLocaleTag() {
        return fileNameByLocaleTag;
    }

    public String getLocaleBundlePath() {
        return localeBundlePath;
    }

    @Override
    public String getTestFileName() {
        return getFileNameByLocaleTag().get(locale.toLanguageTag());
    }


    @Override
    public Locale getLocale() {
        return locale;
    }
}
