package ru.otus.aivanov.home02.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration  implements TestConfig, TestFileNameProvider  {

    @Value("${test.fileName}")
    private String testFileName;

    @Value("${test.rightAnswersCountToPass}")
    private int rightAnswersCountToPass;

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public String getTestFileName() {
        return testFileName;
    }

}
