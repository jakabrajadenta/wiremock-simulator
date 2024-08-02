package com.braja.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;

public class MainTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(3000);

    @Before
    public void configureStub(){


    }

}
