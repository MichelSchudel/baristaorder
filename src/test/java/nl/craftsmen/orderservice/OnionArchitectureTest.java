package nl.craftsmen.orderservice;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

class OnionArchitectureTest {

    @Test
    void test_onion_architecture() {
        JavaClasses classes = new ClassFileImporter()
                .importPackages(OrderApplication.class.getPackageName()
                );
        onionArchitecture()
                .domainModels("..core..")
                .domainServices("..core..")
                .applicationServices("..core..")
                .adapter("presentation", "..presentation..")
                .adapter("persistence", "..persistence..")
                .adapter("gateway", "..gateway..")
                .check(classes);
    }
}
