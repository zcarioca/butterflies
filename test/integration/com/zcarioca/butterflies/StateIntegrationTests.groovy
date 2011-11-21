package com.zcarioca.butterflies

import grails.test.*

class StateIntegrationTests extends GroovyTestCase {
   
   void testBootstrap() {
      def states = State.list([sort: 'name', order: 'desc'])
      
      assertEquals 52, State.count()
      
      states.each {
         println it.toString()
      }
   }
}
