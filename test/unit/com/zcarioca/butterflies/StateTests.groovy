package com.zcarioca.butterflies

import grails.test.*

class StateTests extends GrailsUnitTestCase {
   
   void testCaseChange() {
      def state = new State(abbreviation: 'va', name: 'virginia')
      
      assertEquals 'VA', state.abbreviation
      assertEquals 'virginia', state.name
      
      state = new State(abbreviation: 'wv', name: 'WEST VIRGINIA')
      
      assertEquals 'WV', state.abbreviation
      assertEquals 'WEST VIRGINIA', state.name
   }
   
   void testTrim() {
      def state = new State(abbreviation: '   va     ', name: 'virginia')
      
      assertEquals 'VA', state.abbreviation
      assertEquals 'virginia', state.name
   }
   
   void testSort() {
      def states = [ new State(abbreviation: 'va'), 
         new State(abbreviation: 'al'), new State(abbreviation: 'ME')]
      
      def names = states.sort()*.abbreviation
      
      assertEquals(['AL', 'ME', 'VA'], names)
   }
}
