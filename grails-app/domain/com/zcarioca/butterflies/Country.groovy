package com.zcarioca.butterflies

class Country implements Comparable<Country> {

   String iso2
   String iso3
   String name

   void setIso2(String iso2) {
      this.iso2 = iso2?.toUpperCase()?.trim()
   }

   void setIso3(String iso3) {
      this.iso3 = iso3?.toUpperCase()?.trim()
   }

   String toString() {
      return "${name}"
   }

   int compareTo(Country country) {
      return name <=> country?.name
   }
   
   static hasMany = [ states : State ]

   static mapping = { sort name : 'asc' }

   static constraints = {
      iso2(size: 2..2, nullable: false, unique: true)
      iso3(size: 3..3, nullable: false, unique: true)
      name(nullable: true)
   }
}
