package org.infinity.dubbo.demoserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.infinity.dubbo.democommon.domain.App;
import org.infinity.dubbo.democommon.service.AppService;
import org.infinity.dubbo.demoserver.exception.NoDataFoundException;
import org.infinity.dubbo.demoserver.repository.AppRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@DubboService(group = "g1", retries = 1)
@Slf4j
public class AppService1Impl implements AppService {

    private final AppRepository appRepository;

    public AppService1Impl(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public Page<App> findAll(Pageable pageable) {
        return appRepository.findAll(pageable);
    }

    @Override
    public Optional<App> findById(String id) {
        return appRepository.findById(id);
    }

    @Override
    public App insert(App domain) {
        appRepository.save(domain);
        log.debug("Created Information for app: {}", domain);
        return domain;
    }

    @Override
    public void update(App domain) {
        appRepository.findById(domain.getName()).map(app -> {
            app.setEnabled(domain.getEnabled());
            appRepository.save(app);
            log.debug("Updated app: {}", app);
            return app;
        }).orElseThrow(() -> new NoDataFoundException(domain.getName()));
    }

    @Override
    public void deleteById(String id) {
        appRepository.deleteById(id);
    }
}