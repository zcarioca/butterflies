package com.zcarioca.butterflies

import org.apache.commons.lang.WordUtils;
import org.hibernate.annotations.FetchMode;

class LepidopteraController {

   def index = { redirect(action: 'list') }

   def list = {
      [ lepidoptera : Lepidoptera.list([sort: 'scientificName', order: 'asc']) ]
   }

   def show = {
      def lepidoptera = Lepidoptera.get(params.id)
      [ species : lepidoptera ]
   }
   
   def search = {
      def queryType = params.query ?: "and"
      def props = Lepidoptera.metaClass.properties*.name
      def lepidoptera = Lepidoptera.withCriteria{
         createAlias('states', 's')
         "${queryType}" {
            params.each { field, value ->
               if (field == 'state' && value) {
                  eq('s.abbreviation', value)
               } else if (props.grep(field) && value) {
                  ilike(field, "%${value}%")
               }
            }
         }
         order('scientificName', 'asc')
      }
      
      def terms = [:]
      
      params.each { field, value ->
         if (props.grep(field) || field == 'state') {
            terms[WordUtils.capitalize(field)] = value
         }
      }
      
      render(view: 'listResults', model: [ terms: terms, lepidoptera : lepidoptera ])
   }

   def simpleNameSearch = {
      def lepidoptera = null
      if (params.term) {
         lepidoptera = Lepidoptera.findAll("from Lepidoptera l where lower(l.scientificName) " +
            "like lower(:term) or lower(l.commonName) like lower(:term) or l.family in " +
            "(from Family f where lower(f.scientificName) like lower(:term) or lower(f.commonName) " + 
            "like lower(:term)) order by l.scientificName asc",
            [term : "%${params.term}%"])
      } else {
         lepidoptera = Lepidoptera.list(sort: 'scientificName', order: 'asc')
      }
      render(view: 'listResults', model: [ lepidoptera : lepidoptera ])
   }
}
