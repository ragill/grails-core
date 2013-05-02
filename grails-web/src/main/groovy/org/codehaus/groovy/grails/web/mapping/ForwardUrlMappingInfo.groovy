/*
 * Copyright 2004-2005 the original author or authors.
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
package org.codehaus.groovy.grails.web.mapping

import groovy.transform.CompileStatic

/**
 * A customizable UrlMappingInfo instance used for forwards and includes.
 *
 * @author Graeme Rocher
 * @since 1.1
 */
@CompileStatic
class ForwardUrlMappingInfo extends AbstractUrlMappingInfo {

    String controllerName
    String actionName
    String pluginName
    String viewName
    String URI
    String id
    Map parameters = new HashMap()

    void setController(String controller) { controllerName = controller }
    void setAction(String action) { actionName = action }
    void setPluginName(String plugin) { pluginName = plugin }
    void setView(String view) { viewName = view }
    void setParams(Map params) {
        if (params) {
            parameters = params
        }
    }

    Map getParameters() {
        if (id) {
            parameters.id = id
        }
        return parameters
    }

    boolean isParsingRequest() {
        return false
    }
}
