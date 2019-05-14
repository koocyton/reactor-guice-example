package com.doopp.gauss.server.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.doopp.gauss.app.utils.IdWorker;

public class ApplicationModule extends AbstractModule {

	@Override
	public void configure() {
		// bind(OAuthService.class).to(OAuthServiceImpl.class).in(Scopes.SINGLETON);
	}

	@Singleton
	@Provides
	public IdWorker userIdWorker() {
		return new IdWorker(1, 1);
	}
}
