package com.zcarioca.butterflies

import org.apache.commons.io.IOUtils

class GeocoderService {

   static transactional = false
   String encoding = "UTF-8"
   String googleGeocoderUrl = 'http://maps.googleapis.com/maps/api/geocode/xml?sensor=false'

   def getLatLngFromAddressString(address) {
      def xml = getXmlForAddress(address)

      def result = xml.result[0]

      [  latitude: Double.parseDouble(result?.geometry?.location?.lat?.toString()),
         longitude: Double.parseDouble(result?.geometry?.location?.lng?.toString()) ]
   }

   def getAddressesFromString(address) {
      def xml =  getXmlForAddress(address)
      
      return getAddressesFromXml(xml)
   }

   def getAddressesFromXml(xml) {
      def addresses = []

      for (int i in 0..xml.result.size()-1) {
         def result = xml.result[i]

         def addrMap = ['name':'']
         def state = null
         def country = null
         
         for (int j in 0..result.address_component.size()-1) {
            def addComp = result.address_component[j]

            def type = getType(addComp)
            if (type != 'name') {
               addrMap[type] = addComp.short_name.toString()
               
               if (type == 'administrative_area_level_1') {
                  state = State.findByAbbreviation(addComp.short_name.toString())
                  
                  if (!state) {
                     state = new State(abbreviation: addComp.short_name.toString(), name: addComp.long_name.toString())
                  }
               }
               if (type == 'country') {
                  def iso2 = addComp.short_name.toString()
                  country = Country.findByIso2(iso2)
               }
            } else {
               addrMap[type] += ", ${addComp.short_name}"
            }
         }
         def geometry = [  latitude: Double.parseDouble(result.geometry?.location?.lat?.toString()),
            longitude: Double.parseDouble(result.geometry?.location?.lng?.toString()) ]

         if (!state.id) {
            country.addToStates(state)
         }
         def street = addrMap['street_number']
         def route = addrMap['route']
         addresses << new Address(name: addrMap['name'] != '' ? addrMap['name'].substring(2) : null,
         address: "${street} ${route}".replace("null", "").trim(),
         city: addrMap['locality'],
         state: state,
         postalCode: addrMap['postal_code'],
         latitude: geometry.latitude,
         longitude: geometry.longitude)
      }
      return addresses
   }

   def getType(addrComp) {
      def type = ''
      for (int i in 0..addrComp.type.size()-1) {
         def theType = addrComp.type[i].toString()
         if (theType) {
            if (theType == 'establishment') {
               return 'name'
            } else if (theType != 'political') {
               type = theType
            }
         }
      }
      return type
   }

   def getXmlForAddress(address) {
      def encodedString = URLEncoder.encode(address, encoding)
      def url = new URL("${googleGeocoderUrl}&address=${encodedString}")

      def stream = url.openStream()
      def xml = new XmlSlurper().parse(stream)

      IOUtils.closeQuietly(stream)

      return xml
   }
   
   def findLocale(iso2) {
      return Locale.iSO2Table.find { it.country == iso2 }
   }
}
