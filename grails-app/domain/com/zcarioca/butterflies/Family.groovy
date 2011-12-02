package com.zcarioca.butterflies

import org.apache.commons.lang.WordUtils;

class Family implements Comparable<Family> {
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
      StringBuilder sb = new StringBuilder()
      sb.append(this.scientificName)
      if (this.commonName) {
         sb.append(" (${commonName})")
      }
      return sb.toString()
   }
   
   int compareTo(Family family) {
      scientificName.toLowerCase() <=> family.scientificName.toLowerCase()
	}

   static constraints = {
      scientificName(blank: false, nullable: false, unique: true)
      commonName(nullable: true)
      description(maxSize: 2000, nullable: true)
      familyType(nullable: false)
      familyOrder(nullable: false)
   }
   
   static mapping = {
     sort scientificName:'asc'
     description type: 'text'
     subFamilies lazy:true
   }
}
