package com.doopp.gauss.server.module;

import com.doopp.gauss.oauth.utils.IdWorker;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class ApplicationModule extends AbstractModule {

	@Override
	public void configure() {
		//bind(MapApiService.class).to(MapApiServiceGaodeImpl.class).in(Scopes.SINGLETON);
		//bind(PointService.class).to(PointServiceImpl.class).in(Scopes.SINGLETON);
		// bind(PointDao.class).in(Scopes.SINGLETON);
	}

	@Singleton
	@Provides
	public IdWorker userIdWorker() {
		return new IdWorker(1, 1);
	}
}
