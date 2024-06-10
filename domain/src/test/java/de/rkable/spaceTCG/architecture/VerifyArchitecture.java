package de.rkable.spaceTCG.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.DependencyRules.*;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.*;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.*;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.*;

import java.net.URL;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = { "de.rkable.spaceTCG.." })
public class VerifyArchitecture {

	//private static final URL myDiagram = VerifyArchitecture.class.getResource("components.puml");

//	@ArchTest
//	static final ArchRule noCycles = slices().matching("de.rkable.spaceTCG.(*)..").should().beFreeOfCycles();
//
//	@ArchTest
//	static final ArchRule noAccessToUpperPackage = NO_CLASSES_SHOULD_DEPEND_UPPER_PACKAGES;

//	@ArchTest
//	static final ArchRule classesMatchComponentsDiagram = classes().should(
//			adhereToPlantUmlDiagram(myDiagram, consideringOnlyDependenciesInAnyPackage("de.rkable.spaceTCG..")));

}
