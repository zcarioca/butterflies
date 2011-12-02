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
      createButterflies()
   }

   def destroy = {
   }

   def createAdminIfNotExists = {
      if (!User.findByUsername('admin')) {
         log.info "Could not find admin, creating user"
         new User(username: 'admin', password: '1234', profile: new Profile(email: 'admin@website.com')).save()
      } else {
         log.info "Admin user already exists"
      }
   }

   def createCountries = {
      if (Country.count() == 0) {
         iterateOverFile("countries.csv") { fields ->
            def country = new Country(name: fields[0], iso2: fields[1], iso3: fields[2])
            log.info "Inserting new country: ${country}"
            country.save()
         }
      }
   }

   def createStates = {
      def usa = Country.findByIso3("USA")

      if (!usa) {
         usa = new Country(iso2: 'US', iso3: 'USA', name: 'United States')
         log.info "Inserting country ${usa}"
         usa.save()
      }
      if (State.count() == 0) {
         iterateOverFile("states.csv") { fields ->
            log.info "Inserting new state: ${fields}"

            usa.addToStates(new State(country: usa, abbreviation: fields[0], name: fields[1]).save())
         }
         usa.save()
      } else {
         log.info "States already inserted"
      }
   }

   def createFamilies = {
      if (Family.count() == 0) {
         iterateOverFile("families.csv") { fields ->
            def scientificName = WordUtils.capitalize(fields[0])
            if (!Family.findByScientificName(scientificName)) {
               def family = new Family(scientificName: scientificName, commonName: fields[1],
                     description: fields[3].replace("\\n", "\n"), familyType: FamilyType.BUTTERFLY, familyOrder: FamilyOrder.valueOf(fields[4]))
               family.save()

               if (fields[2]) {
                  def parentName = WordUtils.capitalize(fields[2]).trim()
                  def parent = Family.findByScientificName(parentName)
                  parent.addToSubFamilies(family)
                  parent.save()
                  log.info "Added Family ${family} to ${parent}"
               } else {
                  family.save()
                  log.info "Added Family ${family}"
               }
            }
         }
      }
   }
   
   def createButterflies = {
      if (Lepidoptera.count() == 0) {
         def admin = User.findByUsername('admin')
         iterateOverFile("butterflies.csv") { fields ->
            if (fields[0] != 'SUPER FAMILY') {
               def familyString = WordUtils.capitalize(fields[2] ?: fields[1]).trim()
               def family = Family.findByScientificName(familyString)
               
               if (family) {
                  def butterfly = new Lepidoptera(scientificName: fields[4], commonName: fields[5], description: fields[6], addedBy: admin, modifiedBy: admin)
                  family.addToSpecies(butterfly)
                  family.save()
                  
                  fields[7].split(',').each {
                     def code = it.trim()
                     def state = State.findByAbbreviation(code)
                     if (state) {
                        butterfly.addToStates(state)
                        log.debug "Added ${state} to ${butterfly}"
                     } else {
                        log.warn "Could not find state ${code}"
                     }
                  }
                  butterfly.save()
                  log.info "Saved ${butterfly}"
               }
            }
         }
      }
   }

   void iterateOverFile(String filename, Closure closure) {

      def fileResource = "classpath:resources/${filename}"
      def text = ApplicationHolder.application.parentContext.getResource(fileResource).inputStream
      text.toCsvReader(['charset':'UTF-8']).eachLine closure
   }
}
