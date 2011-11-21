package com.zcarioca.butterflies

import com.sun.org.apache.xalan.internal.xsltc.compiler.Sort;

class State implements Comparable<State> {

   String abbreviation
   String name
   
   void setAbbreviation(String abbreviation) {
      this.abbreviation = abbreviation?.toUpperCase()?.trim()
   }
   
   String toString() {
      return "${abbreviation}"
   }
   
   int compareTo(State state) {
		return abbreviation <=> state?.abbreviation
	}
   
   static belongsTo = [ country : Country ]
   
   static mapping = {
      sort abbreviation : 'asc'
   }

   static constraints = {
      abbreviation(size:2..3, nullable: false, unique: true)
      name(nullable: true)
   }
}
