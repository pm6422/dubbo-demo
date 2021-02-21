package org.infinity.dubbo.democlient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.infinity.dubbo.democommon.domain.App;
import org.infinity.dubbo.democommon.service.AppService;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "测试")
@Slf4j
public class TestController {

    private final ApplicationContext applicationContext;
    private final Environment        env;
    @Reference(url = "127.0.0.1:24010")
    private       AppService         appService;

    public TestController(ApplicationContext applicationContext, Environment env) {
        this.applicationContext = applicationContext;
        this.env = env;
    }

    @ApiOperation("测试直连")
    @GetMapping("/api/test/direct-url")
    public List<App> testDirectUrl() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<App> all = appService.findAll(pageable);
        return all.getContent();
    }
}