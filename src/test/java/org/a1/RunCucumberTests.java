package org.a1;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features",
        glue="org.a1",
        plugin = {"pretty"})

public class RunCucumberTests {}

