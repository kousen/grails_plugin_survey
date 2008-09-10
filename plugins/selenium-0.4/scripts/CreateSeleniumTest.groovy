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
 * Gant script that generates a new empty selenium test.
 * Supply the path of the test you want to create, relative to
 * 'web-app/selenium/tests'. The extension of this must be one
 * of html, psv, gsp. The test file will be created using the syntax
 * based on the file extension.
 * 
 * Example use:
 *  grails create-selenium-test registration/errors/InvalidUsername.gsp
 * creates
 *  web-app/selenium/tests/registration/errors/InvalidUsername.gsp
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

target ('default': "Creates an empty Selenium test") {
    depends(checkVersion)
    def dest = args?.toString()?.toLowerCase()
    def type = defaultType
    
    if(!dest) {
        Ant.input(addProperty:"artifact.name", message:"Test path not specified. Please enter:")
	dest = Ant.antProject.properties."artifact.name"
    }

    def a = dest.lastIndexOf('.')
    if(a>-1) {
        type = dest.substring(a+1)
    }

    if(!types.contains(type)) {
        println "Type invalid or not specified (must be one of ${types}). Defaulting to ${defaultType}."
        type = defaultType
    }
	      
    def fileName        = "web-app/selenium/tests/${dest}"
		
    Ant.sequential {  
        copy(file:"${pluginHome}/src/templates/artifacts/SeleniumTest.${type}", tofile:fileName) 
        replace(file:fileName, token:"XcontextPathX", value:"${grailsAppName}" )
    }	                                                                            
    println "Selenium Test generated at $fileName"  
}