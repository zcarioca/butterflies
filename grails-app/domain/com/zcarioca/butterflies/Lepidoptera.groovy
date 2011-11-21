package com.zcarioca.butterflies

class Lepidoptera {

   String scientificName
   String commonName
   String abbreviatedName
   String description

   User addedBy
   User modifiedBy
   Date createdDate = new Date()
   Date lastModified = new Date()

   static hasMany = [ states : State ]

   static belongsTo = [ family : Family ]

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
