package com.zcarioca.butterflies

import org.apache.commons.lang.WordUtils;

class Lepidoptera {

   String scientificName
   String commonName
   String abbreviatedName
   String description

   User addedBy
   User modifiedBy
   Date createdDate = new Date()
   Date lastModified = new Date()

   void setScientificName(String scientificName) {
      this.scientificName = WordUtils.capitalize(scientificName?.trim())
   }
   
   void setCommonName(String commonName) {
      this.commonName = WordUtils.capitalize(commonName?.trim())
   }
   
   String toString() {
      StringBuilder sb = new StringBuilder(scientificName)
      if (commonName) {
         sb.append(" (${commonName})")
      }
      return sb.toString()
   }
   
   static hasMany = [ states : State ]

   static belongsTo = [ family : Family ]
   
   static mapping = {
      sort scientificName:'asc'
      description type: 'text'
      states lazy:false
   }

   static constraints = {
      scientificName(maxSize: 255, nullable: false, unique: true)
      commonName(maxSize: 255, nullable: true)
      abbreviatedName(maxSize: 255, nullable: true)
      description(maxSize: 2000, nullable: true)
      addedBy(nullable: false)
      modifiedBy(nullable: false)
      createdDate(nullable: false)
      lastModified(nullable: false)
   }
}
