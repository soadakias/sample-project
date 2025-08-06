package com.company.demo_app;

import com.company.demo_app.controllers.ConfigurationController;
import com.company.demo_app.controllers.MatchActionsController;
import com.company.demo_app.controllers.MatchOverviewController;
import com.company.demo_app.services.ConfigurationService;
import com.company.demo_app.services.MatchActionsService;
import com.company.demo_app.services.MatchOverviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {MatchOverviewController.class, MatchActionsController.class, ConfigurationController.class})
@ActiveProfiles("local-dev")
public class ControllerTests {
    @Autowired
    MockMvc mvc;

    @Autowired
    MatchOverviewController matchOverviewController;

    @Autowired
    MatchActionsController matchActionsController;

    @Autowired
    ConfigurationController configurationController;

    @MockitoBean
    MatchOverviewService matchOverviewService;

    @MockitoBean
    MatchActionsService matchActionsService;

    @MockitoBean
    ConfigurationService configurationService;

    @Test
    void matchOverview() throws Exception {
        Method[] controllerMethods = matchOverviewController.getClass().getMethods();
        String basePath = "/matches/overview";
        List<String> eps = new ArrayList<>();
        for (Method method : controllerMethods) {
            if (Objects.nonNull(method.getAnnotation(GetMapping.class))) {
                eps.add(basePath + method.getAnnotation(GetMapping.class).value()[0]);
            }
        }
        for (String ep : eps) {
            if (ep.matches("(.*?)/\\{(.*?)}")) {
                mvc.perform(get(ep, 1)
                                .contentType(APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
            } else {
                mvc.perform(get(ep)
                                .contentType(APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
            }
        }
    }

    @Test
    void matchActions() throws Exception {
        Method[] controllerMethods = matchActionsController.getClass().getMethods();
        String basePath = "/matches/actions";
        String matchDtoBody = "{\"id\":null,\"description\":\"test\",\"matchDate\":\"2025-08-20\",\"matchTime\":\"21:32\",\"sport\":\"FOOTBALL\",\"matchOddsDto\":null}";
        String matchOddsDtoBody = "{\"id\":null,\"matchId\":1,\"specifier\":\"X\",\"odd\":1.85}";

        for (Method method : controllerMethods) {
            if (Objects.nonNull(method.getAnnotation(PutMapping.class))) {
                String ep = basePath + method.getAnnotation(PutMapping.class).value()[0];
                String content = ep.contains("Odds")
                        ? matchOddsDtoBody
                        : matchDtoBody;
                mvc.perform(put(ep)
                                .contentType(APPLICATION_JSON_VALUE)
                                .content(content))
                        .andExpect(status().isCreated());
            }
            if (Objects.nonNull(method.getAnnotation(PostMapping.class))) {
                String ep = basePath + method.getAnnotation(PostMapping.class).value()[0];
                String content = ep.contains("Odds")
                        ? matchOddsDtoBody
                        : matchDtoBody;
                mvc.perform(post(ep)
                                .contentType(APPLICATION_JSON_VALUE)
                                .content(content))
                        .andExpect(status().isOk());
            }
            if (Objects.nonNull(method.getAnnotation(DeleteMapping.class))) {
                String ep = basePath + method.getAnnotation(DeleteMapping.class).value()[0];
                mvc.perform(delete(ep, 1)
                                .contentType(APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
            }
        }
    }

    @Test
    void configuration() throws Exception {
        Method[] controllerMethods = configurationController.getClass().getMethods();
        String basePath = "/matches/configuration";
        String sportBody = "{\"sportName\":\"FOOTBALL\"}";
        String specifierBody = "{\"specifierName\":\"1\"}";

        for (Method method : controllerMethods) {
            if (Objects.nonNull(method.getAnnotation(GetMapping.class))) {
                String ep = basePath + method.getAnnotation(GetMapping.class).value()[0];
                mvc.perform(get(ep)
                                .contentType(APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
            }
            if (Objects.nonNull(method.getAnnotation(PutMapping.class))) {
                String ep = basePath + method.getAnnotation(PutMapping.class).value()[0];
                String content = ep.contains("Sport")
                        ? sportBody
                        : specifierBody;
                mvc.perform(put(ep)
                                .contentType(APPLICATION_JSON_VALUE)
                                .content(content))
                        .andExpect(status().isCreated());
            }
            if (Objects.nonNull(method.getAnnotation(DeleteMapping.class))) {
                String ep = basePath + method.getAnnotation(DeleteMapping.class).value()[0];
                String content = ep.contains("Sport")
                        ? sportBody
                        : specifierBody;
                mvc.perform(delete(ep)
                                .contentType(APPLICATION_JSON_VALUE)
                                .content(content))
                        .andExpect(status().isOk());
            }
        }
    }

}
