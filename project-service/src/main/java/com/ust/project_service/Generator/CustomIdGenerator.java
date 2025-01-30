package com.ust.project_service.Generator;

import java.io.Serializable;
import java.time.Year;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.ust.project_service.repository.ProjectRepository;


@Component // Register this class as a Spring bean
public class CustomIdGenerator implements IdentifierGenerator, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext context) {
        applicationContext = context;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        ProjectRepository projectRepository= applicationContext.getBean(ProjectRepository.class);
        int count = (int) projectRepository.count();
        int currentYear = Year.now().getValue();
        String yearSuffix = String.valueOf(currentYear).substring(2);
        String formattedCount = String.format("%04d", count + 1);
        return "PRO" + yearSuffix + formattedCount;
    }
}

