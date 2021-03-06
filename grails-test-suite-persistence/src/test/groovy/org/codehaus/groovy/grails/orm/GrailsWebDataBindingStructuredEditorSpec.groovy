package org.codehaus.groovy.grails.orm

import org.codehaus.groovy.grails.web.binding.GrailsWebDataBinder
import org.grails.databinding.DataBindingSource
import org.grails.databinding.SimpleMapDataBindingSource
import org.grails.databinding.converters.AbstractStructuredBindingEditor

import spock.lang.Specification

class GrailsWebDataBindingStructuredEditorSpec extends Specification {
    
    void 'Test structured editor'() {
        given: 'A binder with a structured editor registered'
        def binder = new GrailsWebDataBinder()

        // in a Grails app you wouldn't do this... if the editor is a bean
        // in the Spring application context it will be auto-discovered
        binder.setStructuredBindingEditors(new StucturedEmployeeEditor())

        def company = new Company()
        
        when: 'binding happens'
        binder.bind company, [president: 'struct', president_firstName: 'Tim', president_lastName: 'Cook',
                              vicePresident: 'struct', vicePresident_firstName: 'Eddy', vicePresident_lastName: 'Cue'] as SimpleMapDataBindingSource
                          
        then: 'the expected values were bound'
        'Tim' == company.president.firstName
        'Cook' == company.president.lastName
        'Eddy' == company.vicePresident.firstName
        'Cue' == company.vicePresident.lastName                    
    }
}

class Company {
    Employee president
    Employee vicePresident
}

class Employee {
    String firstName
    String lastName
}

class StucturedEmployeeEditor extends AbstractStructuredBindingEditor<Employee> {

    @Override
    public Employee getPropertyValue(Map values) {
        def employee = new Employee()
        employee.firstName = values.firstName
        employee.lastName = values.lastName
        employee
    }
}

