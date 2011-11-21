package com.zcarioca.butterflies

import org.apache.commons.lang.WordUtils;

class Family {
   String scientificName
   String commonName
   String description
   FamilyType familyType
   FamilyOrder familyOrder = FamilyOrder.FAMILY
   Date createdDate = new Date()
   
   static hasMany = [ subFamilies : Family, species: Lepidoptera ]
   
   static belongsTo = [ superFamily: Family ]
   
   void setScientificName(String scientificName) {
      this.scientificName = WordUtils.capitalize(scientificName?.toLowerCase())
   }
   
   void setCommonName(String commonName) {
      this.commonName = WordUtils.capitalize(commonName?.toLowerCase())
   }
   
   String toString() {
      return this.scientificName
   }

   static constraints = {
      scientificName(blank: false, nullable: false, unique: true)
      commonName(nullable: true)
      description(maxSize: 2000, nullable: true)
      familyType(nullable: false)
      familyOrder(nullable: false)
   }
}
