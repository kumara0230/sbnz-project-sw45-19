package com.ftn.sbnz.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.ftn.sbnz.model.Hero;
import com.ftn.sbnz.service.HeroFactory;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolConfig {
	
	private KieServices kieServices = KieServices.Factory.get();

	private KieFileSystem getKieFileSystem() throws IOException {
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		kieFileSystem.write(ResourceFactory.newClassPathResource("test.drl"));
		return kieFileSystem;

	}

	@Bean
	public KieContainer getKieContainer() throws IOException {
		System.out.println("Container created...");
		getKieRepository();
		KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
		kb.buildAll();
		KieModule kieModule = kb.getKieModule();
		KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
		return kContainer;

	}

	private void getKieRepository() {
		final KieRepository kieRepository = kieServices.getRepository();
		kieRepository.addKieModule(new KieModule() {
			public ReleaseId getReleaseId() {
				return kieRepository.getDefaultReleaseId();
			}
		});
	}

	@Bean
	public KieSession getKieSession() throws IOException {
		System.out.println("session created...");
		KieSession kieSession = getKieContainer().newKieSession();
		
		List<Hero> allHeroes = HeroFactory.getAllHeroes();
        kieSession.setGlobal("allHeroes", allHeroes);
        
		List<Hero> recommendedHeroes = new ArrayList<>();
		kieSession.setGlobal("recommendedHeroes", recommendedHeroes);
        
        BooleanWrapper flag = new BooleanWrapper();
        kieSession.setGlobal("flag", flag);
        
        BooleanWrapper ableToSort = new BooleanWrapper();
        kieSession.setGlobal("ableToSort", ableToSort);
        
		return kieSession;

	} 

}
