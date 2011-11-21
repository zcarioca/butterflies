package com.zcarioca.butterflies

class Address {

   String name
   String address
   String city
   State state
   String postalCode
   double latitude
   double longitude
   
   String toString() {
      def str = "${name}, ${address}, ${city}, ${state.abbreviation} ${postalCode}, ${state.country.iso3}"
      str = str.replace("null", "").trim().replaceAll("\\s+", " ")
      while (str =~ /^, /) {
         str = str.substring(2)
      }
      return str.replaceAll('\\s+,', ',')
   }
   
   static constraints = {
      address(nullable:true)
      city(nullable: true)
      state(nullable: false)
      postalCode(nullable: true, matches: '[0-9]{5}(\\-[0-9]{4})?')
      latitude(nullable: false)
      longitude(nullable: false)
   }
}
