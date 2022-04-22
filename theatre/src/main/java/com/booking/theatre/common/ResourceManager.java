package com.booking.theatre.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;

@Component
public class ResourceManager {

	@Autowired
	private MessageSource messageSource;

	private MessageSourceAccessor messageSourceAccessor;

	@PostConstruct
	private void init() {
		messageSourceAccessor = new MessageSourceAccessor(messageSource);
	}

	public String getMessage(final String key) {
		return getMessage(key, (Object[]) null);
	}

	public String getMessage(final String key, final String defaultMessage) {
		return messageSourceAccessor.getMessage(key, defaultMessage);
	}

	public String getMessage(final String key, final Object[] parameters) {
		return messageSourceAccessor.getMessage(key, parameters);
	}

	public String getDefaultMessage(final String key) {
		return getDefaultMessage(key, (Object[]) null);
	}

	public String getDefaultMessage(final String key, final Object[] parameters) {
		return messageSourceAccessor.getMessage(key, parameters);
	}
}
