package com.company.demo_app;

import com.company.demo_app.controllers.ConfigurationController;
import com.company.demo_app.controllers.MatchActionsController;
import com.company.demo_app.controllers.MatchOverviewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoAppApplicationTests {

    @Autowired
    private MatchOverviewController matchOverviewController;
    @Autowired
    private MatchActionsController matchActionsController;
    @Autowired
    private ConfigurationController configurationController;

    @Test
    void contextLoads() {
        assertThat(matchOverviewController).isNotNull();
        assertThat(matchActionsController).isNotNull();
        assertThat(configurationController).isNotNull();
    }
}
