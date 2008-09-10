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
 * Gant script that runs Selenium in the specified browser. 
 * Specify the path to your browser as a command line parameter i.e.
 *      grails run-selenium /usr/bin/firefox
 * or, if the executable is on the path you would just need
 *      grails run-selenium firefox
 *
 * In your application.properties, you can specify:
 *      selenium.auto=true
 *      selenium.close=true
 *      selenium.multiWindow=true
 *      selenium.highlight=true
 *      selenium.resultsUrl=/your/url/here (defaults to ${grailsAppName}/selenium/postResults)
 *      selenium.runInterval=1000
 *      selenium.baseUrl=
 * 
 * See http://selenium.openqa.org/installing.html (section titled Continuous Integration) for more 
 * information on selenium and continuous integration.
 *
 * @author Paul Rule
 *
 * @since 0.4
 */

Ant.property(environment:"env")                             
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"    

includeTargets << new File ( "${grailsHome}/scripts/Init.groovy" )

def browser = args?.toString()
if(!browser) {
    Ant.input(addProperty:"artifact.name", message:"Browser path not specified. Please enter:")
    browser = Ant.antProject.properties."artifact.name"
}

def auto = Boolean.valueOf(Ant.antProject.properties.'selenium.auto')
def close = Boolean.valueOf(Ant.antProject.properties.'selenium.close')
def multiWindow = Boolean.valueOf(Ant.antProject.properties.'selenium.multiWindow')
def highlight = Boolean.valueOf(Ant.antProject.properties.'selenium.highlight')

def saveXmlTo = Ant.antProject.properties.'selenium.saveXmlTo' ? Ant.antProject.properties.'selenium.saveXmlTo' : ''
def runInterval = Ant.antProject.properties.'selenium.runInterval' ? "&runInterval=${Integer.valueOf(Ant.antProject.properties.'selenium.runInterval')}" : ''
def resultsUrl = Ant.antProject.properties.'selenium.resultsUrl' ? "&resultsUrl=${Ant.antProject.properties.'selenium.resultsUrl'}" : "&resultsUrl=${grailsAppName}/selenium/postResults%3Ffile%3D${saveXmlTo}"
def baseUrl = Ant.antProject.properties.'selenium.baseUrl' ? "&baseUrl=${Ant.antProject.properties.'selenium.baseUrl'}" : ''

def params = "&auto=${auto}&close=${close}&multiWindow=${multiWindow}&highlight=${highlight}${runInterval}${resultsUrl}${baseUrl}"


Ant.exec(executable:browser) {
    arg(value:"http://localhost:8080/${grailsAppName}/selenium/core/TestRunner.html?test=..%2F..%2Fselenium/suite${params}")
}