package com.zcarioca.butterflies

import grails.test.*

class FamilyIntegrationTests extends GroovyTestCase {
   
   void testSaveButterfly() {
      def family = new Family(scientificName: 'awesome butterfly', familyType: FamilyType.BUTTERFLY)
      assertNotNull family.save()
      assertNotNull family.id
      
      def found = Family.get(family.id)
      assertNotNull found
      assertEquals FamilyType.BUTTERFLY, found.familyType
      assertEquals FamilyOrder.FAMILY, found.familyOrder
      assertEquals 'Awesome Butterfly', found.scientificName
   }
   
   void testSaveMoth() {
      def family = new Family(scientificName: 'awesome moth', familyType: FamilyType.MOTH)
      assertNotNull family.save()
      assertNotNull family.id
      
      def found = Family.get(family.id)
      assertNotNull found
      assertEquals FamilyType.MOTH, found.familyType
      assertEquals FamilyOrder.FAMILY, found.familyOrder
      assertEquals 'Awesome Moth', found.scientificName
   }
   
   void testSubFamilies() {
      def family = new Family(scientificName: 'awesome moth', familyType: FamilyType.MOTH).save()
      def subFamily1 = new Family(scientificName: 'sub moth 1', familyType: FamilyType.MOTH, familyOrder: FamilyOrder.SUB_FAMILY)
      def subFamily2 = new Family(scientificName: 'sub moth 2', familyType: FamilyType.MOTH, familyOrder: FamilyOrder.SUB_FAMILY)
      
      family.addToSubFamilies(subFamily1)
      family.addToSubFamilies(subFamily2)
      
      def found = Family.get(family.id)
      assertEquals 2, found.subFamilies.size()
      assertEquals(['Sub Moth 1', 'Sub Moth 2'], found.subFamilies*.scientificName.sort())
   }
   
   void testRunUpChain() {
      def subFamily = Family.findByScientificName('Danaidae')
      assertNotNull subFamily
      
      def family = subFamily.superFamily
      assertNotNull family
      assertEquals 'Nymphalidae', family.scientificName
      
      def superFamily = family.superFamily
      assertNotNull superFamily
      assertEquals 'Papilionoidea', superFamily.scientificName
   }
   
   void testRunDownChain() {
      def superFamily = Family.findByScientificName('Papilionoidea')
      
      println "Start with ${superFamily}"
      assertEquals 5, superFamily.subFamilies.size()
      
      for (def family in superFamily.subFamilies) {
         if (family.scientificName == 'Nymphalidae') {
            println "Checking ${family}"
            
            assertEquals 3, family.subFamilies.size()
            for (def subFamily in family.subFamilies) {
               println "Found sub family ${subFamily}"
            }
         }
      }
   }
}
