package ru.otus.aivanov.home03.service;

import org.springframework.lang.Nullable;

public interface LocalizedMessage {

    public String getLocalizedMessage(String stringForLocalize);

    public String getLocalizedMessage(String stringForLocalize, @Nullable Object[] args);

}
