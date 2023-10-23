package ru.otus.aivanov.home03.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties(prefix = "test")
public class AppProps {

    private int rightAnswersCountToPass;

    private Locale locale;

    private Map<String, String> fileNameByLocaleTag;

    private String localeBundlePath;

    @ConstructorBinding
    public AppProps(Locale locale,
                    Map<String, String> fileNameByLocaleTag,
                    int rightAnswersCountToPass,
                    String localeBundlePath) {
        this.locale = locale;
        this.fileNameByLocaleTag = fileNameByLocaleTag;
        this.rightAnswersCountToPass = rightAnswersCountToPass;
        this.localeBundlePath = localeBundlePath;

    }

    public Locale getLocale() {
        return locale;
    }

    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    public Map<String, String> getFileNameByLocaleTag() {
        return fileNameByLocaleTag;
    }

    public String getLocaleBundlePath() {
        return localeBundlePath;
    }
}
