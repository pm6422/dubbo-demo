package org.infinity.dubbo.democommon.service;

import org.infinity.dubbo.democommon.domain.App;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface AppService {

    Page<App> findAll(Pageable pageable);

    Optional<App> findById(String id);

    App insert(App domain);

    void update(App domain);

    void deleteById(String id);
}