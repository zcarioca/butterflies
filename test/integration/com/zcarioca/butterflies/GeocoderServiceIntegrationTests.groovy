package com.zcarioca.butterflies

import grails.test.*

class GeocoderServiceIntegrationTests extends GroovyTestCase {
   def geocoderService

   void testFirstAddress() {
      def addr = geocoderService.getLatLngFromAddressString('VA')

      assertEquals(37.4315734, addr.latitude)
      assertEquals(-78.6568942, addr.longitude)
   }

   void testSecondAddress() {
      def addr = geocoderService.getLatLngFromAddressString('Philadelphia, PA')

      assertEquals(39.9523350, addr.latitude)
      assertEquals(-75.1637890, addr.longitude)
   }

   void testThirdAddress() {
      def addr = geocoderService.getLatLngFromAddressString('1600 Penn Ave Washington, DC')

      assertEquals(38.8976777, addr.latitude)
      assertEquals(-77.0365170, addr.longitude)
   }
   
   void testFirstFullAddress() {
      def addr = geocoderService.getAddressesFromString('VA')
      
      assertEquals 1, addr.size()
      assertEquals "VA, USA", addr[0].toString()
   }
   
   void testSecondFullAddress() {
      def addr = geocoderService.getAddressesFromString('Tyler Arboretum, PA')
      
      assertEquals 1, addr.size()
      assertEquals "Tyler Arboretum, 515 Painter Rd, Media, PA 19063-4424, USA", addr[0].toString()
   }
   
   void testThirdFullAddress() {
      def addr = geocoderService.getAddressesFromString('Washington Monument')
      
      assertEquals 1, addr.size()
      assertEquals "Washington Monument, The National Mall, 2 NW 15th St, Washington, DC 20024, USA", addr[0].toString()
   }
   
   void testFourthFullAddress() {
      def addr = geocoderService.getAddressesFromString('maracana, rj')
      
      assertEquals 1, addr.size()
      assertEquals "Rio de Janeiro, RJ, BRA", addr[0].toString()
   }
}
