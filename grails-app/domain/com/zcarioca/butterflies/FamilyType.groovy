package com.zcarioca.butterflies

/**
 * The family type.
 * 
 * 
 * @author zcarioca
 */
enum FamilyType {
   BUTTERFLY('Butterfly'),
   MOTH('Moth')

   String name

   FamilyType(String name) {
      this.name = name
   }

   String toString() {
      return this.name
   }
}
