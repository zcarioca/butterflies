package com.zcarioca.butterflies

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
      [ lepidoptera : lepidoptera]
   }
}
