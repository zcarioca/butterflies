package com.zcarioca.butterflies

/**
 * The family order (super-family, family, and sub-family).
 * 
 * 
 * @author zcarioca
 */
enum FamilyOrder {
   SUPER_FAMILY('Super-Family'),
   FAMILY('Family'),
   SUB_FAMILY('Sub-Family')

   String name

   FamilyOrder(String name) {
      this.name = name
   }

   String toString() {
      return this.name
   }
}
