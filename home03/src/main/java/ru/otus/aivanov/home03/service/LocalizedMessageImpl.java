package ru.otus.aivanov.home03.service;

import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.otus.aivanov.home03.config.AppProps;

@Service
public class LocalizedMessageImpl implements LocalizedMessage {

    private final MessageSource messageSource;

    private final AppProps props;

    public LocalizedMessageImpl(MessageSource messageSource, AppProps props) {
        this.props = props;
        this.messageSource = messageSource;
    }

    @Override
    public String getLocalizedMessage(String stringForLocalize) {
        return messageSource.getMessage(stringForLocalize, null, stringForLocalize, props.getLocale());
    }


    @Override
    public String getLocalizedMessage(String stringForLocalize, @Nullable Object[] args) {
        return messageSource.getMessage(stringForLocalize, args, stringForLocalize , props.getLocale());
    }

}