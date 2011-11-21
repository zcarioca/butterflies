package com.zcarioca.butterflies

import grails.test.*

class AddressTests extends GrailsUnitTestCase {

   void testFirstAddress() {
      Address addr = new Address(state: new State(abbreviation: 'va', name: 'Virginia', country: new Country(iso3:'USA')))
      
      assertEquals "VA, USA", addr.toString()
   }

   void testSecondAddress() {
      Address addr = new Address(city: 'Abington', state: new State(abbreviation: 'va', name: 'virginia', country: new Country(iso3:'USA')))
      
      assertEquals "Abington, VA, USA", addr.toString()
   }

   void testThirdAddress() {
      Address addr = new Address(address: '1234 Main St', postalCode: '22123', city: 'Fairfax', state: new State(abbreviation: 'va', name: 'virginia', country: new Country(iso3:'USA')))
      
      assertEquals "1234 Main St, Fairfax, VA 22123, USA", addr.toString()
   }
}
