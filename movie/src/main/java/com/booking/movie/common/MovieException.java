package com.booking.movie.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class MovieException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;
	private final String titleKey;
	private final String detailKey;
	private final String type;
	private final transient Object[] args;
	private final transient ResourceManager resourceManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieException.class);

	public MovieException(final ResourceManager resourceManager, final String titleKey, final String detailKey,
			final HttpStatus status) {
		this(resourceManager, titleKey, detailKey, null, status, null);
	}

	public MovieException(final ResourceManager resourceManager, final String titleKey, final String detailKey,
			final HttpStatus status, final Throwable cause) {
		this(resourceManager, titleKey, detailKey, status);
		this.initCause(cause);
	}

	public MovieException(final ResourceManager resourceManager, final String titleKey, final HttpStatus status) {
		this(resourceManager, titleKey, titleKey, null, status, null);
	}

	public MovieException(final ResourceManager resourceManager, final String titleKey, final String detailKey,
			final Object[] args, final HttpStatus status, final String type) {
		super(detailKey);
		this.titleKey = titleKey;
		this.detailKey = detailKey;
		this.args = args;
		this.status = status;
		this.type = type;
		this.resourceManager = resourceManager;
	}

	public String getTitle(final boolean localize) {
		return getResource(titleKey, localize);
	}

	public String getDetails(final boolean localize) {
		return getResource(detailKey, args, localize);
	}

	private String getResource(final String key, final boolean localize) {
		return localize ? resourceManager.getMessage(key) : resourceManager.getDefaultMessage(key);
	}

	private String getResource(final String key, final Object[] args, final boolean localize) {
		try {
			return localize ? resourceManager.getMessage(key, args) : resourceManager.getDefaultMessage(key, args);
		} catch (final NullPointerException e) {
			LOGGER.error("Message not found and caught into exeption", e);
			return "Message not found";
		}
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	@Override
	public String getMessage() {
		return getDetails(false);
	}

}
