import org.apache.commons.lang.WordUtils

import java.beans.Statement

import org.apache.commons.lang.WordUtils
import org.codehaus.groovy.grails.commons.ApplicationHolder

import com.zcarioca.butterflies.*

class BootStrap {

   def init = { servletContext ->
      createAdminIfNotExists()
      createCountries()
      createStates()
      createFamilies()
   }

   def destroy = {
   }

   def createAdminIfNotExists = {
      if (!User.findByUsername('admin')) {
         println "Could not find admin, creating user"
         new User(username: 'admin', password: '1234', profile: new Profile(email: 'admin@website.com')).save()
      } else {
         println "Admin user already exists"
      }
   }

   def createCountries = {
      if (Country.count() == 0) {
         iterateOverFile("countries.csv") { line ->
            def fields = line.split(",")
            def country = new Country(name: fields[0], iso2: fields[1], iso3: fields[2])
            println "Inserting new country: ${country}"
            country.save()
         }
      }
   }

   def createStates = {
      def usa = Country.findByIso3("USA")

      if (!usa) {
         usa = new Country(iso2: 'US', iso3: 'USA', name: 'United States')
         println "Inserting country ${usa}"
         usa.save()
      }
      if (State.count() == 0) {
         iterateOverFile("states.csv") { line ->
            println "Inserting new state: ${line}"
            def fields = line.split(",")

            usa.addToStates(new State(country: usa, abbreviation: fields[0], name: fields[1]))
         }
      } else {
         println "States already inserted"
      }
   }

   def createFamilies = {
      if (Family.count() == 0) {
         iterateOverFile("families.csv") { line->
            def fields = line.split(';')
            
            def scientificName = WordUtils.capitalize(fields[0])
            if (!Family.findByScientificName(scientificName)) {
               def family = new Family(scientificName: scientificName, commonName: fields[1], 
                  description: fields[3], familyType: FamilyType.BUTTERFLY, familyOrder: FamilyOrder.valueOf(fields[4]))
               family.save()
               
               if (fields[2]) {
                  def parentName = WordUtils.capitalize(fields[2]).trim()
                  def parent = Family.findByScientificName(parentName)
                  parent.addToSubFamilies(family)
                  parent.save()
                  println "Added Family ${family} to ${parent}"
               } else {
                  family.save()
                  println "Added Family ${family}"
               }
            }
         }
      }
   }

   void iterateOverFile(String filename, Closure closure) {
      def fileResource = "classpath:resources/${filename}"
      def text = ApplicationHolder.application.parentContext.getResource(fileResource).inputStream
      text.eachLine { closure it }
   }
}
