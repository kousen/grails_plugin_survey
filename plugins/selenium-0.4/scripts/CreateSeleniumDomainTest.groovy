/*
 * Copyright 2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Gant script that generates a new selenium test for the given domain class.
 * Supply the name of the domain class you want to test, followed by and extension
 * that identifies the sytax to use. The extension of this must be one
 * of html, psv, gsp. 
 * 
 * Example use:
 *  grails create-selenium-domain-test book.gsp
 * creates
 *  web-app/selenium/tests/book.gsp
 * 
 * The generated test exercises the default grails scaffolding for CRUD functionality.
 *
 * @author Paul Rule
 *
 * @since 0.4
 */
import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU


Ant.property(environment:"env")                             
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"    
pluginHome = new File("./plugins").listFiles().find { it.name.startsWith("selenium")}
def types = ['html','gsp','psv']
def defaultType = 'gsp'

includeTargets << new File ( "${grailsHome}/scripts/Init.groovy" )

target ('default': "Creates a skeleton of a Selenium (functional test) for a given domain name") {
    depends(checkVersion)
    def domain = args?.toString()?.toLowerCase()
    def type = defaultType

    if(!domain) {
        Ant.input(addProperty:"artifact.name", message:"Domain class not specified. Please enter:")
	domain = Ant.antProject.properties."artifact.name"
    }

    def a = domain.lastIndexOf('.')
    if(a>-1) {
        type = domain.substring(a+1)
        domain = domain.substring(0, a)
    }

    def className       = GCU.getClassNameRepresentation(domain)
    def propertyName    = GCU.getPropertyNameRepresentation(domain)
    

    if(!types.contains(type)) {
        println "Type invalid or not specified (must be one of ${types}). Defaulting to ${defaultType}."
        type = defaultType
    }
	      
    def fileName = "web-app/selenium/tests/${domain}.${type}"
		
    Ant.sequential {  
        copy(file:"${pluginHome}/src/templates/artifacts/SeleniumDomainTest.${type}", tofile:fileName) 
	replace(file:fileName, token:"XclassNameX", value:"${className}" )
	replace(file:fileName, token:"XpropertyNameX", value:"${propertyName}" )
        replace(file:fileName, token:"XcontextPathX", value:"${grailsAppName}" )
    }	                                                                            
    println "Selenium Test generated at $fileName"  
}