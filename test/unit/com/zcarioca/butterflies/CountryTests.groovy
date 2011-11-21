package com.zcarioca.butterflies

import grails.test.*

class CountryTests extends GrailsUnitTestCase {
    
   
   void testSort() {
      def countries = [ new Country(name: 'United States'), 
         new Country(name: 'Brazil'), new Country(name: 'Chile')]
      
      def names = countries.sort()*.name
      
      assertEquals(['Brazil', 'Chile', 'United States'], names)
   }
}
